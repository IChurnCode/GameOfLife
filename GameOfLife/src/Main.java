import java.util.*;

enum CellState
{
    ALIVE, DEAD
}

//interface Transformation
//{
//    public Cell transform(Cell cell);
//}
//
//class AliveToDead implements Transformation
//{
//    private boolean meetsCriteria()
//    {
//        return true;
//    }
//    public Cell transform(Cell cell)
//    {
//        if(meetsCriteria(cell))
//
//        return cell;
//    }
//}
abstract class State
{
    public abstract boolean isAlive();
    //have list of transformations for a state and go through all transformations
    public abstract State transform(Integer numberOfLiveNeighbours);
    public CellState state;
}

class Dead extends State
{
    public Dead()
    {
        state = CellState.DEAD;
    }
    @Override
    public String toString()
    {
        return "d";
    }

    public void comeAlive()
    {
        state = CellState.ALIVE;
    }

    public State transform(Integer numberOfActiveNeighbours)
    {
        if(numberOfActiveNeighbours == 3)
        {
            return new Alive();
        }
        return this;
    }

    public boolean isAlive()
    {
        return false;
    }
}

class Alive extends State
{
    public Alive()
    {
        state = CellState.ALIVE;
    }
    @Override
    public String toString()
    {
        return "a";
    }
    public void die()
    {
        state = CellState.DEAD;
    }

    public State transform(Integer numberOfActiveNeighbours)
    {
        if(numberOfActiveNeighbours < 2 || numberOfActiveNeighbours > 3)
        {
            return new Dead();
        }
        return this;
    }

    public boolean isAlive()
    {
        return true;
    }
}


class Dimension
{
    Integer row;
    Integer column;

    public Dimension(Integer row, Integer column)
    {
        this.row = row;
        this.column = column;
    }

    public Integer row()
    {
        return row;
    }

    public Integer column()
    {
        return column;
    }
}
class Cell
{
    //Dimension dimension;
    Integer row;
    Integer column;
    State state;
    ArrayList<Cell> neighbours = new ArrayList<Cell>();

//    public void die()
//    {
//
//    }

    public void addNeighbour(Cell neighbour)
    {
        neighbours.add(neighbour);
    }

    public Integer Row()
    {
        return row;
    }

    public Integer Column()
    {
        return column;
    }
    public boolean isAlive()
    {
        return state.isAlive();
    }
    public Integer numberOfLiveNeighbours()
    {
        Integer count = 0;
        for(Cell c:neighbours)
        {
            if(c.isAlive())
                count++;
        }
        return count;
    }

    public Cell(Integer row, Integer column, State state)
    {
        this.row =  row;
        this.column = column;
        this.state = state;

    }

    public Cell playGame()
    {

        state = state.transform(numberOfLiveNeighbours());
        return this;
    }

    @Override
    public String toString()
    {
        return state.toString();
    }


}
class StateFactory
{
    public static State constructState(CellState state)
    {


        if(state.equals(CellState.ALIVE))
            return new Alive();
        return new Dead();
    }
}
class Grid
{
    ArrayList<Cell> cells = new ArrayList<Cell>();
    Integer number_of_transitions;
    ArrayList<Dimension> alive_dimensions = new ArrayList<Dimension>();
    Integer rows;
    Integer columns;

    private void buildCell(Integer row, Integer column)
    {
        CellState state = CellState.DEAD;


        for(Dimension dimension: alive_dimensions)
        {

            if(dimension.row() == row && dimension.column() == column)
                state = CellState.ALIVE;
        }



        cells.add(new Cell(row, column, StateFactory.constructState(state)));

    }

    public void buildRow(Integer row, Integer columns)
    {
        for(int j=0;j<columns;j++)
            buildCell(row,j);
    }

    public boolean notWithinBound(Integer row, Integer column)
    {
        return (row>=rows || column>=columns);
    }

    public Cell findCellByRowColumn(Integer row, Integer column)
    {
        if(notWithinBound(row, column))
            return null;
        for(Cell cell:cells)
        {
            if(cell.Row()==row && cell.Column()==column)
                return cell;
        }
        return null;
    }

    private Cell addNeighbours(Cell cell)
    {
        Cell neighbour = findCellByRowColumn(cell.Row()-1, cell.Column()-1);
        if(neighbour != null)
            cell.addNeighbour(neighbour);
        neighbour = findCellByRowColumn(cell.Row()-1, cell.Column());
        if(neighbour != null)
            cell.addNeighbour(neighbour);
        neighbour = findCellByRowColumn(cell.Row()-1, cell.Column()+1);
        if(neighbour != null)
            cell.addNeighbour(neighbour);
        neighbour = findCellByRowColumn(cell.Row(), cell.Column()-1);
        if(neighbour != null)
            cell.addNeighbour(neighbour);
        neighbour = findCellByRowColumn(cell.Row(), cell.Column()+1);
        if(neighbour != null)
            cell.addNeighbour(neighbour);
        neighbour = findCellByRowColumn(cell.Row()+1, cell.Column()-1);
        if(neighbour != null)
            cell.addNeighbour(neighbour);
        neighbour = findCellByRowColumn(cell.Row()+1, cell.Column());
        if(neighbour != null)
            cell.addNeighbour(neighbour);
        neighbour = findCellByRowColumn(cell.Row()+1, cell.Column()+1);
        if(neighbour != null)
            cell.addNeighbour(neighbour);
        return cell;
    }


    private void buildNeighbours()
    {
        Integer i=0;
        for(Cell cell:cells)
        {
            cells.set(i,addNeighbours(cell));
            i++;
        }
    }
    public Grid(Integer rows, Integer columns, ArrayList<Dimension> alive_dimensions, Integer number_of_transitions)
    {
        this.number_of_transitions = number_of_transitions;
        this.rows = rows;
        this.columns = columns;
        this.alive_dimensions.addAll(alive_dimensions);
        for(int i=0;i<rows;i++)
            buildRow(i, columns);
        buildNeighbours();
        startTransitions();
    }

    private void startTransitions()
    {
        Integer i = 1;
        while(i <= number_of_transitions)
        {
            playGameOfLife();
            i++;
        }
    }

    private void playGameOfLife()
    {
        int i =0;
        for(Cell cell:cells)
        {

            Cell newCell = cell.playGame();
            cells.set(i, newCell);
            i++;
        }
    }

    public void printCells()
    {
        for(Cell cell:cells)
        {
            System.out.println("Row " + cell.Row() + " Column "+ cell.Column() + " value " + cell.toString());
        }
    }

}

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Dimension> alive_dimension = new ArrayList<Dimension>();
        alive_dimension.add(new Dimension(0,2));
        alive_dimension.add(new Dimension(0,4));
        alive_dimension.add(new Dimension(1,0));
        alive_dimension.add(new Dimension(1,1));
        alive_dimension.add(new Dimension(1,3));
        alive_dimension.add(new Dimension(1,4));
        alive_dimension.add(new Dimension(2,0));
        alive_dimension.add(new Dimension(2,3));
        alive_dimension.add(new Dimension(3,2));
        alive_dimension.add(new Dimension(3,3));
        alive_dimension.add(new Dimension(3,4));
        new Grid(4, 5, alive_dimension, 1).printCells();
    }
}
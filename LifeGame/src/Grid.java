import java.util.ArrayList;

public class Grid {
    private ArrayList<Cell> cells = new ArrayList<Cell>();
    private Integer number_of_transitions;
    private ArrayList<Dimension> alive_dimensions = new ArrayList<Dimension>();
    private Integer rows;
    private Integer columns;



    private void buildCell(Integer row, Integer column) {
        CellState state = CellState.DEAD;


        for (Dimension dimension : alive_dimensions) {

            if (dimension.cellRow() == row && dimension.cellColumn() == column)
                state = CellState.ALIVE;
        }


        cells.add(new Cell(new Dimension(row, column), state));

    }

    private void buildRow(Integer row, Integer columns) {
        for (int j = 0; j < columns; j++)
            buildCell(row, j);
    }

    private boolean notWithinBound(Integer row, Integer column) {
        return (row >= rows || column >= columns);
    }

    private Cell findCellByRowColumn(Integer row, Integer column) {
        if (notWithinBound(row, column))
            return null;
        for (Cell cell : cells) {
            if (cell.cellRow() == row && cell.cellColumn() == column)
                return cell;
        }
        return null;
    }

    private Cell addNeighbours(Cell cell) {
        cell.addNeighbour(findCellByRowColumn(cell.cellRow() - 1, cell.cellColumn() - 1));
        cell.addNeighbour(findCellByRowColumn(cell.cellRow() - 1, cell.cellColumn()));
        cell.addNeighbour(findCellByRowColumn(cell.cellRow() - 1, cell.cellColumn() + 1));
        cell.addNeighbour(findCellByRowColumn(cell.cellRow(), cell.cellColumn() - 1));
        cell.addNeighbour(findCellByRowColumn(cell.cellRow(), cell.cellColumn() + 1));
        cell.addNeighbour(findCellByRowColumn(cell.cellRow() + 1, cell.cellColumn() - 1));
        cell.addNeighbour(findCellByRowColumn(cell.cellRow() + 1, cell.cellColumn()));
        cell.addNeighbour(findCellByRowColumn(cell.cellRow() + 1, cell.cellColumn() + 1));
        return cell;
    }


    private void buildNeighbours() {
        Integer i = 0;
        for (Cell cell : cells) {
            addNeighbours(cell);
            i++;
        }
    }

    public Grid(Integer rows, Integer columns, ArrayList<Dimension> alive_dimensions, Integer number_of_transitions) {
        this.number_of_transitions = number_of_transitions;
        this.rows = rows;
        this.columns = columns;
        this.alive_dimensions.addAll(alive_dimensions);
        for (int i = 0; i < rows; i++)
            buildRow(i, columns);
        buildNeighbours();
        startTransitions();
    }

    private void startTransitions() {
        Integer i = 1;
        while (i <= number_of_transitions) {
            playGameOfLife();
            i++;
        }
    }

    private void playGameOfLife() {
        int i = 0;
        ArrayList<Cell> newCells = new ArrayList<Cell>();
        for (Cell cell : cells) {
            Cell newCell = cell.playGame();
            newCells.add(newCell);
            i++;
        }
        this.cells = newCells;
    }
    public String[][] Cells()
    {
        int cellCount = 0;
        String[][] gridCells=new String[rows][columns];
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                gridCells[i][j] = cells.get(cellCount).toString();
                cellCount++;
            }
        }

        return gridCells;
    }
    public void printCells() {
        String[][] gridCells = Cells();
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                System.out.print(gridCells[i][j]+" ");

            }
            System.out.println();
        }
    }

}
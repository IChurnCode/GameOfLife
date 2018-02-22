import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CellTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Cell.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

//    @Test
//    public void addNeighbour() {
//        Cell cell = new Cell(new Dimension(0,1),CellState.ALIVE);
//        cell.addNeighbour(new Cell(new Dimension(0,0),CellState.ALIVE));
//        //
//    }

    @Test
    public void cellRow() {
        Cell cell = new Cell(new Dimension(0,1),CellState.ALIVE);
        assertEquals((Integer)0, cell.cellColumn());
    }

    @Test
    public void cellColumn() {
        Cell cell = new Cell(new Dimension(0,1),CellState.ALIVE);
        assertEquals((Integer)1, cell.cellColumn());
    }

    @Test
    public void isAlive() {
        Cell cell = new Cell(new Dimension(0,1),CellState.ALIVE);
        assertEquals(true,cell.isAlive());
    }

    @Test
    public void numberOfLiveNeighbours() {
        Cell cell = new Cell(new Dimension(0,1),CellState.ALIVE);
        cell.addNeighbour(new Cell(new Dimension(0,0),CellState.ALIVE));
        cell.addNeighbour(new Cell(new Dimension(0,2),CellState.ALIVE));
        cell.addNeighbour(new Cell(new Dimension(1,0),CellState.ALIVE));
        cell.addNeighbour(new Cell(new Dimension(1,1),CellState.ALIVE));
        cell.addNeighbour(new Cell(new Dimension(1,2),CellState.DEAD));
        assertEquals(4,cell.numberOfLiveNeighbours());
    }

    @Test
    public void playGame() {
        Cell cell = new Cell(new Dimension(0,1),CellState.ALIVE);
        cell.addNeighbour(new Cell(new Dimension(0,0),CellState.ALIVE));
        cell.addNeighbour(new Cell(new Dimension(0,2),CellState.ALIVE));
        cell.addNeighbour(new Cell(new Dimension(1,0),CellState.ALIVE));
        cell.addNeighbour(new Cell(new Dimension(1,1),CellState.ALIVE));
        cell.addNeighbour(new Cell(new Dimension(1,2),CellState.ALIVE));
        cell.playGame();
        assertEquals("a", cell.toString());
    }

//    @Test
//    public void toString() {
//
//    }
}

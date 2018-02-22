import java.util.ArrayList;

public class Neighbours extends ArrayList<Cell> {

    public void addNeighbour(Cell neighbour) {
        this.add(neighbour);
    }

    private Integer countIfLive(Cell neighbour)
    {
        if(neighbour.isAlive())
            return 1;
        return 0;
    }
    public Integer numberOfLiveNeighbours() {
        Integer count = 0;
        for (Cell cell : this) {
            count += countIfLive(cell);
        }
        return count;
    }

}
public class Cell {
    private Dimension dimension;
    private State state;
    private Neighbours neighbours = new Neighbours();

    public void addNeighbour(Cell neighbour) {
        if (neighbour != null)
            neighbours.add(neighbour);
    }

    public Integer cellRow() {
        return dimension.cellRow();
    }

    public Integer cellColumn() {
        return dimension.cellColumn();
    }

    public boolean isAlive() {
        return state.isAlive();
    }

    public Integer numberOfLiveNeighbours() {
        return neighbours.numberOfLiveNeighbours();
    }

    public Cell(Dimension dimension, CellState state) {
        this.dimension = dimension;
        this.state = StateFactory.createState(state);
    }

    public Cell playGame() {
        Cell transformedCell = this;
        for (ITransformable transformation : state.applicableTransformations()) {
            transformedCell = transformation.transform(this);
        }
        return transformedCell;
    }

    @Override
    public String toString() {
        return state.toString();
    }


}
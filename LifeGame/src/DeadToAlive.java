public class DeadToAlive implements ITransformable {
    private boolean meetsCriteria(Cell cell) {
        return cell.numberOfLiveNeighbours() == 3;

    }

    public Cell transform(Cell cell) {
        if (meetsCriteria(cell))
            return new Cell(new Dimension(cell.cellRow(), cell.cellColumn()), CellState.ALIVE);

        return cell;
    }
}
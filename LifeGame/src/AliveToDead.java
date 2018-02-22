public class AliveToDead implements ITransformable {

    private boolean meetsCriteria(Cell cell) {
        return cell.numberOfLiveNeighbours() < 2 || cell.numberOfLiveNeighbours() > 3;
    }

    public Cell transform(Cell cell) {
        if (meetsCriteria(cell))
            return new Cell(new Dimension(cell.cellRow(), cell.cellColumn()), CellState.DEAD);

        return cell;
    }
}
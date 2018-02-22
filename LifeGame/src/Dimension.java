public class Dimension {
    private Integer row;
    private Integer column;

    public Dimension(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    public Integer cellRow() {
        return row;
    }

    public Integer cellColumn() {
        return column;
    }
}
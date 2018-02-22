import java.util.*;








public class Main {
    public static void main(String[] args) {
        ArrayList<Dimension> alive_dimension = new ArrayList<Dimension>();
        alive_dimension.add(new Dimension(0, 0));
        alive_dimension.add(new Dimension(0, 1));
        alive_dimension.add(new Dimension(0, 2));
        alive_dimension.add(new Dimension(1, 0));
        alive_dimension.add(new Dimension(1, 1));
        alive_dimension.add(new Dimension(1, 2));
        alive_dimension.add(new Dimension(2, 0));
        alive_dimension.add(new Dimension(2, 1));
        alive_dimension.add(new Dimension(2, 2));

        new Grid(3, 3, alive_dimension, 1).printCells();
    }
}
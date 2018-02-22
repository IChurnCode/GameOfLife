public class StateFactory {
    public static State createState(CellState state) {
        if (state == CellState.ALIVE)
            return new Alive();
        return new Dead();
    }
}
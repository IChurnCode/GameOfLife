import java.util.*;

public class Alive implements State {
    private CellState state = CellState.ALIVE;

    public ArrayList<ITransformable> applicableTransformations() {
        ArrayList<ITransformable> applicableTransformations = new ArrayList<ITransformable>();
        applicableTransformations.add(new AliveToDead());
        return applicableTransformations;
    }

    public boolean isAlive() {
        return true;
    }

    public String toString() {
        return "a";
    }
}
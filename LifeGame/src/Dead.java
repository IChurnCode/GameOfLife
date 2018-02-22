import java.util.ArrayList;

public class Dead implements State {
    private CellState state = CellState.DEAD;

    public ArrayList<ITransformable> applicableTransformations() {
        ArrayList<ITransformable> applicableTransformations = new ArrayList<ITransformable>();
        applicableTransformations.add(new DeadToAlive());
        return applicableTransformations;
    }

    public boolean isAlive() {
        return false;
    }

    public String toString() {
        return "d";
    }
}
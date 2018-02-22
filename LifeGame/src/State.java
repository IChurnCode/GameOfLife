import java.util.*;
public interface State {
    ArrayList<ITransformable> applicableTransformations();

    boolean isAlive();

    @Override
    String toString();
}


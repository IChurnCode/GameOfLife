import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GridTest {
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(. class).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }

    @org.junit.Test
    public void cells() {
        ArrayList<Dimension> dimensions = new ArrayList<Dimension>();
        dimensions.add(new Dimension(0,0));
        dimensions.add(new Dimension(0,1));
        dimensions.add(new Dimension(0,2));
        dimensions.add(new Dimension(1,0));
        dimensions.add(new Dimension(1,1));
        dimensions.add(new Dimension(1,2));
        dimensions.add(new Dimension(2,0));
        dimensions.add(new Dimension(2,1));
        dimensions.add(new Dimension(2,2));
        Grid grid = new Grid(3,3,dimensions,1);
        String[][] result = {{"a", "d", "a"}, {"d", "d", "d"}, {"a", "d", "a"}};
        assertArrayEquals(result, grid.Cells());

    }


}

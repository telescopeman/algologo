import java.awt.Shape;

public class Spawner extends GameObject {
    GameObject obj;

    public Spawner(GameObject objType, Sequence bounds)
    {
        obj = objType;
        myShape = bounds;
    }
}

import java.util.LinkedList;
import java.awt.Graphics;

/**
 * Handles objects.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/12/21
 * @since 4/8/21
 */
public class Handler
{
    public static LinkedList<GameObject> object = new LinkedList<>();
    
    private static Camera camera;

    public static void tick()
    {
        for (GameObject tempObject : object) {
            tempObject.tick();

            // camera motion
            if (tempObject.getID() == ID.Player) {
                camera.watch( (LivingObject) tempObject);
            }
        }
    }
    
    public static void render(Graphics g)
    {
        for (GameObject tempObject : object) {
            tempObject.render(g, -(int) camera.getX(), -(int) camera.getY());
        }
    }
    
    public static void addObject(GameObject obj) {
        if (obj.getID() == ID.Camera)
        {
            camera = (Camera) obj;
        }
        object.add(obj);
    }
    
    public static void addObject(GameObject obj, int x, int y)
    {
        addObject(obj);
        obj.setX(x);
        obj.setY(y);
    }

    public static void addObjects(GameObject[] objs)
    {
        for (GameObject obj : objs)
        {
            addObject(obj);
        }
    }
    
    public static void removeObject(GameObject obj)
    {
        object.remove(obj);
    }


}

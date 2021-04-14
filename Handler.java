import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Graphics;

/**
 * Handles objects.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/13/21
 * @since 4/8/21
 */
public class Handler
{
    public static LinkedList<GameObject> object = new LinkedList<>();

    private static final LinkedList<GameObject> deletionQueue = new LinkedList<>();

    private static final LinkedList<GameObject> additionQueue = new LinkedList<>();
    
    private static Camera camera;

    private static int lastX = 0,lastY = 0;

    public static void tick()
    {
        for (Iterator<GameObject> iterator = deletionQueue.iterator(); iterator.hasNext();) {
            GameObject obj = iterator.next();
            removeObject(obj);
            deletionQueue.remove(obj);
        }

        for (Iterator<GameObject> iterator = additionQueue.iterator(); iterator.hasNext();) {
            GameObject obj = iterator.next();
            addObject(obj);
            additionQueue.remove(obj);
        }

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
            tempObject.applyStyle(g);
            tempObject.render(g, -(int) camera.getX(), -(int) camera.getY());
        }
    }
    
    public static void addObject(GameObject obj) {
        if (obj.hasID(ID.Camera))
        {
            camera = (Camera) obj;
        }
        object.add(obj);
        if (!obj.hasID(ID.UI)) {
            addObject(new HUD(obj));
        }
    }
    
    public static void addObject(GameObject obj, int x, int y)
    {
        if (obj.getID() == ID.Player)
        {
               lastX = x;
               lastY = y;
        }
        addObject(obj);
        obj.setX(x);
        obj.setY(y);
    }

    public static void addObjects(GameObject[] objects)
    {
        for (GameObject obj : objects)
        {
            addObject(obj);
        }
    }
    
    public static void removeObject(GameObject obj) { object.remove(obj); }


    public static void queueForDeletion(GameObject obj)
    {
        deletionQueue.add(obj);
    }

    public static void queueForAddition(GameObject obj)
    {
        additionQueue.add(obj);
    }

    public static void doDeathAnimation(Player player)
    {
        queueForDeletion(player);
        // note: this doesn't actually reset everything. maybe do that later.
        long time_initial = System.currentTimeMillis();
        int DEATH_WAIT_TIME = 800;
        while (System.currentTimeMillis() < time_initial + DEATH_WAIT_TIME)
        {
            // do nothing lol
        }
        camera.setX(0);
        camera.setY(0);
        queueForAddition(new Player(lastX,lastY));
    }
}

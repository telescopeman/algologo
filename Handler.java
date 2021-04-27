import java.awt.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Handles objects.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/27/21
 * @since 4/8/21
 */
public class Handler
{
    private static final Color BACKGROUND_COLOR = Color.white;
    public static LinkedList<GameObject> object = new LinkedList<>();

    private static final LinkedList<GameObject> deletionQueue = new LinkedList<>(), additionQueue = new LinkedList<>();
    private static boolean needsNewRender = true;
    private static int lastX = 0,lastY = 0;

    public static void loadQueuedObjects()
    {
        for (Iterator<GameObject> iterator = deletionQueue.iterator(); iterator.hasNext();) {
            GameObject obj = iterator.next();
            removeObject(obj);
            iterator.remove();
            needsNewRender = true;
        }
    }
    public static void removeQueuedObjects()
    {
        for (Iterator<GameObject> iterator2 = additionQueue.iterator(); iterator2.hasNext();) {
            GameObject obj = iterator2.next();
            addObject(obj);
            iterator2.remove();
            needsNewRender = true;
        }
    }

    public static void tick()
    {
        loadQueuedObjects();

        removeQueuedObjects();


        for (Iterator<GameObject> iterator2 = object.iterator(); iterator2.hasNext();) {
            GameObject obj = iterator2.next();
            obj.tick();
            if (obj.getNeedsRender())
            {
                needsNewRender = true;
            }
        }
    }

    public static void render(GraphicsHelper g) throws CloneNotSupportedException {
        if (needsNewRender) {
            Collections.sort(object, new Priority());
            g.setColor(BACKGROUND_COLOR);
            g.fillRect(Window.getMinX(),Window.getMinY(), Window.getMaxX(), Window.getMaxY());
            for (Iterator<GameObject> iterator2 = object.iterator(); iterator2.hasNext(); ) {
                GameObject tempObject = iterator2.next();
                tempObject.render(g);
            }
            needsNewRender = false;
        }
    }

    
    public static void addObject(GameObject obj) {
        //System.out.println("Adding new " + obj.getID().toString());
        object.add(obj);
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
        long time_initial = System.currentTimeMillis();
        int DEATH_WAIT_TIME = 800;
        while (System.currentTimeMillis() < time_initial + DEATH_WAIT_TIME)
        {
            // do nothing lol
        }
        //queueForAddition(new Player(lastX,lastY));
    }
}

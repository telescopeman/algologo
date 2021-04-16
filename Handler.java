import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Graphics;

/**
 * Handles objects.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/14/21
 * @since 4/8/21
 */
public class Handler
{
    public static LinkedList<Entity> object = new LinkedList<>();

    private static final LinkedList<Entity> deletionQueue = new LinkedList<>();

    private static final LinkedList<Entity> additionQueue = new LinkedList<>();

    private static int lastX = 0,lastY = 0;

    public static void tick()
    {
        for (Iterator<Entity> iterator = deletionQueue.iterator(); iterator.hasNext();) {
            Entity obj = iterator.next();
            removeObject(obj);
            iterator.remove();
        }
        //deletionQueue.clear();

        for (Iterator<Entity> iterator2 = additionQueue.iterator(); iterator2.hasNext();) {
            Entity obj = iterator2.next();
            addObject(obj);
            iterator2.remove();
        }
        //additionQueue.clear();

        for (Iterator<Entity> iterator2 = object.iterator(); iterator2.hasNext();) {
            Entity obj = iterator2.next();
            if (obj.getEnabled()) {
                obj.tick();
            }
        }
    }

    public static void render(Graphics g)
    {
        for (Iterator<GameObject> iterator2 = object.iterator(); iterator2.hasNext();) {
            GameObject tempObject = iterator2.next();
            if (tempObject.getEnabled()) {
                tempObject.applyStyle(g);
                tempObject.render(g, -(int) camera.getX(), -(int) camera.getY());
            }
        }
    }
    
    public static void addObject(Entity obj) {
        System.out.println("Adding new " + obj.getID().toString());

        object.add(obj);

    }

    public static void addObjects(Entity[] objects)
    {
        for (Entity obj : objects)
        {
            addObject(obj);
        }
    }
    
    public static void removeObject(Entity obj) { object.remove(obj); }


    public static void queueForDeletion(Entity obj)
    {
        deletionQueue.add(obj);
    }

    public static void queueForAddition(Entity obj)
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
        //camera.setX(0);
        //camera.setY(0);
        //queueForAddition(new Player(lastX,lastY));
    }
}

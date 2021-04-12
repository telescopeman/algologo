import java.util.LinkedList;
import java.awt.Graphics;

/**
 * Write a description of class Handler here.
 *
 * @author RealTutsGML
 * @version 4/8/21
 * @since 4/8/21
 */
public class Handler
{
    // instance variables - replace the example below with your own
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    private Camera camera;

    /**
     * Constructor for objects of class Handler
     */
    public Handler()
    {
        camera = new Camera(0,0);
        //addObject(camera);
    }

    public void tick()
    {
        for (int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            tempObject.tick();
            
            // camera motion
            if (tempObject.getID() == ID.Player)
            {
                assert(tempObject instanceof Player);
                camera.watch((LivingObject) tempObject);
            }
        }
        camera.tick();
    }
    
    public void render(Graphics g)
    {
        for (int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            tempObject.render(g, - (int)camera.getX(), - (int)camera.getY());
        }
    }
    
    public void addObject(GameObject object)
    {
        this.object.add(object);
        
    }
    
    public void addObject(GameObject object, int x, int y)
    {
        this.object.add(object);
        object.setX(x);
        object.setY(y);
    }
    
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
}

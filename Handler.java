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

    /**
     * Constructor for objects of class Handler
     */
    public Handler()
    {
        // initialise instance variables
        //x = 0;
    }

    public void tick()
    {
        for (int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
        
    }
    
    public void render(Graphics g)
    {
        for (int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object)
    {
        this.object.add(object);
        
    }
    
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
}

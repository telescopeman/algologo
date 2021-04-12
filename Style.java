import java.awt.Color;
import java.awt.Dimension;
import java.awt.BasicStroke;
/**
 * Write a description of class Style here.
 *
 * @author Caleb Copeland
 * @version 4/12/21
 * @since 4/8/21
 */
public class Style
{
    public final Color color;
    public final Dimension bounds;
    public final DrawType drawer;
    public final float thickness;
    
    /**
     * Constructor for objects of class Style
     */
    public Style(Color col, Dimension b, float thickness, DrawType drawer) 
    {
        // initialise instance variables
        color = col;
        this.thickness = thickness;
        bounds = b;
        this.drawer = drawer;
        
    }
    
    /**
     * Default Style
     */
    public Style() 
    {
        // initialise instance variables
        color = Color.white;
        this.thickness = 3;
        bounds = new Dimension(Game.WIDTH,Game.HEIGHT);
        drawer = DrawType.FILL;
    }

    /**
     * Default Style
     */
    public Style(DrawType drawer) 
    {
        // initialise instance variables
        color = Color.white;
        this.thickness = 3;
        bounds = new Dimension(Game.WIDTH*4,Game.HEIGHT*4);
        this.drawer = drawer;
    }
    
    /**
     * Default Style
     */
    public Style(DrawType drawer, Dimension dim) 
    {
        // initialise instance variables
        color = Color.white;
        this.thickness = 3;
        bounds = dim;
        this.drawer = drawer;
    }

    public BasicStroke getStroke()
    {
        return new BasicStroke(thickness);

    }
}

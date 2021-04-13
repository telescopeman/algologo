import java.awt.Color;
import java.awt.Dimension;
import java.awt.BasicStroke;
/**
 * Represents the style of a polygon shape to be drawn.
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
    public float thickness = 3;
    
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
        color = Game.TERRAIN_COLOR;
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

    public Style(DrawType drawer, Color col)
    {
        // initialise instance variables
        color = col;
        bounds = new Dimension(Game.WIDTH*4,Game.HEIGHT*4);
        this.drawer = drawer;
    }
    

    public Style(DrawType drawer, Dimension dim) 
    {
        // initialise instance variables
        color = Color.white;
        bounds = dim;
        this.drawer = drawer;
    }

    public BasicStroke getStroke()
    {
        return new BasicStroke(thickness);

    }
}

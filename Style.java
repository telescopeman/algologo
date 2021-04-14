import java.awt.Color;
import java.awt.Dimension;
import java.awt.BasicStroke;
/**
 * Represents the style of a polygon shape to be drawn.
 *
 * @author Caleb Copeland
 * @version 4/14/21
 * @since 4/8/21
 */
public class Style
{
    public Color color = Game.TERRAIN_COLOR;
    public Dimension bounds = new Dimension(Game.WIDTH*4,Game.HEIGHT*4);
    public DrawType drawer = DrawType.FILL;
    public BasicStroke thickness = new BasicStroke(3);
    
    /**
     * Constructor for objects of class Style
     */
    public Style(Color col, Dimension b, float thickness, DrawType drawer) 
    {
        // initialise instance variables
        color = col;
        this.thickness = new BasicStroke(thickness);
        bounds = b;
        this.drawer = drawer;
    }
    
    /**
     * Default Style
     */
    public Style() 
    {

    }

    /**
     * Default Style
     */
    public Style(DrawType drawer) 
    {
        this.drawer = drawer;
    }

    public Style(DrawType drawer, Color col)
    {
        color = col;
        this.drawer = drawer;
    }
    

    public Style(DrawType drawer, Dimension dim) 
    {
        bounds = dim;
        this.drawer = drawer;
    }



}

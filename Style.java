import java.awt.Dimension;
import java.awt.*;
/**
 * Write a description of class Style here.
 *
 * @author Caleb Copeland
 * @version (a version number or a date)
 */
public class Style
{
    // instance variables - replace the example below with your own
    public Color color;
    public Dimension bounds;
    public boolean isClosed;
    public boolean filled;
    
    /**
     * Constructor for objects of class Style
     */
    public Style(Color col, Dimension b, boolean close, boolean fill) 
    {
        // initialise instance variables
        color = col;
        bounds = b;
        isClosed = close;
        filled = fill;
        
    }
    
     /**
     * Constructor for objects of class Style
     */
    public Style() 
    {
        // initialise instance variables
        color = Color.black;
        bounds = new Dimension(800,800);
        isClosed = true;
        filled = true;
        
    }

    
}

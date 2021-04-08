import java.awt.Dimension;
import java.awt.*;
/**
 * Write a description of class Style here.
 *
 * @author Caleb Copeland
 * @version 4/8/21
 * @since 4/8/21
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
    
    

    
}

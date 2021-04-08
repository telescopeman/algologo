import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Shape;


/**
 * Write a description of class Player here.
 *
 * @author Caleb Copeland
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Rectangle shape;

    private final Dimension SIZE = new Dimension(40,40);
    private final Style STYLE = new Style(Color.green,SIZE,true,true);
    
    private double xVel;
    private double yVel;
    
    private double xPos;
    private double yPos;
    
    /**
     * Constructor for objects of class Player
     */
    public Player(double xInit, double yInit)
    {
        // initialise instance variables
        shape = new Rectangle(SIZE);
        xPos = xInit;
        yPos = yInit;
        updateRect();
        
    }
    
    private void updateRect()
    {
        // clarification: the position of the Player is at the bottom-center of its sprite.
        shape.x = (int) xPos-SIZE.width/2;
        shape.y = (int) yPos-SIZE.height;
    }

    /**
     * Gets the sprite of the Player.
     */
    public AlgoShape getShape()
    {
        // put your code here
        return new AlgoShape(shape, STYLE);
    }

    
}

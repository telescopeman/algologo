import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Shape;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

/**
 * Write a description of class Player here.
 *
 * @author Caleb Copeland, Rob Camick [motion only]
 * @version 4/8/21
 * @since 4/8/21
 */
public class Player extends GameObject 
{
    // instance variables - replace the example below with your own
    private Rectangle shape;

    private final Dimension SIZE = new Dimension(40,40);

    
    private final double JUMPVEL = 9;
    
    
    public final double K = 0.1;
    
    
    private final double AIRACCEL = 1;
    private final double GRAVITY = -0.098;
    /**
     * Constructor for objects of class Player
     */
    public Player(int xInit, int yInit)
    {
        super(xInit,yInit,ID.Player);
        setSoughtVelocityX(0);
        setSoughtVelocityY(0);
        maxSpeedH = 3.0;
        maxSpeedV = 10.0;
        // initialise instance variables
        setGrounded(true);
        //setVelocityX(1);
        shape = new Rectangle(SIZE);
        updateRect();

    }

    private void updateRect()
    {
        // clarification: the position of the Player is at the bottom-center of its sprite.
        shape.x = (int) x-SIZE.width/2;
        shape.y = (int) y-SIZE.height;
    }

    public void tick()
    {
        
        x += velX;
        y += velY;

        //double absSpeedX = Math.abs(velX);
        //System.out.println(velX);
        if (velX < getSoughtVelocityX())
        {
            velX += K;
        }
        else if (velX > getSoughtVelocityX())
        {
            
            velX -= K;
            
        }
        
        if (velY < getSoughtVelocityX())
        {
            velX += K;
        }
        else if (velX > getSoughtVelocityX())
        {
            
            velX -= K;
            
        }
    }

    public void render(Graphics g)
    {
        updateRect();
        g.setColor(Color.white);  //this does not make the screen white
        g.fillRect(shape.x,shape.y,SIZE.width,SIZE.height);

    }


    public void jump()
    {
        velY = - JUMPVEL;
        setSoughtVelocityY(maxSpeedV);
        setGrounded(false);
    }

}

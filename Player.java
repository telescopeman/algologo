import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Shape;
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

    private Handler handler;

    //private GameObject currentSurface;

    //private final double AIRACCEL = 1;
    /**
     * Constructor for objects of class Player
     */
    public Player(int xInit, int yInit, Handler handler)
    {
        super(xInit,yInit,ID.Player);

        this.handler = handler;
        setSoughtVelocityX(0);
        setSoughtVelocityY(maxSpeedV);
        
        maxSpeedH = 8.0;
        maxSpeedV = 10.0;
        velJumpMultiplier = 0.3;
        baseJump = 6;
        health = 10;
        damage = 0;
        K = 0.5;
        setGrounded(false);
        setVelocityX(0);
        setVelocityY(0);
        shape = new Rectangle(SIZE);
        updateRect();

    }

    private void updateRect()
    {
        // clarification: the position of the Player is at the bottom-center of its sprite.

        shape.x = (int) x-SIZE.width/2;
        shape.y = (int) y-SIZE.height;
    }

    public Shape getBounds(){
        return shape;
    }

    public boolean intersects(Rectangle rect)
    {
        return false;
    }

    public void tick()
    {
        updateRect();
        collision();
        if (currentSurface != null)
        {
            
            if (currentSurface.intersects( (Rectangle) getBounds()))
            {
                setGrounded(true);
                
            }
            else
            {
                setGrounded(false);
                currentSurface = null;
            }
            
        }
        
        

        updateRect();
        doPhysics();

    }

    private void collision()
    {
        for(int i = 0; i < handler.object.size();i++)
        {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.intersects(  (Rectangle) getBounds()  ))
            {
                switch( tempObject.getID() )
                {
                    case Platform:
                    {
                        land(tempObject);
                        break;
                    }
                    case Enemy:
                    {
                        takeDamage(2);
                        break;

                    }
                    default:
                    {
                        break;
                    }

                }
            }
        }

    }

    public void land(GameObject surface)
    {
        currentSurface = surface;
        setGrounded(true);
        //System.out.println("land");
        //setSoughtVelocityY(0);
        setVelocityY(0);
        //if surface is below this code works.
        while(surface.intersects( (Rectangle) getBounds() ))
        {
            y--;
            updateRect();
        }
        y++;

        updateRect();
        
        //setGrounded(false);
    }


    public void render(Graphics g, int offsetX, int offsetY)
    {
        updateRect();
        g.setColor(Color.white);  //this does not make the screen white
        g.fillRect(shape.x + offsetX,shape.y + offsetY,SIZE.width,SIZE.height);

    }

}
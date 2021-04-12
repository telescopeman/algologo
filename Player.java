import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Write a description of class Player here.
 *
 * @author Caleb Copeland, Rob Camick [motion only]
 * @version 4/12/21
 * @since 4/8/21
 */
public class Player extends LivingObject
{
    // instance variables - replace the example below with your own
    private final Rectangle shape;
    private final Dimension SIZE = new Dimension(40,40);


    /**
     * Constructor for objects of class Player
     */
    public Player(int xInit, int yInit, Handler handler)
    {
        super(xInit,yInit,ID.Player,handler);

        damageSources = new String[]{ID.Enemy.name()};
        maxSpeedH = 8.0;
        maxSpeedV = 10.0;
        velJumpMultiplier = 0.3;
        baseJump = 6;

        health = 10;
        setDamage(0);
        K = 0.5;
        //setSoughtVelocityY(0);
        //setGrounded(false);
        shape = new Rectangle(SIZE);
        updateForm();

    }

    public void updateForm()
    {
        // clarification: the position of the Player is at the bottom-center of its sprite.
        shape.x = (int) x-SIZE.width/2;
        shape.y = (int) y-SIZE.height;
    }

    public Rectangle getBounds(){
        return shape;
    }

    public boolean intersects(Rectangle rect)
    {
        return false;
    }

    public void tick()
    {
        updateForm();
        collision();
        checkContact();
        updateForm();
        doPhysics();

    }

    public void render(Graphics g, int offsetX, int offsetY)
    {
        updateForm();
        g.setColor(Color.white);  //this does not make the screen white
        g.fillRect(shape.x + offsetX,shape.y + offsetY,SIZE.width,SIZE.height);

    }

}
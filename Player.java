import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Write a description of class Player here.
 *
 * @author Caleb Copeland
 * @version 4/12/21
 * @since 4/8/21
 */
public class Player extends LivingObject
{
    private final Dimension SIZE = new Dimension(40,40);
    private final Color PLAYER_COLOR = Color.red;

    /**
     * Constructor for objects of class Player
     */
    public Player(int xInit, int yInit, Handler handler)
    {
        super(xInit,yInit,ID.Player,handler);

        damageSources = new String[]{ID.Enemy.name()};
        velJumpMultiplier = 0.3;
        baseJump = 6;
        setMaxHealth(10);
        fullHeal();

        shape = new Rectangle(SIZE);
        updateForm();

    }

    public void updateForm()
    {
        // clarification: the position of the Player is at the bottom-center of its sprite.
        assert(shape instanceof Rectangle);
        Rectangle rect = (Rectangle) shape;
        rect.x = (int) getX() - SIZE.width/2;
        rect.y = (int) getY() - SIZE.height;
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
        g.setColor(PLAYER_COLOR);
        assert(shape instanceof Rectangle);
        Rectangle rect = (Rectangle) shape;
        g.fillRect(rect.x + offsetX,rect.y + offsetY,SIZE.width,SIZE.height);

    }

}
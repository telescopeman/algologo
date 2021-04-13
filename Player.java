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
    private final Dimension PLAYER_SIZE = new Dimension(40,40);
    private final Color PLAYER_COLOR = Color.green;

    /**
     * Constructor for objects of class Player
     */
    public Player(int xInit, int yInit)
    {
        super(xInit,yInit,ID.Player);

        damageSources = new ID[]{ID.Enemy};
        velJumpMultiplier = 0.3;
        baseJump = 6;
        setMaxHealth(10);
        fullHeal();

        setBounds(PLAYER_SIZE);
        updateForm();

    }

    public void updateForm()
    {
        // clarification: the position of the Player is at the bottom-center of its sprite.
        assert(shape instanceof Rectangle);
        Rectangle rect = (Rectangle) shape;
        rect.x = (int) getX() - PLAYER_SIZE.width/2;
        rect.y = (int) getY() - PLAYER_SIZE.height;
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

    @Override
    public void die()
    {
        Handler.doDeathAnimation(this);
    }



    public void render(Graphics g, int offsetX, int offsetY)
    {
        updateForm();
        g.setColor(PLAYER_COLOR);
        assert(shape instanceof Rectangle);
        Rectangle rect = (Rectangle) shape;

        g.fillPolygon(adjust(rect,offsetX,offsetY));
    }

}
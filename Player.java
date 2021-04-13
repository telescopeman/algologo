import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Write a description of class Player here.
 *
 * @author Caleb Copeland
 * @version 4/13/21
 * @since 4/8/21
 */
public class Player extends LivingObject
{
    private final Dimension PLAYER_SIZE = new Dimension(40,40);
    private final Color PLAYER_COLOR = Color.green;
    private final int PLAYER_MAX_HP = 10;

    /**
     * Constructor for objects of class Player
     */
    public Player(int xInit, int yInit)
    {
        super(xInit, yInit, ID.Player, false, 10);
        setColor(PLAYER_COLOR);
        damageSources = new ID[]{ID.Enemy};
        velJumpMultiplier = 0.3;
        baseJump = 6;
        setBounds(PLAYER_SIZE);

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

    @Override
    public void die()
    {
        Handler.doDeathAnimation(this);
    }

    public void render(Graphics g, int offsetX, int offsetY)
    {
        updateForm();
        assert(shape instanceof Rectangle);
        Rectangle rect = (Rectangle) shape;
        g.fillPolygon(adjust(rect,offsetX,offsetY));
    }

}
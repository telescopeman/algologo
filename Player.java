import java.awt.Dimension;
import java.awt.Color;

/**
 * Represents the player.
 *
 * @author Caleb Copeland
 * @version 4/14/21
 * @since 4/8/21
 */
public class Player extends LivingObject {
    private final Dimension INIT_PLAYER_SIZE = new Dimension(40,40);
    private final Color INIT_PLAYER_COLOR = Color.green;

    /**
     * Constructor for objects of class Player
     */
    public Player(int xInit, int yInit)
    {
        super(xInit, yInit, ID.Player, false, 10);
        setColor(INIT_PLAYER_COLOR);
        setDamage(3);
        damageSources = new ID[]{ID.Enemy};
        velJumpMultiplier = 1;
        baseJump = 9;
        setBounds(INIT_PLAYER_SIZE);
    }


    @Override
    public void die()
    {
        Handler.doDeathAnimation(this);
    }


    public void shoot()
    {
        Handler.queueForAddition(
                new Bullet(getX(), getY() - getBounds().getBounds().getHeight() / 2,
                        10,ID.Enemy,
                        Math.signum(getVelocityX() + 0.01)*5 + getVelocityX(),
                        0,getDamage()));
    }

}
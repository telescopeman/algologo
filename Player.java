import java.awt.Dimension;
import java.awt.Color;

/**
 * Represents the player.
 *
 * @author Caleb Copeland
 * @version 4/14/21
 * @since 4/8/21
 */
public class Player extends LivingObject
{
    private final Dimension INIT_PLAYER_SIZE = new Dimension(40,40);
    private final Color INIT_PLAYER_COLOR = Color.green;


    /**
     * Constructor for objects of class Player
     */
    public Player(int xInit, int yInit)
    {
        super(xInit, yInit, ID.Player, false, 10);
        setColor(INIT_PLAYER_COLOR);
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



}
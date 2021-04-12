import java.awt.*;

/**
 * The game camera.
 *
 * @author Caleb Copeland
 * @since 4/8/21
 * @version 4/12/21
 */
public class Camera extends PhysicsObject
{
    
    /**
     * Constructor for objects of class Camera
     */
    public Camera(double x, double y)
    {
        super(x,y,ID.Camera);
        maxSpeedH = 11;
        maxSpeedV = 11;
        K = 1;
        K2 = 1;
    }

    public void render(Graphics g, int offsetX, int offsetY)
    {
        // do nothing lmao
    }


    public boolean intersects(Rectangle rect)
    {
        return false;
        // a camera is not a physical object
    }

    public void watch(LivingObject obj)
    {
        final double followSpeedX = 1;
        final double followSpeedY = 0.1;

        final double baseX = 2;
        final double baseY = 10;
        final int room = 200;

        final double xAdj =  obj.getX() - getX();
        final double yAdj =  obj.getY() - getY();
        
        final boolean tooRight = (xAdj) > Game.WIDTH - room;
        final boolean tooLeft = (obj.getX() - getX()) < room;
        final boolean tooHigh = (yAdj) < room;
        final boolean tooLow = (yAdj) > Game.HEIGHT - room;
        
        if (tooRight || tooLeft)
        {
            final double v = Math.signum(obj.getX()-getX() - Game.WIDTH/2.0);
            setSoughtVelocityX(v * (Math.abs(obj.getVelocityX()*followSpeedX) + baseX));
        }
        else
        {
            setSoughtVelocityX(0);
        }

        if (tooHigh || tooLow)
        {
            final int v = (int) Math.signum(obj.getY() - getY() - Game.HEIGHT / 2.0);
            setSoughtVelocityY(v * (Math.abs(obj.getVelocityY()*followSpeedY) + baseY));
        }
        else
        {
            setSoughtVelocityY(0);
        }
    }

}

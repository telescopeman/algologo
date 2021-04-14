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
     * Makes a camera at a certain position.
     */
    public Camera(double x, double y)
    {
        super(x,y,ID.Camera);
        init();

    }

    private void init()
    {
        maxSpeedH = 11;
        maxSpeedV = 11;
        K = 1;
        K2 = 1;
        setBounds(new Rectangle(-9999,-9998,-9999,-9998));
    }

    /**
     * Makes a camera at the origin.
     */
    public Camera()
    {
        super(0,0,ID.Camera);
        init();
    }

    public void render(Graphics g, int offsetX, int offsetY)
    {
        // do nothing lmao
    }

    public void updateForm()
    {
        // do nothing.
    }


    public boolean intersects(Shape rect)
    {
        return false;
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

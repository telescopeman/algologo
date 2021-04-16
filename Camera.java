import java.awt.*;

/**
 * The game camera.
 *
 * @author Caleb Copeland
 * @since 4/8/21
 * @version 4/16/21
 */
public class Camera extends Entity
{

    private Entity target;

    /**
     * Constructs a camera at a certain position.
     */
    public Camera(Entity target)
    {
        super(new Vector3D(),false,ID.Camera);
        this.target = target;
    }

    public void setTarget(Entity target)
    {
        this.target = target;
    }


    public void tick()
    {
        final double followSpeedX = 1;
        final double followSpeedY = 0.1;


        final double baseX = 2;
        final double baseY = 10;
        final int room = 200;

//        final double xAdj =  obj.getX() - getX();
//        final double yAdj =  obj.getY() - getY();
//
//        final boolean tooRight = (xAdj) > Game.WIDTH - room;
//        final boolean tooLeft = (obj.getX() - getX()) < room;
//        final boolean tooHigh = (yAdj) < room;
//        final boolean tooLow = (yAdj) > Game.HEIGHT - room;
        
//        if (tooRight || tooLeft)
//        {
//            final double v = Math.signum(obj.getX()-getX() - Game.WIDTH/2.0);
//            setSoughtVelocityX(v * (Math.abs(obj.getVelocityX()*followSpeedX) + baseX));
//        }
//        else
//        {
//            setSoughtVelocityX(0);
//        }
//
//        if (tooHigh || tooLow)
//        {
//            final int v = (int) Math.signum(obj.getY() - getY() - Game.HEIGHT / 2.0);
//            if (shouldMove)
//                setSoughtVelocityY(v * (Math.abs(obj.getVelocityY()*followSpeedY) + baseY));
//        }
//        else
//        {
//            setSoughtVelocityY(0);
        }

    @Override
    public void onDespawn() {
        // do nothing
    }
}

}

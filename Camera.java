//import java.math;
/**
 * Write a description of class Camera here.
 *
 * @author Caleb Copeland
 * @version 4/8/21
 */
public class Camera extends PhysicsObject
{
    // instance variables - replace the example below with your own
    //private final double 


    
    /**
     * Constructor for objects of class Camera
     */
    public Camera(double x, double y)
    {
        super(x,y);
        
        maxSpeedH = 11;
        maxSpeedV = 11;
        K = 1;
        K2 = 1;
        //setVelocityX(0);
    }


    public void watch(GameObject obj)
    {

        final double multX = 1;
        final double multY = 0.1;

        final double baseX = 2;
        final double baseY = 10;
        final int room = 200;
        
        
        double xAdj =  obj.getX() - getX();
        double yAdj =  obj.getY() - getY();
        
        boolean tooRight = (xAdj) > Game.WIDTH - room;
        boolean tooLeft = (obj.getX() - getX()) < room;
        boolean tooHigh = (yAdj) < room;
        boolean tooLow = (yAdj) > Game.HEIGHT - room;
        
        if (tooRight || tooLeft)
        {
            double v = Math.signum(obj.getX()-getX() - Game.WIDTH/2.0);
            setSoughtVelocityX(v * (Math.abs(obj.getVelocityX()*multX) + baseX));
        }
        else
        {
            setSoughtVelocityX(0);
        }

        if (tooHigh || tooLow)
        {
            int v = (int) Math.signum(obj.getY()-getY() - Game.HEIGHT/2) * 1;
            setSoughtVelocityY(v * (Math.abs(obj.getVelocityY()*multY) + baseY));
        }
        else
        {
            setSoughtVelocityY(0);
        }
        
    }

}

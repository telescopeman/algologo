
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
        setGrounded(true);
        maxSpeedH = 11;
        K = 1;
        setVelocityX(0);
    }


    public void watch(GameObject obj)
    {
        if (Math.abs(obj.getX() - getX()) > Game.WIDTH - 100)
        {
            double v = Math.signum(obj.getX()) * maxSpeedH;
            setSoughtVelocityX(v);
            System.out.println(getSoughtVelocityX());

        }
        else
        {
            System.out.println("Stop");
            setSoughtVelocityX(0);
        }

    }

}

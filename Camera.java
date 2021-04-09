
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
    private final int room = 200;

    private final double mult = 1;
    
    private final double base = 2;
    /**
     * Constructor for objects of class Camera
     */
    public Camera(double x, double y)
    {
        super(x,y);
        
        maxSpeedH = 11;
        maxSpeedV = 11;
        K = 1;
        setVelocityX(0);
    }


    public void watch(GameObject obj)
    {
        double xAdj =  obj.getX() - getX();
        double yAdj =  obj.getY() - getY();
        
        boolean tooRight = (xAdj) > Game.WIDTH - room;
        boolean tooLeft = (obj.getX() - getX()) < 0 + room;
        boolean tooHigh = (yAdj) < 0;
        boolean tooLow = (yAdj) > Game.HEIGHT - room;
        
        if (tooRight || tooLeft)
        {
            double v = Math.signum(obj.getX()-getX() - Game.WIDTH/2) * maxSpeedH;
            setSoughtVelocityX(v * Math.abs(obj.getVelocityX()*mult) + v * base);
            //System.out.println(getSoughtVelocityX());

        }
        else
        {
            //System.out.println("Stop");
            setSoughtVelocityX(0);
        }

        if (tooHigh || tooLow)
        {
            int v = (int) Math.signum(obj.getY()-getY() + Game.HEIGHT/2) * 1;
            setSoughtVelocityY(v * Math.abs(obj.getVelocityY()*mult) + v * base);
            System.out.println(getSoughtVelocityY());

        }
        else
        {
            //System.out.println("Stop");
            setSoughtVelocityY(0);
        }
        
    }

}

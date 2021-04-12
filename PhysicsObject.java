
/**
 * Write a description of class PhysicsObject here.
 *
 * @author Caleb Copeland
 * @since 4/9/21
 * @version 4/12/21
 */
public abstract class PhysicsObject extends GameObject
{
    protected double velX = 0, velY = 0,seekVelX = 0,seekVelY = 10,maxSpeedH,maxSpeedV = 10,K,K2 = 0.4; //gravity


    public void tick()
    {
         process();
    }
    

    public PhysicsObject(double x, double y, ID id)
    {
        super(x,y,id);
    }

    public void process()
    {
        x += velX;
        y += velY;
        
        if (getVelocityX() != getSoughtVelocityX())
        {
            double v = - Math.signum(getVelocityX() - getSoughtVelocityX()) * 1;
            velX += v*K;
        }
        
        if (getVelocityY() != getSoughtVelocityY())
        {
            double v = - Math.signum(getVelocityY() - getSoughtVelocityY()) * 1;
            velY += v*K2;
        }
            
        
        
    }


    public void setVelocityX(double v)
    {
        velX = v;
    }
    
    public void setVelocityY(double v)
    {
        velY = v;
    }
    
    public double getVelocityX()
    {
        return velX;
    }
    
    public double getVelocityY()
    {
        return velY;
    }
    
    public double getSoughtVelocityX()
    {
        return seekVelX;
    }
    
    public double getSoughtVelocityY()
    {
        return seekVelY;
    }
    
    public void setSoughtVelocityX(double n)
    {
         seekVelX = n;
    }
    
    public void setSoughtVelocityY(double n)
    {
         seekVelY = n;
    }

    
}

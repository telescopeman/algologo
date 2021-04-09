import java.awt.Graphics;

/**
 * Write a description of class PhysicsObject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class PhysicsObject
{
    // instance variables - replace the example below with your own
    protected double x, y;
    protected double velX, velY,seekVelX,seekVelY;
    protected double maxSpeedH,maxSpeedV,K;
    public final double K2 = 0.4; //gravity
    

    public void tick()
    {
         process();
    }
    

    public PhysicsObject(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.maxSpeedV = 10;
        setSoughtVelocityY(maxSpeedV);
    }
    
    public void process()
    {
        x += velX;
        y += velY;

        if (getVelocityX() < getSoughtVelocityX())
        {
            velX += K;
        }
        else if (getVelocityX() > getSoughtVelocityX())
        {
            velX -= K;
        }
        
        if (getVelocityY() < getSoughtVelocityY())
        {
            velY += K2;
        }
        else if (getVelocityY() > getSoughtVelocityY())
        {
            velY -= K2;
        }
        
    }

    public void setX(double x)
    {
        this.x = x;
    }
    
    public double getX()
    {
        return x;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }
    
    public double getY()
    {
        return y;
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

/**
 * Write a description of class PhysicsObject here.
 *
 * @author Caleb Copeland
 * @since 4/9/21
 * @version 4/12/21
 */
public abstract class PhysicsObject extends GameObject
{
    protected double velX = 0, velY = 0,
            seekVelX = 0,seekVelY = 10,
            maxSpeedH = 8,maxSpeedV = 10,
            K = 0.5,K2 = 0.4; //gravity


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
        
        if (getVelocityX() != getSoughtVelocityX()) {
            double v = - Math.signum(getVelocityX() - getSoughtVelocityX()) * 1;
            velX += v*K;
        }
        
        if (getVelocityY() != getSoughtVelocityY()) {
            double v = - Math.signum(getVelocityY() - getSoughtVelocityY()) * 1;
            velY += v*K2;
        }
    }

    /**
     * Actions to do when the object hits a wall.
     * @param side The direction this object was going.
     * @param object The surface that was hit.
     */
    public void bonk(SIDE side, GameObject object)
    {
        if (side == SIDE.BOTTOM || side == SIDE.TOP)
        {
            transformVelocity(0,-1);
            transformSoughtVelocity(0,-1);
        }
        else
        {
            transformVelocity(-1,0);
            transformSoughtVelocity(-1,0);
        }
    }

    public void transformVelocity(int x_factor, int y_factor)
    {
        setVelocityX(velX * x_factor);
        setVelocityY(velY * y_factor);
    }

    public void transformSoughtVelocity(int x_factor, int y_factor)
    {
        setSoughtVelocityX(seekVelX * x_factor);
        setSoughtVelocityY(seekVelY * y_factor);
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

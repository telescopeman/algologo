/**
 * Write a description of class PhysicsObject here.
 *
 * @author Caleb Copeland
 * @since 4/9/21
 * @version 4/15/21
 */
public abstract class PhysicsObject extends DynamicObject
{
    protected double velX = 0, velY = 0,
            seekVelX = 0,seekVelY = 10,
            maxSpeedH = 8,maxSpeedV = 10,
            K = 0.5, K2 = 0.4,
            diffCutoff = 0.05;
    private double horizontal_resistance = 1;

    public void setHorizontalResistance(double r)
    {
        horizontal_resistance = r;
    }

    /**
     * Sets the velocity according to a speed and angle.
     * @param speed The magnitude of the new combined velocity.
     * @param angle The angle in degrees, relative to the horizontal to the right.
     */
    public void setTrajectory(double speed, double angle)
    {
        double x_vel = speed * Math.cos(angle);
        double y_vel = speed * Math.sin(angle);
        setVelocityY(y_vel);
        setVelocityX(x_vel);
    }

    public void freezeX()
    {
        setSoughtVelocityX(getVelocityX());
    }

    public void tick()
    {
        physics_process();
    }
    

    public PhysicsObject(RenderJob[] jobs, double x, double y)
    {
        super(jobs, x,y);
    }

    public void physics_process()
    {
        physics_process(1);
    }





    /**
     * @param subdivisions How divided-up it is.
     */
    public void physics_process(int subdivisions)
    {
        setX(getX() + velX / subdivisions);
        setY(getY() + velY / subdivisions);

        if (Math.abs(getVelocityX() - getSoughtVelocityX()) < diffCutoff)
        {
            setVelocityX(getSoughtVelocityX());
        }
        if (Math.abs(getVelocityY() - getSoughtVelocityY()) < diffCutoff)
        {
            setVelocityY(getSoughtVelocityY());
        }

        if (getVelocityX() != getSoughtVelocityX()) {
            double v = - Math.signum(getVelocityX() - getSoughtVelocityX()) * 1;
            velX += v*(K / horizontal_resistance)/subdivisions;
        }

        if (getVelocityY() != getSoughtVelocityY()) {
            double v = - Math.signum(getVelocityY() - getSoughtVelocityY()) * 1;
            velY += v*K2/subdivisions;
        }
    }

    public void transformVelocity(double x_factor, double y_factor)
    {
        setVelocityX(velX * x_factor);
        setVelocityY(velY * y_factor);
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

/**
 * Write a description of class PhysicsObject here.
 *
 * @author Caleb Copeland
 * @since 4/9/21
 * @version 4/15/21
 */
public abstract class PhysicsObject extends GameObject
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
    

    public PhysicsObject(double x, double y, ID id)
    {
        super(x,y,id);
    }

    public void physics_process()
    {
        physics_process(1);
    }





    


}

import java.awt.*;

/**
 * Abstract class GameObject - write a description of the class here
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/12/21
 */
public abstract class LivingObject extends PhysicsObject
{
    protected double baseJump = 6;
    protected double velJumpMultiplier = 0.3;
    
    protected int health,damage;

    protected boolean canFly;
    
    
    public LivingObject(double x, double y, ID id)
    {
        super(x,y,id);
        setFlightAbility(false);
    }

    public LivingObject(double x, double y, ID id, boolean canFly)
    {
        super(x,y,id);
        setFlightAbility(canFly);
    }
    
    public void fall()
    {
        if (isGrounded())
        {
            setSoughtVelocityY(0);
        }
        else
        {
            setSoughtVelocityY(maxSpeedV);
        }
    }

    public void checkContact() {
        if (currentSurface != null) {
            if (currentSurface.intersects((Rectangle) getBounds())) {
                setGrounded(true);

            } else {
                setGrounded(false);
                currentSurface = null;
            }
        }
    }

    public void tick()
    {
        doPhysics();
    }
    
    public void doPhysics()
    {
        process();
        fall();
    }
    

        
    public abstract Shape getBounds();
    

    
    public void takeDamage(int n)
    {
        health -= n;
    }

    /**
     * Gets the amount of damage this object can deal.
     */
    public int getDamage()
    {
        return damage;
    }

    /**
     * Jumps.
     */
    public void jump()
    {
        if (getFlightAbility() || getCurrentGround() != null) {
            setGrounded(false);
            setVelocityY(-(baseJump + (Math.abs(getVelocityY()) * velJumpMultiplier)));
            setY(y - 3);
        }
    }


    /**
     * Checks if the object can fly.
     */
    public boolean getFlightAbility()
    {
        return canFly;
    }

    public void setFlightAbility(boolean f)
    {
        canFly = f;
    }
    
    
    
    
    
}

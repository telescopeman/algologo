import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Polygon;

/**
 * Abstract class GameObject - write a description of the class here
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/8/21
 */
public abstract class GameObject
{
    // instance variables - replace the example below with your own
    public boolean isOnGround;
    
    protected double x, y;
    protected ID id;
    protected double velX, velY,seekVelX,seekVelY;
    
    protected double maxSpeedH,maxSpeedV,baseJump,velJumpMultiplier,K;
    protected int health;
    protected int damage;
    
    public final double GRAVITY = 0.4;
    
    public GameObject(double x, double y, ID id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
        
    }
    
    public void process()
    {
        x += velX;
        y += velY;

        if (velX < getSoughtVelocityX())
        {
            velX += K;
        }
        else if (velX > getSoughtVelocityX())
        {
            velX -= K;
        }
        if (velY < getSoughtVelocityY() && !isGrounded())
        {
            velY += GRAVITY;
        }
        
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g,int offsetX, int offsetY);
    
    
    public abstract Shape getBounds();
    
    public abstract boolean intersects(Rectangle rect);
    
    public void takeDamage(int n)
    {
        health -= n;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public void jump()
    {
        setGrounded(false);
        setVelocityY(- (baseJump + getVelocityY() * velJumpMultiplier));
        setSoughtVelocityY(maxSpeedV);
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
    
    public void setID(ID id)
    {
        this.id = id;
    }
    
    public ID getID()
    {
        return id;
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
    
    
    public boolean isGrounded()
    {
        return isOnGround;
    }
    
    public void setGrounded(boolean d)
    {
        isOnGround = d;
        
    }
    
    public void accelerate(double x1, double y1)
    {
        velX += x1;
        velY += y1;
    }
}

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
public abstract class GameObject extends PhysicsObject
{
    // instance variables - replace the example below with your own
    
    
    
    protected ID id;
    
    
    protected double baseJump,velJumpMultiplier;
    
    protected int health,damage;
    
    public GameObject currentSurface;
    
    
    
    public GameObject(double x, double y, ID id)
    {
        super(x,y);
        this.id = id;
        
    }
    
    
    
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
        setVelocityY(- (baseJump + ( Math.abs(getVelocityY()) * velJumpMultiplier)));
        setY(y-3);
        
    }
    
    
    
    
    
    public void setID(ID id)
    {
        this.id = id;
    }
    
    public ID getID()
    {
        return id;
    }
    
    
    
    
    
}

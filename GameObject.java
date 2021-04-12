import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Graphics;

/**
 * Any object in the game.
 */
public abstract class GameObject {

    protected ID id;
    protected double x, y;
    protected int damage = 0;

    public GameObject(double x, double y, ID id)
    {
        setX(x);
        setY(y);
        setID(id);
    }

    public abstract boolean intersects(Rectangle rect);

    public boolean intersects(GameObject obj)
    {
        return intersects(obj.getBounds());
    }

    public boolean intersects(Shape rect)
    {
        return intersects((Rectangle) rect);
    }

    public void tick()
    {
        // do nothing (for now)
    }

    public abstract void render(Graphics g, int offsetX, int offsetY);

    public abstract Shape getBounds();

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

    /**
     * Gets the amount of damage this object can deal.
     */
    public int getDamage()
    {
        return damage;
    }

    public void setDamage(int dmg)
    {
        damage = dmg;
    }

    public void setID(ID id) { this.id = id; }

    public ID getID() { return id; }
}

//import java.awt.*;

import java.awt.Rectangle;
import java.awt.Graphics;

/**
 * Any object in the game.
 */
public abstract class GameObject {

    protected ID id;

    protected double x, y;

    public GameObject(double x, double y, ID id)
    {
        setX(x);
        setY(y);
        setID(id);
    }

    public abstract boolean intersects(Rectangle rect);

    public void tick()
    {
        // do nothing (for now)
    }

    public abstract void render(Graphics g, int offsetX, int offsetY);

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
}

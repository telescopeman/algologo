import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Polygon;
/**
 * Any object in the game.
 * @author RealTutsGML, Caleb Copeland, Joop Eggen [rectangleToPolygon() only]
 */
public abstract class GameObject {

    protected ID id;
    protected double x, y;
    protected int damage = 0;

    public final int INSTANT_KILL = 999;

    protected Shape shape;

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

    public Shape getBounds()
    {
        return shape;
    }

    public void setBounds(Shape sh)
    {
        shape = sh;
        updateForm();
    }

    protected abstract void updateForm();

    public void setBounds(Dimension dim)
    {
        shape = new Rectangle(dim);
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

    /**
     * Gets the amount of damage this object can deal.
     */
    public int getDamage()
    {

        return damage;
    }

    public void setDamage(int dmg)
    {
        if (dmg == -1)
            damage = INSTANT_KILL;
        else
            damage = dmg;
    }

    public void setID(ID id) { this.id = id; }

    public ID getID() { return id; }

    public static Polygon rectangleToPolygon(Rectangle rect)
    {
        final int[] x_points = {rect.x,rect.x + rect.width,rect.x + rect.width, rect.x};
        final int[] y_points = {rect.y,rect.y, rect.y + rect.height, rect.y + rect.height};
        return new Polygon(x_points, y_points, 4);
    }

    public static Polygon adjust(Polygon poly, int offsetX, int offsetY)
    {
        Polygon altered = new Polygon();
        for(int i = 0; i < poly.npoints; i++)
        {
            altered.addPoint(poly.xpoints[i] + offsetX,poly.ypoints[i] + offsetY);
        }
        return altered;
    }

    public boolean hasID(ID i)
    {
        return getID() == i;
    }

    public static Polygon adjust(Rectangle rect, int offsetX, int offsetY)
    {
        final Polygon POLY = rectangleToPolygon(rect);
        return adjust(POLY,offsetX,offsetY);
    }
}

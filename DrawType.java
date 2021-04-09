import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.Rectangle;

/**
 * Enumeration class DrawType - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum DrawType
{
    FILL
    {
        @Override public void draw(Graphics g, Polygon poly) 
        {
            g.fillPolygon(poly);
        }

        @Override public boolean intersects(Polygon poly, Rectangle rect) 
        {
            return poly.intersects(rect);
        }
    },
    OUTLINE_OPEN
    {
        @Override public void draw(Graphics g, Polygon poly)
        {
            g.drawPolyline(poly.xpoints, poly.ypoints, poly.npoints);
        }

        @Override public boolean intersects(Polygon poly, Rectangle rect) 
        {
            return false;
            //new Path2D.Float(poly.npoints).intersects(rect);
        }
    },
    OUTLINE_CLOSED
    {
        @Override public void draw(Graphics g, Polygon poly) 
        {
            g.drawPolygon(poly.xpoints, poly.ypoints, poly.npoints);
            g.drawLine(poly.xpoints[0],poly.ypoints[0],
                poly.xpoints[poly.npoints],poly.ypoints[poly.npoints]);
        }

        @Override public boolean intersects(Polygon poly, Rectangle rect) 
        {
            return new Path2D.Float(poly.npoints).intersects(rect);
        }
    },
    FILL_BELOW
    {
        @Override public void draw(Graphics g, Polygon poly) 
        {

        g.fillPolygon(extendBelow(poly));
        //g.fillRect(poly.xpoints[0],poly.ypoints[0],poly.xpoints[poly.npoints],9999);
    }
    @Override public boolean intersects(Polygon poly, Rectangle rect) 
    {
        return extendBelow(poly).intersects(rect);
    }
};

private DrawType() {
}

// Yes, enums *can* have abstract methods. This code compiles...
public abstract void draw(Graphics g, Polygon poly);

public abstract boolean intersects(Polygon poly, Rectangle rect);

private static Polygon extendBelow(Polygon p)
{
Polygon p2 = new Polygon();
p2.addPoint(p.xpoints[0],9999);
for(int i = 0; i < p.npoints;i++)
{
p2.addPoint(p.xpoints[i],p.ypoints[i]);
}
p2.addPoint(p.xpoints[p.npoints],9999);
return p2;
}

};

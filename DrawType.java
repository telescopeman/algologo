import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Path2D;
import java.awt.Rectangle;

/**
 * Enumeration class DrawType - write a description of the enum class here
 *
 * @author Caleb Copeland
 * @version 4/9/21
 * @since 4/8/21
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
            return false; // DO NOT LEAVE THIS IN
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
    NONE
            {
                @Override public void draw(Graphics g, Polygon poly)
                {
                    // do nothing
                }

                @Override public boolean intersects(Polygon poly, Rectangle rect)
                {
                    return FILL.intersects(poly,rect);
                }
            },
    FILL_BELOW
    {
        @Override public void draw(Graphics g, Polygon poly) 
        {
            g.fillPolygon(extendBelow(poly));
    }
    @Override public boolean intersects(Polygon poly, Rectangle rect) 
    {
        return extendBelow(poly).intersects(rect);
    }
};

    DrawType() {
}

public abstract void draw(Graphics g, Polygon poly);

public abstract boolean intersects(Polygon poly, Rectangle rect);

private static Polygon extendBelow(Polygon p)
{
    final int down = 2000;
    Polygon p2 = new Polygon();
    p2.addPoint(p.xpoints[0],down);
    for(int i = 0; i < p.npoints;i++)
    {
        p2.addPoint(p.xpoints[i],p.ypoints[i]);
    }
    p2.addPoint(p.xpoints[p.npoints],down);
    return p2;
}

}

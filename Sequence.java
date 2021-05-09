import java.awt.*;
import java.awt.Window;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Sequence implements Cloneable {

    private int num_points = 0;

    private ArrayList<Point2D> points;

    public Sequence()
    {
        points = new ArrayList<Point2D>();
    }

    public Sequence(Polygon poly)
    {
        points = new ArrayList<Point2D>();
        int k = poly.npoints;
        for (int i = 0; i < k; i++)
        {
            addPoint(poly.xpoints[i],poly.ypoints[i]);
        }
    }

    public Sequence(Rectangle rect)
    {
        points = new ArrayList<Point2D>();
        addPoint(rect.getMinX(), rect.getMinY());
        addPoint(rect.getMaxX(), rect.getMinY());
        addPoint(rect.getMaxX(), rect.getMaxY());
        addPoint(rect.getMinX(), rect.getMaxY());
    }

    public Sequence(Point2D pt)
    {
        points = new ArrayList<Point2D>();
        addPoint(pt.getX(),pt.getY());
    }


    public int get_num_points()
    {
        return num_points;
    }

    public void addPoint(double x, double y)
    {
        points.add(new Point2D.Double(x,y));
        num_points++;
    }

    public void set(Point2D point, int index)
    {
        points.set(index, point);
    }


    public Point2D get(int i) {
        return points.get(i);
    }

    public Sequence clone() throws CloneNotSupportedException {
        Sequence clone = (Sequence) super.clone();
        return clone;
    }

    public Path2D toPath2D()
    {
        Path2D poly = new Path2D.Double();
        poly.moveTo(points.get(0).getX(),points.get(0).getY());
        for(int i = 0; i < num_points; i++)
        {
            poly.lineTo(points.get(i).getX(), points.get(i).getY());
        }
        return poly;
    }

    public Point2D getRandomPointIn() {
        Rectangle rect = getBounds();
        Random r = new Random();
        Point2D pt = new Point2D.Double();
        for (int i = 0; i < 500; i++) {
            double tx = r.nextDouble() * Window.WIDTH;
            double ty = r.nextDouble() * Window.WIDTH;
            pt =new Point2D.Double(tx,ty);
            if (rect.contains(pt))
            {
                return pt;
            }
        }
        return new Point2D.Double(0,0);
    }

    public Rectangle getBounds()
    {
        return toPath2D().getBounds();
    }


    public Sequence offset(double x, double y) throws CloneNotSupportedException {
        Sequence seq = clone();
        for(int i = 0; i < num_points; i++)
        {
            Point2D cur = seq.get(i);
            seq.set(new Point2D.Double(
                    cur.getX() + x,cur.getY() + y),i);
        }
        return seq;
    }

}

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Sequence implements Cloneable {

    private int num_points = 0;
    private Polygon poly;

    private ArrayList<Point2D> points;

    public Sequence()
    {
        points = new ArrayList<Point2D>();
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



    public Point2D get(int i) {
        return points.get(i);
    }

    public Sequence clone() throws CloneNotSupportedException {
        Sequence clone = (Sequence) super.clone();
        return clone;
    }

    public Path2D toPolygon()
    {
        Path2D poly = new Path2D.Double();
        poly.moveTo(points.get(0).getX(),points.get(0).getY());
        for(int i = 0; i < num_points; i++)
        {
            poly.lineTo(points.get(i).getX(), points.get(i).getY());
        }
        return poly;
    }

}

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Sequence extends Path2D.Double{

    private int num_points = 0;

    public void addPoint(double x, double y)
    {
        super.lineTo(x,y);
        num_points++;
    }

    public Polygon toPolygon()
    {
        Polygon poly = new Polygon();
        for (int i = 0; i < num_points; i++)
        {
            poly.addPoint(get);
        }
    }

}

import java.util.function.Function;
import java.awt.Dimension;
import java.awt.Polygon;

/**
 * Write a description of class Shape here.
 *
 * @author Caleb Copeland, Ozymandias
 * @version 4/7/21
 */
public class AlgoShape extends Polygon
{
    // instance variables - replace the example below with your own
    //private int x;

    /**
     * Constructor for objects of class Shape
     */
    public AlgoShape(Dimension bounds, Term func, int xpos, int ypos)
    {
        int lowBoundX = bounds.width / 2;
        int highBoundX = - bounds.width / 2;
        System.out.println(lowBoundX);
        for (double x = lowBoundX; x <= highBoundX; x = x + 1) {
            addPoint((int)x + xpos, ypos - (int)func.get(x));

            System.out.println("pt");
        }
        //g2.drawPolyline(p.xpoints, p.ypoints, p.npoints);
    }

    
}

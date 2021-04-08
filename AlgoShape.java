import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
/**
 * Write a description of class Shape here.
 *
 * @author Caleb Copeland, Ozymandias from StackOverflow, Joop Eggen [RectangleToPolygon() method only]
 * @version 4/7/21
 */
public class AlgoShape extends Polygon
{
    // instance variables - replace the example below with your own
    //private int x;
    //private Polygon shape = new Polygon();
    private Term term;
    public Color color;
    public boolean isFull;
    public boolean isClosed;
    
    /**
     * Constructor for objects of class Shape
     */
    public AlgoShape(Term func, Style style)
    {
        setStyle(style);
        //Polygon p = new Polygon();
        final int lowBoundX = - style.bounds.width;
        final int highBoundX = style.bounds.width;

        final int lowBoundY = - style.bounds.height;
        final int highBoundY = style.bounds.height;

        //System.out.println(lowBoundY);
        for (double x = lowBoundX; x <= highBoundX; x = x + 1) {
            int myX = (int) x + highBoundX;
            int y = (int) func.get(x);
            int myY =  highBoundY - (int) y;
            System.out.println("Go " + myX + ", " + myY);
            // if (y < highBoundY * 2 && y > lowBoundY  * 2)
            // {
                addPoint(myX,myY);
                System.out.println("g");
            //}
        }
        
    }
    
    /**
     * Constructor for objects of class Shape
     */
    public AlgoShape(Rectangle r, Style style)
    {
        setStyle(style);
        Polygon poly = rectangleToPolygon(r);
        for (int i = 0; i < poly.npoints; i++){
            addPoint(poly.xpoints[i],poly.ypoints[i]);
        }
        
    }
    
    private void setStyle(Style style)
    {
        color = style.color;
        isFull = style.filled;
        isClosed = style.isClosed;
        
    }
   

    public static Polygon rectangleToPolygon(Rectangle rect) {
        int[] xpoints = {rect.x, rect.x + rect.width, rect.x + rect.width, rect.x};
        int[] ypoints = {rect.y, rect.y, rect.y + rect.height, rect.y + rect.height};
        return new Polygon(xpoints, ypoints, 4); 
    }
}
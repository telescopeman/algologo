import java.util.function.Function;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
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
    //private Polygon shape = new Polygon();
    private Term term;
    public Color color;
    
    /**
     * Constructor for objects of class Shape
     */
    public AlgoShape(Term func, Style style)
    {
        color = style.color;
        //Polygon p = new Polygon();
        final int lowBoundX = - style.bounds.width / 2;
        final int highBoundX = style.bounds.width / 2;

        final int lowBoundY = - style.bounds.height / 2;
        final int highBoundY = style.bounds.height / 2;

        //System.out.println(lowBoundY);
        for (double x = lowBoundX; x <= highBoundX; x = x + 1) {
            int myX = (int) x + highBoundX * 2;
            int y = (int) func.get(x);
            int myY =  highBoundY*2 - (int) y;
            //System.out.println("Go " + myX + ", " + myY);
            if (y < highBoundY * 2 && y > lowBoundY  * 2)
            {
                addPoint(myX,myY);
                //System.out.println("g");
            }
        }
        
    }
    
    
   

}
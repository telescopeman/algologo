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
    public AlgoShape(Term func, Color col, Dimension bounds)
    {
        color = col;
        //Polygon p = new Polygon();
        final int lowBoundX = - bounds.width / 2;
        final int highBoundX = bounds.width / 2;

        final int lowBoundY = - bounds.height / 2;
        final int highBoundY = bounds.height / 2;

        //System.out.println(lowBoundY);
        for (double x = lowBoundX; x <= highBoundX; x = x + 1) {
            int myX = (int) x + highBoundX;
            int y = (int) func.get(x);
            int myY = 2 * highBoundY - (int) y;
            System.out.println("Go " + myX + ", " + myY);
            if (y < highBoundY && y > lowBoundY)
            {
                addPoint((int)myX,myY);
                System.out.println("g");
            }
        }
        
    }
    
    
   

}
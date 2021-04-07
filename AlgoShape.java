import java.util.function.Function;
import java.awt.Dimension;
import java.awt.Polygon;

/**
 * Write a description of class Shape here.
 *
 * @author Caleb Copeland, Ozymandias
 * @version (a version number or a date)
 */
public class AlgoShape extends Polygon
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Shape
     */
    public AlgoShape(Dimension bounds, Function func)
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}

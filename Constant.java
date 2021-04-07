
/**
 * Write a description of class Constant here.
 *
 * @author Caleb Copeland
 * @version (a version number or a date)
 */
public class Constant implements Term
{
    // instance variables - replace the example below with your own
    private double myVal;

    /**
     * Constructor for objects of class Constant
     */
    public Constant(double i)
    {
        // initialise instance variables
        myVal = i;
    }

    
    public double get(double x)
    {
        // put your code here
        return myVal;
    }
}

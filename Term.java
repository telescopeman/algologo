
/**
 * Write a description of class Term here.
 *
 * @author Caleb Copeland
 * @version 4/7/21
 */
public class Term
{
    // instance variables - replace the example below with your own
    private Double myVal;

    /**
     * Constructor for objects of class Constant
     */
    public Term(Double i)
    {
        // initialise instance variables
        myVal = i;
    }

    
    public double get(double x)
    {
        if (myVal == null)
        {
            return x;
        }
        // put your code here
        return myVal;
    }
}
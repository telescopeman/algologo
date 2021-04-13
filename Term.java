
/**
 * Write a description of class Term here.
 *
 * @author Caleb Copeland
 * @version 4/12/21
 */
public class Term
{
    private final Double myVal;

    /**
     * Constructor for objects of class Constant
     */
    public Term(Double i)
    {
        myVal = i;
    }
    
    

    public String toString()
    {
        if (myVal == null)
        {
            return "x";
        }
        else
        {
            return String.valueOf(myVal);
        }
    }
    
    public double get(double x)
    {
        if (myVal == null)
        {
            return x;
        }
        return myVal;
    }
}
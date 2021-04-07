/**
 * Write a description of class GraphingFunction here.
 *
 * @author Caleb Copeland
 * @version 4/7/21
 */
public class MathExpression implements Term
{
    private Operator myOperator;
    private Term term1,term2;
    

    /**
     * Constructor for objects of class GraphingFunction
     */
    public MathExpression(Term t1, Operator op, Term t2)
    {
        myOperator = op;
        term1=t1;
        term2=t2;
        //x = 0;
    }

    public double get(double x)
    {
        return myOperator.apply(term1.get(x),term2.get(x));
    }
}

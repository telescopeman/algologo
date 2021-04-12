/**
 * Represents a mathematical expression.
 *
 * @author Caleb Copeland
 * @version 4/8/21
 */
public class MathExpression extends Term
{
    private final Operator myOperator;
    private final Term term1;
    private final Term term2;
    
    /**
     * Constructor for objects of class GraphingFunction
     */
    public MathExpression(Term t1, Operator op, Term t2)
    {
        super(null);
        myOperator = op;
        term1=t1;
        term2=t2;
    }
    
    /**
     * Constructor for objects of class GraphingFunction
     */
    public MathExpression(Double t1, Operator op, Double t2)
    {
        super(null);
        myOperator = op;
        term1= new Term(t1);
        term2= new Term(t2);
    }
    
    /**
     * Constructor for objects of class GraphingFunction
     */
    public MathExpression(Double t1, Operator op, Term t2)
    {
        super(null);
        myOperator = op;
        term1= new Term(t1);
        term2= t2;
    }
    
    /**
     * Constructor for objects of class GraphingFunction
     */
    public MathExpression(Term t1, Operator op, Double t2)
    {
        super(1.0);
        myOperator = op;
        term1= t1;
        term2= new Term(t2);
    }
    
    
    
    public String toString()
    {
        return "(" + term1.toString() + " " + myOperator.toString() + " " +  term2.toString() + ")";
    }

    public double get(double x)
    {
        return myOperator.apply(term1.get(x),term2.get(x));
    }
    
    
}

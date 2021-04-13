/**
 * Represents a mathematical expression.
 *
 * @author Caleb Copeland
 * @version 4/12/21
 * @since 4/8/21
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


    public static Term parse(String str)
    {
        Term t1;
        Operator op;
        Term t2;
        int paren1 = str.indexOf("(");
        int paren2 = str.indexOf(")");
        if (paren1 > 0) //do not nest parentheses or this breaks, hard
        {
            t1 = parse(str.substring(paren1+1,paren2));
            op = Operator.get(str.substring(paren2+1,paren2+2));
            t2 = parse(str.substring(paren2 + 1));
        }
        else
        {
            int operator_index = -1;
            op = Operator.ADD;
            for(Operator o : Operator.values()) {
                int n = str.indexOf(o.toString());
                if (n > operator_index)
                {
                    operator_index = n;
                    op = o;
                }
            }
            if(str.charAt(0) == 'x') {
                t1 = new Term(null);
            }
            else {
                t1 = new Term(Double.valueOf(str.substring(0,operator_index)));
            }

            if(str.charAt(operator_index+1) == 'x') {
                t2 = new Term(null);
            }
            else {
                t2 = new Term(Double.valueOf(str.substring(operator_index+1)));
            }

        }
        return new MathExpression(t1,op,t2);
    }

}

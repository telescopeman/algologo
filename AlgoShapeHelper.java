import java.awt.Dimension;

/**
 * @author Jon Skeet, Caleb Copeland
 */
public enum AlgoShapeHelper
{
    LINEAR_SLOPE() 
    {
        @Override public AlgoShape get(double[] factors,int x, int y){
            DataHelper.checkLength(factors,1);
            Term myTerm3 = new MathExpression(new Term(null),Operator.MULTIPLY,new Term(0.1));
            return (new AlgoShape(myTerm3,new Style(DrawType.FILL_BELOW),x,y));
        }
        @Override public AlgoShape get(double[] factors, Dimension d, int x, int y){
            DataHelper.checkLength(factors,1);
            Term myTerm3 = new MathExpression(new Term(null),Operator.MULTIPLY,new Term(0.1));
            return (new AlgoShape(myTerm3,new Style(DrawType.FILL_BELOW,d),x,y));
        }
    },
    PARABOLA() 
    {
        @Override
        public AlgoShape get(double[] factors, int x, int y) {
            DataHelper.checkLength(factors,3);
            Term xSquared = new MathExpression(new Term(null),Operator.POWER,new Term(2.0)); //
            Term firstTerm = new MathExpression(xSquared,Operator.MULTIPLY,new Term(factors[0])); // "a"
            Term secondTerm = new MathExpression(new Term(null),Operator.MULTIPLY,new Term(factors[1]));
            Term compound = new MathExpression(firstTerm,Operator.ADD,secondTerm);
            Term fin = new MathExpression(compound,Operator.ADD,factors[2]);
            
            return new AlgoShape(fin,new Style(DrawType.FILL),x,y);
        }
        @Override
        public AlgoShape get(double[] factors, Dimension d,int x, int y) {
            DataHelper.checkLength(factors,3);
            Term xSquared = new MathExpression(new Term(null),Operator.POWER,new Term(2.0)); //
            Term firstTerm = new MathExpression(xSquared,Operator.MULTIPLY,new Term(factors[0])); // "a"
            Term secondTerm = new MathExpression(new Term(null),Operator.MULTIPLY,new Term(factors[1]));
            Term compound = new MathExpression(firstTerm,Operator.ADD,secondTerm);
            Term fin = new MathExpression(compound,Operator.ADD,factors[2]);
            
            return new AlgoShape(fin,new Style(DrawType.FILL,d),x,y);
        }
    };


// Yes, enums can have abstract methods. This code compiles...
public abstract AlgoShape get(double[] factors, int x, int y);

public abstract AlgoShape get(double[] factors, Dimension d, int x, int y);

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
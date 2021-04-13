import java.awt.Dimension;

/**
 * @author Caleb Copeland
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


public abstract AlgoShape get(double[] factors, int x, int y);

public abstract AlgoShape get(double[] factors, Dimension d, int x, int y);






}
//import java.Exception;
//import java.io.*;
/**
 * @author Jon Skeet, Caleb Copeland
 */

public enum AlgoShapeHelper
{
    LINEAR_SLOPE() 
    {
        @Override public AlgoShape get(double[] factors){
            checkLength(factors,1);
            Term myTerm3 = new MathExpression(new Term(null),Operator.MULTIPLY,new Term(0.1));
            return (new AlgoShape(myTerm3,new Style(DrawType.FILL_BELOW),-Game.WIDTH/2,-Game.HEIGHT/2));
        }
    },
    PARABOLA() 
    {
        @Override
        public AlgoShape get(double[] factors) {
            checkLength(factors,3);
            Term xSquared = new MathExpression(new Term(null),Operator.POWER,new Term(2.0)); //
            Term firstTerm = new MathExpression(xSquared,Operator.MULTIPLY,new Term(factors[0])); // "a"
            Term secondTerm = new MathExpression(new Term(null),Operator.MULTIPLY,new Term(factors[1]));
            Term compound = new MathExpression(firstTerm,Operator.ADD,secondTerm);
            Term fin = new MathExpression(compound,Operator.ADD,factors[2]);
            
            return new AlgoShape(fin,new Style(DrawType.FILL),-Game.WIDTH/2,Game.HEIGHT/2);
        }
    };


// Yes, enums *can* have abstract methods. This code compiles...
public abstract AlgoShape get(double[] factors);

public void checkLength(double[] arr, int i)
{
    if (arr.length < i)
        {
            throw new IllegalArgumentException("Must have length of " + i);
        }
}

};
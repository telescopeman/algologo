import java.awt.Dimension;
import java.awt.Color;

/**
 * Write a description of class Defaults here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Defaults
{
    // instance variables - replace the example below with your own
    static final Dimension WINDOWSIZE = new Dimension(800,800);

    static final Style MYSTYLE = new Style(Color.black,new Dimension(400,400),true,true);
    static final MathExpression hill = new MathExpression(-0.5,Operator.MULTIPLY,(new MathExpression((Double) null,Operator.POWER,1.5)));
    // /**
     // * Constructor for objects of class Defaults
     // */
    // public Defaults()
    // {
        // // initialise instance variables
        // x = 0;
    // }

    // /**
     // * An example of a method - replace this comment with your own
     // *
     // * @param  y  a sample parameter for a method
     // * @return    the sum of x and y
     // */
    // public int sampleMethod(int y)
    // {
        // // put your code here
        // return x + y;
    // }
}

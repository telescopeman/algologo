import javax.swing.JComponent;
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
/**
 * Write a description of class World here.
 *
 * @author (your name)
 * @version 4/7/21
 */
public class World extends JComponent
{
    // instance variables - replace the example below with your own
    private int w;
    private int h;

    /**
     * Constructor for objects of class World
     */
    public World()
    {
        // initialise instance variables
        //x = 0;
    }

    private void drawAxes(Graphics g)
    {
        drawAxes((Graphics2D) g);
    }
  
    private void drawAxes(Graphics2D g)
    {
        updateDim();
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.black);
        g.drawLine(0,h,w*2,h);
        g.drawLine(w,0,w,h*2); 
        //g.drawString("0", w - 7, h + 13);
        
    }
    
    /**
     * Draws EVERYTHING to the screen
     */
    public void paintComponent(Graphics g)
    {   
        //w is x, and h is y (as in x/y values in a graph)
        updateDim();
        
        drawAxes(g); //maybe removed in later versions?

        Term testTerm = new MathExpression(new Variable(),Operator.POWER,new Constant(2));
        Term testTerm2 = new MathExpression(new Variable(),Operator.ADDITION,new Constant(2));
        AlgoShape alg = new AlgoShape(testTerm,Color.red,new Dimension(w,h));
        drawPoly((Graphics2D) g,alg);
        AlgoShape alg2 = new AlgoShape(testTerm2,Color.red,new Dimension(w,h));
        drawPoly((Graphics2D) g,alg2);
        

    }
    
    private void drawPoly(Graphics2D g, AlgoShape p)
    {
        g.setStroke(new BasicStroke(4));
        g.setColor(p.color);
        //g.drawLine(0,400,700,300);
        g.fillPolygon(p.xpoints, p.ypoints, p.npoints);
    }
    
    private AlgoShape constructShape(Term t)
    {
        return new AlgoShape(t,Color.red,this.getSize());
        
    }
    
    private void updateDim()
    {
        w = this.getWidth()/2;
        h = this.getHeight()/2;
    }
}

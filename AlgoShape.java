import java.util.function.Function;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
/**
 * Write a description of class Shape here.
 *
 * @author Caleb Copeland, Ozymandias
 * @version 4/7/21
 */
public class AlgoShape extends JComponent
{
    // instance variables - replace the example below with your own
    //private int x;
    private Polygon shape = new Polygon();

    
    /**
     * Constructor for objects of class Shape
     */
    public Polygon constructShape(Term func, int xpos, int ypos,Dimension bounds)
    {
        Polygon p = new Polygon();
        final int lowBoundX = - bounds.width / 2;
        final int highBoundX = bounds.width / 2;

        final int lowBoundY = - bounds.height / 2;
        final int highBoundY = bounds.height / 2;

        //System.out.println(lowBoundY);
        for (double x = lowBoundX; x <= highBoundX; x = x + 1) {
            int myX = (int) x + (int) xpos;
            int y = (int) func.get(x);
            int myY = ypos - y;
            //System.out.println("Go " + x + " in bounds " + lowBoundX + " to " + highBoundX + ".");
            if (y < highBoundY && y > lowBoundY)
            {
                p.addPoint(myX,myY);
                //System.out.println("g");
            }
        }
        return p;
    }
    
    
    private Term term;
    private Color color;

    /**
     * Constructor for objects of class ShapeCtrl
     */
    public AlgoShape(Term s, Color c)
    {
        term = s;
        color = c;
    }

    public void paintComponent(Graphics g)
    {   

        final int w = this.getWidth()/2;
        final int h = this.getHeight()/2;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g2.setColor(color);
        Dimension dim = new Dimension(200,200);
        
        Polygon p = constructShape(term,w,h,dim);
        g2.drawPolyline(p.xpoints, p.ypoints, p.npoints);
    }
}

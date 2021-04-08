import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Write a description of class World here.
 *
 * @author Caleb Copeland, "Ozymandias"
 * @version 4/8/21
 * @since 4/7/21
 */
public class World extends JComponent
{
    // instance variables - replace the example below with your own
    private int w;
    private int h;

    Player player;
    AlgoShape alg2;
    
    private boolean started;
    /**
     * Constructor for objects of class World
     */
    public World()
    {
        started = false;
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
        g.drawString("0", w - 7, h + 13);

    }

    public void createObjs(Graphics g)
    {
        if (started)
        {
            return;
        }
        started = true;
        updateDim();
        player = new Player(w,h);
        MathExpression t = new MathExpression((Double) null,Operator.POWER,2.0);
        MathExpression t2 = new MathExpression(-0.005,Operator.MULTIPLY,t);
        alg2 = constructShape(t2);
    }
    
    /**
     * Draws EVERYTHING to the screen
     */
    public void paintComponent(Graphics g)
    {   
        //w is x, and h is y (as in x/y values in a graph)
        createObjs(g);
        updateDim();
        
        

        


    }
    
    private void render(Graphics g)
    {
        drawAxes(g); //maybe removed in later versions?
        Graphics2D g2 = (Graphics2D) g;
        
        drawPoly(g2,alg2,true);

        //drawPoly(g2,player.getShape(),true);
    }

    private void drawPoly(Graphics2D g, AlgoShape p,boolean isFull)
    {
        //System.out.println("add");
        g.setStroke(new BasicStroke(3));
        g.setColor(p.color);
        if (isFull)
        {
            g.fillPolygon(p);
        }
        else
        {
            g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
        }
    }

    private AlgoShape constructShape(Term t)
    {
        return new AlgoShape(t,new Style(Color.black, new Dimension(w,h), true, true));

    }

    private void updateDim()
    {
        w = this.getWidth()/2;
        h = this.getHeight()/2;
        //System.out.println(w + "/" + h);
    }
}

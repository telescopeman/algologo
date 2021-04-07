import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @author Ozymandias, Caleb Copeland
 */
public class DrawingStuff extends JComponent {
    
    public void paintComponent(Graphics g)
    {   
        //w is x, and h is y (as in x/y values in a graph)
        final int w = this.getWidth()/2;
        final int h = this.getHeight()/2;
        //drawAxes(g);
        
        Graphics2D g1 = (Graphics2D) g;
        g1.setStroke(new BasicStroke(2));
        g1.setColor(Color.black);
        g1.drawLine(0,h,w*2,h);
        g1.drawLine(w,0,w,h*2); 
        g1.drawString("0", w - 7, h + 13);
        
        

        
        
        //System.out.println("pt");

    }
    
    
    public void drawAxes(Graphics g)
    {
        
        
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setTitle("Graphs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  
        DrawingStuff draw = new DrawingStuff();
        frame.add(draw);
        
        Term testTerm = new MathExpression(new Variable(),Operator.POWER,new Constant(2));
        frame.add(new AlgoShape(testTerm,Color.red));
        
        frame.setVisible(true);
        
    }

}
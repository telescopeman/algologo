import java.awt.*;
import javax.swing.JComponent;

import javax.swing.JFrame;
/**
 * @author Ozymandias
 */
public class DrawingStuff extends JComponent {
    public void paintComponent(Graphics g)
    {   
        //w is x, and h is y (as in x/y values in a graph)
        int w = this.getWidth()/2;
        int h = this.getHeight()/2;

        Graphics2D g1 = (Graphics2D) g;
        g1.setStroke(new BasicStroke(2));
        g1.setColor(Color.black);
        g1.drawLine(0,h,w*2,h);
        g1.drawLine(w,0,w,h*2); 
        g1.drawString("0", w - 7, h + 13);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.red);
        //line1

        Dimension dim = new Dimension(-100,100);
        MathExpression testTerm = new MathExpression(new Variable(),Operator.POWER,new Constant(2));
        AlgoShape p = new AlgoShape(dim,testTerm,w,h);
        g2.drawPolyline(p.xpoints, p.ypoints, p.npoints);
        //System.out.println("pt");

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setTitle("Graphs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  
        DrawingStuff draw = new DrawingStuff();
        frame.add(draw);
        frame.setVisible(true);
    }

}
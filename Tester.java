import javax.swing.JFrame;

/**
 * Write a description of class Tester here.
 *
 * @author Ozymandias
 * @version (a version number or a date)
 */
public class Tester
{
    // instance variables - replace the example below with your own
    public static void main(String[] args) {
        EasyFrame frame = new EasyFrame("AlgoLogo",EasyFrame.MAIN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  
        DrawingStuff draw = new DrawingStuff();
        //draw.paintComponent();
        frame.add(draw);
        frame.setVisible(true);
    }
    
    public static void testTerm() {
        //Term ex = new MathExpression()
    }
}

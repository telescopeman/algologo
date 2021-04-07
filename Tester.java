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
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setTitle("Graphs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  
        DrawingStuff draw = new DrawingStuff();
        frame.add(draw);
        frame.setVisible(true);
    }
}

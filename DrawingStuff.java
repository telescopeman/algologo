import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @author Ozymandias, Caleb Copeland
 */
public class DrawingStuff extends JComponent {
    
    
    
    
    

    public static void main(String[] args) {
        EasyFrame frame = new EasyFrame("Algologo");
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  
        EasyPanel gameScreen = new EasyPanel(800,600);
        //frame.add(gameScreen);
        World w = new World();
        frame.add(w);
        
        
        frame.setVisible(true);
        
    }

}
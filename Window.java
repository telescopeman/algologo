import java.awt.Canvas;
//import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Canvas;
/**
 * Write a description of class Window here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Window extends Canvas
{

    private static final long serialVersionUID = 185258291L;
    
    public Window(int width, int height, String title, Game game)
    {
        EasyFrame frame = new EasyFrame(title,new Dimension(width,height));
        //frame.setSize();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}

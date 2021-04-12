import java.awt.Dimension;
import java.awt.Canvas;
import java.io.Serial;

/**
 * The game window.
 *
 * @author RealTutsGML
 * @since 4/7/21
 */
public class Window extends Canvas
{

    @Serial
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

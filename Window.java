
import javax.swing.JFrame;
import java.awt.*;
import java.io.Serial;

/**
 * The game window.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/27/21
 * @since 4/7/21
 */
public class Window extends Canvas
{

    public static final String TITLE = "Algologo";
    private static JFrame frame;
    private static int minX, maxX, minY, maxY;

    public static int getMaxX() {
        return maxX;
    }

    public static int getMaxY() {
        return maxY;
    }

    public static int getMinX() {
        return minX;
    }
    public static int getMinY()
    {
        return minY;
    }

    @Serial
    private static final long serialVersionUID = 185258291L;
    public Window(int minX, int maxX, int minY, int maxY, Game game)
    {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        frame = new JFrame(TITLE);
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }




    private static void setFrame(int x1,int y1,int x2,int y2)
    {
        minX = x1;
        minY = y1;
        maxX = x2;
        maxY = y2;
    }

    public static Dimension getDimension()
    {
        return frame.getSize();
    }


}

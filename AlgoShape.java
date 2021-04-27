import java.awt.*;

/**
 * @author Caleb Copeland, "Ozymandias"
 * @version 4/12/21
 * @since 4/7/21
 */
public class AlgoShape extends GameObject
{

    public AlgoShape(Term func, RenderJob[] styles)
    {
        super(styles);
        for (int x = Window.getMinX(); x <= Window.getMaxX(); x++) {
            int y = (int) func.get(x);
            if (y < Window.getMaxY() && y > Window.getMinY())
            {
                myShape.addPoint(x, - y);
            }
        }
    }

}
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * @author Caleb Copeland, StackOverflow User "Ozymandias"
 * @version 4/27/21
 * @since 4/7/21
 */
public class AlgoShape extends GameObject
{

    private double scale = 0.1;
    public AlgoShape(Term func, RenderJob[] styles)
    {
        super(styles);
        renderFunction(func);
    }

    public AlgoShape(Term func)
    {
        super(new RenderJob[]{RenderJob.FILL_SHAPE,RenderJob.FILL_BELOW});
        renderFunction(func);
    }

    private void renderFunction(Term func)
    {
        color = Color.green;
        myShape = new Sequence();

        for (double x = Window.getMinX(); x <= Window.getMaxX(); x+= scale) {
            double y = func.get(x);
            System.out.println(y);
            if (true)
            {
                (myShape).addPoint(x, - y);
            }
        }
    }
}
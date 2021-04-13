import java.awt.Polygon;

/**
 * @author Caleb Copeland, "Ozymandias"
 * @version 4/12/21
 * @since 4/7/21
 */
public class AlgoShape extends ShapeObject
{
    public AlgoShape(Term func, Style style, int xpos, int ypos)
    {
        super(new Polygon(),style,xpos,xpos);
        final int highBoundX = style.bounds.width;
        final int highBoundY = style.bounds.height;

        for (int x = 0; x <= highBoundX; x++) {
            int y = (int) func.get(x);
            if (y < highBoundY)
            {
                ( (Polygon) shape ).addPoint(x + xpos, ypos - y);
            }
        }

    }
}
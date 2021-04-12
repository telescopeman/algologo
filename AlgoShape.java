import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Caleb Copeland, "Ozymandias"
 * @version 4/12/21
 * @since 4/7/21
 */
public class AlgoShape extends GameObject
{
    private final Style style;

    public AlgoShape(Term func, Style style,int xpos, int ypos)
    {
        super(xpos,ypos,ID.Platform);
        this.style = style;
        shape = new Polygon();
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

    public void render(Graphics g, int offsetX, int offsetY)
    {
        g.setColor(style.color);  
        ((Graphics2D) g).setStroke(style.getStroke());
        
        style.drawer.draw(g,adjust((Polygon) shape,offsetX,offsetY));
    }





    public boolean intersects(Rectangle rect)
    {
        return style.drawer.intersects((Polygon) shape,rect);
    }




}
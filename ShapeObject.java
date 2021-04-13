import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Polygon;

public class ShapeObject extends GameObject{

    private final Style style;

    public ShapeObject(Shape shape, Style style, int xpos, int ypos)
    {
        super(xpos,ypos,ID.Platform);
        this.shape = shape;
        this.style = style;
    }

    public ShapeObject(Shape shape, Style style, int xpos, int ypos, ID id)
    {
        super(xpos,ypos,id);
        this.shape = shape;
        this.style = style;
    }

    public void render(Graphics g, int offsetX, int offsetY)
    {
        g.setColor(style.color);
        ((Graphics2D) g).setStroke(style.getStroke());
        style.drawer.draw(g,adjust((Polygon) shape,offsetX,offsetY));
    }

    public boolean intersects(Rectangle rect)
    {
        return style.drawer.intersects( (Polygon) shape,rect);
    }

}

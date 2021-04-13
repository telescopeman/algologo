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
        setColor(style.color);
    }

    public ShapeObject(Shape shape, int xpos, int ypos)
    {
        super(xpos,ypos,ID.Platform);
        this.shape = shape;
        this.style = new Style();
        setColor(style.color);
    }

    public ShapeObject(Shape shape)
    {
        super(0,0,ID.Platform);
        this.shape = shape;
        this.style = new Style();
        setColor(style.color);
    }

    public ShapeObject(Shape shape, Style style, int xpos, int ypos, ID id)
    {
        super(xpos,ypos,id);
        this.shape = shape;
        this.style = style;
        applyStyle(style);
    }

    private void applyStyle(Style s)
    {
        setColor(s.color);
        setStroke(s.thickness);
    }

    public void render(Graphics g, int offsetX, int offsetY)
    {
        style.drawer.draw(g,adjust((Polygon) shape,offsetX,offsetY));
    }

    public void updateForm()
    {
        // do nothing
    }



    public boolean intersects(Rectangle rect)
    {
        return style.drawer.intersects( (Polygon) shape,rect);
    }

}

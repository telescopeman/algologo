import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class ShapeObject extends GameObject{

    private final Style style;

    public ShapeObject(Shape shape, Style style, int xpos, int ypos)
    {
        super(xpos,ypos,ID.Platform);
        setBounds(shape);
        this.style = style;
        setColor(style.color);
    }

    public ShapeObject(Shape shape, int xpos, int ypos)
    {
        super(xpos,ypos,ID.Platform);
        setBounds(shape);
        this.style = new Style();
        setColor(style.color);
    }

    public ShapeObject(Shape shape)
    {
        super(0,0,ID.Platform);
        setBounds(shape);
        this.style = new Style();
        setColor(style.color);
    }

    public ShapeObject(Shape shape, Style style, int xpos, int ypos, ID id)
    {
        super(xpos,ypos,id);
        setBounds(shape);
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
        applyColor(g);
        applyStroke(g);
        style.drawer.draw(g,modify(offsetX,offsetY));
    }

    public void updateForm()
    {
        // do nothing
    }


    private Polygon modify(int offsetX, int offsetY)
    {
        return adjust((Polygon) shape,
                offsetX + (int) getX(),offsetY + (int) getY());
    }

    private Polygon modify()
    {
        return modify(0,0);
    }

    public boolean intersects(Shape rect)
    {
        if (rect instanceof Rectangle) {
            return style.drawer.intersects( modify(), (Rectangle) rect);
        }
        else if (rect instanceof Ellipse2D.Double)
        {
            return getBounds().contains(new Point2D.Double(
                    ((Ellipse2D) rect).getX(), ((Ellipse2D) rect).getY()));
        }
        else
        {
            throw new IllegalStateException("Unhandled shape type!");
        }

    }

    @Override
    public void tick() {
        //do nothing
    }

}

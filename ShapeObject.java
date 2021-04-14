import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;

public class ShapeObject extends GameObject{

    private final Style style;

    public ShapeObject(Shape shape, Style style, int xpos, int ypos)
    {
        super(xpos,ypos,ID.Platform);
        this.style = style;
        setBounds(process(shape));
        setColor(style.color);
    }

    public ShapeObject(Shape shape, int xpos, int ypos)
    {
        super(xpos,ypos,ID.Platform);
        setBounds(process(shape));
        this.style = new Style();
        setColor(style.color);
    }

    public ShapeObject(Shape shape)
    {
        super(0,0,ID.Platform);
        this.style = new Style();
        setBounds(process(shape));

        setColor(style.color);
    }

    public ShapeObject(Shape shape, Style style, int xpos, int ypos, ID id)
    {
        super(xpos,ypos,id);
        this.style = style;
        setBounds(process(shape));
        applyStyle(style);
    }

    private void applyStyle(Style s)
    {
        setColor(s.color);
        setStroke(s.thickness);
    }



    public void updateForm()
    {
        shape = adjust((Polygon) shape,
                 (int) getX(), (int) getY());
    }







    private Polygon process(Shape s)
    {
        switch (style.drawer)
        {
            case FILL:
                return (Polygon) s;

            case FILL_BELOW:
                return extendBelow((Polygon) s);

            case OUTLINE_CLOSED:
                Polygon p = (Polygon) s;
                p.addPoint(p.xpoints[0],p.ypoints[0]);
                return p;

            default:
                return new Polygon();
        }
    }

    private static Polygon extendBelow(Polygon p)
    {
        final int down = 2000;
        Polygon p2 = new Polygon();
        p2.addPoint(p.xpoints[0],down);
        for(int i = 0; i < p.npoints;i++)
        {
            p2.addPoint(p.xpoints[i],p.ypoints[i]);
        }
        p2.addPoint(p.xpoints[p.npoints],down);
        return p2;
    }


    @Override
    public void tick() {
        //do nothing
    }

}

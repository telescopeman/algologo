import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class GraphicsHelper {
    private Graphics graphics;

    public GraphicsHelper(Graphics g)
    {
        graphics = g;
    }

    public Graphics getGraphics()
    {
        return graphics;
    }

    public void drawRect(int x, int y, int width, int height) {
        graphics.drawRect(adjustX(x), adjustY(y),
                adjustX(width), adjustY(height));
    }

    private int adjustX(double x)
    {
        int adjusted = (int) (
                Window.getDimension().width / 2
                        + (x * Window.getDimension().width)
                        / Math.abs(Window.getMaxX() - Window.getMinX()));
        //Game.printDebug(x + " --> " + adjusted);
        return adjusted;
    }

    private int adjustY(double y)
    {
        int adjusted = (int) (
                Window.getDimension().height / 2
                        + (y * Window.getDimension().height)
                        / Math.abs(Window.getMaxY() - Window.getMinY()));
        //Game.printDebug(y + " --> " + adjusted);
        return adjusted;
    }

    private Polygon adjust(Polygon myShape)
    {
        Polygon poly = new Polygon();
        for (int i = 0; i < myShape.npoints; i++)
        {
            poly.addPoint(
                    adjustX(myShape.xpoints[i]),
                    adjustY(myShape.ypoints[i])
            );
        }
        return poly;
    }

    public void drawPolygon(Polygon myShape) {
        graphics.drawPolygon(adjust(myShape));
    }


    public void drawPolygon(Path2D myShape) {
        //myShape.getPathIterator()
        graphics.drawPolygon(adjust((Polygon) myShape.createTransformedShape(new AffineTransform())));
    }

    public void fillPolygon(Path2D myShape) {
        Polygon poly = new Polygon();
        graphics.fillPolygon(adjust((Polygon) myShape.createTransformedShape(new AffineTransform())));
    }

    public void fillRect(int x, int y, int width, int height) {
        graphics.fillRect(adjustX(x), adjustY(y),
                adjustX(width), adjustY(height));
    }

    public void setColor(Color c) {
        graphics.setColor(c);
    }

    public void setStroke(BasicStroke stroke)
    {
        ((Graphics2D) graphics).setStroke(stroke);
    }

    public void setStroke(float size)
    {
        ((Graphics2D) graphics).setStroke(new BasicStroke(size));
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        graphics.drawLine(adjustX(x1),adjustY(y1),adjustX(x2),adjustY(y2));
    }

    public void drawString(String str, int x, int y)
    {
        graphics.drawString(str, adjustX(x),adjustY(y));
    }

    public void drawStringIgnoreX(String str, int x, int y)
    {
        graphics.drawString(str, x, adjustY(y));
    }

    public void drawStringIgnoreY(String str, int x, int y)
    {
        graphics.drawString(str, adjustX(x),y);
    }
}

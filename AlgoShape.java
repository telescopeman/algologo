import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.*;
/**
 * Write a description of class Shape here.
 *
 * @author Caleb Copeland, Ozymandias from StackOverflow, Joop Eggen [RectangleToPolygon() method only]
 * @version 4/7/21
 */
public class AlgoShape extends GameObject
{
    private Term term;
    public Color color;
    public boolean isFull;
    public boolean isClosed;
    private Polygon poly;
    private Style style;
    
    /**
     * Constructor for objects of class Shape
     */
    public AlgoShape(Term func, Style style,int xpos, int ypos)
    {
        super(xpos,ypos,ID.Platform);
        this.style = style;
        poly = new Polygon();
        final int lowBoundX = - style.bounds.width;
        final int highBoundX = style.bounds.width;

        final int lowBoundY = - style.bounds.height;
        final int highBoundY = style.bounds.height;

        //System.out.println(lowBoundY);
        for (double x = lowBoundX; x <= highBoundX; x = x + 1) {
            int myX = (int) x + highBoundX;
            int y = (int) func.get(x);
            int myY =  highBoundY - (int) y;
            //System.out.println("Go " + myX + ", " + myY);
            if (y < highBoundY * 2 && y > lowBoundY  * 2)
            {
                poly.addPoint(myX + xpos,myY + ypos);
                //System.out.println("g");
            }
        }
        
    }
    
    public void render(Graphics g, int offsetX, int offsetY)
    {
        g.setColor(style.color);  
        ((Graphics2D) g).setStroke(new BasicStroke(style.thickness));  
        style.drawer.draw(g,poly);

    }
    
    public Shape getBounds()
    {
        return poly;
    }
    
    public boolean intersects(Rectangle rect)
    {
        return style.drawer.intersects(poly,rect);
    }
    
    public void tick()
    {
        
        
    }
    
    
   

    public static Polygon rectangleToPolygon(Rectangle rect) {
        int[] xpoints = {rect.x, rect.x + rect.width, rect.x + rect.width, rect.x};
        int[] ypoints = {rect.y, rect.y, rect.y + rect.height, rect.y + rect.height};
        return new Polygon(xpoints, ypoints, 4); 
    }
}
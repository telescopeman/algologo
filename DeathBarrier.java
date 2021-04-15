import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Graphics;

public class DeathBarrier extends ShapeObject {

    /**
     * @param y The y-position of the death barrier.
     */
    DeathBarrier(int y) // y position
    {
        super(rectangleToPolygon(new Rectangle(0,y,Game.WIDTH * 2,50)),
                new Style(DrawType.FILL, Color.red), 0,y);
        //setY(y);
        setDamage(-1); // instant kill
    }

    @Override
    public boolean intersects(Shape rect)
    {
        int BARRIER_WIDTH = 50;
        return rect.getBounds().getY() > getY();
    }

    /**
     * This render method ignores camera motion in the x direction.
     */
    @Override
    public void render(Graphics g, int offsetX, int offsetY)
    {
        super.render(g,0,offsetY);
    }

}

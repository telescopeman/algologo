import java.awt.*;

public class DeathBarrier extends ShapeObject {

    private final int y;

    /**
     * @param y The y-position of the death barrier.
     */
    DeathBarrier(int y) // y position
    {
        super(rectangleToPolygon(new Rectangle(Game.WIDTH * 2,50)),new Style(DrawType.NONE, Color.red), 0,y,ID.Enemy);
        this.y = y;
        setDamage(-1); // instant kill
    }

//    @Override
//    public boolean intersects(Shape rect)
//    {
//        int BARRIER_WIDTH = 50;
//        return rect.y > y && rect.y < y + BARRIER_WIDTH;
//
//    }

    /**
     * This render method ignores camera motion in the x direction.
     */
    @Override
    public void render(Graphics g, int offsetX, int offsetY)
    {
        super.render(g,0,offsetY);
    }

}

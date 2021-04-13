import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class GeometryHelper {

    private static final int SLIVER = 5;

    public static Rectangle getSide(Shape shape, SIDE side)
    {
        System.out.println("don't do this");
        return new Rectangle();
    }


    public static Rectangle getSide(Rectangle shape, SIDE side) {
        int right = shape.x + shape.width;
        int left = shape.x;
        int top = shape.y;
        int bottom = shape.y + shape.height;

        return switch (side) {
            case TOP -> new Rectangle(left, top, shape.width, SLIVER);
            case BOTTOM -> new Rectangle(left, bottom-SLIVER, shape.width, SLIVER);
            case LEFT -> new Rectangle(left, top, SLIVER, shape.height);
            case RIGHT -> new Rectangle(right-SLIVER, top, SLIVER, shape.height);
        };
    }

    public static boolean sideIntersects(Rectangle shape, SIDE side, GameObject collider)  {
        return collider.getBounds().intersects(getSide(shape,side));
    }

}

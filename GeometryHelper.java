import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.geom.Line2D;

public class GeometryHelper {

    private static final int SLIVER = 5;




    public static Rectangle getSide(Rectangle shape, SIDE side) {
        int right = shape.x + shape.width - SLIVER;
        int left = shape.x;
        int top = shape.y;
        int bottom = shape.y + shape.height - SLIVER;

        return switch (side) {
            case TOP -> new Rectangle(left, top, shape.width, SLIVER);
            case BOTTOM -> new Rectangle(left, bottom, shape.width, SLIVER);
            case LEFT -> new Rectangle(left, top, SLIVER, shape.height);
            case RIGHT -> new Rectangle(right, top, SLIVER, shape.height);
        };
    }

    public static boolean sideIntersects(Rectangle shape, SIDE side, GameObject collider)  {
        return collider.intersects(getSide(shape,side));
    }

}

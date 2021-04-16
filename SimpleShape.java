import java.awt.Polygon;
import java.awt.Color;

public class SimpleShape extends Polygon {
    public Color color;
    public Polygon polygon;

    public SimpleShape(Color color, Polygon polygon)
    {
        this.color = color;
        this.polygon = polygon;
    }
}

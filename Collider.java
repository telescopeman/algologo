import java.awt.*;

public class Collider {
    protected Shape shape;

    public Collider(Shape shape)
    {
        this.shape = shape;
    }

    public void setShape(Shape shape)
    {
        this.shape = shape;
    }

    public boolean collidesWith(Collider o)
    {
        return false;
    }

}

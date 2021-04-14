import java.awt.Shape;

/**
 * An object that is bound to another object.
 * @author Caleb Copeland
 * @since 4/14/21
 * @version 4/14/21
 */
public abstract class BoundObject extends GameObject {

    protected final GameObject linked_object;
    private boolean isTimeBased = false;
    private long lastWakeupTime = 0, length = 0;


    public BoundObject(GameObject object, boolean isTimeBased)
    {
        super(object.getX(), object.getY(), ID.UI);
        setEnabled(false);
        this.isTimeBased = isTimeBased;
        linked_object = object;
    }

    @Override
    public void tick() {
        if (isTimeBased)
        {
            setEnabled( Math.abs(System.currentTimeMillis() - lastWakeupTime) < length );
        }

        if (getEnabled()) {
            updatePosition();
            updateForm();
        }
    }



    @Override
    public boolean intersects(Shape rect) {
        return false;
    }

    public void wake(long time)
    {
        setEnabled(true);
        lastWakeupTime = System.currentTimeMillis();
        length = time;
    }

    public void updatePosition()
    {
        setX(linked_object.getX() + getOffsetX());
        setY(linked_object.getY() + getOffsetY());
    }

    public abstract double getOffsetX();

    public abstract double getOffsetY();
}

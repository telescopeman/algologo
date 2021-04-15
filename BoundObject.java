import java.awt.Shape;

/**
 * An object that is bound physically to another object.
 * @author Caleb Copeland
 * @since 4/14/21
 * @version 4/15/21
 */
public abstract class BoundObject extends GameObject {

    protected final GameObject linked_object;
    private final boolean isTimeBased;
    private CooldownTimer statusTimer = new CooldownTimer(10);


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
            setEnabled(!statusTimer.isExpired());
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
        statusTimer.set_timer_length(time);
        statusTimer.wake();
    }

    public void updatePosition()
    {
        setX(linked_object.getX() + getOffsetX());
        setY(linked_object.getY() + getOffsetY());
    }

    public abstract double getOffsetX();

    public abstract double getOffsetY();
}

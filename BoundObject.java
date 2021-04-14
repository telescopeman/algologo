import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * An object that is bound to another object.
 * @author Caleb Copeland
 * @since 4/14/21
 * @version 4/14/21
 */
public abstract class BoundObject extends GameObject {

    protected final GameObject linked_object;
    private boolean enabled = false;
    private boolean isTimeBased = false;
    private long lastWakeupTime = 0, length = 0;

    public void setEnabled(boolean bool)
    {
        enabled = bool;
    }

    public BoundObject(GameObject object, boolean isTimeBased)
    {
        super(object.getX(), object.getY(), ID.UI);
        this.isTimeBased = isTimeBased;
        linked_object = object;
    }

    @Override
    public void tick() {
        if (isTimeBased)
        {
            setEnabled( Math.abs(System.currentTimeMillis() - lastWakeupTime) < length );
        }

        if (enabled) {
            updatePosition();
            updateForm();
        }
    }

    @Override
    public void render(Graphics g, int offsetX, int offsetY) {
        if (enabled)
        {
            draw(g, offsetX, offsetY);
        }
    }

    @Override
    public boolean intersects(Rectangle rect) {
        return false;
    }

    public void wake(long time)
    {
        setEnabled(true);
        lastWakeupTime = System.currentTimeMillis();
        length = time;
    }
    public abstract void draw(Graphics g, int offsetX, int offsetY);

    public void updatePosition()
    {
        setX(linked_object.getX() + getOffsetX());
        setY(linked_object.getY() + getOffsetY());
    }

    public abstract double getOffsetX();

    public abstract double getOffsetY();
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Heads-up-Display displaying info about objects.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/13/21
 * @since 4/12/21
 */
public class HUD extends GameObject
{
    public static final Color HUD_COLOR = Color.gray, HUD_TEXT_COLOR = Color.white;
    private final int width = 100, height = 20;
    private final GameObject linked_object;
    private String display = "dummy";

    public HUD(GameObject object)
    {
        super(object.getX(), object.getY(), ID.UI);
        linked_object= object;
    }


    @Override
    public boolean intersects(Rectangle rect) {
        return false;
    }

    @Override
    public void tick() {
        updateForm();
    }

    @Override
    public void render(Graphics g, int offsetX, int offsetY) {
        g.setColor(HUD_COLOR);
        g.fillRect((int) getX() + offsetX,(int) getY() + offsetY, width, height);
        g.setColor(HUD_TEXT_COLOR);
        g.drawString(display,
                (int) getX() + offsetX,
                (int) getY() + offsetY + height/2);
    }

    @Override
    protected void updateForm() {
        setX(linked_object.getX());
        setY(linked_object.getY() - 2* linked_object.getBounds().getBounds().getHeight());
        display = "";
        display += convertPositionToString();
    }

    private String convertPositionToString()
    {
        return "X: " + (int) getX() + ", Y: " + (int) getY();
    }


}

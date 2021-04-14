import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Heads-up-Display displaying info about objects.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/14/21
 * @since 4/12/21
 */
public class HUD extends BoundObject
{
    public static final Color HUD_COLOR = Color.gray, HUD_TEXT_COLOR = Color.white;
    private final int width = 100, height = 20;
    private final int PADDING = 40;
    private String display = "dummy";

    public HUD(GameObject object)
    {
        super(object,false);
    }




    @Override
    public void render(Graphics g, int offsetX, int offsetY) {
        g.setColor(HUD_COLOR);
        g.fillRect((int) getX() + offsetX,
                (int) getY() - offsetY - PADDING, width, height);
        g.setColor(HUD_TEXT_COLOR);
        g.drawString(display,
                (int) getX() + offsetX,
                (int) getY() + offsetY + height/2 - PADDING);
    }

    @Override
    public void updateForm() {
        display = linked_object.getID().toString()  + " " + convertPositionToString();
    }

    public double getOffsetX() { return 0; }

    public double getOffsetY() { return  2 * linked_object.getBounds().getBounds().getHeight(); }

    private String convertPositionToString()
    {
        return "X: " + (int) getX() + ", Y: " + (int) getY();
    }


}

import java.awt.Color;
import java.awt.Graphics;

/**
 * Heads-up-Display in the corner.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/8/21
 * @since 4/12/21
 */
public class HUD
{
    public static final Color HUD_COLOR = Color.gray;
    public static final Color HUD_TEXT_COLOR = Color.white;
    
//    public void tick(){
//
//    }
    public void render(Graphics g, String s1)
    {
        g.setColor(HUD_COLOR);
        g.fillRect(20,20,200,40);
        g.setColor(HUD_TEXT_COLOR);
        g.drawString(s1,15,31);
    }
}

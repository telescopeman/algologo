import java.awt.Graphics;
import java.awt.Color;

/**
 * Write a description of class HUD here.
 *
 * @author RealTutsGML
 * @version (a version number or a date)
 */
public class HUD
{
    // instance variables - replace the example below with your own
    public static int health = 100;
    
    public void tick(){
        
    }
    
    
    public void render(Graphics g)
    {
        g.setColor(Color.gray);

        g.fillRect(15,15,200,32);
    }
}

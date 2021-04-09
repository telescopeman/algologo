import java.awt.Graphics;
import java.awt.Color;

/**
 * Write a description of class InfoMarker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class InfoMarker
{
    // instance variables - replace the example below with your own
    //private int x;
    
    

    public void tick(){
        
    }
    
    
    public void render(Graphics g, PhysicsObject obj)
    {
        g.setColor(Color.gray);

        g.fillRect((int)obj.getX(),(int)obj.getY(),200,32);
        g.setColor(Color.white);
        //g.drawString(s1,15,31);
    }
}

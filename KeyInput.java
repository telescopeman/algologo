import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Write a description of class KeyInput here.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version (a version number or a date)
 */
public class KeyInput extends KeyAdapter
{
    // instance variables - replace the example below with your own

    private Handler handler;

    public KeyInput(Handler handler)
    {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        //System.out.println("a");

        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID() == ID.Player)
            {
                if (key == KeyEvent.VK_LEFT)
                {
                    System.out.println(tempObject.maxSpeedH);
                    tempObject.setSoughtVelocityX(- tempObject.maxSpeedH);
                }
                else if (key == KeyEvent.VK_RIGHT)
                    tempObject.setSoughtVelocityX(tempObject.maxSpeedH);
                else if (key == KeyEvent.VK_UP && tempObject.currentSurface != null)
                    tempObject.jump(); //

            }
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID() == ID.Player)
            {
                if (key == KeyEvent.VK_LEFT)
                {
                    if (tempObject.getSoughtVelocityX() < 0)
                    {
                        tempObject.setSoughtVelocityX(0);
                    }
                }
                else if (key == KeyEvent.VK_RIGHT)
                {
                    if (tempObject.getSoughtVelocityX() > 0)
                    {
                        tempObject.setSoughtVelocityX(0);
                    }
                }

            }
        }}

}

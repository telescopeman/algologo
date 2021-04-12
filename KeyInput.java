import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * Write a description of class KeyInput here.
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/8/21
 * @since 4/8/21
 */
public class KeyInput extends KeyAdapter
{
    // instance variables - replace the example below with your own

    private final Handler handler;

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
            GameObject tempObject0 = handler.object.get(i);

            if (tempObject0.getID() == ID.Player)
            {
                assert(tempObject0 instanceof Player);
                Player tempObject = (Player) tempObject0;
                if (key == KeyEvent.VK_LEFT)
                {
                    //System.out.println(tempObject.maxSpeedH);
                    tempObject.setSoughtVelocityX(- tempObject.maxSpeedH);
                }
                else if (key == KeyEvent.VK_RIGHT)
                    tempObject.setSoughtVelocityX(tempObject.maxSpeedH);
                else if (key == KeyEvent.VK_UP)
                    tempObject.jump();
            }
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject0 = handler.object.get(i);
            if (tempObject0.getID() == ID.Player)
            {
                Player tempObject = (Player) tempObject0;
                //assert(tempObject instanceof Player);
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

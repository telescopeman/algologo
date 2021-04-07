import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Inherited class that has a few methods I like to have handy in frames.
 *
 * @author Caleb Copeland
 * @version 4/6/21
 */
public class EasyFrame extends JFrame implements ActionListener
{
    // size presets
    public final Dimension STANDARD = new Dimension(350,100);
    public final Dimension SUPERSTANDARD = new Dimension(550,150);
    public final Dimension SMALL = new Dimension(200,200);
    public final Dimension ROUNDISH = new Dimension(350,250);
    public final Dimension LONG = new Dimension(900,250);
    public final Dimension LONG2 = new Dimension(900,150);
    public final Dimension MAIN = new Dimension(850,1000);


    /**
     * Constructor for objects of class EasyFrame
     */
    public EasyFrame()
    {
        // initialise instance variables

    }

    public void addHeader(String text)
    {
        JLabel title = new JLabel(text,JLabel.CENTER);
        title.setFont(new Font(title.getFont().getFontName(),Font.BOLD,12));
        add(title);

    }
    
    public void addCenteredText(String text)
    {
        JLabel title = new JLabel(text,JLabel.CENTER);
        add(title);
    }

    public EasyFrame(String name)
    {
        // initialise instance variables
        setTitle(name);
    }

    public void addButton(String name, ActionListener trig)
    {
        JButton jb3 = new JButton(name);    
        jb3.addActionListener(trig);
        add(jb3);
    }

    /**
     * Generates a Dimension with the given size.
     */
    public Dimension getDim(int x, int y)
    {
        return new Dimension(x,y);

    }
    
    public void setWidth(int x)
    {
         setSize(new Dimension(x,getSize().height));
    }
    
    public void setHeight(int x)
    {
         setSize(new Dimension(getSize().width,x));
    }

    // public GridLayout getGrid(int x, int y)
    // {
    // return new GridLayout(x,y);
    // }

    public void setGrid(int x, int y)
    {
        setLayout(new GridLayout(x,y));
    }

    public void appear()
    {
        show();
        requestFocusInWindow();
    }

    public void appear(Dimension dim)
    {
        show();
        requestFocusInWindow();
        setSize(dim);
    }


    /**
     * Adds a label with the specified String.
     */
    public void add(String str)
    {
        add(new JLabel(str));

    }

    public void clear()
    {
        // put your code here
        getContentPane().removeAll();
    }

    public void actionPerformed(ActionEvent e)
    {
        act(e.getActionCommand());
    }

    /**
     * If not overridden, assumes no-arg method should be used.
     */
    public void act(String id)
    {
        act();
    }
    
    
    public void act()
    {
        
    }
}

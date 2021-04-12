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
public class EasyFrame extends JFrame
{

    public static final Dimension MAIN = new Dimension(800,800);


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
    
    public EasyFrame(String name, Dimension dim)
    {
        // initialise instance variables
        setTitle(name);
        setSize(dim);
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
    
    @SuppressWarnings("SuspiciousNameCombination")
    public void setHeight(int x)
    {
         setSize(new Dimension(getSize().width,x));
    }

    public void setGrid(int x, int y)
    {
        setLayout(new GridLayout(x,y));
    }

    public void appear()
    {
        setVisible(true);
        requestFocusInWindow();
    }

    public void appear(Dimension dim)
    {
        appear();
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


}

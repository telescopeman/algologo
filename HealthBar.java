import java.awt.Graphics;
import java.awt.Color;


/**
 * @author Caleb Copeland, StackOverFlow User 1924249, KevLoughrey
 */
public class HealthBar extends BoundObject{


    private final int BAR_HEIGHT = 10;
    private int hp, max_hp, width;
    private LivingObject obj;

    public HealthBar(LivingObject object)
    {
        super(object,true);
        obj = object;
    }


    @Override
    public double getOffsetX() {
        return - ((float)width / 2);
    }

    @Override
    public double getOffsetY() {
        return - linked_object.getBounds().getBounds().getHeight() - BAR_HEIGHT * 2;
    }

    @Override
    public void draw(Graphics g, int offsetX, int offsetY) {
        updateForm();

        g.setColor(Color.red);
        g.fillRect((int) getX() + offsetX, (int) getY() + offsetY, width, BAR_HEIGHT);
        g.setColor(Color.green);
        g.fillRect((int) getX() + offsetX, (int) getY() + offsetY, width * (hp / max_hp), BAR_HEIGHT);
    }

    public void updateForm()
    {
        width = (int) linked_object.getBounds().getBounds().getWidth();
        hp = obj.getHealth();
        max_hp = obj.getMaxHealth();
        System.out.println(hp + "/" + max_hp);
    }







}

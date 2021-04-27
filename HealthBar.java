import java.awt.Graphics;
import java.awt.Color;


/**
 * @author Caleb Copeland, StackOverFlow User 1924249, KevLoughrey
 * @since 4/14/21
 * @version 4/14/21
 */
public class HealthBar extends BoundObject{


//    private final int BAR_HEIGHT = 10;
//    private double hp, max_hp;
//    private int width;
//    private final LivingObject obj;
//
//    public HealthBar(LivingObject object)
//    {
//        super(object,true);
//        obj = object;
//    }
//
//
//    @Override
//    public double getOffsetX() {
//        return - ((float)width / 2);
//    }
//
//    @Override
//    public double getOffsetY() {
//        return - linked_object.getCollider().shape.getBounds().getBounds().getHeight() - BAR_HEIGHT * 2;
//    }
//
//    @Override
//    public void render(Graphics g) {
//        updateForm();
//        g.setColor(Color.red);
//        g.fillRect((int) getX(), (int) getY(), width, BAR_HEIGHT);
//        g.setColor(Color.green);
//        g.fillRect((int) getX(), (int) getY(), (int) (width * (hp / max_hp)), BAR_HEIGHT);
//    }
//
//    public void updateForm()
//    {
//        width = (int) linked_object.getCollider().shape.getBounds().getBounds().getWidth();
//        hp = obj.getHealth();
//        max_hp = obj.getMaxHealth();
//    }
}

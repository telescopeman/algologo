import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Bullet extends LivingObject{

    private final ID target;
    private double size;

    public Bullet(double x, double y, double size, ID target,double speed, double angle, int damage) {
        super(x, y, ID.Bullet,false,20);
        setDamage(damage);
        this.target = target;
        setBounds(new Ellipse2D.Double(x,y,size,size));
        System.out.println(getBounds());
        setTrajectory(speed,angle);
        freezeX();
        K2 = 0.2;
    }

    public Shape getBounds()
    {
        return shape.getBounds();
    }

    @Override
    public void updateForm() {
        //Ellipse2D circle = ((Ellipse2D.double) shape);
        ((Ellipse2D.Double) shape).x = getX();
        ((Ellipse2D.Double) shape).y = getY();
        //((Ellipse2D.Double) shape).width = size;
    }
}

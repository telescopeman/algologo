import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bullet extends CollidingObject{

    private final ID target;
    private double size;

    public Bullet(double x, double y, double size, ID target,double speed, double angle, int damage) {
        super(x, y, ID.Bullet,false);
        setDamage(damage);
        setDespawns(true);
        this.target = target;
        setBounds(new Ellipse2D.Double(x,y,size,size));
        setDisplayColor(Color.red);
        setTrajectory(speed,angle);
        freezeX();
        K2 = 0.2;
    }

    public void transformVelocity(double x, double y)
    {
        super.transformVelocity(x, y);
        freezeX();
    }

    @Override
    public void onDealDamage(int damage, LivingObject victim) {
        despawn();
    }
}

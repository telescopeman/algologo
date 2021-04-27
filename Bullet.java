import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Bullet {

//    private final ID target;
//    private double size;
//    private LivingObject source;
//    private CooldownTimer timer = new CooldownTimer(3000);
//
//    public Bullet(double x, double y, double size, ID target,
//                  double speed, double angle, int damage, LivingObject src) {
//        super(x, y, ID.Bullet,false);
//        setDamage(damage);
//        setDespawns(true);
//        source = src;
//        this.target = target;
//        setBounds(new Ellipse2D.Double(x,y,size,size));
//        setDisplayColor(Color.red);
//        setTrajectory(speed,angle);
//        freezeX();
//        K2 = 0.1;
//        timer.wake();
//    }
//
//
//
//    public void transformVelocity(double x, double y)
//    {
//        super.transformVelocity(x, y);
//        freezeX();
//    }
//
//    public void onDespawned() {
//        source.free_bullet(this);
//    }
//
//
//    @Override
//    public void onDealDamage(int damage, LivingObject victim) {
//        despawn();
//    }
}

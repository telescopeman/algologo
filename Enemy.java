import java.awt.Rectangle;

public class Enemy extends NPC {
    private ID soughtObjects = ID.Player;

    public Enemy(double x, double y, boolean canFly, int HP) {
        super(x, y, ID.Enemy, canFly, HP, new Rectangle(40,40));
        damageSources = new ID[]{};
        maxSpeedH = 4;
    }


    @Override
    public Activity think() {
        for (GameObject object : scan(sense_range)) {
            if (object.hasID(soughtObjects))
            {
                setFocusedObject(object);
                return Activity.ATTACK;
            }
        };
        setFocusedObject(null);
        return Activity.IDLE;
    }

    @Override
    public void idle() {

    }

    @Override
    public void attack(GameObject target) {
        if (target == null) {
            return;
        } else
        {
            // if target is higher, jump
            if(target.getY() < getY() -5 ) {
                jump();
            }
            setSoughtVelocityX( Math.signum(target.getX() - getX()) * maxSpeedH);
        }
    }
}

import java.awt.Rectangle;

public class Enemy extends NPC {

    public Enemy(double x, double y, boolean canFly, int HP, int damageDealt) {
        super(x, y, ID.Enemy, canFly, HP, new Rectangle(40,40));
        damageSources = new ID[]{ID.Bullet};
        setDamage(damageDealt);
        maxSpeedH = 4;
        //setBounds(INIT_PLAYER_SIZE);
    }



    @Override
    public Activity think() {
        for (GameObject object : scan(sense_range)) {
            if (object.hasID(ID.Player))
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

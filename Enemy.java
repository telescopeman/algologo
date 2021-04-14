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
    public Activity think(double range) {
        for (GameObject object : scan(range)) {
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
        setSoughtVelocityX(0);
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

    @Override
    public void onDealDamage(int damage, LivingObject victim) {
        SIDE go;
        if (getVelocityX() > 0) {
            go = SIDE.RIGHT;
        }
        else
        {
            go = SIDE.LEFT;
        }
        endActivity();
        bonk(go,victim,damage);
        jump();
        setFocusedObject(null);
    }
}

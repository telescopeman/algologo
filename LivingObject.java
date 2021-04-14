import java.util.ArrayList;

/**
 * An object that acts like a creature (player, enemy, etc)
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/14/21
 * @since 4/8/21
 */
public abstract class LivingObject extends CollidingObject {
    protected double baseJump = 6, velJumpMultiplier = 0.3;
    private int health, maxHealth;
    private long damageTimer = 0, hitstun_time = 500;
    protected boolean canFly = false;

    public ID[] damageSources;

    private final HealthBar healthBar = new HealthBar(this);

    public LivingObject(double x, double y, ID id, boolean canFly, int HP,boolean canLand) {
        super(x, y, id,canLand);
        damageTimer = System.currentTimeMillis() - hitstun_time;
        setFlightAbility(canFly);
        setMaxHealth(HP);
        fullHeal();
        Handler.addObject(healthBar);
    }

    public int getHealth() { return health; }
    public void setHealth(int n) {
        health = n;
        if (n <= 0)
        {
            despawn();
        }
        healthBar.wake(2000);
    }


    public void despawn() {
        Handler.queueForDeletion(healthBar);
        super.despawn();
    }


    public int getMaxHealth() { return maxHealth; }
    public void setMaxHealth(int n) { maxHealth = n; }
    public void fullHeal() { setHealth(maxHealth); }

    private boolean inHitStun()
    {

        return (System.currentTimeMillis() - damageTimer < hitstun_time);
    }

    @Override
    public void updateForm()
    {
        if(inHitStun()) {
            setDisplayColor(soften(getColor()));
        }
        else
        {
            setDisplayColor(getColor());
        }

        super.updateForm();
    }


    public void takeDamage(int n, GameObject obj) {
        if (!inHitStun()) {
            setHealth(getHealth() - n);
            damageTimer = System.currentTimeMillis();
            obj.onDealDamage(n,this);
        }

    }

    /**
     * Jumps.
     */
    public void jump() {
        if (getFlightAbility() || getCurrentGround() != null) {
            setGrounded(false);
            translate(0, -3);
            setVelocityY(-Math.abs(
                    baseJump + Math.abs(getVelocityY() * velJumpMultiplier)));
        }
    }

    /**
     * Checks if the object can fly.
     */
    public boolean getFlightAbility() {
        return canFly;
    }

    /**
     * Sets if the object can fly.
     */
    public void setFlightAbility(boolean f) { canFly = f; }

    @Override
    public void onCollision(GameObject tempObject)
    {
        if (isDamagedBy(tempObject)) { //rework this
            takeDamage(tempObject.getDamage(),tempObject);
        }
        super.onCollision(tempObject);
    }

    public boolean isDamagedBy(GameObject obj) {
        if (damageSources == null)
        {
            return false;
        }
        ArrayList<String> list2 = new ArrayList<String>();
        for (ID id : damageSources)
        {
            list2.add(id.toString());
            //System.out.print(id.toString() + ", ");
        }
        //System.out.println("");
        return list2.contains(obj.getID().toString());
    }
}

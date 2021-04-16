import java.util.ArrayList;

/**
 * An object that acts like a creature (player, enemy, etc)
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/15/21
 * @since 4/8/21
 */
public abstract class LivingObject extends CollidingObject {
    protected double baseJump = 6, velJumpMultiplier = 0.3;
    private int health, maxHealth, num_bullets = 0;
    private int max_bullets = 3;
    private final CooldownTimer hitstun_timer = new CooldownTimer(500);
    private boolean canFly = false;

    public ID[] damageSources;

    //private final HealthBar healthBar = new HealthBar(this);

    public LivingObject(double x, double y, ID id, boolean canFly, int HP,boolean canLand) {
        super(x, y, id,canLand);
        hitstun_timer.wake();
        setFlightAbility(canFly);
        setMaxHealth(HP);
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



    public int getMaxHealth() { return maxHealth; }
    public void setMaxHealth(int n) { maxHealth = n; setHealth(n);}


    @Override
    public void updateForm()
    {
        if(hitstun_timer.isExpired()) {
            setDisplayColor(soften(getColor()));
        }
        else
        {
            setDisplayColor(getColor());
        }

        super.updateForm();
    }



    private void takeDamage(int n, GameObject obj) {
        if (hitstun_timer.isExpired()) {
            setHealth(getHealth() - n);
            hitstun_timer.wake();
            obj.onDealDamage(n,this);
            onDamaged(n,obj);
        }
    }

    public void onDamaged(int amount, GameObject obj)
    {
        // do nothing.
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

    /**
     * Checks for damage.
     * @param tempObject Object to be collided with.
     */
    @Override
    public void otherCollisionTests(GameObject tempObject)
    {
        if (isDamagedBy(tempObject)) { //rework this
            takeDamage(tempObject.getDamage(),tempObject);
        }
    }

    /**
     * Tests to see if this object should be damaged by a GameObject.
     */
    public boolean isDamagedBy(GameObject obj) {
        if (damageSources == null)
        {
            return false;
        }
        ArrayList<String> list2 = new ArrayList<String>();
        for (ID id : damageSources)
        {
            list2.add(id.toString());
        }
        return list2.contains(obj.getID().toString());
    }

    /**
     * Removes a bullet.
     * @param bullet
     */
    public void free_bullet(Bullet bullet)
    {
        num_bullets--;
    }

    public void shoot()
    {
        System.out.println(num_bullets);
        if (num_bullets < max_bullets) {
            num_bullets++;
            Handler.queueForAddition(
                    new Bullet(getX(), getY() - getBounds().getBounds().getHeight() / 2,
                            10, ID.Enemy,
                            Math.signum(getVelocityX() + 0.01) * 5 + getVelocityX(),
                            0, getDamage(), this)
            );
        }
    }



}

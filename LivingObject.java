import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * An object that acts like a creature (player, enemy, etc)
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/14/21
 * @since 4/8/21
 */
public abstract class LivingObject extends PhysicsObject {
    protected double baseJump = 6, velJumpMultiplier = 0.3;
    private double lastX, lastY;
    private int health, maxHealth;
    protected boolean isOnGround = false, canFly = false;
    private GameObject currentSurface;
    protected ID[] damageSources;
    private final double slopeCutoffX = 20, slopeCutoffY = 3;
    private final HealthBar healthBar = new HealthBar(this);


    public LivingObject(double x, double y, ID id, boolean canFly, int HP) {
        super(x, y, id);

        savePos();
        setFlightAbility(canFly);
        setMaxHealth(HP);
        fullHeal();
        Handler.addObject(healthBar);
    }

    public void setLastX(double n) {
        lastX = n;
    }
    public void setLastY(double n) {
        lastY = n;
    }
    public void saveY()
    {
        setLastY(getY());
    }
    public void saveX()
    {
        setLastX(getX());
    }
    public void savePos()
    {
        saveY();
        saveX();
    }
    public double getLastY() { return lastY; }
    public double getLastX() { return lastX; }
    public void recoverX() { setX(getLastX()); }
    public void recoverY() { setY(getLastY()); }
    public void recoverPos() { recoverX(); recoverY(); }

    public void fall() {
        if (isGrounded()) {
            savePos();
            setSoughtVelocityY(0);
        } else {
            // sets sought velocity to terminal velocity
            setSoughtVelocityY(maxSpeedV);
        }
    }

    /**
     * Actions to do when the object hits a wall.
     * @param side The direction this object was going.
     * @param object The surface that was hit.
     */
    public void bonk(SIDE side, GameObject object)
    {
        final int BONK_LIMIT = 50;
        final double BONK_POWER_X = 0.9;
        final double BONK_POWER_Y = 0.8;

        int x_motion = 0;
        int y_motion = 0;

        if (side == SIDE.BOTTOM || side == SIDE.TOP)
        {
            transformVelocity(1,-BONK_POWER_Y);
            y_motion = (int) Math.signum(getVelocityY());
        }
        else
        {
            transformVelocity(-BONK_POWER_X,1);
            x_motion = (int) Math.signum(getVelocityX());
        }
        inchToEscape(x_motion, y_motion, object,BONK_LIMIT,true);
        //physics_process(STEPS);
        updateForm();
    }

    /**
     * Checks for contact.
     */
    public void collision() {
        for (int i = 0; i < Handler.object.size(); i++) {
            // find a way to avoid cycling through too many objects
            GameObject tempObject = Handler.object.get(i);
            if (tempObject.intersects(this)) {
                if (isDamagedBy(tempObject)) { //rework this
                    takeDamage(tempObject.getDamage());
                }
                if (tempObject.hasID(ID.Platform)) {
                    for (SIDE side : SIDE.values()) {

                        if (GeometryHelper.sideIntersects( (Rectangle) shape, side, tempObject))
                        {
                            if (side == SIDE.BOTTOM) {
                                if (land(tempObject)) {
                                    break;
                                }
                            }
                            else {
                                bonk(side, tempObject);
                            }
                        }

                    }
                }

            }
        }
    }

    public int getHealth() { return health; }
    public void setHealth(int n) {
        health = n;
        if (n <= 0)
        {
            die();
        }
        healthBar.wake(2000);
    }

    public abstract void die();


    public int getMaxHealth() { return maxHealth; }
    public void setMaxHealth(int n) { maxHealth = n; }
    public void fullHeal() { setHealth(maxHealth); }



    private boolean land(GameObject surface) {
        setCurrentGround(surface);
        setGrounded(true);
        setVelocityY(0);
        boolean canReach = inchToEscape(0,-1,surface,40,true);
        if (!canReach)
        {
            recoverPos();
            return false;
        }
        updateForm();
        return true;
    }

    /**
     *
     * @param x_distance
     * @param y_distance
     * @param surface
     * @param maxTimes The maximum number of times it moves until it decides it's not possible.
     * @param polarity If true, it moves until it has escaped the surface. If false, it moves until it touches the surface.
     * @return
     */
    public boolean inchToEscape(int x_distance, int y_distance, GameObject surface, int maxTimes, boolean polarity)
    {
        savePos();
        boolean maxExists = maxTimes > 0;
        int i = 0;
        while ((polarity == surface.intersects( this )) && (i < maxTimes || !maxExists)) {
            translate(x_distance, y_distance);
            updateForm();
            i++;
        }
        if (!maxExists || i < maxTimes) {
            savePos();
            return true;
        }
        else
        {
            recoverPos();
            return false;
        }
    }

    /**
     * Literally just moves the object by some coordinates.
     * @param x_distance
     * @param y_distance
     */
    public void translate(int x_distance, int y_distance)
    {
        setX(getX() + x_distance);
        setY(getY() + y_distance);
    }

    public void checkContact() {
        if (currentSurface != null) {
            if (currentSurface.intersects( this )) {
                setGrounded(true);

            } else if (Math.abs(getY() - getLastY()) < slopeCutoffY && Math.abs(getX() - getLastX()) < slopeCutoffX) {
                boolean reached = inchToEscape(0,1,currentSurface,50,false);
                if (reached) {
                    setGrounded(true);
                }
                else {
                    loseContact();
                }
            } else {
                loseContact();
            }
        }
    }


    public void loseContact() {
        setGrounded(false);
        currentSurface = null;
    }

    public void tick() {
        updateForm();
        /**
         * How many steps physics processing should be divided up into.
         */
        int STEPS = 8;
        for(int i = 0; i < STEPS; i++) {
            physics_process(STEPS);
            collision();
        }
        fall();
        checkContact();
    }



    public GameObject getCurrentGround() {
        return currentSurface;
    }
    public void setCurrentGround(GameObject sur) {
        currentSurface = sur;
    }
    public boolean isGrounded() {
        return isOnGround;
    }
    public void setGrounded(boolean d) {
        isOnGround = d;
    }

    public void takeDamage(int n) {
        setHealth(getHealth() - n);
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

    public boolean isDamagedBy(GameObject obj) {
        ArrayList<String> list2 = new ArrayList<String>();
        for (ID id : damageSources)
        {
            list2.add(id.toString());
        }
        return list2.contains(obj.getID().toString());
    }


}

import java.awt.Shape;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract class GameObject - write a description of the class here
 *
 * @author RealTutsGML, Caleb Copeland
 * @version 4/12/21
 */
public abstract class LivingObject extends PhysicsObject {
    protected double baseJump = 6, velJumpMultiplier = 0.3, lastX, lastY;
    protected int health, maxHealth;
    protected boolean isOnGround = false, canFly = false;
    protected GameObject currentSurface;
    protected final Handler handler;
    protected String[] damageSources;
    protected final double slopeCutoffX = 20, slopeCutoffY = 3;

    public LivingObject(double x, double y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
    }

    public LivingObject(double x, double y, ID id, Handler handler, boolean canFly) {
        super(x, y, id);
        this.handler = handler;
        setFlightAbility(canFly);
    }

    public void fall() {
        if (isGrounded()) {
            savePos();
            setSoughtVelocityY(0);
        } else {
            // sets sought velocity to terminal velocity
            setSoughtVelocityY(maxSpeedV);
        }
    }


    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.intersects(this)) {
                if (isDamagedBy(tempObject)) {
                    takeDamage(tempObject.getDamage());
                }
                if (tempObject.getID() == ID.Platform) {
                    land(tempObject);

                }
            }

        }
        updateForm();

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

    public int getHealth() { return health; }

    public void setHealth(int n) { health = n; }

    public int getMaxHealth() { return maxHealth; }

    public void setMaxHealth(int n) { maxHealth = n; }

    public void fullHeal() { setHealth(maxHealth); }

    public abstract void updateForm();

    public void land(GameObject surface) {
        setCurrentGround(surface);
        setGrounded(true);
        setVelocityY(0);

        while (surface.intersects( this )) {
            inchY(-1);
        }
        inchY(1);
    }

    public void inchY(int n)
    {
        setY(getY() + n);
        updateForm();
    }

    public void checkContact() {
        if (currentSurface != null) {
            if (currentSurface.intersects( this )) {
                setGrounded(true);

            } else if (Math.abs(getY() - getLastY()) < slopeCutoffY && Math.abs(getX() - getLastX()) < slopeCutoffX) {
                int i = 0;
                saveY();
                while ((!currentSurface.intersects( this ) ) && i < 50) {
                    inchY(1);
                    i++;
                }
                if (i < 50) {
                    setGrounded(true);
                    saveY();
                }
                else {
                    loseContact();
                    recoverY();
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
        doPhysics();
    }

    public void doPhysics() {
        process();
        fall();
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
        health -= n;
    }


    /**
     * Jumps.
     */
    public void jump() {
        if (getFlightAbility() || getCurrentGround() != null) {
            setGrounded(false);
            inchY(-3);
            setVelocityY(-Math.abs(baseJump + Math.abs(getVelocityY() * velJumpMultiplier)));
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

        List<String> list = Arrays.asList(damageSources);

        return list.contains(obj.getID().toString());
    }


}

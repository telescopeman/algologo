
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
    protected int health;
    protected boolean isOnGround = false, canFly = false;
    protected GameObject currentSurface;
    protected Handler handler;
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
            setLastX(getX());
            setLastY(getY());
            setSoughtVelocityY(0);
        } else {
            // sets sought velocity to terminal velocity
            setSoughtVelocityY(maxSpeedV);
        }
    }


    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.intersects(getBounds())) {
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

    public double getLastY() {
        return lastY;
    }

    public double getLastX() {
        return lastX;
    }

    public abstract void updateForm();

    public void land(GameObject surface) {
        setCurrentGround(surface);
        setGrounded(true);
        setVelocityY(0);

        while (surface.intersects(getBounds())) {
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
            if (currentSurface.intersects(getBounds())) {
                setGrounded(true);

            } else if (Math.abs(getY() - getLastY()) < slopeCutoffY && Math.abs(getX() - getLastX()) < slopeCutoffX) {
                int i = 0;
                setLastY(getY());
                while ((!currentSurface.intersects(getBounds()) ) && i < 50) {
                    inchY(1);
                    i++;
                }
                if (i < 50) {
                    setGrounded(true);
                    return;
                }
                else {
                    loseContact();
                    setY(getLastY());
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
            setY(getY() - 3);
            setVelocityY(-Math.abs(baseJump + Math.abs(getVelocityY() * velJumpMultiplier)));
            System.out.println(getVelocityY());

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
    public void setFlightAbility(boolean f) {
        canFly = f;
    }

    public boolean isDamagedBy(GameObject obj) {

        List<String> list = Arrays.asList(damageSources);

        return list.contains(obj.getID().toString());
    }


}

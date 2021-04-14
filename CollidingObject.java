import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * @since 4/14/21
 * @version 4/14/21
 */
public class CollidingObject extends PhysicsObject {
    private final double slopeCutoffX = 20, slopeCutoffY = 3;
    private GameObject currentSurface;
    private boolean isOnGround = false, canLand;
    private double lastX, lastY;

    public CollidingObject(double x, double y, ID id, boolean canLand) {
        super(x, y, id);
        savePos();
        this.canLand = canLand;
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

    /**
     * Lands on the ground.
     * @param surface The ground landed on.
     * @return Whether it successfully landed or not. (A case where it failed would be something like an incorrect collision detection in an earlier part of the program.)
     */
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

    /**
     * Actions to do when the object hits a wall.
     * @param side The direction this object was going.
     * @param object The surface that was hit.
     */
    public void bonk(SIDE side, GameObject object, double power)
    {
        final int BONK_LIMIT = 50;
        final double BONK_POWER_X = 0.9;
        final double BONK_POWER_Y = 0.8;

        int x_motion = 0;
        int y_motion = 0;

        if (side == SIDE.BOTTOM || side == SIDE.TOP)
        {
            transformVelocity(1,-BONK_POWER_Y * power);
            y_motion = (int) Math.signum(getVelocityY());
        }
        else
        {
            transformVelocity(-BONK_POWER_X * power,1);
            x_motion = (int) Math.signum(getVelocityX());
        }
        inchToEscape(x_motion, y_motion, object,BONK_LIMIT,true);
        //physics_process(STEPS);
        updateForm();
    }

    /**
     * Actions to do when the object hits a wall.
     * @param side The direction this object was going.
     * @param object The surface that was hit.
     */
    public void bonk(SIDE side, GameObject object)
    {
        bonk(side,object,1);
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
     *
     * @param x_distance How fast to inch horizontally
     * @param y_distance How fast to inch vertically
     * @param surface The surface in question to be escaped/sought out.
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

    public void collision() {
        for (int i = 0; i < Handler.object.size(); i++) {
            // find a way to avoid cycling through too many objects
            GameObject tempObject = Handler.object.get(i);
            if (tempObject.intersects(this)) {
                onCollision(tempObject);

            }
        }
    }



    public void onCollision(GameObject tempObject)
    {
        if (tempObject.hasID(ID.Platform)) {
            platformSpecificCollision(tempObject);
        }
    }

    private void platformSpecificCollision(GameObject tempObject)
    {
        if (shape instanceof Rectangle) {
            for (SIDE side : SIDE.values()) {
                if (GeometryHelper.sideIntersects((Rectangle) shape, side, tempObject)) {
                    if (side == SIDE.BOTTOM && canLand) {
                        if (land(tempObject)) {
                            break;
                        }
                    } else {
                        bonk(side, tempObject);
                    }
                }
            }
        }
        else if (shape instanceof Ellipse2D.Double)
        {
            Ellipse2D.Double ellipse = (Ellipse2D.Double) shape;
            if (tempObject.getBounds().contains(
                    ellipse.getCenterX(), ellipse.getMaxY()))
            {
                bonk(SIDE.BOTTOM,tempObject);
            }
            if (tempObject.getBounds().contains(
                    ellipse.getCenterX(), ellipse.getMinY()))
            {
                bonk(SIDE.TOP,tempObject);
            }
            if (tempObject.getBounds().contains(
                    ellipse.getMaxX(), ellipse.getCenterY()))
            {
                bonk(SIDE.RIGHT,tempObject);
            }
            if (tempObject.getBounds().contains(
                    ellipse.getMinX(), ellipse.getCenterY()))
            {
                bonk(SIDE.LEFT,tempObject);
            }
        }
        else
        {
            throw new IllegalStateException("Unhandled shape type!");
        }

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

}

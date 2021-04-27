import javafx.scene.shape.*;


/**
 * @version 4/18/21
 * @since 4/16/21
 */
public abstract class ModeledEntity extends Entity {
    private final double slopeCutoffH = 20, slopeCutoffV = 3;
    public Shape3D model;
    private ModeledEntity currentSurface;
    private boolean isOnGround = false, canLand;
    private Vector3D lastPosition;

    public ModeledEntity(Vector3D position, boolean temporary, ID id, Shape3D shape)
    {
        super(position,temporary,id);
        model = shape;
    }

    public ModeledEntity getCurrentGround() {
        return currentSurface;
    }
    public void setCurrentGround(ModeledEntity sur) {
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
    private boolean land(ModeledEntity surface) {
        setCurrentGround(surface);
        setGrounded(true);
        getVelocity().y = 0;
        boolean canReach = inchToEscape(0,-1,0,surface,40,true);
        if (!canReach)
        {
            recoverPos();
            return false;
        }
        return true;
    }



    public void tick() {
        /**
         * How many steps physics processing should be divided up into.
         */
        physics_process();
        fall();
        checkContact();
    }

    public void do_kinematics(int divs)
    {
        super.do_kinematics(divs);
        collision();
    }

    /**
     * Actions to do when the object hits a wall.
     * @param side The direction this object was going.
     * @param object The surface that was hit.
     */
    public void bonk(SIDE side, ModeledEntity object, double power)
    {
        final int BONK_LIMIT = 50;
        final double BONK_POWER_X = 0.9;
        final double BONK_POWER_Y = 0.8;

        int x_motion = 0;
        int y_motion = 0;
        int z_motion = 0;

        if (side == SIDE.BOTTOM || side == SIDE.TOP)
        {
            getVelocity().multiply(new Vector3D(1, -BONK_POWER_Y * power,1));
            y_motion = (int) Math.signum(getVelocity().y);
        }
        else
        {
            getVelocity().multiply(new Vector3D(-BONK_POWER_X * power, 1,1));
            x_motion = (int) Math.signum(getVelocity().x);
        }
        inchToEscape(x_motion, y_motion, z_motion, object,BONK_LIMIT,true);
    }

    /**
     * Actions to do when the object hits a wall.
     * @param side The direction this object was going.
     * @param object The surface that was hit.
     */
    public void bonk(SIDE side, ModeledEntity object)
    {
        bonk(side,object,1);
    }


    public void fall() {
        if (isGrounded()) {
            savePos();
            velocity.y = 0;
            acceleration.y = 0;
        } else {
            // sets sought velocity to terminal velocity
            acceleration.y = 9.8;
        }
    }

    /**
     * Inches along until it either escapes or touches a surface.
     * @param x_distance How fast to inch horizontally
     * @param y_distance How fast to inch vertically
     * @param surface The surface in question to be escaped/sought out.
     * @param maxTimes The maximum number of times it moves until it decides it's not possible.
     * @param polarity If true, it moves until it has escaped the surface. If false, it moves until it touches the surface.
     * @return Whether the object was able to escape/reach the surface by the end of its attempts.
     */
    public boolean inchToEscape(int x_distance, int y_distance, int z_distance, ModeledEntity surface, int maxTimes, boolean polarity)
    {
        savePos();
        boolean maxExists = maxTimes > 0;
        int i = 0;
        while ((polarity == surface.intersects( this )) && (i < maxTimes || !maxExists)) {
            getPosition().add(new Vector3D(x_distance, y_distance, z_distance));
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



    public void checkContact() {
        if (currentSurface != null) {
            if (currentSurface.intersects( this )) {
                setGrounded(true);

            } else if (Math.abs(getPosition().y - lastPosition.y) < slopeCutoffV &&
                    Math.abs(getPosition().x - lastPosition.x) < slopeCutoffH &&
                    Math.abs(getPosition().z - lastPosition.z) < slopeCutoffH) {
                boolean reached = inchToEscape(0,1,0,currentSurface,50,false);
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

    public boolean intersects(ModeledEntity modeledEntity)
    {
        return false;
    }


    public void loseContact() {
        setGrounded(false);
        currentSurface = null;
    }

    public void collision() {
        for (int i = 0; i < Handler.object.size(); i++) {
            // find a way to avoid cycling through too many objects
            Entity tempObject = Handler.object.get(i);
            if (tempObject instanceof ModeledEntity) {
                if (((ModeledEntity) tempObject).intersects(this)) {
                    if (tempObject.hasID(ID.Platform)) {
                        platformSpecificCollision((ModeledEntity) tempObject);
                    }
                    otherCollisionTests((ModeledEntity) tempObject);
                }
            }
        }
    }

    public void otherCollisionTests(ModeledEntity tempObject)
    {
        // do nothing
    }

    private void platformSpecificCollision(ModeledEntity tempObject)
    {
//        if (shape instanceof Rectangle) {
//            for (SIDE side : SIDE.values()) {
//                if (GeometryHelper.sideIntersects((Rectangle) shape, side, tempObject)) {
//                    if (side == SIDE.BOTTOM && canLand) {
//                        if (land(tempObject)) {
//                            tempObject.onLandedOn(this);
//                            break;
//                        }
//                    } else {
//                        bonk(side, tempObject);
//                    }
//                }
//            }
//        }
//        else if (shape instanceof Ellipse2D.Double ellipse)
//        {
//            if (tempObject.getBounds().contains(
//                    ellipse.getCenterX(), ellipse.getMaxY()))
//            {
//                bonk(SIDE.BOTTOM,tempObject);
//            }
//            if (tempObject.getBounds().contains(
//                    ellipse.getCenterX(), ellipse.getMinY()))
//            {
//                bonk(SIDE.TOP,tempObject);
//            }
//            if (tempObject.getBounds().contains(
//                    ellipse.getMaxX(), ellipse.getCenterY()))
//            {
//                bonk(SIDE.RIGHT,tempObject);
//            }
//            if (tempObject.getBounds().contains(
//                    ellipse.getMinX(), ellipse.getCenterY()))
//            {
//                bonk(SIDE.LEFT,tempObject);
//            }
//        }
//        else
//        {
//            throw new IllegalStateException("Unhandled shape type!");
//        }

    }

    public void savePos() { lastPosition = getPosition(); }
    public void recoverPos() { position = lastPosition; }


}

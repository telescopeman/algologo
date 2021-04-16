/**
 * Any object in the game.
 * @since 4/16/21
 * @version 4/16/21
 * @author RealTutsGML, Caleb Copeland, Joop Eggen [rectangleToPolygon() only]
 */
public abstract class Entity {

    protected ID id;
    private double x, y, z;
    private double velocityX, velocityY, velocityZ;
    private boolean enabled = true, temporary = false;
    private boolean isLockedX = false, isLockedY = false, isLockedZ = false;
    

    public Entity(double x, double y, ID id) {
        setX(x);
        setY(y);
        setID(id);
    }

    public void setDespawns(boolean d)
    {
        despawns = d;
    }

    public abstract void tick();

    public void setEnabled(boolean bool) {
        enabled = bool;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void despawn() {
        onDespawn();
        Handler.queueForDeletion(this);
    }

    public abstract void onDespawn();

    public void setX(double x) {
        if (!isLockedX)
        {
            this.x = x;
        }
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        if (!isLockedY)
        {
            this.y = y;
        }
    }

    public double getY() {
        return y;
    }
    
     public void setZ(double z) {

        if (!isLockedY)
        {
            this.z = z;
        }
    }

    public double getZ() {
        return z;
    }

    public double getDistanceTo(GameObject target) {
        return Math.sqrt(Math.pow(target.getX() - getX(), 2) 
            + Math.pow(target.getY() - getY(), 2) 
         + Math.pow(target.getZ() - getZ(), 2));
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

    public boolean hasID(ID i) {
        return getID() == i;
    }
    
    /**
     * @param subdivisions How divided-up it is.
     */
    public void physics_process(int subdivisions)
    {
        setX(getX() + velX / subdivisions);
        setY(getY() + velY / subdivisions);

        if (Math.abs(getVelocityX() - getSoughtVelocityX()) < diffCutoff)
        {
            setVelocityX(getSoughtVelocityX());
        }
        if (Math.abs(getVelocityY() - getSoughtVelocityY()) < diffCutoff)
        {
            setVelocityY(getSoughtVelocityY());
        }

        if (getVelocityX() != getSoughtVelocityX()) {
            double v = - Math.signum(getVelocityX() - getSoughtVelocityX()) * 1;
            velX += v*(K / horizontal_resistance)/subdivisions;
        }

        if (getVelocityY() != getSoughtVelocityY()) {
            double v = - Math.signum(getVelocityY() - getSoughtVelocityY()) * 1;
            velY += v*K2/subdivisions;
        }
    }

    public void transformVelocity(double x_factor, double y_factor)
    {
        setVelocityX(velX * x_factor);
        setVelocityY(velY * y_factor);
    }


    public void setVelocityX(double v)
    {
        velX = v;
    }
    
    public void setVelocityY(double v)
    {
        velY = v;
    }
    
    public double getVelocityX()
    {
        return velX;
    }
    
    public double getVelocityY()
    {
        return velY;
    }
    
    public double getSoughtVelocityX()
    {
        return seekVelX;
    }
    
    public double getSoughtVelocityY()
    {
        return seekVelY;
    }
    
    public void setSoughtVelocityX(double n)
    {
         seekVelX = n;
    }
    
    public void setSoughtVelocityY(double n)
    {
         seekVelY = n;
    }
}


/**
 * Any object in the game.
 * @since 4/16/21
 * @version 4/16/21
 * @author Caleb Copeland
 */
public abstract class Entity  {

    private ID id;
    public Vector3D position;
    public Vector3D velocity = new Vector3D(), acceleration = new Vector3D(),angle = new Vector3D();
    private boolean enabled = true, temporary = false;

    public Vector3D getVelocity()
    {
        return velocity;
    }

    public Entity(Vector3D position, boolean temporary, ID id) {
        this.position = position;
        this.temporary = temporary;
        this.id = id;
    }

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

    public Vector3D getPosition() {
        return this.position;
    }

    public double getDistanceTo(Entity target) {
        return Math.sqrt(Math.pow(target.getPosition().x - position.x, 2)
                + Math.pow(target.getPosition().y - position.y, 2)
                + Math.pow(target.getPosition().z - position.z, 2));
    }

    public ID getID() {
        return id;
    }

    public boolean hasID(ID i) {
        return getID() == i;
    }

    public void physics_process()
    {
        final double DIVISIONS = 4;

        for (int i = 0; i < DIVISIONS; i++) {
            do_kinematics(i);
        }
    }

    public void do_kinematics(int DIVISIONS)
    {
        velocity = velocity.add(acceleration.multiply(1 / DIVISIONS));
        position = position.add(velocity.multiply(1 / DIVISIONS));
    }

}

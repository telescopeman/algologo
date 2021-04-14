import java.awt.Shape;
import java.util.LinkedList;

/**
 * @author Caleb Copeland, RealTutsGML [tick() method only]
 */
public abstract class NPC extends LivingObject {

    private Activity currentActivity = Activity.IDLE;
    private GameObject focusedObject;
    public double sense_range = 400;
    private long timer;


    protected void setFocusedObject(GameObject object)
    {
        focusedObject = object;
    }


    public NPC(double x, double y, ID id, boolean canFly, int HP, Shape shape) {
        super(x, y, id, canFly, HP);
        setBounds(shape);
        timer = System.currentTimeMillis();
    }

    public LinkedList<GameObject> scan(double range)
    {
        LinkedList<GameObject> list = new LinkedList<GameObject>();
        for (int i = 0; i < Handler.object.size(); i++) {
            // find a way to avoid cycling through too many objects
            GameObject tempObject = Handler.object.get(i);
            if (getDistance(tempObject) < range) {
                list.add(tempObject);
            }
        }
        return list;
    }



    @Override
    public void tick() {
        super.tick();
        if(System.currentTimeMillis() - timer > 1000) {
            currentActivity = think();
            timer += 1000;
        }
        act();
    }

    public void act() {
        switch (currentActivity) {
            case IDLE -> idle();
            case ATTACK -> attack(focusedObject);
            default -> think();
        }
    }
    public abstract Activity think();

    public abstract void idle();

    public abstract void attack(GameObject target);

    /**
     * If an NPC takes damage, immediately reconsider the current action.
     * @param dmg Amount of damage to take.
     */
    public void takeDamage(int dmg)
    {
        super.takeDamage(dmg);
        if (currentActivity == Activity.IDLE)
        {
            think();
        }
    }

}

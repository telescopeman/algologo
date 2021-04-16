import java.awt.Shape;
import java.util.LinkedList;

/**
 * @author Caleb Copeland, RealTutsGML [tick() method only]
 */
public abstract class NPC extends LivingObject {
    private Activity currentActivity = Activity.IDLE;
    private GameObject focusedObject;
    private double sense_range = 400;
    private long timer;


    protected void setFocusedObject(GameObject object)
    {
        focusedObject = object;
    }


    public NPC(double x, double y, ID id, boolean canFly, int HP, Shape myShape) {
        super(x, y, id, canFly, HP,true);
        setBounds(myShape);
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
            currentActivity = think(sense_range);
            timer += 1000;
        }
        act();
    }

    public void act() {
        switch (currentActivity) {
            case IDLE:
                idle();
                break;
            
            case ATTACK:
                attack(focusedObject);
                break;
            
            default:
                think(sense_range);
                break;
            
        }
    }
    
    public abstract Activity think(double range);

    public abstract void idle();

    public abstract void attack(GameObject target);

    public void endActivity()
    {
        currentActivity = Activity.IDLE;
        timer = System.currentTimeMillis();
    }

    /**
     * If an NPC takes damage, immediately reconsider the current action.
     * @param damage_amount Amount of damage taken.
     */
    public void onDamaged(int damage_amount, GameObject damage_source)
    {
        if (currentActivity == Activity.IDLE)
        {
            think(sense_range * damage_amount * 2);
        }
    }



}

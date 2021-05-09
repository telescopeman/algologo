import java.awt.*;
import java.awt.geom.Point2D;

public class Spawner extends GameObject {
    protected DynamicObject obj;
    private CooldownTimer timer;

    public Spawner(DynamicObject objType, Rectangle bounds, long freq)
    {
        super(new RenderJob[]{RenderJob.NONE});
        obj = objType;
        myShape = new Sequence(bounds);
        timer = new CooldownTimer(freq);
    }

    public Spawner(DynamicObject objType, Polygon bounds, long freq)
    {
        super(new RenderJob[]{RenderJob.NONE});
        obj = objType;
        myShape = new Sequence(bounds);
        timer = new CooldownTimer(freq);
    }

    public Spawner(DynamicObject  objType, Point2D bounds, long freq)
    {
        super(new RenderJob[]{RenderJob.NONE});
        obj = objType;
        myShape = new Sequence(bounds);
        timer = new CooldownTimer(freq);
    }

    @Override
    public void tick() {

        if (!timer.isExpired()) {
            try {
                spawnObject();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            timer.wake();
        }
    }

    public void spawnObject() throws CloneNotSupportedException {
        Point2D loc = myShape.getRandomPointIn();
        DynamicObject newObject = (DynamicObject) ((DynamicObject) obj).clone();
        newObject.setX(loc.getX());
        newObject.setY(loc.getY());
        Handler.queueForAddition(obj);
        System.out.println("new spawn");
    }

}

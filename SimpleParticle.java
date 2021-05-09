import java.awt.Color;

public class SimpleParticle extends CollidingObject{

    public SimpleParticle(double width, double height, Color color) {
        super(new RenderJob[]{RenderJob.FILL_SHAPE}, 0, 0,ID.UI,false);
        myShape = new Sequence();
        myShape.addPoint(0,0);
        myShape.addPoint(width,0);
        myShape.addPoint(width,height);
        myShape.addPoint(0,height);
        this.color = color;
        loseContact();
        //velY = 5;
    }

    public void tick()
    {
        velY = 2;
    }

    public void collision()
    {
        // do literally nothing
    }
}

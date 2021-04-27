import java.awt.*;

public class Axes extends GameObject {
    public Axes() {

        super(null);
        priority = 999;
    }

    private final int divisions = 5;
    private final int markerSize = 30;


    @Override
    public void render(GraphicsHelper g) {
        g.setColor(Color.black);
        g.setStroke(3);
        // draw the actual axes
        g.drawLine(0,-400,0,400);
        g.drawLine(-400,0,400,0);


        //draw markers
        for (int i = 1; i < divisions; i++)
        {
            int x = i * Window.getMaxX() /divisions;
            g.drawStringIgnoreY(String.valueOf(x),x,-markerSize);
            g.drawStringIgnoreX(String.valueOf(x),-markerSize,x);
            g.drawStringIgnoreY(String.valueOf(-x),-x,-markerSize);
            g.drawStringIgnoreX(String.valueOf(-x),-markerSize,-x);
        }

    }
}

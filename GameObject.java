import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Any object in the game.
 * @version 4/27/21
 * @author RealTutsGML, Caleb Copeland, Joop Eggen [rectangleToPolygon() only]
 */
public abstract class GameObject {
    protected Color color = Game.TERRAIN_COLOR;
    private BasicStroke stroke = new BasicStroke(1);
    protected Sequence myShape;
    protected Collider collider;
    protected int priority = 0;
    private boolean needsRender = false;

    private RenderJob[] renderRoutine;

    public GameObject(RenderJob[] renderRoutine)
    {
        this.renderRoutine = renderRoutine;
    }

    public GameObject()
    {
        this.renderRoutine = new RenderJob[]{RenderJob.FILL_SHAPE};
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color col) {
        color = col;
    }

    /**
     * Returns a color slightly less vibrant than the one inputted. Does not change the original color.
     *
     * @return The color, reduced in vibrancy.
     * @since 4/14/21
     */
    public static Color soften(Color c) {
        float r = c.getRed() / 255f;
        float g = c.getGreen() / 255f;
        float b = c.getBlue() / 255f;
        float m = 0.9f;
        return new Color(m * r, m * g, m * b);
    }

    public boolean getNeedsRender()
    {
        return needsRender;
    }

    public BasicStroke getStroke() {
        return stroke;
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    public Collider getCollider()
    {
        return collider;
    }

    public void despawn() {
        onDespawned();
        Handler.queueForDeletion(this);
    }

    public void onDespawned() {
        // do nothing
    }


    public void tick()
    {

    }

    public void render(GraphicsHelper g) throws CloneNotSupportedException {
        g.setColor(color);
        for (RenderJob job : renderRoutine)
        {
            switch(job)
            {
                case DRAW_OUTLINE:
                {
                    g.drawPolygon(myShape);
                    break;
                }
                case FILL_SHAPE:
                    g.fillPolygon(myShape);
                    break;
                case FILL_BELOW:
                    int left = (int) myShape.toPolygon().getBounds().getMinX();
                    int right = (int) myShape.toPolygon().getBounds().getMaxX();
                    int top = (int) myShape.toPolygon().getBounds().getMinY();
                    int bottom = (int) myShape.toPolygon().getBounds().getMaxY();
                    Sequence poly = (Sequence) ((Sequence)myShape).clone();
                    poly.addPoint(Window.getMaxX(), Window.getMinY());
                    //poly.addPoint(left, Window.getMinY() + bottom);
                    g.fillPolygon(poly);
                    break;
            }
        }
    }

    public void onDealDamage(int damage, LivingObject victim) {};


    public static Polygon rectangleToPolygon(Rectangle rect) {
        final int[] x_points = {rect.x, rect.x + rect.width, rect.x + rect.width, rect.x};
        final int[] y_points = {rect.y, rect.y, rect.y + rect.height, rect.y + rect.height};
        return new Polygon(x_points, y_points, 4);
    }

    public static Polygon adjust(Polygon poly, int offsetX, int offsetY) {
        Polygon altered = new Polygon();
        for (int i = 0; i < poly.npoints; i++) {
            altered.addPoint(poly.xpoints[i] + offsetX, poly.ypoints[i] + offsetY);
        }
        return altered;
    }


    public void onCollided(CollidingObject obj)
    {

    }

    public static Polygon adjust(Rectangle rect, int offsetX, int offsetY) {
        final Polygon POLY = rectangleToPolygon(rect);
        return adjust(POLY, offsetX, offsetY);
    }


    public int getPriority()
    {
        return priority;
    }
}

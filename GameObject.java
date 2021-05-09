import java.awt.*;

/**
 * Any object in the game.
 * @version 5/8/21
 * @author RealTutsGML, Caleb Copeland, Joop Eggen [rectangleToPolygon() only]
 */
public class GameObject implements Cloneable{
    protected Color color = Game.TERRAIN_COLOR;
    private BasicStroke stroke = new BasicStroke(1);
    protected Sequence myShape;
    protected Collider collider;
    protected int priority = 0;
    protected boolean needsRender = true;

    private RenderJob[] renderRoutine;

    public GameObject(RenderJob[] renderRoutine)
    {
        this.renderRoutine = renderRoutine;
    }

    public GameObject()
    {
        this.renderRoutine = new RenderJob[]{RenderJob.FILL_SHAPE};
    }

    public RenderJob[] getRenderRoutine()
    {
        return renderRoutine;
    }

    public Color getColor() {
        return color;
    }

    protected Object clone() throws CloneNotSupportedException
    {
        GameObject obj = new GameObject(renderRoutine);
        return obj;
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
        render_helper(g, myShape);
    }

    protected void render_helper(GraphicsHelper g, Sequence seq) throws CloneNotSupportedException {
        g.setColor(color);
        for (RenderJob job : renderRoutine)
        {
            switch(job)
            {
                case DRAW_OUTLINE:
                {
                    g.drawPolygon(seq);
                    break;
                }
                case FILL_SHAPE:
                {
                    g.fillPolygon(seq);
                    break;
                }
                case FILL_BELOW:
                    int left = (int) seq.getBounds().getMinX();
                    int right = (int) seq.getBounds().getMaxX();
                    int top = (int) seq.getBounds().getMinY();
                    int bottom = (int) seq.getBounds().getMaxY();
                    Sequence poly = seq.clone();
                    poly.addPoint(Window.getMaxX(), Window.getMinY());
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

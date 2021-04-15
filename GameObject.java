import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Any object in the game.
 *
 * @author RealTutsGML, Caleb Copeland, Joop Eggen [rectangleToPolygon() only]
 */
public abstract class GameObject {

    protected ID id;
    private double x, y;
    private int damage = 0;
    private final int INSTANT_KILL = 999;
    private boolean enabled = true, despawns = false;
    private boolean isLockedX = false, isLockedY =false;
    private double surface_friction = 1;


    protected Shape shape;
    private Color color = Game.TERRAIN_COLOR, displayColor;
    private BasicStroke stroke = new BasicStroke(1);

    public GameObject(double x, double y, ID id) {
        setX(x);
        setY(y);
        setID(id);
        displayColor = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color col) {
        color = col;
    }

    public void setDespawns(boolean d)
    {
        despawns = d;
    }

    public void onLandedOn(CollidingObject object)
    {
        object.setHorizontalResistance(surface_friction);
    }

    public void setDisplayColor(Color col) {
        displayColor = col;
    }

    public void applyColor(Graphics g) {
        g.setColor(displayColor);
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

    public BasicStroke getStroke() {
        return stroke;
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    public void applyStroke(Graphics g) {
        ((Graphics2D) g).setStroke(getStroke());
    }

    public boolean intersects(GameObject obj) {
        return intersects(obj.getBounds());
    }

    public void setLockedX(boolean setting)
    {
        isLockedX = setting;
    }

    public void setLockedY(boolean setting)
    {
        isLockedY = setting;
    }

    public boolean intersects(Shape rect) {
        updateForm();
        if (rect instanceof Rectangle) {
            return intersects((Rectangle) rect);
        } else if (rect instanceof Ellipse2D.Double) {
            return intersects((Ellipse2D.Double) rect);
        } else {
            throw new IllegalStateException("Unhandled shape type!");
        }
    }

    public boolean intersects(Rectangle rect) {
        return shape.intersects((Rectangle) rect);
    }

    public boolean intersects(Ellipse2D.Double rect) {
        return shape.intersects(rect.getBounds());
    }

    public abstract void tick();

    public void setEnabled(boolean bool) {
        enabled = bool;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void despawn() {
        onDespawned();
        Handler.queueForDeletion(this);
    }

    public void onDespawned() {
        // do nothing
    }

    public void render(Graphics g, int offsetX, int offsetY) {
        updateForm();

        if (despawns && (Math.abs(getY() + offsetY) > Game.HEIGHT ||
                Math.abs(getX() + offsetX) > Game.WIDTH)) {
            despawn();
        }
        if (getBounds() instanceof Rectangle rect) {
            g.fillPolygon(adjust(rect, offsetX, offsetY));
        } else if (getBounds()  instanceof Polygon) {
            g.fillPolygon(adjust((Polygon) getBounds() , offsetX, offsetY));
        } else if (getBounds()  instanceof Ellipse2D) {
            g.fillOval((int) getX() + offsetX, (int) getY() + offsetY,
                    (int) ((Ellipse2D) getBounds() ).getWidth(), (int) ((Ellipse2D) getBounds() ).getHeight());
        } else {
            throw new IllegalStateException("Unhandled shape type!");
        }
    }

    public void applyStyle(Graphics g) {
        applyColor(g);
        applyStroke(g);
    }

    public Shape getBounds() {
        return shape;
    }

    public void onDealDamage(int damage, LivingObject victim) {};

    public void setBounds(Shape sh) {
        shape = sh;
        updateForm();
    }

    public void updateForm()
    {
        // note: the position of the rectangle object is at the bottom-center of its sprite.
        if (getBounds() instanceof Rectangle rect) {
            rect.x = (int) getX() - getBounds().getBounds().width / 2;
            rect.y = (int) getY() - getBounds().getBounds().height;
        }
        else if (getBounds() instanceof Ellipse2D.Double)
        {
            ((Ellipse2D.Double) shape).x = getX();
            ((Ellipse2D.Double) shape).y = getY();
        }
        else if (getBounds() instanceof Polygon)
        {
            Polygon poly = (Polygon) getBounds();

            // do nothing?
        }
        else
        {
            throw new IllegalStateException("Unhandled shape type for LivingObject!");
        }
    }

    protected void setBounds(Dimension dim) {
        shape = new Rectangle(dim);
    }

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

    public double getDistance(GameObject target) {
        return Math.sqrt(Math.pow(target.getX() - getX(), 2) + Math.pow(target.getY() - getY(), 2));
    }

    /**
     * Gets the amount of damage this object can deal.
     */
    public int getDamage() {

        return damage;
    }

    public void setDamage(int dmg) {
        if (dmg == -1)
            damage = INSTANT_KILL;
        else
            damage = dmg;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

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

    public boolean hasID(ID i) {
        return getID() == i;
    }

    public static Polygon adjust(Rectangle rect, int offsetX, int offsetY) {
        final Polygon POLY = rectangleToPolygon(rect);
        return adjust(POLY, offsetX, offsetY);
    }


}

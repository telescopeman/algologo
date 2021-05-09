public class DynamicObject extends GameObject implements Cloneable {

    private double x, y;

    public DynamicObject(RenderJob[] jobs, double x, double y)
    {
        super(jobs);
        this.x = x;
        this.y = y;
    }
    protected Object clone() throws CloneNotSupportedException {
        RenderJob[] myRenderRoutine = ((GameObject) super.clone()).getRenderRoutine();
        DynamicObject obj = new DynamicObject(myRenderRoutine,x,y);
        return obj;
    }

    @Override
    public void render(GraphicsHelper g) throws CloneNotSupportedException {
        render_helper(g,myShape.offset(getX(),getY()));
    }

    public void setX(double x) {
        this.x = x;
        needsRender = true;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
        needsRender = true;
    }

    public double getY() {
        return y;
    }

    public double getDistance(DynamicObject target) {
        return Math.sqrt(Math.pow(target.getX() - getX(), 2) + Math.pow(target.getY() - getY(), 2));
    }

}

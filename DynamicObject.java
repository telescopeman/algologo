public abstract class DynamicObject extends GameObject {

    private double x, y;

    public DynamicObject(RenderJob[] jobs, double x, double y)
    {
        super(jobs);
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public void setX(double x) {

        this.x = x;

    }

    public double getX() {
        return x;
    }

    public void setY(double y) {

            this.y = y;

    }

    public double getY() {
        return y;
    }

    public double getDistance(DynamicObject target) {
        return Math.sqrt(Math.pow(target.getX() - getX(), 2) + Math.pow(target.getY() - getY(), 2));
    }

}

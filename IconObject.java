public abstract class IconObject extends Entity {
    private SimpleShape icon;

    public IconObject(Vector3D position, SimpleShape icon, boolean temporary, ID id)
    {
        super(position, temporary, id);
        this.icon = icon;
    }
}

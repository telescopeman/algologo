public abstract class IconObject extends ModeledEntity {
    private Icon icon;

    public IconObject(Vector3D position, boolean temporary, Icon icon, ID id)
    {
        super(position, temporary, id);
        this.icon = icon;
    }


}

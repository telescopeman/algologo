import javafx.scene.shape.Mesh.*;

public abstract class ModeledEntity extends Entity {

    private TriangleMesh model;

    public ModeledEntity(Vector3D position, boolean temporary, ID id, Model model)
    {
        super(position,temporary,id);
    }
}

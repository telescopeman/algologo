
/**
 * Write a description of class Level here.
 *
 * @author Caleb Copeland
 * @version 4/12/21
 * @since 4/9/21
 */
public class Level
{
    public final AlgoShape[] shapes;
    public final GameObject[] entities;
    
    public Level(AlgoShape[] theShapes, GameObject[] theEntities)
    {
        this.shapes = theShapes;
        this.entities = theEntities;
    }
}

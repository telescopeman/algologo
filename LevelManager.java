import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * Manages the objects in each level.
 */
public class LevelManager {

    public static Player player = new Player(Game.WIDTH/2,Game.HEIGHT/2);

    public enum LEVELS { BETA_LEVEL, LEVEL_ONE }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public static void loadLevel(LEVELS lev)
    {
        Handler.addObject(player,0,-500);
        Handler.addObject(new Camera());
        Handler.addObject(new DeathBarrier(1000));
        switch (lev)
        {
            case BETA_LEVEL:
                Handler.addObject(makeShape("1+1",0,0, DrawType.FILL_BELOW)); // flat plane
                Handler.addObject(makeShape("x/5",-600,0,
                        new Style(DrawType.FILL_BELOW, new Dimension(Game.WIDTH,Game.HEIGHT)))); // triangle
                Handler.addObject(new ShapeObject(GameObject.rectangleToPolygon(
                        new Rectangle(200,200))),0,-250);
                break;
            default:
                break;
        }
    }

    private static AlgoShape makeShape(String s, int x, int y, DrawType drw)
    {
        return makeShape(s,x,y,new Style(drw));
    }

    private static AlgoShape makeShape(String s, int x, int y, Style style)
    {
        return new AlgoShape(MathExpression.parse(s),style,x,y);
    }
}

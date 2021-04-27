import java.util.Comparator;

public class Priority implements Comparator<GameObject> {
    @Override
    public int compare(GameObject o1, GameObject o2) {
        return (o1.getPriority() - o2.getPriority());
    }
}

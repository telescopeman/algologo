public class DataHelper {

    public static void checkLength(double[] arr, int i)
    {
        if (arr.length < i)
        {
            throw new IllegalArgumentException("Must have length of " + i);
        }
    }
}

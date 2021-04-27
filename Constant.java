public class Constant  implements Term {
    private final Double myValue;
    public Constant(Double i)
    {
        myValue = i;
    }

    @Override
    public double get(double x) {
        return myValue;
    }
}

/**
 * @author Jon Skeet, Caleb Copeland
 */

public enum Operator
{
    ADD("+") {
        @Override public double apply(double x1, double x2) {
            return x1 + x2;
        }
    },
    SUBTRACT("-") {
        @Override public double apply(double x1, double x2) {
            return x1 - x2;
        }
    },
    POWER ("^"){
        @Override public double apply(double x1, double x2) {
            return Math.pow(x1,x2);
        }
    },
    MULTIPLY ("*"){
        @Override public double apply(double x1, double x2) {
        return x1*x2;
    }
},
DIVIDE ("/"){
@Override public double apply(double x1, double x2) {
return x1/x2;
}
};
// You'd include other operators too...

private final String text;

private Operator(String text) {
    this.text = text;
}

// private Operator get(String t)
// {
// return Operator(t);
// }

// Yes, enums *can* have abstract methods. This code compiles...
public abstract double apply(double x1, double x2);

@Override public String toString() {
    return text;
}
};
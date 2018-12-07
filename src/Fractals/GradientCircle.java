package Fractals;

public class GradientCircle implements Fractal {

    private final double pow;

    public GradientCircle(double pow) {
        this.pow = pow;
    }

    @Override
    public double getColor(double x, double y) {
        double d = Math.pow(x * x + y * y, pow);
        if (d < 1)
            return 1 - d;
        else
            return 0;
    }
}

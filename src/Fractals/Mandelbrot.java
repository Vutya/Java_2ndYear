package Fractals;

public class Mandelbrot implements Fractal{

    private final int iters;

    public Mandelbrot(int iters) {
        this.iters = iters;
    }

    @Override
    public double getColor(double x, double y) {
        double rez = 0;
        double imz = 0;
        double R = 1000000000;
        for (int i = 0; i < iters; i++) {
            double rez_tmp = rez * rez - imz * imz + x;
            double imz_tmp = 2 * rez * imz + y;
            rez = rez_tmp;
            imz = imz_tmp;
            if (rez * rez + imz * imz > R * R)
                return (double) i / iters;
        }
        return 1;
    }
}

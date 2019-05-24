package Fractals;

public class Mandelbrot implements Fractal {

    private final int iters;

    Mandelbrot(int iters) {
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
            double abs2 = rez * rez + imz * imz;
            if (abs2 > R * R) {
                double fix = Math.log(Math.log(abs2) / Math.log(R) / 2) / Math.log(2);
                return (i - fix) / iters;
            }
        }
        return 1;
    }
}

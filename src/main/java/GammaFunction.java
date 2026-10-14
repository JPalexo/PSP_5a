// funcion gamma para enteros y para mitades (1/2, 3/2, 5/2, ...)
public class GammaFunction {

    private double gammaValue;

    // para enteros: G(n) = (n - 1)!
    public double computeIntGamma(int intValue) {
        gammaValue = 1;

        for (int i = 2; i < intValue; i++) {
            gammaValue = gammaValue * i;
        }

        return gammaValue;
    }

    // para mitades: G(x) = (x - 1)(x - 2)...(1/2) * raiz(pi)
    public double computeDblGamma(double doubleValue) {
        gammaValue = Math.sqrt(Math.PI);
        double actual = 0.5;

        while (actual < doubleValue) {
            gammaValue = gammaValue * actual;
            actual = actual + 1;
        }

        return gammaValue;
    }
}

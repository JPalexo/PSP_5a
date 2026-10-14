// una pasada de la regla de Simpson sobre la distribucion t, paso a paso
// como en la Tabla 2 del 5a
public class SimpsonIntegration {

    private int intNumSeg;
    private double dblW;
    private int intDOF;
    private double dblX;
    private double[] dblTotXi;
    private double[] dblFirstBaseTerms;
    private double dblExponent;
    private double dblCoeff;
    private double[] dblFxi;
    private double[] dblFinalTerms;
    private double dblFinalValue;

    // ancho de cada segmento: W = x / num_seg
    public double computeW(int intNumSeg, double dblX) {
        this.intNumSeg = intNumSeg;
        this.dblX = dblX;
        dblW = dblX / intNumSeg;
        return dblW;
    }

    // los puntos xi = i * W, de 0 hasta x
    public double[] computeXi(int intNumSeg) {
        dblTotXi = new double[intNumSeg + 1];

        for (int i = 0; i <= intNumSeg; i++) {
            dblTotXi[i] = i * dblW;
        }

        return dblTotXi;
    }

    // 1 + xi^2 / dof
    public double[] computeFirstBaseTerms(int intNumSeg, double[] dblTotXi,
            int intDOF) {
        this.intDOF = intDOF;
        dblFirstBaseTerms = new double[intNumSeg + 1];

        for (int i = 0; i <= intNumSeg; i++) {
            dblFirstBaseTerms[i] = 1 + ((dblTotXi[i] * dblTotXi[i]) / intDOF);
        }

        return dblFirstBaseTerms;
    }

    // -(dof + 1) / 2
    public double computeExponent(int intDOF) {
        dblExponent = -(intDOF + 1) / 2.0;
        return dblExponent;
    }

    // G((dof + 1) / 2) / ( raiz(dof * pi) * G(dof / 2) )
    public double computeCoefficient(int intDOF) {
        double arriba = gamma((intDOF + 1) / 2.0);
        double abajo = Math.sqrt(intDOF * Math.PI) * gamma(intDOF / 2.0);
        dblCoeff = arriba / abajo;
        return dblCoeff;
    }

    // F(xi) = coeficiente * (1 + xi^2 / dof) ^ exponente
    public double[] computeFxi(int intNumSeg) {
        dblFxi = new double[intNumSeg + 1];

        for (int i = 0; i <= intNumSeg; i++) {
            dblFxi[i] = dblCoeff * Math.pow(dblFirstBaseTerms[i], dblExponent);
        }

        return dblFxi;
    }

    // (W / 3) * multiplicador * F(xi); el multiplicador es 1 en los
    // extremos, 4 en los impares y 2 en los pares
    public double[] computeFinalTerms(int intNumSeg) {
        dblFinalTerms = new double[intNumSeg + 1];

        for (int i = 0; i <= intNumSeg; i++) {
            int multiplicador = 2;

            if (i == 0 || i == intNumSeg) {
                multiplicador = 1;
            } else if (i % 2 == 1) {
                multiplicador = 4;
            }

            dblFinalTerms[i] = (dblW / 3) * multiplicador * dblFxi[i];
        }

        return dblFinalTerms;
    }

    // p = suma de todos los terminos
    public double computeFinalValue(int intNumSeg) {
        dblFinalValue = 0;

        for (int i = 0; i <= intNumSeg; i++) {
            dblFinalValue = dblFinalValue + dblFinalTerms[i];
        }

        return dblFinalValue;
    }

    // usa la gamma de enteros o la de mitades segun el valor
    private double gamma(double valor) {
        GammaFunction gammaFunction = new GammaFunction();

        if (valor == Math.floor(valor)) {
            return gammaFunction.computeIntGamma((int) valor);
        }

        return gammaFunction.computeDblGamma(valor);
    }
}

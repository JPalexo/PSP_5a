// arma cada prueba de la Tabla 1: integra la distribucion t de 0 a x y
// escribe el resultado
public class Logic {

    private int intNumSeg;
    private double dblE;
    private int intDOF;
    private double dblX;

    public void logic1a(int prueba) {
        Output output = new Output();

        double[] valoresX = {1.1, 1.1812, 2.750};
        int[] valoresDOF = {9, 10, 30};

        double p = integrar(valoresX[prueba - 1], valoresDOF[prueba - 1]);

        String resultado = String.format("Test %d  x=%.4f  dof=%d  p=%.5f",
                prueba, dblX, intDOF, p);

        System.out.println(resultado);
        output.writeData("data/out_test" + prueba + ".txt", resultado + "\n");
    }

    // duplica los segmentos hasta que dos resultados seguidos difieran
    // menos que el error aceptable
    public double integrar(double x, int dof) {
        dblX = x;
        intDOF = dof;
        intNumSeg = 10;
        dblE = 0.00001;

        double anterior = calcularPasada();
        intNumSeg = intNumSeg * 2;
        double actual = calcularPasada();

        while (Math.abs(actual - anterior) > dblE) {
            anterior = actual;
            intNumSeg = intNumSeg * 2;
            actual = calcularPasada();
        }

        return actual;
    }

    // una pasada de Simpson con los segmentos actuales
    private double calcularPasada() {
        SimpsonIntegration simpson = new SimpsonIntegration();

        simpson.computeW(intNumSeg, dblX);
        double[] xi = simpson.computeXi(intNumSeg);
        simpson.computeFirstBaseTerms(intNumSeg, xi, intDOF);
        simpson.computeExponent(intDOF);
        simpson.computeCoefficient(intDOF);
        simpson.computeFxi(intNumSeg);
        simpson.computeFinalTerms(intNumSeg);

        return simpson.computeFinalValue(intNumSeg);
    }
}

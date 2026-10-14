// arma cada prueba: lee x y dof, integra la distribucion t y escribe el
// resultado
public class Logic {

    private String dataX;
    private String dataDof;
    private String[] arrDataX;
    private String[] arrDataDof;
    private double thisX;
    private double thisDof;

    public void logic5a(int prueba) {
        Input input = new Input();
        Data data = new Data();
        Output output = new Output();

        dataX = input.readData("data/x.txt");
        dataDof = input.readData("data/dof.txt");
        arrDataX = data.saveData(dataX);
        arrDataDof = data.saveData(dataDof);

        thisX = Double.parseDouble(arrDataX[prueba - 1]);
        thisDof = Double.parseDouble(arrDataDof[prueba - 1]);

        DistribucionT distribucion = new DistribucionT(thisDof);
        IntegracionSimpson integracion = new IntegracionSimpson(distribucion);
        double p = integracion.integrar(thisX);

        String resultado = String.format("Test %d  x=%.4f  dof=%.0f  p=%.5f",
                prueba, thisX, thisDof, p);

        System.out.println(resultado);
        output.writeData("data/out_test" + prueba + ".txt", resultado + "\n");
    }
}

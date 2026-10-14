// funcion de la distribucion t para unos grados de libertad (dof)
public class DistribucionT {

    private double dof;

    public DistribucionT(double dof) {
        this.dof = dof;
    }

    // gamma para enteros y mitades: G(x) = (x - 1) * G(x - 1)
    public double gamma(double x) {
        if (x == 1) {
            return 1;
        }
        if (x == 0.5) {
            return Math.sqrt(Math.PI);
        }
        return (x - 1) * gamma(x - 1);
    }

    public double calcularF(double x) {
        double arriba = gamma((dof + 1) / 2);
        double abajo = Math.sqrt(dof * Math.PI) * gamma(dof / 2);
        double base = 1 + ((x * x) / dof);
        double potencia = Math.pow(base, -(dof + 1) / 2);

        return (arriba / abajo) * potencia;
    }
}

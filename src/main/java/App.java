// punto de entrada: corre una prueba (1 a 3) o las tres si no se indica
public class App {

    public static void main(String[] args) {
        Logic logic = new Logic();

        if (args.length == 0) {
            for (int i = 1; i <= 3; i++) {
                logic.logic1a(i);
            }
        } else {
            int prueba = Integer.parseInt(args[0]);

            if (prueba >= 1 && prueba <= 3) {
                logic.logic1a(prueba);
            } else {
                System.out.println("La prueba debe ser 1, 2 o 3");
            }
        }
    }
}

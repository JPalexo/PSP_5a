import java.io.BufferedReader;
import java.io.FileReader;

// lee un archivo de texto y lo devuelve completo, una linea por renglon
public class Input {

    String data;
    BufferedReader br = null;

    public String readData(String inFile) {
        String texto = "";

        try {
            br = new BufferedReader(new FileReader(inFile));

            while ((data = br.readLine()) != null) {
                texto = texto + data + "\n";
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error al leer el archivo: "
                    + inFile);
        }

        return texto;
    }
}

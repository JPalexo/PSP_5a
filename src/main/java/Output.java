import java.io.PrintWriter;

// escribe un texto en un archivo
public class Output {

    public void writeData(String outFile, String outText) {
        try {
            PrintWriter pw = new PrintWriter(outFile);
            pw.print(outText);
            pw.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error al escribir el archivo");
        }
    }
}

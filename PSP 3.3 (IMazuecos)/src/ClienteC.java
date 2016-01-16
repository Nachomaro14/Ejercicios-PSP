import java.net.*;
import java.io.*;

public class ClienteC {
    public static void main(String[] arg) throws Exception{
        String Host = "localhost";
        int Puerto = 6000;
        
        System.out.println("Programa Cliente C iniciado.");
        Socket ClienteC = new Socket(Host, Puerto);
        
        DataOutputStream flujoSalida = new DataOutputStream(ClienteC.getOutputStream());
        
        flujoSalida.writeUTF("Saludos al Servidor desde el Cliente C.");
        
        DataInputStream flujoEntrada = new DataInputStream(ClienteC.getInputStream());
        
        System.out.println("Recibiendo mensaje del Servidor: \n\t" +
                flujoEntrada.readUTF());
        
        flujoEntrada.close();
        flujoSalida.close();
        ClienteC.close();
    }
}
import java.net.*;
import java.io.*;

public class ClienteA {
    public static void main(String[] arg) throws Exception{
        String Host = "localhost";
        int Puerto = 6000;
        
        System.out.println("Programa Cliente A iniciado.");
        Socket ClienteA = new Socket(Host, Puerto);
        
        DataOutputStream flujoSalida = new DataOutputStream(ClienteA.getOutputStream());
        
        flujoSalida.writeUTF("Saludos al Servidor desde el Cliente A.");
        
        DataInputStream flujoEntrada = new DataInputStream(ClienteA.getInputStream());
        
        System.out.println("Recibiendo mensaje del Servidor: \n\t" +
                flujoEntrada.readUTF());
        
        flujoEntrada.close();
        flujoSalida.close();
        ClienteA.close();
    }
}
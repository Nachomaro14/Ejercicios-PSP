import java.net.*;
import java.io.*;

public class ClienteB {
    public static void main(String[] arg) throws Exception{
        String Host = "localhost";
        int Puerto = 6000;
        
        System.out.println("Programa Cliente B iniciado.");
        Socket ClienteB = new Socket(Host, Puerto);
        
        DataOutputStream flujoSalida = new DataOutputStream(ClienteB.getOutputStream());
        
        flujoSalida.writeUTF("Saludos al Servidor desde el Cliente B.");
        
        DataInputStream flujoEntrada = new DataInputStream(ClienteB.getInputStream());
        
        System.out.println("Recibiendo mensaje del Servidor: \n\t" +
                flujoEntrada.readUTF());
        
        flujoEntrada.close();
        flujoSalida.close();
        ClienteB.close();
    }
}
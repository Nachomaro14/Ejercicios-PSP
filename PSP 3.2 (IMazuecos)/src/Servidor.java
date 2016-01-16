import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException{
        int numeroPuerto = 6000;
        Socket clienteConectado = null;
        OutputStream salida = null;
        try{
            ServerSocket servidor = new ServerSocket(numeroPuerto);
            System.out.println("Enviamos un mensaje al cliente...");
            clienteConectado = servidor.accept();
            salida = clienteConectado.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);
            flujoSalida.writeUTF("Saludos, cliente.");
            
            salida.close();
            flujoSalida.close();
            clienteConectado.close();
            servidor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
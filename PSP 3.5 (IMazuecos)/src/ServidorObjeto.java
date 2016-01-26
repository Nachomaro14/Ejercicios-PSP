import java.io.*;
import java.net.*;

public class ServidorObjeto {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        //Puerto por el que escucha el servidor: 9786
        DatagramSocket serverSocket = new DatagramSocket(9786);
        byte[] recibidos = new byte[1024];
        
        System.out.println("Esperando datagrama...");
        //RECIBO DATAGRAMA        
        recibidos = new byte[1024];
        DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
        serverSocket.receive(paqRecibido);//recibo el datagrama
        //CONVERTIMOS BYTES A OBJETO
        ByteArrayInputStream bais = new ByteArrayInputStream(recibidos);
        ObjectInputStream in = new ObjectInputStream(bais);
        Persona persona = (Persona) in.readObject();//Obtengo objeto
        System.out.println("Recibido: " + persona.getNombre() + " * " + persona.getEdad());
        in.close();
    }
}
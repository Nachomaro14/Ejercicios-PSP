import java.io.*;
import java.net.*;

public class ClienteObjeto {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Persona persona = new Persona("Maria", 22);
        //CONVERTIMOS OBJETO A BYTES    
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bs);
        out.writeObject(persona);//escribir objeto en el stream
        out.close();//cerrar stream
        byte[] bytes = bs.toByteArray();//objeto en bytes
        
        DatagramSocket clientSocket = new DatagramSocket();//socket cliente
        
        //DATOS DEL SERVIDOR al que enviar mensaje
        InetAddress IPServidor = InetAddress.getLocalHost();//localhost
        int puerto = 9786;//puerto por el que escucha

        //ENVIANDO DATAGRAMA AL SERVIDOR
        System.out.println("Envío: " + persona.getNombre() + " * " + persona.getEdad());
        DatagramPacket envio = new DatagramPacket (bytes, bytes.length, IPServidor, puerto);
        clientSocket.send(envio);
    }
}
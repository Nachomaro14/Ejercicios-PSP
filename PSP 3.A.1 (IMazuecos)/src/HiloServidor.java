import java.io.*;
import java.net.*;
import javax.swing.*;

public class HiloServidor extends Thread{
    Socket socket;
    int identificador;
    static JLabel texto = new JLabel();
    DataOutputStream salida;
    ObjectInputStream inObjeto;
    
    public HiloServidor(Socket s, int idCliente) throws IOException{
        socket = s;
        inObjeto = new ObjectInputStream(socket.getInputStream());
        salida = new DataOutputStream(socket.getOutputStream());
    }
    
    public void run(){
        try{
            AccesoDatos adat = new AccesoDatos();
            while(true){
                Departamentos depar = (Departamentos) inObjeto.readObject();
                String respuesta = adat.insertarDepartamento(depar);
                salida.writeUTF(respuesta);
            }
        }catch(IOException | ClassNotFoundException e){
            try{
                inObjeto.close();
                salida.close();
                socket.close();
            }catch(IOException ee){
                ee.printStackTrace();
            }
        }
    }
}
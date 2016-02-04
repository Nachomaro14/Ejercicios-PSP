import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.JOptionPane;

public class Cliente implements Runnable{
    private static final long serialVersionUID = 1L;
    boolean repetir = true;
    static Socket socket;
    
    ObjectInputStream inObjeto;
    
    public void run(){
        while(repetir){
            try{
                inObjeto = new ObjectInputStream(socket.getInputStream());
                Departamentos d = null;
                d = (Departamentos) inObjeto.readObject();
                if(d == null){
                    JOptionPane.showMessageDialog(null, "El Departamento no ha sido recibido correctamente");
                }else{
                    JOptionPane.showMessageDialog(null, "Departamento insertado:\n" + "Nº: " + d.getDeptNo() + "\n"
                            + "Nombre: " + d.getDnombre() + "\n" + "Localidad: " + d.getLoc());
                }
            }catch(SocketException s){
                repetir = false;
            }catch(IOException e){
                e.printStackTrace();
                repetir = false;
            }catch(ClassNotFoundException e){
                e.printStackTrace();
                repetir = false;
            }
        }
        try{
            socket.close();
            System.exit(0);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.JOptionPane;

public class Cliente implements Runnable{
    private static final long serialVersionUID = 1L;
    boolean repetir = true;
    static Socket socket;
    
    public void run(){
        while(repetir){
            try{
                ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
                Departamentos d = (Departamentos) inObjeto.readObject();
                if(d == null){
                    JOptionPane.showMessageDialog(null, "El Departamento no ha sido recibido correctamente");
                }else{
                    JOptionPane.showMessageDialog(null, "Departamento insertado:\n" + "Nº: " + d.getDeptNo() + "\n"
                            + "Nombre: " + d.getDnombre() + "\n" + "Localidad: " + d.getLoc());
                }
            }catch(SocketException s){
                repetir = false;
                JOptionPane.showMessageDialog(null, "ERROR DE SOCKET");
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "ERROR DE IO");
                e.printStackTrace();
                repetir = false;
            }catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "ERROR DE CLASE");
                e.printStackTrace();
                repetir = false;
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "ERROR DE VALOR NULO");
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
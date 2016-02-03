import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ServidorChat extends JFrame implements ActionListener{
    private static long serialVersionUID = 1L;
    static ServerSocket servidor;
    static final int PUERTO = 44444;
    static int CONEXIONES = 0;
    static int ACTUALES = 0;
    static int MAXIMO = 10;
    
    static JTextField mensaje = new JTextField("");
    static JTextField mensaje2 = new JTextField("");
    private JScrollPane scrollpane1;
    static JTextArea textarea;
    JButton salir = new JButton("Salir");
    static Socket tabla[] = new Socket[10];
    
    public ServidorChat(){
        super(" VENTANA DEL SERVIDOR DE CHAT ");
        setLayout(null);
        mensaje.setBounds(10, 10, 400, 30);
        add(mensaje);
        mensaje.setEditable(false);
        mensaje2.setBounds(10, 348, 400, 30);
        add(mensaje2);
        mensaje2.setEditable(false);
        
        textarea = new JTextArea();
        scrollpane1 = new JScrollPane(textarea);
        
        scrollpane1.setBounds(10, 50, 400, 300);
        add(scrollpane1);
        salir.setBounds(420, 10, 100, 30);
        add(salir);
        
        textarea.setEditable(false);
        salir.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == salir){
            try{
                servidor.close();
            }catch(IOException e1){
                e1.printStackTrace();
            }
            System.exit(0);
        }
    }
    
    public static void main(String args[]) throws IOException{
        servidor = new ServerSocket(PUERTO);
        System.out.println("Servidor iniciado...");
        ServidorChat pantalla = new ServidorChat();
        pantalla.setBounds(0, 0, 540, 400);
        pantalla.setVisible(true);
        mensaje.setText("NUMERO DE CONEXIONES ACTUALES: " + 0);
        
        while(CONEXIONES < MAXIMO){
            Socket s = new Socket();
            try{
                s = servidor.accept();
            }catch(SocketException ns){
                break;
            }
            tabla[CONEXIONES] = s;
            CONEXIONES++;
            ACTUALES++;
            HiloServidor hilo = new HiloServidor(s);
            hilo.start();
        }
        
        if(!servidor.isClosed()){
            try{
                mensaje2.setForeground(Color.red);
                mensaje2.setText("MÁXIMO Nº DE CONEXIONES ESTABLECIDAD: " + CONEXIONES);
                servidor.close();
            }catch(IOException e1){
                e1.printStackTrace();
            }
        }
        System.out.println("Servidor finalizado...");
    }
}

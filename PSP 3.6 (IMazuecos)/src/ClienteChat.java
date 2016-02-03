import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClienteChat extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    Socket socket = null;
    DataInputStream fentrada;
    DataOutputStream fsalida;
    String nombre;
    static JTextField mensaje = new JTextField();
    private JScrollPane scrollpane1;
    static JTextArea textarea1;
    JButton boton = new JButton("Enviar");
    JButton desconectar = new JButton("Salir");
    boolean repetir = true;
    
    public ClienteChat(Socket s, String nombre){
        super(" CONEXIÓN DEL CLIENTE CHAT: " + nombre);
        setLayout(null);
        mensaje.setBounds(10, 10, 400, 30);
        add(mensaje);
        textarea1 = new JTextArea();
        scrollpane1 = new JScrollPane(textarea1);
        scrollpane1.setBounds(10, 50, 400, 300);
        add(scrollpane1);
        boton.setBounds(420, 10, 100, 30);
        add(boton);
        desconectar.setBounds(420, 50, 100, 30);
        textarea1.setEditable(false);
        boton.addActionListener(this);
        desconectar.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        socket = s;
        this.nombre = nombre;
        
        try{
            fentrada = new DataInputStream(socket.getInputStream());
            fsalida = new DataOutputStream((socket.getOutputStream()));
            String texto = " > Entrada en el Chat... " + nombre;
            fsalida.writeUTF(texto);
        }catch(IOException e){
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == boton){
            String texto = nombre + "> " + mensaje.getText();
            try{
                mensaje.setText("");
                fsalida.writeUTF(texto);
            }catch(IOException e1){
                e1.printStackTrace();
            }
        }
        
        if(e.getSource() == desconectar){
            String texto = " > Abandona el Chat... " + nombre;
            try{
                fsalida.writeUTF(texto);
                fsalida.writeUTF("*");
                repetir = false;
            }catch(IOException e1){
                e1.printStackTrace();
            }
        }
    }
    
    public void ejecutar(){
        String texto = "";
        while(repetir){
            try{
                texto = fentrada.readUTF();
                textarea1.setText(texto);
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(), "<<MENSAJE DE ERROR:2>>", JOptionPane.ERROR_MESSAGE);
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
    
    public static void main(String args[]){
        int puerto = 44444;
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick:");
        Socket s = null;
        try{
            s = new Socket("192.168.1.39", puerto);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(), "<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        if(!nombre.trim().equals("")){
            ClienteChat cliente = new ClienteChat(s, nombre);
            cliente.setBounds(0, 0, 540, 400);
            cliente.setVisible(true);
            cliente.ejecutar();
        }else{
            System.out.println("El nombre está vacío...");
        }
    }
}
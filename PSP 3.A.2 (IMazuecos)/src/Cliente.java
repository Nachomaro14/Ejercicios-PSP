import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Cliente extends JFrame implements ActionListener, Runnable{
    private static final long serialVersionUID = 1L;
    static JTextField empconsultar = new JTextField(2);
    static JLabel etiqueta = new JLabel("Empleado a consultar:");
    private JScrollPane scrollpane1;
    static JTextArea textarea1;
    JButton boton = new JButton("Consultar");
    JButton desconectar = new JButton("Salir");
    boolean repetir = true;
    static Socket socket;
    
    ObjectInputStream inObjeto;
    DataOutputStream salida;
    
    public Cliente(Socket s){
        super("OPERACIONES CON BD");
        socket = s;
        try{
            salida = new DataOutputStream(socket.getOutputStream());
            inObjeto = new ObjectInputStream(socket.getInputStream());
        }catch(IOException e){
            e.printStackTrace();
            System.exit(0);
        }
        setLayout(null);
        etiqueta.setBounds(10, 10, 200, 30);
        add(etiqueta);
        empconsultar.setBounds(210, 10, 50, 30);
        add(empconsultar);
        
        textarea1 = new JTextArea();
        scrollpane1 = new JScrollPane(textarea1);
        scrollpane1.setBounds(10, 50, 400, 300);
        add(scrollpane1);
        boton.setBounds(420, 10, 100, 30);
        add(boton);
        desconectar.setBounds(420, 50, 100, 30);
        add(desconectar);
        
        textarea1.setEditable(false);
        boton.addActionListener(this);
        desconectar.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == boton){
            try{
                salida.writeUTF(empconsultar.getText());
            }catch(IOException e1){
                e1.printStackTrace();
            }
        }
        
        if(e.getSource() == desconectar){
            try{
                socket.close();
            }catch(IOException e1){
                e1.printStackTrace();
            }
            System.exit(0);
        }
    }
    
    public void run(){
        String texto = "";
        while(repetir){
            try{
                Empleados e = (Empleados) inObjeto.readObject();
                textarea1.setText("");
                textarea1.setForeground(Color.blue);
                if(e == null){
                    textarea1.setForeground(Color.red);
                    PintaMensaje("<<EL EMPLEADO NO EXISTE>>");
                }else{
                    texto = "Empleado: " + e.getEmpNo() + "\n   " + "Oficio: " + e.getOficio() + "\tApellido: " + e.getApellido() +
                            "\n    " + "Comisión: " + e.getComision() + "\tDirección: " + e.getDir() + "\n    " +
                            "Alta: " + e.getFechaAlt() + "\tSalario: " + e.getSalario() +
                            "\n    " + "Departamento: " + e.getDepartamentos().getDnombre();
                    textarea1.append(texto);
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
    
    public void PintaMensaje(String mensaje){
       textarea1.append("\n============================================");
       textarea1.append("\n" + mensaje);
       textarea1.append("\n============================================");
    }
    
    public static void main(String args[]) throws UnknownHostException, IOException{
        int puerto = 44441;
        Socket s = new Socket("localhost", puerto);
        Cliente hiloC = new Cliente(s);
        hiloC.setBounds(0, 0, 540, 400);
        hiloC.setVisible(true);
        new Thread(hiloC).start();
    }
}
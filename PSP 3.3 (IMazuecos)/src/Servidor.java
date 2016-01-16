import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca cuántos clientes puede recibir el servidor:");
        int n = sc.nextInt();
        int numeroPuerto = 6000;
        Socket clienteConectado = null;
        InputStream entrada = null;
        OutputStream salida = null;
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;
        
        try{
            for(int i = 0; i < n; i++){
                System.out.print("Esperando al cliente "+(i+1)+"...");
                clienteConectado = servidor.accept();
                entrada = clienteConectado.getInputStream();
                
                flujoEntrada = new DataInputStream(entrada);
                
                System.out.println("Recibiendo mensaje del Cliente: \n\t" +
                        flujoEntrada.readUTF());
                
                salida = clienteConectado.getOutputStream();
                flujoSalida = new DataOutputStream(salida);
                
                flujoSalida.writeUTF("Saludos. Eres el cliente número "+(i+1)+".");
            }
            entrada.close();
            flujoEntrada.close();
            salida.close();
            flujoSalida.close();
            clienteConectado.close();
            servidor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
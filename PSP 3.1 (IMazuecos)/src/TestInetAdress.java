
import java.net.*;
import java.util.Scanner;

public class TestInetAdress {
    public static void main(String[] args){
        InetAddress dir = null;
        
        System.out.println("Introduzca el nombre de la máquina o la IP.");
        Scanner sc = new Scanner(System.in);
        String maquina = sc.nextLine();
        
        System.out.println("===========================================");
        System.out.println("SALIDA PARA "+maquina+":");
        
        try{
            dir = InetAddress.getByName(maquina);
            pruebaMetodos(dir);
        }catch(UnknownHostException e1){
            e1.printStackTrace();
        }
    }
    
    private static void pruebaMetodos(InetAddress dir){
        System.out.println("\tMetodo getByName(): " + dir);
        InetAddress dir2 = null;
        try{
            dir2 = InetAddress.getLocalHost();
        }catch(UnknownHostException e2){
            e2.printStackTrace();
        }            
        System.out.println("\tMetodo getLocalHost(): " + dir2);
        System.out.println("\tMetodo getHostName(): " + dir.getHostName());
        System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMetodo toString(): " + dir.toString());
        System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }
}
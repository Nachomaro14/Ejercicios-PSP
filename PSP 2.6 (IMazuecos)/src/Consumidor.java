
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor extends Thread{
    private Cola cola;
    private String s;
    
    public Consumidor(Cola c){
        cola = c;
    }
    
    public void run(){
        for(int i = 0; i < 15; i++){
            System.out.print(cola.get());
        }
    }
}
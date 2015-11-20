public class Cola {
    private String cadena = "";
    private boolean disponible = false;
    
    public synchronized String get(){
        while(disponible == false){
            try{
                wait();
            }catch(InterruptedException e){}
        }
        disponible = false;
        notifyAll();
        return cadena;
    }
    
    public synchronized void put(String s){
        while(disponible == true){
            try{
                wait();
            }catch(InterruptedException e){}
        }
        cadena =  s;
        disponible = true;
        notifyAll();
    }
}
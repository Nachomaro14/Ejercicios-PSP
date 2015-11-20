public class Productor extends Thread{
    private Cola cola;
    private String ping;
    private String pong;
    int n = 0;
    
    public Productor(Cola c){
        cola = c;
        ping = "PING ";
        pong = "PONG ";
    }
    
    public void run(){
        for(int i = 0; i < 15; i++){
            if(n == 0){
                cola.put(ping);
                n = 1;
            }else{
                cola.put(pong);
                n = 0;
            }
        }
    }
}

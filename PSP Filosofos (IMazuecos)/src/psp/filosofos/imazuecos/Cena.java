package psp.filosofos.imazuecos;

import java.util.ArrayList;

public class Cena {
    
    //Interpretamos a los palillos como hilos y que de la actividad de ellos dependerá
    //que coman los filósofos.
    static public class Palillo extends Thread{
        private boolean enUso = true;
        
        public void run(){
            while(enUso){
                try {
                    //Cada palillo será utilizado durante 2 segundos (cada hilo se mantendrá 
                    //dormido 2 segundos).
                    sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                enUso = false;
            }
        }
    }
    
    //Creamos un método para reiniciar el hilo una vez finalice.
    public static synchronized Palillo reiniciar(Palillo p){
        p = new Palillo();
        p.start();
        return p;
    }
    
    static public class Tiempo extends Thread{
        //Manejaremos el recorrido de cada hilo (o palillo) recorriendo un ArrayList infinitamente.
        ArrayList<Palillo> palillos = new ArrayList<>();
        private boolean infinito = true;
        
        public void run(){
            //Mediante este FOR recorreremos una y otra vez la situación de cada filósofo.
            for(int i = 0; i <= 5; i++){
                //En el caso en el que alcancemos la posición del último filósofo tendremos que
                //reiniciar la cuenta atrás del FOR.
                if(i == 4){
                    try {
                        if(!palillos.get(i).isAlive()){
                            palillos.set(i, reiniciar(palillos.get(i)));
                        }
                        //Como cada palillo se usa 2 segundos, cada filósofo comerá solo 1 segundo.
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if(palillos.get(4).isAlive() && palillos.get(0).isAlive()){
                        System.out.println("El filósofo 5 come.");
                    }else if(!palillos.get(4).isAlive() && palillos.get(0).isAlive()){
                        System.out.println("El filósofo 5 piensa.");
                    }
                    //Reiniciamos la cuenta atrás del FOR. Ponemos -1 porque llega al final del IF
                    //y el FOR sumará +1 justo después, posicionándose en el filósofo 0.
                    i = -1;
                }else{
                    try {
                        if(!palillos.get(i).isAlive()){
                            palillos.set(i, reiniciar(palillos.get(i)));
                        }
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if(palillos.get(0).isAlive() && palillos.get(1).isAlive()){
                        System.out.println("El filósofo 1 come.");
                    }else if(!palillos.get(0).isAlive() && palillos.get(1).isAlive()){
                        System.out.println("El filósofo 1 piensa.");
                    }
                    if(palillos.get(1).isAlive() && palillos.get(2).isAlive()){
                        System.out.println("El filósofo 2 come.");
                    }else if(!palillos.get(1).isAlive() && palillos.get(2).isAlive()){
                        System.out.println("El filósofo 2 piensa.");
                    }
                    if(palillos.get(2).isAlive() && palillos.get(3).isAlive()){
                        System.out.println("El filósofo 3 come.");
                    }else if(!palillos.get(2).isAlive() && palillos.get(3).isAlive()){
                        System.out.println("El filósofo 3 piensa.");
                    }
                    if(palillos.get(3).isAlive() && palillos.get(4).isAlive()){
                        System.out.println("El filósofo 4 come.");
                    }else if(!palillos.get(3).isAlive() && palillos.get(4).isAlive()){
                        System.out.println("El filósofo 4 piensa.");
                    }
                }
                //No ha sido implementada la comprobación paralela de que haya 2 filósofos comiendo
                //a la vez. Para ello habría que iniciar un temporizador que recorra la mesa 2 o 3
                //posiciones por delante del temporizador actual o habría que modificar el procedimiento
                //del ejercicio al completo, de manera que se compruebe toda la mesa instantáneamente y
                //los hilos (o palillos) se ejecuten paralelamente.
                
                //Para realizar una implementación diferente habría que modificar el planteamiento
                //en caso de ser erróneo o ineficaz.
            }
        }
    }
    
    public static void main(String[] args){
        //Creamos el temporizador.
        Tiempo tiempo = new Tiempo();
        
        //Creamos los hilos (o palillos).
        Palillo A = new Palillo();
        Palillo B = new Palillo();
        Palillo C = new Palillo();
        Palillo D = new Palillo();
        Palillo E = new Palillo();
        
        //Añadimos los hilos al ArrayList del temporizador.
        tiempo.palillos.add(A);
        tiempo.palillos.add(B);
        tiempo.palillos.add(C);
        tiempo.palillos.add(D);
        tiempo.palillos.add(E);
        
        //Iniciamos el temporizador.
        tiempo.start();
    }
}
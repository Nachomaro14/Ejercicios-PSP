package psp.appletrebote;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PSPAppletRebote extends Applet implements ActionListener{
    private long sleep = 10;
    private Font fuente;
    private Button boton;
    
    HiloRebote hilo = new HiloRebote(0, sleep);
    
    class HiloRebote extends Thread{
        private int contador;
        private boolean pausado = false;
        private long slep;
        private boolean direccion = true;
        
        public HiloRebote(int c, long s){
            contador = c;
            slep = s;
        }
        
        public void run(){
            while(!pausado){
                try{
                    sleep(slep);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                if(contador == getWidth() - 10){
                    direccion = false;
                }else if(contador == -3){
                    direccion = true;
                }
                if(direccion == true){
                    contador++;
                }else{
                    contador--;
                }
                repaint();
            }
        }
        
        public void interrumpir(){
            pausado = true;
        }
        
        public int getContador(){
            return contador;
        }
    }
    
    public void init(){
        setBackground(Color.green);
        add(boton = new Button("Parar"));
        boton.addActionListener(this);
        fuente= new Font("Times New Roman", Font.BOLD, 30);
        
        hilo.start();
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton){
            
            if(hilo.pausado == false){
                boton.setLabel("Reanudar");
                hilo.interrumpir();
            }else{
                boton.setLabel("Parar");
                boolean direccion = hilo.direccion;
                hilo = new HiloRebote(hilo.getContador(), sleep);
                hilo.direccion = direccion;
                hilo.start();
            } 
        }
    }
    
    public void paint(Graphics g){
        g.clearRect(1, 1, 400, 400);
        g.setFont(fuente);
        g.drawString("o", hilo.getContador(), 100);
    }
}
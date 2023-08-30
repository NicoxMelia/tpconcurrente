package Procesos;
import Recursos.LogClass;

import java.util.ArrayList;

public class HiloEscritor implements Runnable{

    private ArrayList<Thread[]> hilos;

    private LogClass log;


    public HiloEscritor(ArrayList<Thread[]> hilos){
        this.hilos = hilos;
        log = new LogClass();
    }


    public void run(){
        while(Proceso_4.getContadorFinal() < 100){
            try{
                log.escribir(hilos);
                Thread.sleep(500);
            }catch(Exception ex){
                System.out.println("FALLA EN HILO ESCRITOR");
            }
        }

    }

}

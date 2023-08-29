package Procesos;

import Recursos.Container;
import Recursos.LogClass;

import java.util.ArrayList;

public class HiloEscritor implements Runnable{

    public static Integer imgInsertadas, imgMejoradas, imgAjustadas, imgUltProceso;
    private ArrayList<Thread[]> hilos;

    private String[] parametros;
    LogClass log;


    public HiloEscritor(ArrayList<Thread[]> hilos){
        this.hilos = hilos;
        imgInsertadas = 0;
        imgMejoradas = 0;
        imgAjustadas = 0;
        imgUltProceso = 0;
        parametros = new String[4];
        log = new LogClass();
    }

    private void escribirHilos(){
        for(Thread[] hilo : hilos){
            for(int i=0; i< hilo.length; i++){
                System.out.println("ESTADO DEL HILO " + hilo[i].getName() + " es " + hilo[i].getState());

            }
        }
    }

    public void run(){
        try{
            while(Container.contenedorFinal.size() < 100){
                Thread.sleep(500);
                parametros[0] = String.valueOf(imgInsertadas);
                parametros[1] = String.valueOf(imgMejoradas);
                parametros[2] = String.valueOf(imgAjustadas);
                parametros[3] = String.valueOf(imgUltProceso);
                log.escribir(parametros, hilos);
            }
        }catch (Exception ex){
            System.out.println("------------------SE ROMPIO EL WRITER ---------------------------");
        }
    }

}

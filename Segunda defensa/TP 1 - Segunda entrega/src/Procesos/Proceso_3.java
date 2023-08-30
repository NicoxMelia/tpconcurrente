package Procesos;

import Recursos.Container;
import Recursos.Imagen;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class Proceso_3 implements Runnable{

    private double tiempoini_proceso3, tiempofin_proceso3;
    private Container contenedor;
    private static HashSet<Imagen> registro3;
    private Semaphore semaforo, semaforo2;
    private static int salvatore = 0;
    private static boolean flagImpre = false;

    public Proceso_3(){
        registro3 = new HashSet<Imagen>();
        semaforo = new Semaphore(contenedor.get_capacidad());
        semaforo2 = new Semaphore(1);
    }
    private static int contadorAJUSTADA=0;
    private static Object lock= new Object();

    public static synchronized int get_contadorAJUSTADA(){return contadorAJUSTADA;}

    private synchronized void aumentarSalvatore(){salvatore++;}
    private synchronized int get_salvatore(){return salvatore;}

    private synchronized boolean get_flagImpre(){return flagImpre;}

    @Override
    public void run(){
        tiempoini_proceso3=System.nanoTime();
        while(get_contadorAJUSTADA()<=99){
                if(contenedor.getSize() > 0){
                    Imagen aux = contenedor.sacar();
                    if(aux != null){
                        if(aux.get_iluminacion_Lista() && !aux.get_tamanio()){
                            aux.modificarTamanio();
                            synchronized (lock) {
                            contadorAJUSTADA++;
                            }
                         }
                        contenedor.poner(aux);
                    }
                }
                try {
                Thread.sleep(40);
                } catch (Exception ex) {
                System.out.println("Excepción lanzada desde el RUN en el PROCESO 2");
                }
                if(get_contadorAJUSTADA() == 99){
                    aumentarSalvatore();
                }
                if(get_salvatore() == 20){
                    synchronized (lock) {
                        contadorAJUSTADA--;
                    }
                }
        }
        try {
            semaforo2.acquire();
            if (get_contadorAJUSTADA() == 100 && !get_flagImpre() ) {
                flagImpre=true;
                System.out.println("Hay " + get_contadorAJUSTADA() + " imagenes al finalizar el proceso 3");
                tiempofin_proceso3 = System.nanoTime() - tiempoini_proceso3;
                System.out.println("El proceso 3 demora: " + tiempofin_proceso3 / 1000000000 + " segundos");
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            semaforo2.release();
        }

    }
}

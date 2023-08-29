package Procesos;
import Recursos.*;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Proceso_2 implements Runnable {

    private static Object obj = new Object();
    private Iterator<Imagen> it;
    private double tiempoini_proceso2, tiempofin_proceso2;

    private Semaphore semaforo;

    //constructor
    public Proceso_2() {
        semaforo = new Semaphore(Container.capacidad); //REVISAR CANTIDAD DE PERMISOS
    }


    @Override
    public void run() {
        tiempoini_proceso2=System.nanoTime();
        while (Container.contAux.size() < Container.capacidad) {
            try {
                semaforo.acquire();
                Imagen aux = Container.sacar();
                aux.aumentarIluminacion(Thread.currentThread().getName());
                Container.poner(aux);

                if (aux.iluminacion_lista) {
                    synchronized (this){
                        Container.contAux.add(aux);
                        HiloEscritor.imgMejoradas++;
                    }
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaforo.release();
            }
            try {
                Thread.sleep(60);
            } catch (Exception ex) {
                System.out.println("Excepción lanzada desde el RUN en el PROCESO 2");
            }
        }
            tiempofin_proceso2=System.nanoTime() - tiempoini_proceso2;
    }
}






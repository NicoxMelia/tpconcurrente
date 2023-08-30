package Procesos;
import Recursos.*;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Proceso_2 implements Runnable {

    private static Object obj = new Object();
    private static double tiempoini_proceso2, tiempofin_proceso2;
    private static int contadorMEJORADO = 0;
    private static HashSet<Imagen> registro2;

    private Semaphore semaforo;
    private static Object key = new Object();

    public Proceso_2() {
        semaforo = new Semaphore(Container.get_capacidad());
        registro2 = new HashSet<Imagen>();
    }
    public static int getContadorMEJORADO (){
        return contadorMEJORADO;
    }
    public synchronized void set_registro(Imagen h){registro2.add(h);}

    private HashSet<Imagen> get_registro(){return registro2;}

    @Override
    public void run() {
        tiempoini_proceso2=System.nanoTime();
        while (contadorMEJORADO < 100) {
            try {
                semaforo.acquire();
                if (Container.getSize() > 0) {
                    Imagen aux = Container.sacar();
                    if (aux != null) {
                        aux.aumentarIluminacion(Thread.currentThread().getName());
                        if (aux.get_iluminacion_Lista() && !get_registro().contains(aux)) {
                            synchronized (obj) {
                                contadorMEJORADO++;
                                set_registro(aux);
                            }
                        }
                        Container.poner(aux);
                        if (contadorMEJORADO == 100) {
                            System.out.println("Hay " + contadorMEJORADO + " imagenes al finalizar el proceso 2");
                            tiempofin_proceso2 = System.nanoTime() - tiempoini_proceso2;
                            System.out.println("El proceso 2 demora: " + tiempofin_proceso2 / 1000000000 + " segundos");
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaforo.release();
            }
            try {
                Thread.sleep(40);
            } catch (Exception ex) {
                System.out.println("Excepción lanzada desde el RUN en el PROCESO 2");
            }
        }
    }
}






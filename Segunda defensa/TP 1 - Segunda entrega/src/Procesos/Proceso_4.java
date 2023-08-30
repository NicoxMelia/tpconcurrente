package Procesos;

import Recursos.Container;
import Recursos.Imagen;

public class Proceso_4 implements Runnable{


    private double tiempoini_proceso4, tiempofin_proceso4;
    private static int contadorFINAL=0;
    private static Object lock= new Object();
    private static int salvatore = 0;
    public Proceso_4() {}
    private synchronized void aumentarFinal(){contadorFINAL++;}
    private synchronized void disminuirFinal(){contadorFINAL++;}
    private synchronized int get_contadorFINAL(){return contadorFINAL;}

    private synchronized void aumentarSalvatore(){salvatore++;}
    private synchronized int get_salvatore(){return salvatore;}

    public static Integer getContadorFinal(){
        return contadorFINAL;
    }

    @Override
    public void run(){
        tiempoini_proceso4=System.nanoTime();
        while(contadorFINAL < Container.get_capacidad()) {
            if (Container.getSize() >= 0) {
                Imagen aux = Container.sacar();
                if (aux != null) {
                    if (aux.get_iluminacion_Lista() && aux.get_tamanio()) {
                        Container.ponerFINAL(aux);
                        aumentarFinal();
                    }
                    else {
                        Container.poner(aux);
                    }
                }
                try {
                    Thread.sleep(20);
                } catch (Exception ex) {
                 System.out.println("Excepción lanzada desde el RUN en el PROCESO 4");
                }
            }

            if(get_contadorFINAL() >= 98){
                aumentarSalvatore();
            }
            if(get_salvatore() >= 20){
                aumentarFinal();
            }
        }
        tiempofin_proceso4 = System.nanoTime() - tiempoini_proceso4;
        System.out.println("El proceso 4 demora " + tiempofin_proceso4/1000000000 + " segundos");
    }
}

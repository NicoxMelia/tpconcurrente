package Procesos;

import Recursos.Container;
import Recursos.Imagen;
import java.util.Random;
import java.util.Collections;

import java.lang.reflect.Array;

public class Proceso_3 implements Runnable{

    private double tiempoini_proceso3, tiempofin_proceso3;
    public Proceso_3(){

    }

    @Override
    public void run(){
        tiempoini_proceso3=System.nanoTime();
        System.out.println("Entra al RUN");
        while (Container.capacidad>Container.contAux.size()){
            Imagen aux = Container.sacar();
            aux.modificarTamanio();
            Container.poner(aux);

            if (aux.tamanio=true) {
                synchronized (this){
                    Container.contAux.add(aux);
                }
            }
        }
            try {
                Thread.sleep(80);
            } catch (Exception ex) {
                System.out.println("Excepción lanzada desde el RUN en el PROCESO 3");
            }
        tiempofin_proceso3=System.nanoTime() - tiempoini_proceso3;
    }
}

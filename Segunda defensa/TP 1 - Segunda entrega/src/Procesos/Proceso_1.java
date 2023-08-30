package Procesos;
import Recursos.Imagen;
import Recursos.Container;

public class Proceso_1 implements Runnable {


    private static Long tiempoini_proceso1, tiempofin_proceso1;
    public Proceso_1(){

    }

    @Override
    public void run(){
                if(tiempoini_proceso1 == null){
                    tiempoini_proceso1 = System.nanoTime();
                }
                while(Container.getCantColocadas() < Container.get_capacidad()) {
                    Container.add(new Imagen("1"));

                    try {
                        Thread.sleep(40);
                    }catch (Exception ex){
                       System.out.println("Excepción lanzada en PROCESO1");
                    }
                }
                
                if (tiempofin_proceso1==null){
                    tiempofin_proceso1=System.nanoTime() - tiempoini_proceso1;
                    System.out.println("El proceso 1 demora: "+tiempofin_proceso1/1000000000 + " segundos");
                }
    }
}

package Procesos;
import Recursos.Imagen;
import Recursos.Container;
import java.util.HashSet;

public class Proceso_1 implements Runnable {

    public Proceso_1(){

    }

    @Override
    public void run(){
                while(!Container.lleno) {
                    Container.add(new Imagen("1"));

                    try {
                        Thread.sleep(40);
                    }catch (Exception ex){
                       System.out.println("Excepción lanzada en PROCESO1");
                    }
                }


    }

}

package Procesos;

import Recursos.Container;
import Recursos.Imagen;

public class Proceso_4 implements Runnable{
    public Proceso_4() {

    }
    public void run(){
        while(Container.getSize() > 0){
            synchronized (this){
                Imagen aux = Container.sacar();
                Container.addFinal(aux);
                HiloEscritor.imgUltProceso++;
            }
        }
        try {
            Thread.sleep(40);
        } catch (Exception ex) {
            System.out.println("Excepción lanzada desde el RUN en el PROCESO 4");
        }
    }

}

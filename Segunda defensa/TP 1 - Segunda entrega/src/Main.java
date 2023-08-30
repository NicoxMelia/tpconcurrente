import Procesos.*;
import Recursos.*;

import java.util.*;

public class Main {
        public static void main(String[] args) {

        double tiempo_inicio_programa= System.nanoTime();
        /** PROCESO 1 - Carga de imágenes en el contenedor*/

           Thread[] hilos_1 = new Thread[2]; //Hilos proceso 1
           Thread[] hilos_2 = new Thread[3]; //Hilos proceso 2
           Thread[] hilos_3 = new Thread[3]; //Hilos proceso 3
           Thread[] hilos_4 = new Thread [2]; //Hilos proceso 4

            ArrayList<Thread[]> hilos = new ArrayList<Thread[]>();
            hilos.add(hilos_1);
            hilos.add(hilos_2);
            hilos.add(hilos_3);
            hilos.add(hilos_4);

            Container contenedor =  new Container();
            Thread escritor = new Thread(new HiloEscritor(hilos));
            escritor.start();

            for (int i = 0; i < 2; i++) {
                hilos_1[i] = new Thread(new Proceso_1(), "Hilo " + (i+1) + " Proceso 1");
                hilos_1[i].start();
            }


        /** PROCESO 2 - Mejora de la iluminación */
            for (int j = 0; j < 3; j++) {
                hilos_2[j] = new Thread(new Proceso_2(), "Hilo " + (j+1) + " Proceso 2");
                hilos_2[j].start();
            }


         /**PROCESO 3 - Ajuste del tamaño*/
            for(int k = 0; k < 3; k++) {
                hilos_3[k] = new Thread(new Proceso_3(), "Hilo " + (k+1) + " Proceso 3");
                hilos_3[k].start();
            }

            /** PROCESO 4 - Almacenamiento en contenedor  */
            for (int m = 0; m < 2; m++) {
                hilos_4[m] = new Thread(new Proceso_4(), "Hilo " + (m+1) + " Proceso 4");
                hilos_4[m].start();
            }

            try{
                for(int m=0; m<2; m++){
                    hilos_4[m].join();
                }
            }catch(Exception ex){
                System.out.println("Excepcion lanzada en JOIN del proceso 4");
            }

            try {
                escritor.join();

            }catch (Exception ex){

            }

        System.out.println("Finalizo el proceso 4° con " + contenedor.get_contenedorFinal().size() + " imágenes en el contenedor final");

        double tiempo_fin_programa= System.nanoTime() - tiempo_inicio_programa;
        System.out.println("El programa demora en total: " + tiempo_fin_programa/1000000000 + " segundos");
     }
}



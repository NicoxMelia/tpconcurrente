import Procesos.*;
import Recursos.*;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Main {
        public static void main(String[] args) {

        double tiempo_inicio= System.nanoTime();
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


            new Container();
            Thread escritor = new Thread(new HiloEscritor(hilos));


            escritor.start();
            double tiempoini_proceso1=System.nanoTime();
            for (int i = 0; i < 2; i++) {
                hilos_1[i] = new Thread(new Proceso_1(), "Hilo " + (i+1) + " Proceso 1");
                hilos_1[i].start();
            }

            try{
                for(int i=0; i<2; i++){
                    hilos_1[i].join();
                    }
            }catch(Exception ex){
            System.out.println("Excepcion lanzada en JOIN-PROCESS 1");
             }
            double tiempofin_proceso1=System.nanoTime()-tiempoini_proceso1;

             System.out.println("El container finalizo con " + Container.contenedor.size() + " imagenes en proceso 1");

        /** PROCESO 2 - Mejora de la iluminación */
        double tiempoini_proceso2=System.nanoTime();
            for (int j = 0; j < 3; j++) {
                hilos_2[j] = new Thread(new Proceso_2(), "Hilo " + (j+1) + " Proceso 2");
                hilos_2[j].start();
            }

            try{
                for(int i=0; i<3; i++){
                    hilos_2[i].join();
                }
            }catch(Exception ex){
                System.out.println("Excepcion lanzada en JOIN-PROCESS 2");
            }

            System.out.println("El container finalizo en proceso 2");

            Container.ponerContOriginal();


            Iterator<Imagen> itPrueba= Container.contenedor.iterator();
            int i=1;
            while(itPrueba.hasNext()) {
                System.out.println("La iluminacion en la imagen " + i +" es: " + itPrueba.next().iluminacion);
                i++;
            }

            double tiempofin_proceso2=System.nanoTime()-tiempoini_proceso2;

            System.out.println("El container finalizo con " + Container.contenedor.size() + " imagenes en proceso 2");

        /** PROCESO 3 - Ajuste del tamaño */
            double tiempoini_proceso3=System.nanoTime();
            for(int k = 0; k < 3; k++) {
                hilos_3[k] = new Thread(new Proceso_3(), "Hilo " + (k+1) + " Proceso 3");
                hilos_3[k].start();
            }

            try{
                for(int k=0; k<3; k++){
                    hilos_3[k].join();
                }
            }catch(Exception ex){
                System.out.println("Excepcion lanzada en JOIN-PROCESS 3");

            }
            System.out.println("El container finalizo con " + Container.contenedor.size() + " imagenes en proceso 3");
            double tiempofin_proceso3=System.nanoTime()-tiempoini_proceso3;



            /** PROCESO 4 - Almacenamiento en contenedor final*/
            double tiempoini_proceso4=System.nanoTime();
            for (int m = 0; m < 2; m++) {
                hilos_4[m] = new Thread(new Proceso_4(), "Hilo " + (m+1) + " Proceso 4");
                hilos_4[m].start();
            }

            try{
                for(int m=0; m<2; m++){
                    hilos_4[m].join();
                }
            }catch(Exception ex){
                System.out.println("Excepcion lanzada en JOIN-PROCESS 4");
            }

        System.out.println("Finalizo el proceso 4° con " + Container.contenedorFinal.size() + " imágenes en el contenedor final");
            double tiempofin_proceso4=System.nanoTime()-tiempoini_proceso4;
        System.out.println("El proceso 1 demora "+tiempofin_proceso1/1000000000+" segundos");
        System.out.println("El proceso 2 demora "+tiempofin_proceso2/1000000000+" segundos");
        System.out.println("El proceso 3 demora "+tiempofin_proceso3/1000000000+" segundos");
        System.out.println("El proceso 4 demora "+tiempofin_proceso4/1000000000+" segundos");
        System.out.println("Tiempo total de ejecución "+(tiempofin_proceso1+tiempofin_proceso2+tiempofin_proceso3+tiempofin_proceso4)/1000000000+" segundos");
        System.out.println("El container finalizo con " + Container.contenedorFinal.size() + " imagenes en proceso 4");
        System.out.println("\n Hicimos lo que pudimos");
     }
}



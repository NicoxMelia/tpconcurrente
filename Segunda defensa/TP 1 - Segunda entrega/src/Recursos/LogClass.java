package Recursos;

import Procesos.Proceso_2;
import Procesos.Proceso_3;
import Procesos.Proceso_4;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LogClass {

    private String ruta;
    private File log;
    public static FileWriter escritor;

    public LogClass(){
        ruta = new File ("Log.txt").getAbsolutePath();
        log = new File(ruta);
        try {
            if(log.createNewFile()){
                System.out.println("Archivo de log creado en -> " + ruta);
            }
            else{System.out.println("El archivo del log ya esta creado y se encuentra en -> " + ruta);}
        } catch (IOException e) {
            System.out.println("No se pudo crear el writer, y lanzo " + e);
        }
    }

    public void escribir(ArrayList<Thread[]> hilos){
        try {
            escritor = new FileWriter(log, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            try{
                escritor.write("IMAGENES INCERTADAS: " + Container.getCantColocadas() +"\n");
                escritor.write("IMAGENES MEJORADAS COMPLETAMENTE: " + Proceso_2.getContadorMEJORADO() + "\n");
                escritor.write("IMAGENES AJUSTADAS: " + Proceso_3.get_contadorAJUSTADA() + "\n");
                escritor.write("IMAGENES FINALIZADAS: " + Proceso_4.getContadorFinal() + "\n");
                for(Thread[] proceso : hilos){
                    for(Thread hilo : proceso){
                        escritor.write("ESTADO DE " + hilo.getName() + ": " + hilo.getState() + "\n");
                    }
                }
                escritor.write("---------------------------------------------------------\n");

            }catch(Exception e){
                System.out.println("FALLA EN WRITER");
            }

        try {
            escritor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

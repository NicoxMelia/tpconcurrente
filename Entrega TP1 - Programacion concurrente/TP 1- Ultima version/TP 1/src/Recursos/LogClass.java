package Recursos;

import java.awt.*;
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

    public void escribir(String[] parametros, ArrayList<Thread[]> hilos){
        try {
            escritor = new FileWriter(log, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            try{
                escritor.write("\nImagenes insertadas al momento: " + parametros[0]);
                escritor.write("\nImagenes mejoradas al momento: " + parametros[1]);
                escritor.write("\nImagenes cortadas al momento: " + parametros[2]);
                escritor.write("\nImagenes en el ultimo proceso al momento: " + parametros[3]);
                escritor.write("\n ");
                escritor.write("\n ");
                for (Thread[] t : hilos){
                    for(int i=0; i<t.length; i++){
                        escritor.write("\n" + t[i].getName() + " " +t[i].getState());
                    }
                }
                escritor.write("--------------------------------------------------------------------------------------");
                escritor.write("\n ~~~~~~~~~~ Fin de ejecucion ~~~~~~~~~~ ");
                escritor.write("\n ");
            }catch(Exception e){
                //System.out.println("#####No se pudo escribir el log******************** LANZA " + e);
            }

        try {
            escritor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

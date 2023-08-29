package Recursos;

import Procesos.HiloEscritor;

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Imagen {

    public int iluminacion= 0;
    public boolean tamanio=false;
    public int mejoras =0;
    public boolean iluminacion_lista=false;
    private HashSet<String> registro;
    private HashSet<String> registro_tamanio;
    public String nombre;
    public static Random rand;

    //Contructor de imagen
    public Imagen(String name) {
        registro = new HashSet<String>();
        nombre = name;
        rand= new Random();
    }

    public HashSet<String> getRegistro(){
        return registro;
    }

    private void setRegistro(String nombreHilo){
        registro.add(nombreHilo);
    }

    public synchronized void aumentarIluminacion(String nombreHilo){
        if(!getRegistro().contains(nombreHilo)){
            iluminacion++;
            setRegistro(nombreHilo);
            System.out.println("iluminacion " + iluminacion);
        }
        if(iluminacion == 3){
            iluminacion_lista=true;
        }
    }

    public void modificarTamanio(){
        {
            int tiempo= rand.nextInt(4) + 1;
            try{
                Thread.sleep(tiempo);
            } catch (InterruptedException e){
                System.out.println("Error en el tiempo de delay al aumentar el tamaño");
            }
            tamanio = true;
            synchronized (this){
                HiloEscritor.imgAjustadas++;
                System.out.println("AJUSTADO");
            }
        }
    }

    public int get_iluminacion(){
        return iluminacion;
    }
    public boolean get_tamanio(){
        return tamanio;
    }

    public int get_mejoras(){
        return mejoras;
    }


}

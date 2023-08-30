package Recursos;


import java.util.HashSet;
import java.util.Random;

public class Imagen {

    private int iluminacion= 0;
    private boolean tamanio=false;
    private int mejoras =0;
    private boolean iluminacion_lista=false;
    private HashSet<String> registro; //Registro de hilos que modificaron el brillo
    private HashSet<String> registro_tamanio;
    private String nombre;
    private static Random rand;
    private Object lock= new Object();

    //Contructor de imagen
    public Imagen(String name) {
        registro = new HashSet<String>();
        nombre = name;
        rand= new Random();
    }

    //GETTERS

    public boolean get_tamanio(){return tamanio;}
    public boolean get_iluminacion_Lista(){return iluminacion_lista;}
    public HashSet get_registro(){return registro;}

    //SETTERS

    public void set_registro(String h){registro.add(h);}

    public void aumentarIluminacion(String nombreHilo){ //ANTES SINCRONIZADO
        if(!get_registro().contains(nombreHilo)){
            iluminacion++;
            set_registro(nombreHilo);
            mejoras++;
        }
        if(iluminacion == 3){
            iluminacion_lista = true;
        }
    }

    public void modificarTamanio(){ // Antes sincronizado
        {
            tamanio = true;
         }
    }




}

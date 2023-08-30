package Recursos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Container {
    // Es responsable de sus propios recursos
    // modificar los atributos a private
    private static ArrayList<Imagen> contenedor;
    private static ArrayList<Imagen> contenedorFinal;
    private static Integer capacidad;
    private static Integer cantColocadas;

    public Container(){
        contenedor = new ArrayList<Imagen>();
        contenedorFinal = new ArrayList<Imagen>();
        capacidad = 100;
        cantColocadas = 0;
    }

    //GETTERS
    public ArrayList get_contenedorFinal(){return contenedorFinal;}
    public static Integer get_capacidad(){return capacidad;}
    //SETTERS


    public static synchronized void add(Imagen i){
        if(contenedor.size() < get_capacidad()){
            contenedor.add(i);
            cantColocadas++;
        }
    }

    public static synchronized Imagen sacar(){
            Imagen aux = null;
            if(contenedor.size() > 0){
                Collections.shuffle(contenedor,new Random());
                aux = contenedor.get(0);
                contenedor.remove(0);
            }
            return aux;
    }

    public static synchronized void poner(Imagen img){
        if(img!=null){
         contenedor.add(img);
        }
    }

    public static synchronized void ponerFINAL(Imagen img){
        if(img!=null){
            contenedorFinal.add(img);
        }
    }

    public static synchronized Integer getSize(){
        return contenedor.size();
    }

    public static Integer getCantColocadas(){
        return cantColocadas;
    }

}

package Recursos;
import Procesos.HiloEscritor;
import Recursos.Imagen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Container {

    public static ArrayList<Imagen> contenedor;
    public static ArrayList<Imagen> contAux;
    public static ArrayList<Imagen> contenedorFinal;
    public static Boolean lleno;
    public static Integer capacidad;

    public Container(){
        contenedor = new ArrayList<Imagen>();
        contAux = new ArrayList<Imagen>();
        contenedorFinal = new ArrayList<Imagen>();
        lleno = false;
        capacidad = 100;

    }

    public static synchronized void add(Imagen i){
        if(contenedor.size() < capacidad){
            contenedor.add(i);
            HiloEscritor.imgInsertadas++;
        }else{
            lleno = true;
        }
    }

    public static synchronized void addFinal(Imagen i){
        if(contenedorFinal.size() < capacidad){
            contenedorFinal.add(i);
        }else{
            lleno = true;
        }
    }

    public ArrayList<Imagen>getContenedor(){
        return contenedor;
    }

    public static synchronized Imagen sacar(){
            Imagen aux = null;
            if(contenedor.size() > 0){
                aux = contenedor.get(0);
                contenedor.remove(0);
            }
            return aux;
    }

    public static synchronized void poner(Imagen img){
        contenedor.add(img);
    }

    public static void ponerContOriginal(){
        contenedor = (ArrayList<Imagen>) contAux.clone();
        contAux.clear();
    }

    public static synchronized Integer getSize(){
        return contenedor.size();
    }

}

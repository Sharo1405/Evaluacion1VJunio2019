/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class ArregloLista implements Expresion {

    private LinkedList<Expresion> listaArreglo = new LinkedList<>();

    public ArregloLista(Expresion listaArreglo, int linea, int col) {
        this.listaArreglo.add(new NodoArreglo(listaArreglo, linea, col));
        System.out.println("add en lista");
    }    
    
    public void addEnLista(Expresion listaArreglo, int linea, int col){
        this.listaArreglo.add(new NodoArreglo(listaArreglo, linea, col));
        System.out.println("add en lista");
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {
            
            for (Expresion expresion : listaArreglo) { //lista para el nivel 0
                
            }
            
        } catch (Exception e) {
            System.out.println("Error en la clase ArregloLista");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        System.out.println("holaholahoahasjdbvhasbvhbadsk");
        return null;
    }

    /**
     * @return the listaArreglo
     */
    public LinkedList<Expresion> getListaArreglo() {
        return listaArreglo;
    }

    /**
     * @param listaArreglo the listaArreglo to set
     */
    public void setListaArreglo(LinkedList<Expresion> listaArreglo) {
        this.listaArreglo = listaArreglo;
    }

    @Override
    public int getLine() {
        return -1;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class ArregloLista implements Expresion {

    private LinkedList<Expresion> listaArreglo = new LinkedList<>();

    public LinkedList<cantidadNivel> listaIndices = new LinkedList<>();
    private int cantXNivel = 0;
    private int contador = 0;

    public ArregloLista(Expresion listaArreglo, int linea, int col) {
        this.listaArreglo.add(new NodoArreglo(listaArreglo, linea, col));
        System.out.println("add en lista");
    }

    public void addEnLista(Expresion listaArreglo, int linea, int col) {
        this.listaArreglo.add(new NodoArreglo(listaArreglo, linea, col));
        System.out.println("add en lista");
    }

    protected void recorrer(LinkedList<Expresion> listaaa, int cant) {
        cant++;
        for (Expresion expresion : listaaa) {
            if (expresion instanceof ArregloLista) {
                ArregloLista ar = (ArregloLista) expresion;
                //listaIndices.add(listaaa.size());
                recorrer(ar.listaArreglo, cant);

            } else if (expresion instanceof NodoArreglo) {
                NodoArreglo nodo = (NodoArreglo) expresion;
                if (nodo.valor instanceof ArregloLista) {
                    ArregloLista ar2 = (ArregloLista) nodo.valor;

                    listaIndices.add(new cantidadNivel(listaaa.size(), cant));
                    recorrer(ar2.listaArreglo, cant);

                    //break;
                } else {
                    listaIndices.add(new cantidadNivel(listaaa.size(), cant));
                    break;
                }
                //recorrer(nodo.valor);
            } else {
                //aqui tendria una hoja
                //aqui solo tendria un nivel {1,2,3,4}
                listaIndices.add(new cantidadNivel(listaaa.size(), cant));
                break;
            }
            //cantXNivel++;
        }

        //listaIndices.addFirst(cantXNivel);
        //cantXNivel = 0;
    }

    protected void recorrido(Object actual) {

        Expresion exp = (Expresion) actual;
        if (exp instanceof ArregloLista) {
            ArregloLista arre = (ArregloLista) exp;
            for (Expresion expresion : arre.listaArreglo) {

                if (expresion instanceof ArregloLista) {
                    ArregloLista ar = (ArregloLista) expresion;
                    recorrido(ar);
                } else if (expresion instanceof NodoArreglo) {
                    NodoArreglo nodo = (NodoArreglo) expresion;
                    recorrido(nodo.valor);
                } else {
                    //aqui tendria una hoja
                    //aqui solo tendria un nivel {1,2,3,4}
                }
            }
        } else if (exp instanceof NodoArreglo) {
            NodoArreglo nodo = (NodoArreglo) exp;
            recorrido(nodo.valor);
        } else {
            //aqui tendria una hoja
        }
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {

            //listaIndices.add(new cantidadNivel(listaArreglo.size(), cantXNivel));
            recorrer(this.listaArreglo, 0);
            /*for (cantidadNivel listaIndice : listaIndices) {
                System.out.println(String.valueOf(listaIndice.cantidad) + "   " + String.valueOf(listaIndice.nivel));
            }*/
            if (validaNiveles() == true) {
                System.out.println("todo cool con los arreglos");
            } else {
                System.out.println("NO SIRVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE EL ARREGLO");
            }

        } catch (Exception e) {
            System.out.println("Error en la clase ArregloLista");
        }
        return null;
    }

    protected Boolean validaNiveles() {
        int nivel = 0;
        int cantidad = 0;
        Hashtable<Integer, Integer> valores = new Hashtable<>();
        for (cantidadNivel listaIndice : listaIndices) {
            valores.put(listaIndice.nivel, listaIndice.cantidad);
        }

        int nivelesss = valores.size();
        for (cantidadNivel listaIndice : listaIndices) {
            for (int i = 0; i < nivelesss; i++) {
                int val = valores.get(listaIndice.nivel);
                if (val != listaIndice.cantidad) {
                    return false;
                }
            }
        }

        return true;
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

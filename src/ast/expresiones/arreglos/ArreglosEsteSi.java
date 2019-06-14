/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.arreglos;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import ast.expresiones.operacion.TipoContenedor;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class ArreglosEsteSi implements Expresion {

    private LinkedList<Expresion> listaExpresionesArreglo;
    private int linea;
    private int col;

    public ArreglosEsteSi(LinkedList<Expresion> listaExpresionesArreglo, int linea, int col) {
        this.listaExpresionesArreglo = listaExpresionesArreglo;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {

            Object obtenido = listaExpresionesArreglo.get(0).getValue(lista, impresion);
            Object tipoObtenido = listaExpresionesArreglo.get(0).getType(lista, impresion);

            TipoContenedor tipoPrimero = new TipoContenedor();
            String tipoPrimeroObjeto = "";
            if (tipoObtenido instanceof TipoContenedor) {
                tipoPrimero = (TipoContenedor) tipoObtenido;
            } else {
                tipoPrimeroObjeto = String.valueOf(tipoObtenido);
            }
            //---------------------------------------------------------------------------------------------------------------------
            if (obtenido instanceof NodoNNario) {
                NodoNNario hijo = (NodoNNario) obtenido;
                int dim = hijo.dimensiones;
                NodoNNario arregloOtro = new NodoNNario(tipoPrimero, dim + 1);
                arregloOtro.hijos.add(obtenido);

                for (int i = 1; i < listaExpresionesArreglo.size(); i++) {
                    obtenido = listaExpresionesArreglo.get(i).getValue(lista, impresion);
                    Object tipoactual = listaExpresionesArreglo.get(i).getType(lista, impresion);

                    TipoContenedor tipoDentro = new TipoContenedor();
                    String tipoDentroObjeto = "";

                    if (tipoactual instanceof TipoContenedor) {
                        tipoDentro = (TipoContenedor) tipoactual;
                        if (tipoDentro.getTipoPrimitivo() != tipoPrimero.getTipoPrimitivo()) {
                            impresion.errores.add(new ast.Error("Los nodos del arreglo no son del mismo tipo", linea, col, "Semantico"));
                            return null;
                        }                        
                    } else {
                        tipoDentroObjeto = String.valueOf(tipoactual);
                        if (!tipoDentroObjeto.equals(tipoPrimeroObjeto)) {
                            impresion.errores.add(new ast.Error("Los nodos del arreglo no son del mismo tipo", linea, col, "Semantico"));
                            return null;
                        }
                    }

                    hijo = (NodoNNario) obtenido;
                    if (dim != hijo.dimensiones) {
                        impresion.errores.add(new ast.Error("Los arreglos tienen diferentes niveles de profundidad", linea, col, "Semantico"));
                        return null;
                    }
                    arregloOtro.hijos.add(obtenido);
                }

                return arregloOtro; //el arreglo dle otro nivel;

            } else {

                NodoNNario arregloObjetido = new NodoNNario(tipoPrimero, 1);
                arregloObjetido.hijos.add(obtenido);

                for (int i = 1; i < listaExpresionesArreglo.size(); i++) {
                    Object obtenido2 = listaExpresionesArreglo.get(i).getValue(lista, impresion);
                    Object tipoSiguiente = (TipoContenedor) listaExpresionesArreglo.get(i).getType(lista, impresion);

                    TipoContenedor tipoObtenido2 = new TipoContenedor();
                    String tipoPrimeroObjeto2 = "";
                    if (tipoSiguiente instanceof TipoContenedor) {
                        tipoObtenido2 = (TipoContenedor) tipoObtenido;
                        if (tipoObtenido2.getTipoPrimitivo() != tipoPrimero.getTipoPrimitivo()) {
                            impresion.errores.add(new ast.Error("Los tipos del arreglo no son iguales", linea, col, "Semantico"));
                            return null;
                        }
                    } else {
                        tipoPrimeroObjeto2 = String.valueOf(tipoObtenido);
                        if (tipoPrimeroObjeto2.equals(tipoPrimeroObjeto)) {
                            impresion.errores.add(new ast.Error("Los tipos del arreglo no son iguales", linea, col, "Semantico"));
                            return null;
                        }
                    }
                    arregloObjetido.hijos.add(obtenido2);
                }
                return arregloObjetido;
            }

            //---------------------------------------------------------------------------------------------------------------------
        } catch (Exception e) {
            System.out.println("Error en la clase ArreglosEsteSi, getValue");
            impresion.errores.add(new ast.Error("Los nodos del arreglo no son del mismo tipo", linea, col, "Semantico"));
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        try {
            Object tipoObtenido = listaExpresionesArreglo.get(0).getType(lista, impresion);

            TipoContenedor tipoPrimero = new TipoContenedor();
            String tipoPrimeroObjeto = "";
            if (tipoObtenido instanceof TipoContenedor) {
                tipoPrimero = (TipoContenedor) tipoObtenido;
                return tipoPrimero;
            } else {
                tipoPrimeroObjeto = String.valueOf(tipoObtenido);
                return tipoPrimeroObjeto;
            }
        } catch (Exception e) {
            System.out.println("Error en la clase ArreglosEsteSi, gettype");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the listaExpresionesArreglo
     */
    public LinkedList<Expresion> getListaExpresiones() {
        return listaExpresionesArreglo;
    }

    /**
     * @param listaExpresionesArreglo the listaExpresionesArreglo to set
     */
    public void setListaExpresiones(LinkedList<Expresion> listaExpresionesArreglo) {
        this.listaExpresionesArreglo = listaExpresionesArreglo;
    }

    /**
     * @return the linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }

    /**
     * @return the col
     */
    public int getCol() {
        return col;
    }

    /**
     * @param col the col to set
     */
    public void setCol(int col) {
        this.col = col;
    }

}

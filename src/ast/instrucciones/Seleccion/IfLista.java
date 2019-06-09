/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.Seleccion;

import ast.expresiones.Expresion;
import ast.instrucciones.Instruccion;

/**
 *
 * @author sharolin
 */
public class IfLista {
    
    private Expresion condicion;
    private Instruccion listaParaEjecutar;
    private int linea;
    private int col;

    public IfLista(Expresion condicion, Instruccion listaParaEjecutar, int linea, int col) {
        this.condicion = condicion;
        this.listaParaEjecutar = listaParaEjecutar;
        this.linea = linea;
        this.col = col;
    }    
    

    /**
     * @return the condicion
     */
    public Expresion getCondicion() {
        return condicion;
    }

    /**
     * @param condicion the condicion to set
     */
    public void setCondicion(Expresion condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the listaParaEjecutar
     */
    public Instruccion getListaParaEjecutar() {
        return listaParaEjecutar;
    }

    /**
     * @param listaParaEjecutar the listaParaEjecutar to set
     */
    public void setListaParaEjecutar(Instruccion listaParaEjecutar) {
        this.listaParaEjecutar = listaParaEjecutar;
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

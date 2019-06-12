/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.arreglos;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;

/**
 *
 * @author sharolin
 */
public class ArreglosNew implements Expresion{
    
    private Expresion valorIndex;
    private int linea;
    private int col;

    public ArreglosNew(Expresion valorIndex, int linea, int col) {
        this.valorIndex = valorIndex;
        this.linea = linea;
        this.col = col;
    }
    
    
    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {           
            return valorIndex.getValue(lista, impresion);          
        } catch (Exception e) {
            System.out.println("Error en la clase ArreglosNew");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        return valorIndex.getType(lista, impresion);
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the valorIndex
     */
    public Expresion getValorIndex() {
        return valorIndex;
    }

    /**
     * @param valorIndex the valorIndex to set
     */
    public void setValorIndex(Expresion valorIndex) {
        this.valorIndex = valorIndex;
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

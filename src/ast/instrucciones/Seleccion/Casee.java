/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.Seleccion;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Casee implements Expresion{
    
    
    private Expresion valorCase;
    private int linea;
    private int col;

    public Casee(Expresion valorCase, int linea, int col) {
        this.valorCase = valorCase;
        this.linea = linea;
        this.col = col;
    }
    
    

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {
            
            return valorCase.getValue(lista, impresion);
            
        } catch (Exception e) {
            System.out.println("Error en la clase Casee getValue");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        try {
            return valorCase.getType(lista, impresion);
        } catch (Exception e) {
            System.out.println("Error en la clase Casee getTipo");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the valorCase
     */
    public Expresion getValorCase() {
        return valorCase;
    }

    /**
     * @param valorCase the valorCase to set
     */
    public void setValorCase(Expresion valorCase) {
        this.valorCase = valorCase;
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

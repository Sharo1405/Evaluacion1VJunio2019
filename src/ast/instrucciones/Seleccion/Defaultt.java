/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.Seleccion;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.instrucciones.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Defaultt implements Instruccion {

    private int linea;
    private int col;

    public Defaultt(int linea, int col) {
        this.linea = linea;
        this.col = col;
    }
    

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            
        } catch (Exception e) {
            System.out.println("Error en la clase Defaultt");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.ciclos.RetCont;

import ast.ListaErrorPrinter;
import ast.clase.LlamadaMetodoFuncion;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import ast.expresiones.operacion.TipoContenedor;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Returnn implements Expresion {

    private Expresion valorRetorno;
    private int linea;
    private int col;
    public Object valor = null;
    public TipoContenedor tipo = new TipoContenedor();

    public Returnn(Expresion valorRetorno, int linea, int col) {
        this.valorRetorno = valorRetorno;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {

            valor = valorRetorno.getValue(lista, impresion);
            return valor;

        } catch (Exception e) {
            System.out.println("Error en la clase Return, getValue");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        try {
            tipo = (TipoContenedor) valorRetorno.getType(lista, impresion);
            return tipo;
        } catch (Exception e) {
            System.out.println("Error en la clase Return, getType");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the valorRetorno
     */
    public Expresion getValorRetorno() {
        return valorRetorno;
    }

    /**
     * @param valorRetorno the valorRetorno to set
     */
    public void setValorRetorno(Expresion valorRetorno) {
        this.valorRetorno = valorRetorno;
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

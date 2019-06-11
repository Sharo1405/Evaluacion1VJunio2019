/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.declaraciones;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import ast.instrucciones.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class VariableDeclarator implements Instruccion{
    
    
    private LinkedList<NodoAST> variablesDeclaradas;
    private Expresion valor;
    private int linea;
    private int col;

    public VariableDeclarator(LinkedList<NodoAST> variablesDeclaradas, Expresion valor, int linea, int col) {
        this.variablesDeclaradas = variablesDeclaradas;
        this.valor = valor;
        this.linea = linea;
        this.col = col;
    }    
    

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the variablesDeclaradas
     */
    public LinkedList<NodoAST> getVariablesDeclaradas() {
        return variablesDeclaradas;
    }

    /**
     * @param variablesDeclaradas the variablesDeclaradas to set
     */
    public void setVariablesDeclaradas(LinkedList<NodoAST> variablesDeclaradas) {
        this.variablesDeclaradas = variablesDeclaradas;
    }

    /**
     * @return the valor
     */
    public Expresion getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Expresion valor) {
        this.valor = valor;
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

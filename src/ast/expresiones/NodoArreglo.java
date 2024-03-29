/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;

/**
 *
 * @author sharolin
 */
public class NodoArreglo implements Expresion {

    public Expresion valor;
    public int linea;
    public int col;

    public NodoArreglo(Expresion valor, int linea, int col) {
        this.valor = valor;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {
            return valor.getValue(lista, impresion);
        } catch (Exception e) {
            System.out.println("Error en la clase NodoArreglo, getValue");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        try {
            return valor.getType(lista, impresion);
        } catch (Exception e) {
            System.out.println("Error en la clase NodoArreglo, getType");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }
}

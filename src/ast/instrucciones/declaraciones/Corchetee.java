/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.declaraciones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import ast.instrucciones.Instruccion;

/**
 *
 * @author sharolin
 */
public class Corchetee implements Expresion{
    
    public boolean corchetes = true;

    public Corchetee() {
    }   

    @Override
    public int getLine() {
        return -1;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        return "[";
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        return "corchete";
    }
}

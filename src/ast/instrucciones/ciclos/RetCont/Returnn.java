/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.ciclos.RetCont;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;

/**
 *
 * @author sharolin
 */
public class Returnn implements Expresion{

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

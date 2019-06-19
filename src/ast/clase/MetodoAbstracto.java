/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.clase;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import ast.expresiones.operacion.TipoContenedor;
import ast.instrucciones.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class MetodoAbstracto extends TodoMetodos implements Expresion {

    public MetodoAbstracto(LinkedList<String> visivilidad, TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo, LinkedList<Parametros> lisParametros, int linea, int col) {
        super(visivilidad, tipo, listaCorchetes, idMetodo, lisParametros, linea, col);
    }

    public MetodoAbstracto(TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo, LinkedList<Parametros> lisParametros, int linea, int col) {
        super(tipo, listaCorchetes, idMetodo, lisParametros, linea, col);
    }
    
    public MetodoAbstracto(LinkedList<String> visivilidad, TipoContenedor tipo, String idMetodo, LinkedList<Parametros> lisParametros, int linea, int col) {
        super(visivilidad, tipo, idMetodo, lisParametros, linea, col);
    }

    public MetodoAbstracto(TipoContenedor tipo, String idMetodo, LinkedList<Parametros> lisParametros, int linea, int col) {
        super(tipo, idMetodo, lisParametros, linea, col);
    }

    
    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.clase;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.expresiones.operacion.TipoContenedor;
import ast.instrucciones.Instruccion;
import com.sun.webkit.InspectorClient;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Mainn extends TodoMetodos implements Instruccion{

    public Mainn(LinkedList<String> visivilidad, TipoContenedor tipo, String idMetodo, LinkedList<Parametros> listaparametros, Instruccion listaContenidoSentencias, Boolean isMain, int linea, int col) {
        super(visivilidad, tipo, idMetodo, listaparametros, listaContenidoSentencias, isMain, linea, col);
    }
    
    
    

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

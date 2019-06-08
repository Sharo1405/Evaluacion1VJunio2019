/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public interface Instruccion extends NodoAST{
    
    Object ejecutar(Entorno lista, ListaErrorPrinter impresion);
}

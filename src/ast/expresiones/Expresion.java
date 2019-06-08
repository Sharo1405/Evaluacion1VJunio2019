/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;

/**
 *
 * @author sharolin
 */
public interface Expresion extends NodoAST{
 
    Object getValue(Entorno lista, ListaErrorPrinter impresion); //lista de entorno
    Object getType(Entorno lista, ListaErrorPrinter impresion);
}

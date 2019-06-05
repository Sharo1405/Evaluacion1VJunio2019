/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.entorno.Entorno;
import ast.instrucciones.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class ArbolAST {
    
    public NodoAST raiz;
    
    public ArbolAST(NodoAST nodo){
        this.raiz = nodo;
    }

    public ArbolAST() {
    }   
   
    
    public void ejecutar(Entorno entorno){
        /*for(NodoAST no: raiz){
            if(no instanceof Instruccion){
                Instruccion instruccion = (Instruccion) no;
                instruccion.ejecutar(entorno);
            }
        }*/        
    }
}

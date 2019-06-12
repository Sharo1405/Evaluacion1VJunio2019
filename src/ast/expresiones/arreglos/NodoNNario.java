/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.arreglos;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class NodoNNario {
    
    public LinkedList<NodoNNario> hijos = new LinkedList<>();
    public Object valor = null;

    public NodoNNario() {
    }

    
    public NodoNNario(LinkedList<NodoNNario> hijos, Object valor) {
        this.hijos = hijos;
        this.valor = valor;
    }

    public void obtenerPorIndice(){
        
    }
    
    
    public void setearPorIndice(){
        
    }
    
}

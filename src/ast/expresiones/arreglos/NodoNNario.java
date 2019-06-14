/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.arreglos;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import ast.expresiones.operacion.TipoContenedor;
import java.awt.Dimension;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class NodoNNario {
    
    public TipoContenedor tipo;
    public int dimensiones;
    public LinkedList<Object> hijos = new LinkedList<>();

    public NodoNNario() {
    }

    public NodoNNario(TipoContenedor tipo, int dimensiones) {
        this.tipo = tipo;
        this.dimensiones = dimensiones;        
    }
    
    public NodoNNario(LinkedList<Object> hijos) {
        this.hijos = hijos;
    }

    public void obtenerPorIndice(){
        
    }
    
    
    public void setearPorIndice(){
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.Seleccion;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import ast.instrucciones.Instruccion;

/**
 *
 * @author sharolin
 */
public class Switchh implements Instruccion{
    
    
    public Expresion condicion;
    
    public int linea;
    public int col;

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            
            
            
            
        } catch (Exception e) {
            System.out.println("Error en la clase Switch");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }
    
}

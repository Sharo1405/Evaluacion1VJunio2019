/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.importaciones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.instrucciones.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Importaciones implements Instruccion{
    
    LinkedList<Importar> listaDeImports;

    public Importaciones(LinkedList<Importar> listaDeImports) {
        this.listaDeImports = listaDeImports;
    }    

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            
        } catch (Exception e) {
            System.out.println("Error en la clase Importaciones, ejecutar");
        }
        return null;
    }

    @Override
    public int getLine() {
        return -1;
    }
    
}

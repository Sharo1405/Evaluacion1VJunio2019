/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.importaciones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.instrucciones.Instruccion;

/**
 *
 * @author sharolin
 */
public class Importar implements Instruccion{
    
    public String archivoParaImportar;
    public int linea;
    public int col;

    public Importar(String archivoParaImportar, int linea, int col) {
        this.archivoParaImportar = archivoParaImportar;
        this.linea = linea;
        this.col = col;
    }
   

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            
        } catch (Exception e) {
            System.out.println("Error en la clase Importar, ejecutar");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }
    
}

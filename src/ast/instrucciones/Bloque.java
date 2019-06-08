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
public class Bloque implements Instruccion {

    public LinkedList<NodoAST> listaIns;

    public Bloque(LinkedList<NodoAST> lista) {

        this.listaIns = lista;

    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {

            Entorno tablaActual = new Entorno(lista);
            for (NodoAST listaIn : listaIns) {
                if (listaIn instanceof Instruccion) {
                    Instruccion lisIn2 = (Instruccion) listaIn;
                    lisIn2.ejecutar(tablaActual, impresion);
                }
            }

        } catch (Exception e) {
            System.out.println("Error en la clase Bloque, ejecutar");
        }
        return null;
    }

    @Override
    public int getLine() {
        return 0;
    }

}

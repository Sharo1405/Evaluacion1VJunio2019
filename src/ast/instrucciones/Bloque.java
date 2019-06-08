/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Bloque implements Instruccion {

    public LinkedList<Instruccion> listaIns;

    public Bloque(LinkedList<Instruccion> lista) {

        this.listaIns = lista;

    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {

            Entorno tablaActual = new Entorno(lista);
            for (Instruccion listaIn : listaIns) {
                listaIn.ejecutar(tablaActual, impresion);
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

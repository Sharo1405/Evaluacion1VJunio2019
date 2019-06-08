/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class ListaErrorPrinter {
    
    public LinkedList<Error> errores;
    public LinkedList<String> impresiones;

    public ListaErrorPrinter(LinkedList<Error> Errores, LinkedList<String> impresiones) {
        this.errores = Errores;
        this.impresiones = impresiones;
    }

    public ListaErrorPrinter() {
        this.errores = new LinkedList<>();
        this.impresiones = new LinkedList<>();
    }     
}

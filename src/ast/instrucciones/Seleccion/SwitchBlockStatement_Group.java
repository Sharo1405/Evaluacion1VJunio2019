/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.Seleccion;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.instrucciones.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class SwitchBlockStatement_Group implements Instruccion{

    private LinkedList<NodoAST> listaCasee;
    private Instruccion listaSentencias;

    
    public SwitchBlockStatement_Group(LinkedList<NodoAST> listaCasee, Instruccion listaSentencias) {
        this.listaCasee = listaCasee;
        this.listaSentencias = listaSentencias;
    }
    
    
    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public int getLine() {
        return -1;
    }

    /**
     * @return the listaCasee
     */
    public LinkedList<NodoAST> getListaCasee() {
        return listaCasee;
    }

    /**
     * @param listaCasee the listaCasee to set
     */
    public void setListaCasee(LinkedList<NodoAST> listaCasee) {
        this.listaCasee = listaCasee;
    }

    /**
     * @return the listaSentencias
     */
    public Instruccion getListaSentencias() {
        return listaSentencias;
    }

    /**
     * @param listaSentencias the listaSentencias to set
     */
    public void setListaSentencias(Instruccion listaSentencias) {
        this.listaSentencias = listaSentencias;
    }
    
}

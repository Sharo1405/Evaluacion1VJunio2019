/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import ast.instrucciones.ciclos.RetCont.Returnn;
import com.sun.javafx.applet.ExperimentalExtensions;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Printer implements Instruccion {

    private Expresion expImpre;
    private int linea;
    private int col;
    private boolean saltoLinea = false;

    public Printer(Expresion expImpre, int linea, int col) {
        this.expImpre = expImpre;
        this.linea = linea;
        this.col = col;
    }

    public Printer(Expresion expImpre, int linea, int col, boolean saltoLinea) {
        this.expImpre = expImpre;
        this.linea = linea;
        this.col = col;
        this.saltoLinea = saltoLinea;
    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {

            if (expImpre != null) {
                Object imprimir = expImpre.getValue(lista, impresion);
                //if (imprimir != null) {
                if (imprimir instanceof Returnn) {
                    Returnn h = (Returnn) imprimir;
                    if(h.valor != null){
                        System.out.println(String.valueOf(h.valor));
                        impresion.impresiones.add(String.valueOf(h.valor));
                    }
                } else {
                    if (saltoLinea == false) {
                        impresion.impresiones.add(String.valueOf(imprimir));
                        System.out.println(String.valueOf(imprimir));
                    } else {
                        impresion.impresiones.add(String.valueOf(imprimir));
                        System.out.println(String.valueOf(imprimir) + "\n");
                    }
                    /*} else {
                        impresion.errores.add(new ast.Error("Expresion/valor no existe para imprimirlo", linea, col, "Semantico"));
                    }*/
                }
            }

        } catch (Exception e) {
            System.out.println("Error en la clase Printer");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the expImpre
     */
    public Expresion getExpImpre() {
        return expImpre;
    }

    /**
     * @param expImpre the expImpre to set
     */
    public void setExpImpre(Expresion expImpre) {
        this.expImpre = expImpre;
    }

    /**
     * @return the linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }

    /**
     * @return the col
     */
    public int getCol() {
        return col;
    }

    /**
     * @param col the col to set
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * @return the saltoLinea
     */
    public boolean isSaltoLinea() {
        return saltoLinea;
    }

    /**
     * @param saltoLinea the saltoLinea to set
     */
    public void setSaltoLinea(boolean saltoLinea) {
        this.saltoLinea = saltoLinea;
    }

}

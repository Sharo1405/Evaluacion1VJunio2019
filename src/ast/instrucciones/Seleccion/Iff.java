/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.Seleccion;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import ast.instrucciones.Bloque;

import ast.instrucciones.Instruccion;
import ast.instrucciones.ciclos.RetCont.Breakk;
import ast.instrucciones.ciclos.RetCont.Continuee;
import ast.instrucciones.ciclos.RetCont.Returnn;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Iff implements Instruccion {

    private Instruccion ejecutarELSE;
    private LinkedList<IfLista> ejecutarIFS;
    private int linea;
    private int col;

    protected boolean entro = false;

    public Iff(Instruccion ejecutarELSE, LinkedList<IfLista> ejecutarIFS, int linea, int col) {
        this.ejecutarELSE = ejecutarELSE;
        this.ejecutarIFS = ejecutarIFS;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {

            for (IfLista ifLista : ejecutarIFS) {

                if ((Boolean) ifLista.getCondicion().getValue(lista, impresion) && entro == false) {
                    entro = true;
                    for (NodoAST nodo : ((Bloque) ifLista.getListaParaEjecutar()).listaIns) {
                        if (nodo instanceof Instruccion) {
                            Object retorno = ((Instruccion) nodo).ejecutar(lista, impresion);
                            if (retorno instanceof Returnn) {
                                return null;
                            } else if (retorno instanceof Breakk) {
                                return new Breakk(((Instruccion) nodo).getLine(), -1);
                            } else if (retorno instanceof Continuee) {
                                return "shar";
                            }
                        } else if (nodo instanceof Expresion) {
                            //AQUI EL RETORNO
                        }
                    }
                }
            }
            if (entro == false && ejecutarELSE != null) {
                for (NodoAST nodo : ((Bloque) ejecutarELSE).listaIns) {
                    if (nodo instanceof Instruccion) {
                        Object retorno = ((Instruccion) nodo).ejecutar(lista, impresion);
                        if (retorno instanceof Returnn) {
                            return null;
                        } else if (retorno instanceof Breakk) {
                            return new Breakk(((Instruccion) nodo).getLine(), -1);
                        } else if (retorno instanceof Continuee) {
                            return "shar";
                        }
                    } else if (nodo instanceof Expresion) {
                        //AQUI EL RETORNO
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error en la clase Iff, ejecutar");
            impresion.errores.add(new ast.Error("La condicion no es valida", linea, col, "Semantico"));
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the ejecutarELSE
     */
    public Instruccion getEjecutarELSE() {
        return ejecutarELSE;
    }

    /**
     * @param ejecutarELSE the ejecutarELSE to set
     */
    public void setEjecutarELSE(Instruccion ejecutarELSE) {
        this.ejecutarELSE = ejecutarELSE;
    }

    /**
     * @return the ejecutarIFS
     */
    public LinkedList<IfLista> getEjecutarIFS() {
        return ejecutarIFS;
    }

    /**
     * @param ejecutarIFS the ejecutarIFS to set
     */
    public void setEjecutarIFS(LinkedList<IfLista> ejecutarIFS) {
        this.ejecutarIFS = ejecutarIFS;
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

}

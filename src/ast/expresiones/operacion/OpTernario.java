/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operacion;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;

/**
 *
 * @author sharolin
 */
public class OpTernario implements Expresion {

    private Expresion condicion;
    private Expresion valTrue;
    private Expresion valFalse;
    private int line;
    private int col;

    public OpTernario(Expresion condicion, Expresion valTrue, Expresion valFalse, int line, int col) {
        this.condicion = condicion;
        this.valTrue = valTrue;
        this.valFalse = valFalse;
        this.line = line;
        this.col = col;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {

            Object conObject = condicion.getValue(lista, impresion);
            if (conObject != null) {
                return (Boolean)condicion.getValue(lista, impresion) ? valTrue.getValue(lista, impresion) : valFalse.getValue(lista, impresion);
            } else {
                System.out.println("Error en ternario");
                impresion.errores.add(new ast.Error("Error en ternario", line, col, "Semantico"));
            }

        } catch (Exception e) {
            System.out.println("Error en la clase OpTernario getValue");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        try {

            Object conObject = condicion.getValue(lista, impresion);
            if ((Boolean) conObject != null) {
                if((Boolean) conObject){
                    return valTrue.getType(lista, impresion);
                }else{
                    return valFalse.getType(lista, impresion);
                }
            } else {
                System.out.println("Error en ternario");
                impresion.errores.add(new ast.Error("Error en ternario", line, col, "Semantico"));
            }

        } catch (Exception e) {
            System.out.println("Error en la clase OpTernario getValor");
        }
        return null;
    }

    @Override
    public int getLine() {
        return line;
    }

    /**
     * @return the condicion
     */
    public Expresion getCondicion() {
        return condicion;
    }

    /**
     * @param condicion the condicion to set
     */
    public void setCondicion(Expresion condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the valTrue
     */
    public Expresion getValTrue() {
        return valTrue;
    }

    /**
     * @param valTrue the valTrue to set
     */
    public void setValTrue(Expresion valTrue) {
        this.valTrue = valTrue;
    }

    /**
     * @return the valFalse
     */
    public Expresion getValFalse() {
        return valFalse;
    }

    /**
     * @param valFalse the valFalse to set
     */
    public void setValFalse(Expresion valFalse) {
        this.valFalse = valFalse;
    }

    /**
     * @param line the line to set
     */
    public void setLine(int line) {
        this.line = line;
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

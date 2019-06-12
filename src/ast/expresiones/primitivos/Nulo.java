/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.primitivos;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import ast.expresiones.operacion.TipoContenedor;

/**
 *
 * @author sharolin
 */
public class Nulo implements Expresion {

    private String valorNulo;
    private TipoContenedor tipo;
    private int linea;
    private int col;

    public Nulo(String valorNulo, TipoContenedor tipo, int linea, int col) {
        this.valorNulo = valorNulo;
        this.tipo = tipo;
        this.linea = linea;
        this.col = col;
    }

    

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        return new TipoContenedor(Simbolo.Tipo.NULO);
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the valorNulo
     */
    public String getValorNulo() {
        return valorNulo;
    }

    /**
     * @param valorNulo the valorNulo to set
     */
    public void setValorNulo(String valorNulo) {
        this.valorNulo = valorNulo;
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

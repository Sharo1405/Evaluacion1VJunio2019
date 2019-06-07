/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.primitivos;

import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import ast.expresiones.operacion.TipoContenedor;

/**
 *
 * @author sharolin
 */
public class Decimal implements Expresion{
    
    
    private Object valor;
    private TipoContenedor tipo;
    private int linea;
    private int columna;
    

    public Decimal(Object valor, TipoContenedor tipo, int linea, int columna) {
        this.valor = valor;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object getValue(Entorno lista) {
        return Double.parseDouble(String.valueOf(valor));
    }

    @Override
    public Object getType(Entorno lista) {
        return new TipoContenedor(Simbolo.Tipo.DOUBLE);
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the valor
     */
    public Object getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }

    /**
     * @return the tipo
     */
    public TipoContenedor getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoContenedor tipo) {
        this.tipo = tipo;
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
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.entorno;

//import ast.expresiones.Tipo;

/**
 *
 * @author sharolin
 */
public class Simbolo {
    
    private String id;
    private Object valor; 
    
    private int fila;
    private int columna;
    
    public enum Rol{
        VARIABLE,
        METODO,
        FUNCION,
        LLAMADAMETODO,
        OBJETO
    }
    
    private Rol rol;
    
    public enum Tipo{
        INT,
        CHAR,
        STRING,
        DOUBLE,
        BOOLEAN,
        ID
    }
   
    private Tipo tipo;

    public Simbolo(String id, Object valor, Object tipo, int linea, int col) {
        this.id = id;
        this.valor = valor;
        this.tipo = (Tipo) tipo;
        this.fila = linea;
        this.columna = col;
    }

    public Simbolo(String id, Object valor) {
        this.id = id;
        this.valor = valor;
    }
    
    public Simbolo(Object valor) {
        this.id = id;
        this.valor = valor;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
    public Object getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.setTipo(tipo);
    }    


    /**
     * @return the fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(int fila) {
        this.fila = fila;
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

    /**
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

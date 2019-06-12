/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.entorno;

//import ast.expresiones.Tipo;

import ast.expresiones.operacion.TipoContenedor;
import java.util.LinkedList;


/**
 *
 * @author sharolin
 */
public class Simbolo {
    
    private String id;
    private Object valor; 
    
    private LinkedList<String> visivilidad;
    
    private int fila;
    private int columna;
    
    private Rol rol;
    private TipoContenedor tipo;
    
    private int tamanio; //dimensiones;
    private LinkedList<Integer> listaValoresDimensiones;
    
    public enum Rol{
        VARIABLE,
        METODO,
        FUNCION,
        LLAMADAMETODO,
        VECTOR
    }
    
    public enum Tipo{
        INT,
        CHAR,
        STRING,
        DOUBLE,
        BOOLEAN,
        NULO,
        ID
    }

    public Simbolo(String id, Object valor, LinkedList<String> visivilidad, int fila, int columna, Rol rol, TipoContenedor tipo, int tamanio, LinkedList<Integer> listaValoresDimensiones) {
        this.id = id;
        this.valor = valor;
        this.visivilidad = visivilidad;
        this.fila = fila;
        this.columna = columna;
        this.rol = rol;
        this.tipo = tipo;
        this.tamanio = tamanio;
        this.listaValoresDimensiones = listaValoresDimensiones; //[3][2]... -----> 3,2,....
    }
    
    public Simbolo(String id, Object valor, LinkedList<String> visivilidad, int fila, int columna, Rol rol, TipoContenedor tipo, int tamanio) {
        this.id = id;
        this.valor = valor;
        this.visivilidad = visivilidad;
        this.fila = fila;
        this.columna = columna;
        this.rol = rol;
        this.tipo = tipo;
        this.tamanio = tamanio;
    }   

    public Simbolo(String id, Object valor, LinkedList<String> visivilidad, int fila, int columna, Rol rol, TipoContenedor tipo) {
        this.id = id;
        this.valor = valor;
        this.visivilidad = visivilidad;
        this.fila = fila;
        this.columna = columna;
        this.rol = rol;
        this.tipo = tipo;
    }
           
    
    public Simbolo(String id, Object valor, Object tipo, int linea, int col) {
        this.id = id;
        this.valor = valor;
        this.tipo = (TipoContenedor) tipo;
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

    /**
     * @return the visivilidad
     */
    public LinkedList<String> getVisivilidad() {
        return visivilidad;
    }

    /**
     * @param visivilidad the visivilidad to set
     */
    public void setVisivilidad(LinkedList<String> visivilidad) {
        this.visivilidad = visivilidad;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoContenedor tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the tamanio
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * @param tamanio the tamanio to set
     */
    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    /**
     * @return the listaValoresDimensiones
     */
    public LinkedList<Integer> getListaValoresDimensiones() {
        return listaValoresDimensiones;
    }

    /**
     * @param listaValoresDimensiones the listaValoresDimensiones to set
     */
    public void setListaValoresDimensiones(LinkedList<Integer> listaValoresDimensiones) {
        this.listaValoresDimensiones = listaValoresDimensiones;
    }
}

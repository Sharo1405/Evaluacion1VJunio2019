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
    
    //private LinkedList<String> visibilidad;
    private Boolean publico = false;
    private Boolean privado = false;
    private Boolean protegido = false;
    
    private Boolean estatico = false;
    private Boolean finall = false;
    
    
    private int fila;
    private int columna;
    
    private Rol rol;
    private TipoContenedor tipo;
    
    
    private int tamanio; //dimensiones;
    private LinkedList<Integer> listaValoresDimensiones;
    private String tipoRetorno;
    
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
        METODO,
        ID
    }

    public Simbolo(String id, Object valor, Boolean publico, Boolean privado, Boolean protegido, Boolean estatico, Boolean finall, 
            int fila, int columna, Rol rol, TipoContenedor tipo, int tamanio, LinkedList<Integer> listaValoresDimensiones) {
        this.id = id;
        this.valor = valor;
        this.publico = publico;
        this.privado = privado;
        this.protegido = protegido;
        this.estatico = estatico;
        this.finall = finall;
        this.fila = fila;
        this.columna = columna;
        this.rol = rol;
        this.tipo = tipo;
        this.tamanio = tamanio;
        this.listaValoresDimensiones = listaValoresDimensiones; //[3][2]... -----> 3,2,....
    }
    
    public Simbolo(String id, Object valor, Boolean publico, Boolean privado, Boolean protegido, Boolean estatico, Boolean finall, 
            int fila, int columna, Rol rol, TipoContenedor tipo, int tamanio, 
            LinkedList<Integer> listaValoresDimensiones, String tipoRetorno) {
        this.id = id;
        this.valor = valor;
        this.publico = publico;
        this.privado = privado;
        this.protegido = protegido;
        this.estatico = estatico;
        this.finall = finall;
        this.fila = fila;
        this.columna = columna;
        this.rol = rol;
        this.tipo = tipo;
        this.tamanio = tamanio;
        this.listaValoresDimensiones = listaValoresDimensiones; //[3][2]... -----> 3,2,....
        this.tipoRetorno = tipoRetorno;
    }
    
    public Simbolo(String id, Object valor, Boolean publico, Boolean privado, Boolean protegido, Boolean estatico, Boolean finall, 
            int fila, int columna, Rol rol, TipoContenedor tipo, int tamanio) {
        this.id = id;
        this.valor = valor;
        this.publico = publico;
        this.privado = privado;
        this.protegido = protegido;
        this.estatico = estatico;
        this.finall = finall;
        this.fila = fila;
        this.columna = columna;
        this.rol = rol;
        this.tipo = tipo;
        this.tamanio = tamanio;
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

    /**
     * @return the publico
     */
    public Boolean getPublico() {
        return publico;
    }

    /**
     * @param publico the publico to set
     */
    public void setPublico(Boolean publico) {
        this.publico = publico;
    }

    /**
     * @return the privado
     */
    public Boolean getPrivado() {
        return privado;
    }

    /**
     * @param privado the privado to set
     */
    public void setPrivado(Boolean privado) {
        this.privado = privado;
    }

    /**
     * @return the protegido
     */
    public Boolean getProtegido() {
        return protegido;
    }

    /**
     * @param protegido the protegido to set
     */
    public void setProtegido(Boolean protegido) {
        this.protegido = protegido;
    }

    /**
     * @return the estatico
     */
    public Boolean getEstatico() {
        return estatico;
    }

    /**
     * @param estatico the estatico to set
     */
    public void setEstatico(Boolean estatico) {
        this.estatico = estatico;
    }

    /**
     * @return the finall
     */
    public Boolean getFinall() {
        return finall;
    }

    /**
     * @param finall the finall to set
     */
    public void setFinall(Boolean finall) {
        this.finall = finall;
    }

    /**
     * @return the tipoRetorno
     */
    public String getTipoRetorno() {
        return tipoRetorno;
    }

    /**
     * @param tipoRetorno the tipoRetorno to set
     */
    public void setTipoRetorno(String tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }
}

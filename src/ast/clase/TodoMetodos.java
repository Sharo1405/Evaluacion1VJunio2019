/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.clase;

import ast.NodoAST;
import ast.expresiones.operacion.TipoContenedor;
import ast.instrucciones.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class TodoMetodos {

    private LinkedList<String> visivilidad = new LinkedList<>();
    private TipoContenedor tipo;
    private LinkedList<Integer> listaCorchetes = new LinkedList<>();
    private String idMetodo;
    private LinkedList<Parametros> listaParametros = new LinkedList<>();
    private Instruccion listaContenidoSentencias;
    private Boolean isMain = false;
    private Boolean isOverride = false;
    private int linea;
    private int col;
    
    private String firma = "";

    //para metodo abstracto
    public TodoMetodos(LinkedList<String> visivilidad, TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo,
            LinkedList<Parametros> lisParametros, int linea, int col) {
        this.visivilidad = visivilidad;
        this.tipo = tipo;
        this.listaCorchetes = listaCorchetes;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.linea = linea;
        this.col = col;
    }

    public TodoMetodos(TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo,
            LinkedList<Parametros> lisParametros, int linea, int col) {
        this.tipo = tipo;
        this.listaCorchetes = listaCorchetes;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.linea = linea;
        this.col = col;
    }
    
    public TodoMetodos(LinkedList<String> visivilidad, TipoContenedor tipo, String idMetodo,
            LinkedList<Parametros> lisParametros, int linea, int col) {
        this.visivilidad = visivilidad;
        this.tipo = tipo;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.linea = linea;
        this.col = col;
    }

    public TodoMetodos(TipoContenedor tipo, String idMetodo,
            LinkedList<Parametros> lisParametros, int linea, int col) {
        this.tipo = tipo;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.linea = linea;
        this.col = col;
    }
    
    
    //MAIN  
    public TodoMetodos(LinkedList<String> visivilidad, TipoContenedor tipo, String idMetodo, LinkedList<Parametros> listaparametros,
            Instruccion listaContenidoSentencias, Boolean isMain, int linea, int col) {
        this.visivilidad = visivilidad;
        this.tipo = tipo;
        this.idMetodo = idMetodo;
        this.listaParametros = listaparametros;
        this.listaContenidoSentencias = listaContenidoSentencias;
        this.isMain = isMain; //true
        this.linea = linea;
        this.col = col;
    }

    
    
    //todos los metodos
    public TodoMetodos(LinkedList<String> visivilidad, TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo,
            LinkedList<Parametros> lisParametros, Instruccion sentencias ,int linea, int col) {
        this.visivilidad = visivilidad;
        this.tipo = tipo;
        this.listaCorchetes = listaCorchetes;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.listaContenidoSentencias = sentencias;
        this.linea = linea;
        this.col = col;
    }

    public TodoMetodos(TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo,
            LinkedList<Parametros> lisParametros, Instruccion sentencias ,int linea, int col) {
        this.tipo = tipo;
        this.listaCorchetes = listaCorchetes;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.listaContenidoSentencias = sentencias;
        this.linea = linea;
        this.col = col;
    }
    
    public TodoMetodos(LinkedList<String> visivilidad, TipoContenedor tipo, String idMetodo,
            LinkedList<Parametros> lisParametros, Instruccion sentencias ,int linea, int col) {
        this.visivilidad = visivilidad;
        this.tipo = tipo;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.listaContenidoSentencias = sentencias;
        this.linea = linea;
        this.col = col;
    }

    public TodoMetodos(TipoContenedor tipo, String idMetodo,
            LinkedList<Parametros> lisParametros, Instruccion sentencias ,int linea, int col) {
        this.tipo = tipo;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.listaContenidoSentencias = sentencias;
        this.linea = linea;
        this.col = col;
    }
    
    
    //metodos override
    public TodoMetodos(LinkedList<String> visivilidad, TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo,
            LinkedList<Parametros> lisParametros, Instruccion sentencias ,int linea, int col, Boolean override) {
        this.visivilidad = visivilidad;
        this.tipo = tipo;
        this.listaCorchetes = listaCorchetes;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.listaContenidoSentencias = sentencias;
        this.linea = linea;
        this.col = col;
        this.isOverride = override;
    }

    public TodoMetodos(TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo,
            LinkedList<Parametros> lisParametros, Instruccion sentencias ,int linea, int col, Boolean override) {
        this.tipo = tipo;
        this.listaCorchetes = listaCorchetes;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.listaContenidoSentencias = sentencias;
        this.linea = linea;
        this.col = col;
        this.isOverride = override;
    }
    
    public TodoMetodos(LinkedList<String> visivilidad, TipoContenedor tipo, String idMetodo,
            LinkedList<Parametros> lisParametros, Instruccion sentencias ,int linea, int col, Boolean override) {
        this.visivilidad = visivilidad;
        this.tipo = tipo;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.listaContenidoSentencias = sentencias;
        this.linea = linea;
        this.col = col;
        this.isOverride = override;
    }

    public TodoMetodos(TipoContenedor tipo, String idMetodo,
            LinkedList<Parametros> lisParametros, Instruccion sentencias ,int linea, int col, Boolean override) {
        this.tipo = tipo;
        this.idMetodo = idMetodo;
        this.listaParametros = lisParametros;
        this.listaContenidoSentencias = sentencias;
        this.linea = linea;
        this.col = col;
        this.isOverride = override;
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
     * @return the listaCorchetes
     */
    public LinkedList<Integer> getListaCorchetes() {
        return listaCorchetes;
    }

    /**
     * @param listaCorchetes the listaCorchetes to set
     */
    public void setListaCorchetes(LinkedList<Integer> listaCorchetes) {
        this.listaCorchetes = listaCorchetes;
    }

    /**
     * @return the idMetodo
     */
    public String getIdMetodo() {
        return idMetodo;
    }

    /**
     * @param idMetodo the idMetodo to set
     */
    public void setIdMetodo(String idMetodo) {
        this.idMetodo = idMetodo;
    }

    /**
     * @return the listaContenidoSentencias
     */
    public Instruccion getListaContenidoSentencias() {
        return listaContenidoSentencias;
    }

    /**
     * @param listaContenidoSentencias the listaContenidoSentencias to set
     */
    public void setListaContenidoSentencias(Instruccion listaContenidoSentencias) {
        this.listaContenidoSentencias = listaContenidoSentencias;
    }

    /**
     * @return the isMain
     */
    public Boolean getIsMain() {
        return isMain;
    }

    /**
     * @param isMain the isMain to set
     */
    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
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
     * @return the listaParametros
     */
    public LinkedList<Parametros> getListaParametros() {
        return listaParametros;
    }

    /**
     * @param listaParametros the listaParametros to set
     */
    public void setListaParametros(LinkedList<Parametros> listaParametros) {
        this.listaParametros = listaParametros;
    }

    /**
     * @return the firma
     */
    public String getFirma() {
        return firma;
    }

    /**
     * @param firma the firma to set
     */
    public void setFirma(String firma) {
        this.firma = firma;
    }

    /**
     * @return the isOverride
     */
    public Boolean getIsOverride() {
        return isOverride;
    }

    /**
     * @param isOverride the isOverride to set
     */
    public void setIsOverride(Boolean isOverride) {
        this.isOverride = isOverride;
    }

}

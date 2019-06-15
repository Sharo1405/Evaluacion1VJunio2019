/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.clase;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.instrucciones.Instruccion;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Clase implements Instruccion {

    private LinkedList<String> visivilidad;
    private String idClase;
    private String idHerencia;
    private LinkedList<NodoAST> instruccionesDentro;
    private Hashtable<String, Simbolo> tablaDeSimbolosClase = new Hashtable<>();
    private int linea;
    private int col;

    public Clase(LinkedList<String> visivilidad, String idClase, String idHerencia, LinkedList<NodoAST> instruccionesDentro, int linea, int col) {
        this.visivilidad = visivilidad;
        this.idClase = idClase;
        this.idHerencia = idHerencia;
        this.instruccionesDentro = instruccionesDentro;
        this.linea = linea;
        this.col = col;
    }

    public Clase(LinkedList<String> visivilidad, String idClase, LinkedList<NodoAST> instruccionesDentro, int linea, int col) {
        this.visivilidad = visivilidad;
        this.idClase = idClase;
        this.idHerencia = "";
        this.instruccionesDentro = instruccionesDentro;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLine() {
        return linea;
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
     * @return the idClase
     */
    public String getIdClase() {
        return idClase;
    }

    /**
     * @param idClase the idClase to set
     */
    public void setIdClase(String idClase) {
        this.idClase = idClase;
    }

    /**
     * @return the idHerencia
     */
    public String getIdHerencia() {
        return idHerencia;
    }

    /**
     * @param idHerencia the idHerencia to set
     */
    public void setIdHerencia(String idHerencia) {
        this.idHerencia = idHerencia;
    }

    /**
     * @return the instruccionesDentro
     */
    public LinkedList<NodoAST> getInstruccionesDentro() {
        return instruccionesDentro;
    }

    /**
     * @param instruccionesDentro the instruccionesDentro to set
     */
    public void setInstruccionesDentro(LinkedList<NodoAST> instruccionesDentro) {
        this.instruccionesDentro = instruccionesDentro;
    }

    /**
     * @return the tablaDeSimbolosClase
     */
    public Hashtable<String, Simbolo> getTablaDeSimbolosClase() {
        return tablaDeSimbolosClase;
    }

    /**
     * @param tablaDeSimbolosClase the tablaDeSimbolosClase to set
     */
    public void setTablaDeSimbolosClase(Hashtable<String, Simbolo> tablaDeSimbolosClase) {
        this.tablaDeSimbolosClase = tablaDeSimbolosClase;
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

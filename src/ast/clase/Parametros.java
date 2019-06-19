/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.clase;

import ast.NodoAST;
import ast.expresiones.operacion.TipoContenedor;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Parametros {

    private Boolean finall = false;
    private TipoContenedor tipoParametro;
    private LinkedList<NodoAST> variable;

    public Parametros(TipoContenedor tipoParametro, LinkedList<NodoAST> variable) {
        this.tipoParametro = tipoParametro;
        this.variable = variable;
    }

    public Parametros(Boolean finall, TipoContenedor tipoParametro, LinkedList<NodoAST> variable) {
        this.finall = finall;
        this.tipoParametro = tipoParametro;
        this.variable = variable;
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
     * @return the tipoParametro
     */
    public TipoContenedor getTipoParametro() {
        return tipoParametro;
    }

    /**
     * @param tipoParametro the tipoParametro to set
     */
    public void setTipoParametro(TipoContenedor tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    /**
     * @return the variable
     */
    public LinkedList<NodoAST> getVariable() {
        return variable;
    }

    /**
     * @param variable the variable to set
     */
    public void setVariable(LinkedList<NodoAST> variable) {
        this.variable = variable;
    }

}

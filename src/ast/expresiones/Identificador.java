/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import java.util.Map;

/**
 *
 * @author sharolin
 */
public class Identificador extends Entorno implements Expresion{

    
    private String id;
    private Simbolo.Tipo tipo;
    private int linea;
    private int columna;

    public Identificador(String id, Simbolo.Tipo tipo, int linea, int columna) {
        this.id = id;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }
    
    
    @Override
    public Object getValue(Entorno lista) {
        Simbolo encontrado = get(id, lista);
        if(encontrado != null){
            if(encontrado.getValor() instanceof Primitivos){
                Primitivos valRetorno = (Primitivos) encontrado.getValor();
                return valRetorno.valor;
            }else{
                return encontrado.getValor();
            }
        }
        return null;
    }

    
    
    @Override
    public Object getType(Entorno lista) {
        Simbolo encontrado = get(id, lista);
        if(encontrado != null){
            if(encontrado.getValor() instanceof Primitivos){
                Primitivos tipoRetorno = (Primitivos) encontrado.getValor();
                return tipoRetorno.tipo;
            }else{
                return encontrado.getTipo();
            }
        }
        return null;
    }

    
    
    @Override
    public int getLine() {
        return linea;
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

    /**
     * @return the tipo
     */
    public Simbolo.Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Simbolo.Tipo tipo) {
        this.tipo = tipo;
    }
}

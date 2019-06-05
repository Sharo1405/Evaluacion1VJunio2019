/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.entorno.Entorno;
import ast.entorno.Simbolo;

/**
 *
 * @author sharolin
 */
public class Primitivos implements Expresion{

    
    public String id;
    public Object valor;
    public Simbolo.Tipo tipo;
    public int linea;
    public int columna;

    public Primitivos() {
    }

    public Primitivos(String id, Object valor, Simbolo.Tipo tipo, int linea, int col) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = col;
    }
    
    
    public Primitivos(Object valor, Simbolo.Tipo tipo, int linea, int col) {
        this.id = "";
        this.valor = valor;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = col;
    }
    
    @Override
    public Object getValue(Entorno lista) {
        
        Simbolo buscando = lista.get(id, lista);
        if(buscando != null){
            return buscando.getValor();
        }else{
            return this.valor;
        }
        //return null;
    }

    @Override
    public Object getType(Entorno lista) {
        switch (tipo) {
            case INT:
                return Simbolo.Tipo.INT;
            case CHAR:
                return Simbolo.Tipo.CHAR;
        }
        return null;
    }

    @Override
    public int getLine() {
        return 0;
    }
    
}

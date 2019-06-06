/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operacion;

import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;

/**
 *
 * @author sharolin
 */
public class Operacion {
    public Expresion exp1;
    public Expresion exp2;
    public Operador op;
    public int line;
    public int col;
    public Simbolo.Tipo tipo;

    public Operacion(Expresion exp1, Expresion exp2, Operador op, int line, int col, Simbolo.Tipo tipo) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
        this.line = line;
        this.col = col;
        this.tipo = tipo;
    }
    
    public Operacion(Expresion exp1, Operador op, int line,  int col, Simbolo.Tipo tipo) {
        this.exp1 = exp1;
        this.exp2 = null;
        this.op = op;
        this.line = line;
        this.col = col;
        this.tipo = tipo;
    }
    
    public static enum Operador{
        MAS,
        MENOS,
        POR,
        DIVIDIR,
        POTENCIA,
        
        OR,
        AND,
        NOT,
        
        DIFERENTE,
        MAYORQ,
        MENORQ,
        MAYORIGUALQ,
        MENORIGUALQ,
        IGUAL
    }
    
    public Simbolo.Tipo tipoResultante(Expresion izquierda, Expresion derecha, Entorno lista){
        
        if(izquierda.getType(lista) == Simbolo.Tipo.STRING || derecha.getType(lista) == Simbolo.Tipo.STRING){
            return Simbolo.Tipo.STRING;
        }else if(izquierda.getType(lista) == Simbolo.Tipo.DOUBLE || derecha.getType(lista) == Simbolo.Tipo.DOUBLE){
            return Simbolo.Tipo.DOUBLE;
        }else if(izquierda.getType(lista) == Simbolo.Tipo.INT || derecha.getType(lista) == Simbolo.Tipo.INT){
            return Simbolo.Tipo.INT;
        }else if(izquierda.getType(lista) == Simbolo.Tipo.CHAR || derecha.getType(lista) == Simbolo.Tipo.CHAR){
            return Simbolo.Tipo.CHAR;
        }else if(izquierda.getType(lista) == Simbolo.Tipo.BOOLEAN || derecha.getType(lista) == Simbolo.Tipo.BOOLEAN){
            return Simbolo.Tipo.BOOLEAN;
        }
        
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operacion;

import ast.entorno.Simbolo;
import ast.expresiones.Expresion;

/**
 *
 * @author sharolin
 */
public class Operacion {
    Expresion exp1;
    Expresion exp2;
    Operador op;
    int line;
    Simbolo.Tipo tipo;

    public Operacion(Expresion exp1, Expresion exp2, Operador op, int line, Simbolo.Tipo tipo) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
        this.line = line;
        this.tipo = tipo;
    }
    
    public Operacion(Expresion exp1, Operador op, int line, Simbolo.Tipo tipo) {
        this.exp1 = exp1;
        this.op = op;
        this.line = line;
        this.tipo = tipo;
    }
    
    public static enum Operador{
        MAS,
        MENOS,
        POR,
        DIVIDIR,
        NULL
    }
}

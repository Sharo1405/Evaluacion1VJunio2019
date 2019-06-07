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
    public TipoContenedor tipo;

    public Operacion(Expresion exp1, Expresion exp2, Operador op, int line, int col, TipoContenedor tipo) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
        this.line = line;
        this.col = col;
        this.tipo = tipo;
    }

    public Operacion(Expresion exp1, Operador op, int line, int col, TipoContenedor tipo) {
        this.exp1 = exp1;
        this.exp2 = null;
        this.op = op;
        this.line = line;
        this.col = col;
        this.tipo = tipo;
    }

    public static enum Operador {
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

    public TipoContenedor tipoResultante(Expresion izquierda, Expresion derecha, Entorno lista) {

        try {

            TipoContenedor izq = (TipoContenedor) izquierda.getType(lista);
            TipoContenedor der = (TipoContenedor) derecha.getType(lista);
            TipoContenedor aux = new TipoContenedor();

            if (aux.isString(izq) || aux.isString(der)) {
                return new TipoContenedor(Simbolo.Tipo.STRING);

            } else if (aux.isBool(izq) || aux.isBool(der)) {
                System.out.println("Error de tipo para aritmeticas");
                return null;

            } else if (aux.isDecimal(izq) || aux.isDecimal(der)) {
                return new TipoContenedor(Simbolo.Tipo.DOUBLE);

            } else if (aux.isEntero(izq) || aux.isEntero(der) || aux.isChar(izq) || aux.isChar(der)) {
                return new TipoContenedor(Simbolo.Tipo.INT);

            }

        } catch (Exception e) {
        }

        return null;
    }

    public TipoContenedor tipoResultanteRELACIONAL(Expresion izquierda, Expresion derecha, Entorno lista) {

        try {

            TipoContenedor izq = (TipoContenedor) izquierda.getType(lista);
            TipoContenedor der = (TipoContenedor) derecha.getType(lista);
            TipoContenedor aux = new TipoContenedor();

            if (aux.isString(izq) && aux.isString(der)) {
                return new TipoContenedor(Simbolo.Tipo.STRING);

            } else if (aux.isBool(izq) && aux.isBool(der)) {
                return new TipoContenedor(Simbolo.Tipo.BOOLEAN);

            } else if (aux.isEntero(izq) && aux.isEntero(der) || aux.isChar(izq) || aux.isChar(der)
                    || aux.isDecimal(izq) && aux.isDecimal(der)) {
                return new TipoContenedor(Simbolo.Tipo.DOUBLE);

            }

        } catch (Exception e) {
        }
        return null;
    }

    public Double casteoRelacional(Expresion exp, Entorno lista) {

        try {

            TipoContenedor aux = (TipoContenedor)exp.getType(lista);

            if (aux.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                char var[] = String.valueOf(exp.getValue(lista)).toCharArray();
                int v = (int) var[0];
                return (int) var[0] * 1.0;
            } else if (aux.getTipoPrimitivo() == Simbolo.Tipo.DOUBLE) {
                return Double.parseDouble(String.valueOf(exp.getValue(lista)));
            } else if (aux.getTipoPrimitivo() == Simbolo.Tipo.INT) {
                return Double.parseDouble(String.valueOf(exp.getValue(lista)));
            }

        } catch (Exception e) {
        }
        return null;
    }
}

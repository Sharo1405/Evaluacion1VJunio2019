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
public class Logica extends Operacion implements Expresion {

    public Logica(Expresion exp1, Expresion exp2, Operador op, int line, int col, Simbolo.Tipo tipo) {
        super(exp1, exp2, op, line, col, tipo);
    }

    public Logica(Expresion exp1, Operador op, int line, int col, Simbolo.Tipo tipo) {
        super(exp1, op, line, col, tipo);
    }

    @Override
    public Object getValue(Entorno lista) {

        if (exp1 != null && exp2 != null) {
            Object op1 = exp1.getValue(lista);
            Object op2 = exp2.getValue(lista);
            if (tipoResultante(exp1, exp2, lista) == Simbolo.Tipo.BOOLEAN) {
                switch (op) {
                    case OR:
                        return Boolean.parseBoolean(String.valueOf(exp1.getValue(lista))) || Boolean.parseBoolean(String.valueOf(exp2.getValue(lista)));

                    case AND:
                        return Boolean.parseBoolean(String.valueOf(exp1.getValue(lista))) && Boolean.parseBoolean(String.valueOf(exp2.getValue(lista)));
                }
            }
        } else {
            Object op1 = exp1.getValue(lista);
            if (exp1.getType(lista) == Simbolo.Tipo.BOOLEAN) {
                switch (op) {
                    case NOT:
                        return !Boolean.parseBoolean(String.valueOf(exp1.getValue(lista)));

                }
            }
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista) {
        if (exp2 != null) {
            return tipoResultante(exp1, exp2, lista);
        } else {
            return exp1.getType(lista);
        }
    }

    @Override
    public int getLine() {
        return line;
    }

}

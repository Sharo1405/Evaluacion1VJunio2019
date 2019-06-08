/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operacion;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;

/**
 *
 * @author sharolin
 */
public class Logica extends Operacion implements Expresion {

    public Logica(Expresion exp1, Expresion exp2, Operador op, int line, int col, TipoContenedor tipo) {
        super(exp1, exp2, op, line, col, tipo);
    }

    public Logica(Expresion exp1, Operador op, int line, int col, TipoContenedor tipo) {
        super(exp1, op, line, col, tipo);
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {

            if (exp1 != null && exp2 != null) {
                Object op1 = exp1.getValue(lista, impresion);
                Object op2 = exp2.getValue(lista, impresion);

                TipoContenedor tresulta = tipoResultante(exp1, exp1, lista, impresion);

                if (tresulta.getTipoPrimitivo() == Simbolo.Tipo.BOOLEAN) {
                    switch (op) {
                        case OR:
                            return Boolean.parseBoolean(String.valueOf(exp1.getValue(lista, impresion))) || Boolean.parseBoolean(String.valueOf(exp2.getValue(lista, impresion)));

                        case AND:
                            return Boolean.parseBoolean(String.valueOf(exp1.getValue(lista, impresion))) && Boolean.parseBoolean(String.valueOf(exp2.getValue(lista, impresion)));
                    }
                } else {
                    System.out.println("Error de tipo");
                    impresion.errores.add(new ast.Error("Error de tipos para -num OP Logica", line, col, "Semantico"));
                }

            } else {
                Object op1 = exp1.getValue(lista, impresion);
                TipoContenedor tresu = (TipoContenedor) exp1.getType(lista, impresion);
                if (tresu.getTipoPrimitivo() == Simbolo.Tipo.BOOLEAN) {
                    switch (op) {
                        case NOT:
                            return !Boolean.parseBoolean(String.valueOf(exp1.getValue(lista, impresion)));

                    }
                } else {
                    impresion.errores.add(new ast.Error("Error de tipos para -num OP Logica", line, col, "Semantico"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error en clase Logiga getValue");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        if (exp2 != null) {
            return tipoResultante(exp1, exp2, lista, impresion);
        } else {
            return exp1.getType(lista, impresion);
        }
    }

    @Override
    public int getLine() {
        return line;
    }

}

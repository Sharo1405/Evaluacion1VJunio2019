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
import java.util.Objects;

/**
 *
 * @author sharolin
 */
public class Relacional extends Operacion implements Expresion {

    public Relacional(Expresion exp1, Expresion exp2, Operador op, int line, int col, TipoContenedor tipo) {
        super(exp1, exp2, op, line, col, tipo);
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impre) {

        try {

            Object op1 = exp1.getValue(lista, impre);
            Object op2 = exp2.getValue(lista, impre);

            if (op1 != null && op2 != null) {

                switch (op) {
                    case MAYORIGUALQ:
                        return casteoRelacional(exp1, lista, impre) >= casteoRelacional(exp2, lista, impre);
                    case MENORIGUALQ:
                        return casteoRelacional(exp1, lista, impre) <= casteoRelacional(exp2, lista, impre);

                    case MAYORQ:
                        return casteoRelacional(exp1, lista, impre) > casteoRelacional(exp2, lista, impre);

                    case MENORQ:
                        return casteoRelacional(exp1, lista, impre) < casteoRelacional(exp2, lista, impre);

                    case IGUAL:

                        TipoContenedor tresulta = tipoResultanteRELACIONAL(exp1, exp1, lista, impre);

                        switch (tresulta.getTipoPrimitivo()) {
                            case STRING:
                                return String.valueOf(exp1.getValue(lista, impre)).equals(String.valueOf(exp2.getValue(lista, impre)));

                            case BOOLEAN:
                                return Boolean.parseBoolean(String.valueOf(exp1.getValue(lista, impre))) == Boolean.parseBoolean(String.valueOf(exp2.getValue(lista, impre)));

                            case DOUBLE:
                                return Objects.equals(casteoRelacional(exp1, lista, impre), casteoRelacional(exp2, lista, impre));

                            default:
                                impre.errores.add(new ast.Error("Error no se puede operar los tipos en Operacion Relacional, para IGUAL", line, col, "Semantico"));
                        }

                    case DIFERENTE:

                        TipoContenedor tresulta2 = tipoResultanteRELACIONAL(exp1, exp1, lista, impre);

                        switch (tresulta2.getTipoPrimitivo()) {
                            case STRING:
                                return !String.valueOf(exp1.getValue(lista, impre)).equals(String.valueOf(exp2.getValue(lista, impre)));

                            case BOOLEAN:
                                return Boolean.parseBoolean(String.valueOf(exp1.getValue(lista, impre))) != Boolean.parseBoolean(String.valueOf(exp2.getValue(lista, impre)));

                            case DOUBLE:
                                return casteoRelacional(exp2, lista, impre) != casteoRelacional(exp1, lista, impre);

                            default:
                                impre.errores.add(new ast.Error("Error no se puede operar los tipos en Operacion Relacional para DIFERETE", line, col, "Semantico"));
                        }
                }
            }
            System.out.println("Error de tipos para comparacion");
            impre.errores.add(new ast.Error("Error no se puede operar un valor NULO, Operacion Relacional", line, col, "Semantico"));

        } catch (Exception e) {
            System.out.println("Error en la clase Relacional getValor");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impre) {
        return new TipoContenedor(Simbolo.Tipo.BOOLEAN);
    }

    @Override
    public int getLine() {
        return line;
    }

}

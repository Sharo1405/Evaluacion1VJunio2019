/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operacion;

import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.ExplicitGroup;
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
    public Object getValue(Entorno lista) {
        Object op1 = exp1.getValue(lista);
        Object op2 = exp2.getValue(lista);

        if (op1 != null && op2 != null) {

            switch (op) {
                case MAYORIGUALQ:
                    return casteoRelacional(exp1, lista) >= casteoRelacional(exp2, lista);
                case MENORIGUALQ:
                    return casteoRelacional(exp1, lista) <= casteoRelacional(exp2, lista);

                case MAYORQ:
                    return casteoRelacional(exp1, lista) > casteoRelacional(exp2, lista);

                case MENORQ:
                    return casteoRelacional(exp1, lista) < casteoRelacional(exp2, lista);

                case IGUAL:

                    TipoContenedor tresulta = tipoResultante(exp1, exp1, lista);

                    switch (tresulta.getTipoPrimitivo()) {
                        case STRING:
                            return String.valueOf(exp1.getValue(lista)).equals(String.valueOf(exp2.getValue(lista)));

                        case BOOLEAN:
                            return Boolean.parseBoolean(String.valueOf(exp1.getValue(lista))) == Boolean.parseBoolean(String.valueOf(exp2.getValue(lista)));

                        case DOUBLE:
                            return casteoRelacional(exp1, lista) == casteoRelacional(exp2, lista);
                    }

                case DIFERENTE:

                    TipoContenedor tresulta2 = tipoResultante(exp1, exp1, lista);

                    switch (tresulta2.getTipoPrimitivo()) {
                        case STRING:
                            return !String.valueOf(exp1.getValue(lista)).equals(String.valueOf(exp2.getValue(lista)));

                        case BOOLEAN:
                            return Boolean.parseBoolean(String.valueOf(exp1.getValue(lista))) != Boolean.parseBoolean(String.valueOf(exp2.getValue(lista)));

                        case DOUBLE:
                            return casteoRelacional(exp2, lista) != casteoRelacional(exp1, lista);
                    }
            }
        }
        System.out.println("Error de tipos para comparacion");
        return null;
    }

    @Override
    public Object getType(Entorno lista) {
        return new TipoContenedor(Simbolo.Tipo.BOOLEAN);
    }

    @Override
    public int getLine() {
        return line;
    }

}

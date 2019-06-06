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

    public Relacional(Expresion exp1, Expresion exp2, Operador op, int line, int col, Simbolo.Tipo tipo) {
        super(exp1, exp2, op, line, col, tipo);
    }

    @Override
    public Object getValue(Entorno lista) {
        Object op1 = exp1.getValue(lista);
        Object op2 = exp2.getValue(lista);

        if (op1 != null && op2 != null) {

            switch (op) {
                case MAYORIGUALQ:
                    return casteoRelacional(exp1,lista) >= casteoRelacional(exp2, lista);
                case MENORIGUALQ:
                    return casteoRelacional(exp1,lista) <= casteoRelacional(exp2,lista);

                case MAYORQ:
                    return casteoRelacional(exp1,lista) > casteoRelacional(exp2,lista);

                case MENORQ:
                    return casteoRelacional(exp1,lista) < casteoRelacional(exp2,lista);

                case IGUAL:
                    if(tipoResultante(exp1, exp2, lista) == Simbolo.Tipo.STRING){
                      return String.valueOf(op1).equals(String.valueOf(op2));
                    }else{
                        return Objects.equals(casteoRelacional(exp1,lista), casteoRelacional(exp2,lista));
                    }

                case DIFERENTE:
                    if(tipoResultante(exp1, exp2, lista) == Simbolo.Tipo.STRING){
                      return !String.valueOf(op1).equals(String.valueOf(op2));
                    }else{
                        return !Objects.equals(casteoRelacional(exp1,lista), casteoRelacional(exp2,lista));
                    }
            }
        }
        System.out.println("Error de tipos para comparacion");
        return null;
    }

    @Override
    public Object getType(Entorno lista) {
        return Simbolo.Tipo.BOOLEAN;
    }

    @Override
    public int getLine() {
        return line;
    }

}

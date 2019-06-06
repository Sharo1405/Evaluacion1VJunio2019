/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;

/**
 *
 * @author sharolin
 */
public class Asignacion implements Instruccion {

    public String id;
    public Expresion valor;
    public int linea;
    public int columna;

    public Asignacion(String id, Expresion valor, int linea, int columna) {
        this.id = id;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object ejecutar(Entorno lista) {

        Simbolo variable = lista.get(id, lista);
        if (variable != null) {
            if (variable.getTipo() == valor.getType(lista)) {
                variable.setValor(valor.getValue(lista));
            }
        }

        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import ast.expresiones.operacion.TipoContenedor;
import java.util.LinkedList;

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
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {

            Simbolo variable = lista.get(id, lista);
            if (variable != null) {
                TipoContenedor tipo = (TipoContenedor) valor.getType(lista, impresion);
                TipoContenedor tipoVar = (TipoContenedor) variable.getTipo();

                if (tipoVar.getTipoPrimitivo() == Simbolo.Tipo.INT && tipo.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {

                    if (Integer.parseInt(String.valueOf(valor.getValue(lista, impresion))) > 0) {
                        char nuevo = (Character) valor.getValue(lista, impresion);
                        int x = (int) nuevo * 1;
                        variable.setValor(x);
                    } else {
                        variable.setValor(Integer.parseInt(String.valueOf(valor.getValue(lista, impresion))));
                    }

                } else if (tipoVar.getTipoPrimitivo() == Simbolo.Tipo.DOUBLE && tipo.getTipoPrimitivo() == Simbolo.Tipo.INT) {
                    Double nueva = Double.parseDouble(String.valueOf(valor.getValue(lista, impresion)));
                    variable.setValor(nueva);

                } else if (tipoVar.getTipoPrimitivo() == Simbolo.Tipo.DOUBLE && tipo.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                    char var[] = String.valueOf(valor.getValue(lista, impresion)).toCharArray();
                    int v = (int) var[0];
                    Double xx = (double) var[0] * 1.0;
                    variable.setValor(xx);

                } else if (tipoVar.getTipoPrimitivo() == tipo.getTipoPrimitivo()) {

                    if (tipoVar.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tipo.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                         if (Integer.parseInt(String.valueOf(valor.getValue(lista, impresion))) < 0) {
                             impresion.errores.add(new ast.Error("Error no puede haber un char negativo", linea, columna, "Semantico"));
                         }else{
                             variable.setValor(valor.getValue(lista, impresion));
                         }
                    } else {
                        variable.setValor(valor.getValue(lista, impresion));
                    }

                } else if (tipoVar.getTipoObjeto().equals(tipo.getTipoObjeto())) {
                    //AQUI VAN OBJETOS
                } else {
                    System.out.println("Error de tipos");
                    impresion.errores.add(new ast.Error("Error de tipos para Asignacion", linea, columna, "Semantico"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error en clase Asignacion ejecutar");
        }

        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

}

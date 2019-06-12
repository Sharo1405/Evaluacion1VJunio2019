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

                if (tipo.getTipoPrimitivo() == Simbolo.Tipo.NULO) {

                    if (tipoVar.getTipoPrimitivo() == Simbolo.Tipo.CHAR || tipoVar.getTipoPrimitivo() == Simbolo.Tipo.INT
                            || tipoVar.getTipoPrimitivo() == Simbolo.Tipo.DOUBLE) {
                        impresion.errores.add(new ast.Error("No se puede asignar, El tipo de la variable no admite NULL", linea, columna, "Semantico"));
                        return null;
                    }
                } else if (tipoVar.getTipoPrimitivo() == Simbolo.Tipo.INT && tipo.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {

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
                        Object paraValor = valor.getValue(lista, impresion);

                        try {
                            int valorsito = (int) paraValor * 1;
                            if (valorsito < 0) {
                                impresion.errores.add(new ast.Error("Error de tipos, un char negativo no existe", linea, columna, "Semantico"));
                            } else {
                                TipoContenedor aux = new TipoContenedor();
                                if (aux.isChar(tipo)) {
                                    variable.setValor(paraValor);
                                }
                            }
                        } catch (Exception e) {
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

        impresion.errores.add(new ast.Error("Variable no existe para asignar valor", linea, columna, "Semantico"));
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

}

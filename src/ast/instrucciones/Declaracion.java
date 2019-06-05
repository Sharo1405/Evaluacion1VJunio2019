/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import ast.expresiones.Primitivos;

/**
 *
 * @author sharolin
 */
public class Declaracion implements Instruccion {

    private Simbolo.Tipo tipo;
    private String id;
    private Expresion valor;
    private int linea;
    private int columna;

    public Declaracion(Simbolo.Tipo type, String id, Expresion valor, int linea, int columna) {
        this.tipo = type;
        this.id = id;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    public Declaracion(Simbolo.Tipo type, String id, int linea, int columna) {
        this.tipo = type;
        this.id = id;
        this.linea = linea;
        this.columna = columna;
        this.valor = null;
    }

    @Override
    public Object ejecutar(Entorno lista) {

        Simbolo buscado = lista.getEnActual(id);
        if (buscado == null) {

            if (valor != null) {

                if (tipo.equals(valor.getType(lista))) {

                    lista.setSimbolo(id, new Simbolo(id, valor.getValue(lista), valor.getType(lista), linea, columna));

                } else {
                    System.out.println("Error de tipo");
                }

            } else {
                lista.setSimbolo(id, new Simbolo(id, valor, tipo, linea, columna));
            }
            System.out.println("Se declaro la variable " + id);

        } else {
            System.out.println("Esa variable ya existe");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the tipo
     */
    public Simbolo.Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Simbolo.Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the valor
     */
    public Expresion getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Expresion valor) {
        this.valor = valor;
    }

    /**
     * @return the linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

}

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
public class Declaracion implements Instruccion {

    private TipoContenedor tipo;
    private String id;
    private Expresion valor;
    private int linea;
    private int columna;

    public Declaracion(TipoContenedor type, String id, Expresion valor, int linea, int columna) {
        this.tipo = type;
        this.id = id;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    public Declaracion(TipoContenedor type, String id, int linea, int columna) {
        this.tipo = type;
        this.id = id;
        this.linea = linea;
        this.columna = columna;
        this.valor = null;
    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {

            Simbolo buscado = lista.getEnActual(id);
            if (buscado == null) {

                if (valor != null) {

                    //Object uu = valor.getType(lista, impresion);
                    TipoContenedor tip = (TipoContenedor) valor.getType(lista, impresion);
                    if (tip != null) {
                        if (!"".equals(tipo.getTipoObjeto())) {
                            //OBJETOS
                        } else {

                            if (tipo.getTipoPrimitivo() == Simbolo.Tipo.INT && tip.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {

                                if (Integer.parseInt(String.valueOf(valor.getValue(lista, impresion))) > 0) {
                                    char nuevo = (Character) valor.getValue(lista, impresion);
                                    int x = (int) nuevo * 1;
                                    lista.setSimbolo(id, new Simbolo(id, x, tipo, linea, columna));
                                } else {
                                    lista.setSimbolo(id, new Simbolo(id, Integer.parseInt(String.valueOf(valor.getValue(lista, impresion))), tipo, linea, columna));
                                }

                                System.out.println("Se declaro la variable " + id + " " + String.valueOf(valor.getValue(lista, impresion)));

                            } else if (tipo.getTipoPrimitivo() == Simbolo.Tipo.DOUBLE && tip.getTipoPrimitivo() == Simbolo.Tipo.INT) {
                                Double nueva = Double.parseDouble(String.valueOf(valor.getValue(lista, impresion)));
                                lista.setSimbolo(id, new Simbolo(id, nueva, tipo, linea, columna));
                                System.out.println("Se declaro la variable " + id + " " + String.valueOf(valor.getValue(lista, impresion)));

                            } else if (tipo.getTipoPrimitivo() == Simbolo.Tipo.DOUBLE && tip.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(valor.getValue(lista, impresion)).toCharArray();
                                int v = (int) var[0];
                                Double xx = (double) var[0] * 1.0;
                                lista.setSimbolo(id, new Simbolo(id, xx, tipo, linea, columna));
                                System.out.println("Se declaro la variable " + id + " " + String.valueOf(valor.getValue(lista, impresion)));

                            } else if (tipo.getTipoPrimitivo() == tip.getTipoPrimitivo()) {
                                if (tipo.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tip.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {

                                    Object paraValor = valor.getValue(lista, impresion);
                                    try {
                                        int valorsito = (int) paraValor * 1;
                                        if (valorsito < 0) {
                                            impresion.errores.add(new ast.Error("Error de tipos, un char negativo no existe", linea, columna, "Semantico"));
                                        } else {
                                            TipoContenedor aux = new TipoContenedor();
                                            if (aux.isChar(tip)) {
                                                lista.setSimbolo(id, new Simbolo(id, valor.getValue(lista, impresion), valor.getType(lista, impresion), linea, columna));
                                            }
                                        }
                                    } catch (Exception e) {
                                        String va = String.valueOf(paraValor);
                                        char v[] = va.toCharArray();
                                        char vava = v[0];

                                        lista.setSimbolo(id, new Simbolo(id, vava, valor.getType(lista, impresion), linea, columna));
                                    }
                                } else {
                                    lista.setSimbolo(id, new Simbolo(id, valor.getValue(lista, impresion), valor.getType(lista, impresion), linea, columna));
                                    System.out.println("Se declaro la variable " + id + String.valueOf(valor.getValue(lista, impresion)));
                                }

                            } else if (tipo.getTipoObjeto().equals(tip.getTipoObjeto())) {
                                //AQUI VAN OBJETOS
                            } else {
                                System.out.println("Error de tipos");
                                impresion.errores.add(new ast.Error("Error de tipos para Declaracion", linea, columna, "Semantico"));
                            }
                        }
                    }else{
                        impresion.errores.add(new ast.Error("Error no existe ID para asignar su valor", linea, columna, "Semantico"));
                    }
                } else {
                    lista.setSimbolo(id, new Simbolo(id, valor, tipo, linea, columna));
                }

            } else {
                System.out.println("Esa variable ya existe");
                impresion.errores.add(new ast.Error("Esa variable ya existe: " + id, linea, columna, "Semantico"));
            }
        } catch (Exception e) {
            System.out.println("Error en la clase Declaracion ejecutar");
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
    public TipoContenedor getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoContenedor tipo) {
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

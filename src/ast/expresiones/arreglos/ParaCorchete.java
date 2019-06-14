/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.arreglos;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import ast.expresiones.operacion.TipoContenedor;
import ast.expresiones.primitivos.Booleano;
import ast.expresiones.primitivos.Cadena;
import ast.expresiones.primitivos.Caracter;
import ast.expresiones.primitivos.Decimal;
import ast.expresiones.primitivos.Entero;
import ast.expresiones.primitivos.Nulo;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class ParaCorchete implements Expresion {

    public LinkedList<NodoAST> listadeExpresiones;
    public LinkedList<Integer> listaIndex = new LinkedList<>();
    public int dimensiones = 0;
    public NodoNNario arbolArreglo = new NodoNNario();

    public ParaCorchete(LinkedList<NodoAST> listadeExpresiones) {
        this.listadeExpresiones = listadeExpresiones;
    }

    public NodoNNario HacerArbol(int nivel, int dim, LinkedList<Integer> listaIndices, NodoNNario actual, TipoContenedor tipo, Entorno lista, ListaErrorPrinter impresion) {

        try {

            if (dim == listaIndices.size()) {
                for (int i = 0; i < listaIndices.get(dim - 1); i++) {

                    //verificar tipos
                    Object ti = tipo.ejecutar(lista, impresion);
                    if (ti instanceof Simbolo.Tipo) {

                        Simbolo.Tipo p = (Simbolo.Tipo) ti;

                        switch (p) {
                            case INT:
                                 actual.hijos.add(new Entero(0, tipo, -1, -1));
                                 break;

                            case STRING:
                                actual.hijos.add(new Cadena("", tipo, -1, -1));
                                break;

                            case DOUBLE:
                                actual.hijos.add(new Decimal(0.0, tipo, -1, -1));
                                break;

                            case CHAR:
                                actual.hijos.add(new Caracter('\0', tipo, -1, -1));
                                break;

                            case BOOLEAN:
                                actual.hijos.add(new Booleano(false, tipo, -1, -1));
                                break;
                        }

                    } else if (ti instanceof String) {
                         actual.hijos.add(new Nulo(null, tipo, nivel, i));
                    }

                }
                return null;
            }

            for (int i = 0; i < listaIndices.get(dim - 1); i++) {
                NodoNNario nuevo = new NodoNNario(tipo, listaIndices.get(nivel)); //verificar que el nivel sea el # de dim de cada nivel
                actual.hijos.add(nuevo);
                HacerArbol(nivel - 1, dim + 1, listaIndices, nuevo, tipo, lista, impresion);
            }
        } catch (Exception e) {
            System.out.println("Error en la clase ParaCorchete, HacerArbol");
        }
        return arbolArreglo;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {

            for (NodoAST listadeExpresione : listadeExpresiones) {
                Expresion exp = (Expresion) listadeExpresione;
                TipoContenedor tu = (TipoContenedor) exp.getType(lista, impresion);
                if (tu.getTipoPrimitivo() == Simbolo.Tipo.INT) {
                    int indice = Integer.parseInt(String.valueOf(exp.getValue(lista, impresion)));
                    dimensiones++;
                    listaIndex.add(indice);
                } else {
                    impresion.errores.add(new ast.Error("Tipo de indice no valido para un arreglo", exp.getLine(), -1, "Semantico"));
                    return null;
                }
            }

            //HacerArbol(listaIndex.size() - 1, listaIndex.get(listaIndex.size() - 1), arbolArreglo.hijos);
            return this;

        } catch (Exception e) {
            System.out.println("Error en la clase ParaCorchete, getValue");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        return "arreglo";
    }

    @Override
    public int getLine() {
        return -1;
    }
}

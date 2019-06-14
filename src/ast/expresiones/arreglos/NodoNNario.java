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
import java.awt.Dimension;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class NodoNNario {

    public TipoContenedor tipo;
    public int dimensiones;
    public LinkedList<Object> hijos = new LinkedList<>();

    Object retorno = new Object();

    public NodoNNario() {
    }

    public NodoNNario(TipoContenedor tipo, int dimensiones) {
        this.tipo = tipo;
        this.dimensiones = dimensiones;
    }

    public NodoNNario(LinkedList<Object> hijos) {
        this.hijos = hijos;
    }

    public Object obtenerPorIndice(NodoNNario arreglo, LinkedList<Integer> indicesPorDimension, Entorno lista, ListaErrorPrinter impresion) {

        try {
            for (int i = 0; i < indicesPorDimension.size(); i++) {
                int indice = indicesPorDimension.get(i);
                Object nodo = arreglo.hijos.get(indice);
                
                if (nodo instanceof NodoNNario) {
                    NodoNNario no = (NodoNNario) nodo;

                    try {
                        int aux = indicesPorDimension.get(i + 1);
                        indicesPorDimension.remove(i);
                        obtenerPorIndice(no, indicesPorDimension, lista, impresion);
                    } catch (Exception e) {
                        return nodo;
                    }
                } else {
                    retorno = nodo;
                    return retorno;
                }

                if (indicesPorDimension.size() == 0) {
                    break;
                }
                
            }

            return retorno;
        } catch (Exception e) {
            System.out.println("Erroe en el NodoNNario, obtenerPorIndice");
        }

        return null;
    }

    public void setearPorIndice(NodoNNario arbol, LinkedList<Integer> listaIndices, Object valor, Entorno lista, ListaErrorPrinter impresion) {
        try {
            for (int i = 0; i < listaIndices.size(); i++) {
                int indice = listaIndices.get(i);
                Object nodo = arbol.hijos.get(indice);

                if (nodo instanceof NodoNNario) {
                    NodoNNario no = (NodoNNario) nodo;

                    try {
                        if (listaIndices.size() == 1) {
                            if (arbol.tipo.ejecutar(lista, impresion) == ((NodoNNario) valor).tipo.ejecutar(lista, impresion)) {
                                ((NodoNNario) nodo).hijos.add(indice, valor);
                            }
                        } else {
                            listaIndices.remove(i);
                            setearPorIndice(arbol, listaIndices, valor, lista, impresion);
                        }
                    } catch (Exception e) {

                    }
                }
                //listaIndices.remove(i);
                if (listaIndices.size() == 0) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erroe en el NodoNNario, setearPorIndice");
        }
    }
}

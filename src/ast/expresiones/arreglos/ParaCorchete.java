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

    public NodoNNario HacerArbol(int nivel, int dim, NodoNNario actual, TipoContenedor tipo) {

        try {

            if (nivel == 0) {
                NodoNNario arregloFinal = new NodoNNario(tipo, dim);
                for (int i = listaIndex.get(nivel); i > 0; i--) {
                    arregloFinal.hijos.add(actual);
                }
                nivel--;
                if (nivel < 0) {
                    arbolArreglo = arregloFinal;
                    return arbolArreglo;
                }
            } else if (nivel == (listaIndex.size() - 1)) {                
                Object objetoHoja = -1;
                NodoNNario nodo = new NodoNNario(tipo, dim);
                
                for (int i = listaIndex.get(nivel); i > 0; i--) {
                    nodo.hijos.add(objetoHoja);
                }
                nivel--;
                if (nivel < 0) {
                    arbolArreglo = nodo;                    
                } else {
                    HacerArbol(nivel, dim + 1, nodo, tipo);
                }
            } else {
                LinkedList<NodoNNario> listanodos = new LinkedList<>();
                for (int i = listaIndex.get(nivel); i > 0; i--) {
                    listanodos.add(actual);
                }
                nivel--;
                HacerArbol(nivel, dim + 1, actual, tipo);
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

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

    /*public NodoNNario HacerArbol(int nivel, int numeroEnPosNivel, LinkedList<NodoNNario> actual) {

        try {

            if (nivel == 0) {
                LinkedList<NodoNNario> listanodos = new LinkedList<>();
                for (int i = listaIndex.get(nivel); i > 0; i--) {
                    listanodos.add(new NodoNNario(actual));
                }
                nivel--;
                if (nivel < 0) {
                    arbolArreglo.hijos = listanodos;
                }
            } else if (nivel == (listaIndex.size() - 1)) {
                LinkedList<NodoNNario> listanodos = new LinkedList<>();

                for (int i = listaIndex.get(nivel); i > 0; i--) {
                    listanodos.add(new NodoNNario(null));
                }
                nivel--;
                if (nivel < 0) {
                    arbolArreglo.hijos = listanodos;                    
                } else {
                    HacerArbol(nivel, listaIndex.get(nivel), listanodos);
                }
            } else {
                LinkedList<NodoNNario> listanodos = new LinkedList<>();
                for (int i = listaIndex.get(nivel); i > 0; i--) {
                    listanodos.add(actual);
                }
                nivel--;
                HacerArbol(nivel, listaIndex.get(nivel), listanodos);
            }

        } catch (Exception e) {
            System.out.println("Error en la clase ParaCorchete, HacerArbol");
        }

        return null;
    }*/

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

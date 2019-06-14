/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import ast.expresiones.Identificador;
import ast.expresiones.arreglos.NodoNNario;
import ast.expresiones.operacion.TipoContenedor;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class PosicionArregloRetorno implements Expresion {

    private String id;
    private LinkedList<NodoAST> indiceArreglo;
    private int linea;
    private int col;

    private int dimensiones = 0;
    private LinkedList<Integer> listaIndex = new LinkedList<>();

    public PosicionArregloRetorno(String id, LinkedList<NodoAST> indiceArreglo, int linea, int col) {
        this.id = id;
        this.indiceArreglo = indiceArreglo;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {
            Identificador idBuscar = new Identificador(id, linea, col);
            Simbolo arregloEncontrado = idBuscar.get(id, lista);
            if (arregloEncontrado != null) {
                listaIndex = new LinkedList<>();
                for (NodoAST listadeExpresione : indiceArreglo) {
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

                NodoNNario arbol = new NodoNNario();
                return arbol.obtenerPorIndice((NodoNNario) arregloEncontrado.getValor(), listaIndex, lista, impresion);

            } else {
                impresion.errores.add(new ast.Error("El arreglo buscano no existe: " + id, linea, col, "Semantico"));
            }
        } catch (Exception e) {
            System.out.println("Error en la clase PosicionArregloRetorno, get value");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        try {
            Identificador idBuscar = new Identificador(id, linea, col);
            Simbolo arregloEncontrado = idBuscar.get(id, lista);
            if (arregloEncontrado != null) {
                return arregloEncontrado.getTipo();
            }
        } catch (Exception e) {
            System.out.println("Error en la clase Posicion ArregloRetorno, get type");
        }
        return null;
    }

    @Override
    public int getLine() {
        return getLinea();
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
     * @return the indiceArreglo
     */
    public LinkedList<NodoAST> getIndiceArreglo() {
        return indiceArreglo;
    }

    /**
     * @param indiceArreglo the indiceArreglo to set
     */
    public void setIndiceArreglo(LinkedList<NodoAST> indiceArreglo) {
        this.indiceArreglo = indiceArreglo;
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
     * @return the col
     */
    public int getCol() {
        return col;
    }

    /**
     * @param col the col to set
     */
    public void setCol(int col) {
        this.col = col;
    }

}

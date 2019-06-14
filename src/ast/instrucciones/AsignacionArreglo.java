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
public class AsignacionArreglo implements Instruccion {

    private String idArreglo;
    private LinkedList<NodoAST> indicesIdArreglos;
    private Expresion valorParaAsignacion;
    private int linea;
    private int col;

    private LinkedList<Integer> listaIndex = new LinkedList<>();
    private int dimensiones = 0;

    public AsignacionArreglo(String idArreglo, LinkedList<NodoAST> indicesIdArreglos, Expresion valorParaAsignacion, int linea, int col) {
        this.idArreglo = idArreglo;
        this.indicesIdArreglos = indicesIdArreglos;
        this.valorParaAsignacion = valorParaAsignacion;
        this.linea = linea;
        this.col = col;
    }

    public void listarIndices(Entorno lista, ListaErrorPrinter impresion) {
        for (NodoAST listadeExpresione : indicesIdArreglos) {
            Expresion exp = (Expresion) listadeExpresione;
            TipoContenedor tu = (TipoContenedor) exp.getType(lista, impresion);
            if (tu.getTipoPrimitivo() == Simbolo.Tipo.INT) {
                int indice = Integer.parseInt(String.valueOf(exp.getValue(lista, impresion)));
                dimensiones++;
                listaIndex.add(indice);
            } else {
                impresion.errores.add(new ast.Error("Tipo de indice no valido para un arreglo", exp.getLine(), -1, "Semantico"));
                return;
            }
        }
    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            Identificador idBuscado = new Identificador(idArreglo, linea, col);
            Simbolo encontrado = lista.get(idArreglo, lista);
            if (encontrado != null) {
                
                listarIndices(lista, impresion);
                NodoNNario modArbol = (NodoNNario) encontrado.getValor();
                modArbol.setearPorIndice(modArbol, listaIndex, valorParaAsignacion.getValue(lista, impresion), lista, impresion);
                
            } else {
                impresion.errores.add(new ast.Error("El id: " + idArreglo + " No existe para asignar el valor", linea, col, "Semantico"));
            }
        } catch (Exception e) {
            System.out.println("Error en la clase AsignacionArreglo, ejecutar");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the idArreglo
     */
    public String getIdArreglo() {
        return idArreglo;
    }

    /**
     * @param idArreglo the idArreglo to set
     */
    public void setIdArreglo(String idArreglo) {
        this.idArreglo = idArreglo;
    }

    /**
     * @return the indicesIdArreglos
     */
    public LinkedList<NodoAST> getIndicesIdArreglos() {
        return indicesIdArreglos;
    }

    /**
     * @param indicesIdArreglos the indicesIdArreglos to set
     */
    public void setIndicesIdArreglos(LinkedList<NodoAST> indicesIdArreglos) {
        this.indicesIdArreglos = indicesIdArreglos;
    }

    /**
     * @return the valorParaAsignacion
     */
    public Expresion getValorParaAsignacion() {
        return valorParaAsignacion;
    }

    /**
     * @param valorParaAsignacion the valorParaAsignacion to set
     */
    public void setValorParaAsignacion(Expresion valorParaAsignacion) {
        this.valorParaAsignacion = valorParaAsignacion;
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

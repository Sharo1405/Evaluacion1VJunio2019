/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.expresiones.arreglos.NodoNNario;
import ast.expresiones.arreglos.ParaCorchete;
import ast.expresiones.operacion.TipoContenedor;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Newww implements Expresion {

    private TipoContenedor tipoInstancia;
    private Expresion valorCualquierapermitido;
    private Object valorvalorvaloravalor;
    private int linea;
    private int columna;

    //arbol para los arreglos
    public NodoNNario arbolArreglo;
    public LinkedList<Integer> listaTamaniosIndice;
    public int dimensiones;

    public Newww(TipoContenedor tipoInstancia, Expresion valorCualquierapermitido, int linea, int columna) {
        this.tipoInstancia = tipoInstancia;
        this.valorCualquierapermitido = valorCualquierapermitido;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {
            valorvalorvaloravalor = valorCualquierapermitido.getValue(lista, impresion);
            if (valorvalorvaloravalor instanceof ParaCorchete) {
                ParaCorchete pa = (ParaCorchete) valorvalorvaloravalor;
                //arbolArreglo = pa.arbolArreglo;
                listaTamaniosIndice = pa.listaIndex;
                dimensiones = pa.dimensiones;

                arbolArreglo = pa.HacerArbol(listaTamaniosIndice.size() - 1, 1, arbolArreglo, tipoInstancia);
                               
                return arbolArreglo;

            }

            return null;

        } catch (Exception e) {
            System.out.println("Error en la clase Newww, getValue");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        return tipoInstancia;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the tipoInstancia
     */
    public TipoContenedor getTipoInstancia() {
        return tipoInstancia;
    }

    /**
     * @param tipoInstancia the tipoInstancia to set
     */
    public void setTipoInstancia(TipoContenedor tipoInstancia) {
        this.tipoInstancia = tipoInstancia;
    }

    /**
     * @return the valorCualquierapermitido
     */
    public Expresion getValorCualquierapermitido() {
        return valorCualquierapermitido;
    }

    /**
     * @param valorCualquierapermitido the valorCualquierapermitido to set
     */
    public void setValorCualquierapermitido(Expresion valorCualquierapermitido) {
        this.valorCualquierapermitido = valorCualquierapermitido;
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

    /**
     * @return the valorvalorvaloravalor
     */
    public Object getValorvalorvaloravalor() {
        return valorvalorvaloravalor;
    }

    /**
     * @param valorvalorvaloravalor the valorvalorvaloravalor to set
     */
    public void setValorvalorvaloravalor(Object valorvalorvaloravalor) {
        this.valorvalorvaloravalor = valorvalorvaloravalor;
    }

}

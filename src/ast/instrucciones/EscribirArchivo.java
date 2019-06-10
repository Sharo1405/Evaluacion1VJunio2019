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
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author sharolin
 */
public class EscribirArchivo implements Expresion {

    private Expresion ruta;
    private Expresion contenido;
    private int linea;
    private int col;

    public EscribirArchivo(Expresion ruta, Expresion contenido, int linea, int col) {
        this.ruta = ruta;
        this.contenido = contenido;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            TipoContenedor tipo = (TipoContenedor) ruta.getType(lista, impresion);

            if (tipo.getTipoPrimitivo() == Simbolo.Tipo.STRING) {

                fichero = new FileWriter(String.valueOf(ruta.getValue(lista, impresion)));
                pw = new PrintWriter(fichero);

                pw.print(String.valueOf(contenido.getValue(lista, impresion)));

            }

        } catch (Exception e) {
            System.out.println("Error en la clase EscribirArchivo, getValue");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {

        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the ruta
     */
    public Expresion getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(Expresion ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the contenido
     */
    public Expresion getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(Expresion contenido) {
        this.contenido = contenido;
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

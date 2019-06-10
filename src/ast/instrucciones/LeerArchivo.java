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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import jdk.jfr.events.FileReadEvent;

/**
 *
 * @author sharolin
 */
public class LeerArchivo implements Expresion {

    private Expresion ruta;
    private int linea;
    private int col;

    public LeerArchivo(Expresion ruta, int linea, int col) {
        this.ruta = ruta;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {

        FileReader archivo = null;
        
        try {
            TipoContenedor tipo = (TipoContenedor) ruta.getType(lista, impresion);
            String contenido = "";
            String cadena = "";
            if (tipo.getTipoPrimitivo() == Simbolo.Tipo.STRING) {
                archivo = new FileReader(String.valueOf(ruta.getValue(lista, impresion)));
                BufferedReader buf = new BufferedReader(archivo);
                while ((contenido = buf.readLine()) != null) {
                    cadena = cadena + contenido;
                }
                buf.close();
                System.out.println("SOY EL ARCHIVO" + cadena);
                return cadena;
            }

        } catch (Exception e) {
            System.out.println("Error en la clase LeerArchivo");
            try {
                if (null != archivo) {
                    archivo.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        try {

            Object o = ruta.getValue(lista, impresion);
            if (o != null) {
                return Simbolo.Tipo.STRING;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error en la case LeerArchivo");
        }
        return null;
    }

    @Override
    public int getLine() {
        return getLinea();
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

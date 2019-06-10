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
import ast.expresiones.primitivos.Cadena;

/**
 *
 * @author sharolin
 */
public class Graficar implements Instruccion {

    private Expresion ruta;
    private Expresion contenidoDot;
    private int linea;
    private int col;

    public Graficar(Expresion ruta, Expresion contenidoDot, int linea, int col) {
        this.ruta = ruta;
        this.contenidoDot = contenidoDot;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            TipoContenedor tipo = (TipoContenedor) ruta.getType(lista, impresion);

            if (tipo.getTipoPrimitivo() == Simbolo.Tipo.STRING) {
                String r = String.valueOf(ruta.getValue(lista, impresion));
                //Cadena rutadot = new Cadena("C:\\Users\\sharolin\\Desktop\\dotGraficas\\grafica.dot", tipo, linea, col);
                //Cadena prueba = new Cadena("graph L {a0[label=\"Bloque\"];a1[label=\"Declaracion\"];a2[label=\"a\"];a1 -- a2;a3[label=\"CHAR\"];a1 -- a3;a4[label=\"3.3\"];a1 -- a4;a0 -- a1;a5[label=\"print\"];a6[label=\"a\"];a5 -- a6;a0 -- a5;a7[label=\"Declaracion\"];a8[label=\"a\"];a7 -- a8;a9[label=\"CHAR\"];a7 -- a9;a10[label=\"v\"];a7 -- a10;a0 -- a7;a11[label=\"print\"];a12[label=\"a\"];a11 -- a12;a0 -- a11;}", tipo, linea, col);
                //EscribirArchivo g = new EscribirArchivo(rutadot, prueba, linea, col);
                EscribirArchivo g = new EscribirArchivo(ruta, contenidoDot, linea, col);
                g.getValue(lista, impresion);
                if (r.contains(".png")) {
                    //C:\Users\sharolin\Desktop\dotGraficas
                    //String paraDot = "dot -Tpng C:\\Users\\sharolin\\Desktop\\dotGraficas\\grafica.dot -o \"" + "C:\\Users\\sharolin\\Desktop\\dotGraficas\\grafica.png"+ "\"";
                    String paraDot = "dot -Tpng C:\\Users\\sharolin\\Desktop\\dotGraficas\\grafica.dot -o \"" + r + "\"";
                    System.out.println(paraDot);
                    Runtime.getRuntime().exec(paraDot);
                }
            } else {
                impresion.errores.add(new ast.Error("Ruta no valida para graficar el Dot", linea, col, "Semantico"));
            }

        } catch (Exception e) {
            System.out.println("Error en la clase Graficar, ejecutar");
        }
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
     * @return the contenidoDot
     */
    public Expresion getContenidoDot() {
        return contenidoDot;
    }

    /**
     * @param contenidoDot the contenidoDot to set
     */
    public void setContenidoDot(Expresion contenidoDot) {
        this.contenidoDot = contenidoDot;
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

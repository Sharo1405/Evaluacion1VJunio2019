/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.operacion.TipoContenedor;
import ast.expresiones.primitivos.Booleano;
import ast.expresiones.primitivos.Cadena;
import ast.expresiones.primitivos.Caracter;
import ast.expresiones.primitivos.Decimal;
import ast.expresiones.primitivos.Entero;
import java.util.Map;

/**
 *
 * @author sharolin
 */
public class Identificador extends Entorno implements Expresion {

    private String id;
    private TipoContenedor tipo;
    private int linea;
    private int columna;

    public Identificador(String id, TipoContenedor tipo, int linea, int columna) {
        this.id = id;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {
            Simbolo encontrado = get(id, lista);
            if (encontrado != null) {

                TipoContenedor ti = (TipoContenedor) encontrado.getTipo();
                TipoContenedor aux = new TipoContenedor();

                if (aux.isString(ti)) {
                    return String.valueOf(encontrado.getValor());

                } else if (aux.isEntero(ti)) {
                    return Integer.parseInt(String.valueOf(encontrado.getValor()));

                } else if (aux.isDecimal(ti)) {
                    return Double.parseDouble(String.valueOf(encontrado.getValor()));

                } else if (aux.isChar(ti)) {
                    //si viene negativo el char osea un numero negativo truwna
                    if (Integer.parseInt(String.valueOf(encontrado.getValor())) > 0) {
                        return encontrado.getValor();
                        
                    } else {
                        return (Character) encontrado.getValor();
                    }

                } else if (aux.isBool(ti)) {
                    return (Boolean) encontrado.getValor();

                } else {
                    return encontrado.getValor();
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la clase Identificador getValue");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        try {
            Simbolo encontrado = get(id, lista);
            if (encontrado != null) {
                return encontrado.getTipo();
            }
        } catch (Exception e) {
            System.out.println("Error en la clase Identificador getType");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
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
     * @return the tipo
     */
    public TipoContenedor getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoContenedor tipo) {
        this.tipo = tipo;
    }
}

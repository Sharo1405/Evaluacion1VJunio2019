/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.operacion.Aritmetica;
import ast.expresiones.operacion.Operacion;
import ast.expresiones.operacion.TipoContenedor;
import ast.expresiones.primitivos.Caracter;
import ast.expresiones.primitivos.Decimal;
import ast.expresiones.primitivos.Entero;
import ast.instrucciones.Asignacion;
import ast.instrucciones.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class OPPostFijo implements Expresion {

    //el pre fijo ++a; 
    //si es 5 y el ++ retorna 6 y a es 6
    
    //el post fijo a++;
    //si es 5 y el ++ retorna 5 y a es 6 despues
    //solo queda id++ solo con ID para el post y pre
    private Identificador id;
    private Operacion.Operador op;
    private int line;
    private int col;

    public OPPostFijo(Identificador id, Operacion.Operador op, int line, int col) {
        this.id = id;
        this.op = op;
        this.line = line;
        this.col = col;
    }

    
    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {
            TipoContenedor tipo = (TipoContenedor) id.getType(lista, impresion);
            Entero en2 = new Entero(1, tipo, line, col);
            switch (tipo.getTipoPrimitivo()) {
                case INT:
                    int valor = (int) id.getValue(lista, impresion);
                    Entero en = new Entero(valor, tipo, line, col);
                    Aritmetica ar = new Aritmetica(en, en2, op, line, col, tipo);
                    Asignacion asig = new Asignacion(id.getId(), ar, line, col);
                    asig.ejecutar(lista, impresion);
                    return valor;

                case DOUBLE:
                    Double valor2 = (Double) id.getValue(lista, impresion);
                    Decimal en3 = new Decimal(valor2, tipo, line, col);
                    Aritmetica ar2 = new Aritmetica(en3, en2, op, line, col, tipo);
                    Asignacion asig2 = new Asignacion(id.getId(), ar2, line, col);
                    asig2.ejecutar(lista, impresion);
                    return valor2;

                case CHAR:
                    char valor3 = (Character) id.getValue(lista, impresion);
                    char valor5 = valor3++;
                    System.out.println("soy el char ++ 5 " + String.valueOf(valor5) + " soy el ++ 3 " + String.valueOf(valor3));
                    Caracter en4 = new Caracter(valor3, tipo, line, col);
                    Asignacion asig3 = new Asignacion(id.getId(), en4, line, col);
                    asig3.ejecutar(lista, impresion);
                    return (int) valor5 * 1;

                default:
                    System.out.println("Error de tipo para oppostfijo");
                    impresion.errores.add(new ast.Error("Error de tipo para Operar postFijo " + op , line, col, "Semantico"));
            }

        } catch (Exception e) {
            System.out.println("Error en clase OPPostFijo getValue");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        try {
            return id.getType(lista, impresion);
        } catch (Exception e) {
        }
        return null;
    }
    

    /*@Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        try {
            return id.getType(lista, impresion);
        } catch (Exception e) {
            System.out.println("Error en clase OPPostFijo getType");
        }
        return null;
    }*/

    @Override
    public int getLine() {
        return line;
    }

    /**
     * @return the id
     */
    public Identificador getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Identificador id) {
        this.id = id;
    }

    /**
     * @return the op
     */
    public Operacion.Operador getOp() {
        return op;
    }

    /**
     * @param op the op to set
     */
    public void setOp(Operacion.Operador op) {
        this.op = op;
    }

    /**
     * @param line the line to set
     */
    public void setLine(int line) {
        this.line = line;
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

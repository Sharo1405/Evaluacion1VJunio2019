/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.declaraciones;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import ast.expresiones.Identificador;
import ast.expresiones.operacion.TipoContenedor;
import ast.instrucciones.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Declaraciones implements Instruccion {

    private LinkedList<String> visibilidad; //lista de strings
    private TipoContenedor tipoo; //tipo normal

    private LinkedList<Object> lista; //lista de objeto que pueden ser: 
    //1.lista de nodosAST id, id[][]  
    //2.VariableDeclarator tiene una lista de nodosAST con id, id[][] y Exp
    private int linea;
    private int col;

    public Declaraciones(LinkedList<String> visibilidad, TipoContenedor tipoo, LinkedList<Object> lista, int linea, int col) {
        this.visibilidad = visibilidad;
        this.tipoo = tipoo;
        this.lista = lista;
        this.linea = linea;
        this.col = col;
    }

    public Declaraciones(TipoContenedor tipoo, LinkedList<Object> lista, int linea, int col) {
        this.visibilidad.add("publico");
        this.tipoo = tipoo;
        this.lista = lista;
        this.linea = linea;
        this.col = col;
    }

    protected Boolean revisarVisibilidad(ListaErrorPrinter impresion) {

        switch (visibilidad.size()) {
            case 3:
                String primero = visibilidad.get(0);
                String segundo = visibilidad.get(1);
                String tercero = visibilidad.get(2);

                if (!(primero.equals("public") || primero.equals("protected") || primero.equals("private"))) {
                    return false;
                }

                if (!segundo.equals("static")) {
                    return false;
                }

                if (!tercero.equals("final")) {
                    return false;
                }
                return true;

            case 2:
                String primero1 = visibilidad.get(0);
                String segundo1 = visibilidad.get(1);

                if (!(primero1.equals("public") || primero1.equals("protected") || primero1.equals("private") || primero1.equals("static"))
                        && !primero1.equals(segundo1)) {
                    return false;
                }

                if (!segundo1.equals("static")) {
                    return false;
                }
                return true;

            case 1:
                String primero2 = visibilidad.get(0);

                if (!(primero2.equals("public") || primero2.equals("protected") || primero2.equals("private"))) {
                    return false;
                }
                return true;

            default:
                impresion.errores.add(new ast.Error("Error visibilidad no valida para variables/objetos", linea, col, "Semantico"));
                break;
        }

        return false;
    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            Boolean vector = false;
            String nombreid = "";
            int dimensiones = 0;

            if (revisarVisibilidad(impresion)) {
                Object tipoVariable = tipoo.ejecutar(lista, impresion);

                for (Object object : this.lista) {

                    if (object instanceof Instruccion) {
                        //esta es la unica que lleva expresion
                        VariableDeclarator var = (VariableDeclarator) object;
                        if (var.getValor() != null) {
                            for (NodoAST variableDeclarada : var.getVariablesDeclaradas()) {
                                if (variableDeclarada instanceof Corchetee) {
                                    dimensiones++;
                                } else if (variableDeclarada instanceof Identificador) {
                                    nombreid = ((Identificador) variableDeclarada).getId();
                                }
                            }

                            Object valorE = var.getValor().getValue(lista, impresion);
                            Object tipoE = var.getValor().getType(lista, impresion);
                            Object tipotipotipo = ((TipoContenedor) tipoE).ejecutar(lista, impresion);
                            if (tipoVariable == tipotipotipo) {
                                
                                if (dimensiones > 0) {
                                    lista.setSimbolo(nombreid, new Simbolo(nombreid, valorE, visibilidad, linea, col, Simbolo.Rol.VECTOR, tipoo, dimensiones));
                                    nombreid = "";
                                    dimensiones = 0;
                                } else if (dimensiones <= 0) {
                                    lista.setSimbolo(nombreid, new Simbolo(nombreid, valorE, visibilidad, linea, col, Simbolo.Rol.VARIABLE, tipoo, dimensiones));
                                    nombreid = "";
                                    dimensiones = 0;
                                }

                            } else {
                                impresion.errores.add(new ast.Error("Tipos diferente para la variable no se puede guardar", linea, col, "Semantico"));
                            }
                        }

                    } else if (object instanceof Expresion) {
                        LinkedList<NodoAST> exp1 = (LinkedList<NodoAST>) object;
                        for (NodoAST exp : exp1) {
                            if (exp instanceof Corchetee) {

                                dimensiones++;
                            } else if (exp instanceof Identificador) {
                                nombreid = ((Identificador) exp).getId();
                            }
                        }

                        //si se sale es una variable de n dimensiones
                        if (dimensiones > 0) {
                            lista.setSimbolo(nombreid, new Simbolo(nombreid, null, visibilidad, linea, col, Simbolo.Rol.VECTOR, tipoo, dimensiones));
                            nombreid = "";
                            dimensiones = 0;
                        } else if (dimensiones <= 0) {
                            lista.setSimbolo(nombreid, new Simbolo(nombreid, null, visibilidad, linea, col, Simbolo.Rol.VARIABLE, tipoo, dimensiones));
                            nombreid = "";
                            dimensiones = 0;
                        }

                    }

                }

            } else {
                impresion.errores.add(new ast.Error("Visibilidad no valida para variable/objetos", linea, col, "Semantico"));
            }

        } catch (Exception e) {
            System.out.println("Error en la clase Declaraciones, ejecutar instrucciones.declaraciones");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the visibilidad
     */
    public LinkedList<String> getVisibilidad() {
        return visibilidad;
    }

    /**
     * @param visibilidad the visibilidad to set
     */
    public void setVisibilidad(LinkedList<String> visibilidad) {
        this.visibilidad = visibilidad;
    }

    /**
     * @return the tipoo
     */
    public TipoContenedor getTipoo() {
        return tipoo;
    }

    /**
     * @param tipoo the tipoo to set
     */
    public void setTipoo(TipoContenedor tipoo) {
        this.tipoo = tipoo;
    }

    /**
     * @return the lista
     */
    public LinkedList<Object> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(LinkedList<Object> lista) {
        this.lista = lista;
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

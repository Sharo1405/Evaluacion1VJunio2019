/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.clase;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import ast.expresiones.Identificador;
import ast.expresiones.operacion.TipoContenedor;
import ast.instrucciones.Instruccion;
import ast.instrucciones.declaraciones.Corchetee;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class MetodoFuncion extends TodoMetodos implements Expresion {

    protected String firma_metodo_guardado = "";
    protected int tamanio = 0;
    private Boolean publico = false;
    private Boolean privado = false;
    private Boolean protegido = false;

    private Boolean estatico = false;
    private Boolean finall = false;

    private int cantCorchetes = 0;

    public MetodoFuncion(LinkedList<String> visibilidad, TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo, LinkedList<Parametros> lisParametros, Instruccion sentencias, int linea, int col) {
        super(visibilidad, tipo, listaCorchetes, idMetodo, lisParametros, sentencias, linea, col);
    }

    public MetodoFuncion(LinkedList<String> visibilidad, TipoContenedor tipo, String idMetodo, LinkedList<Parametros> lisParametros, Instruccion sentencias, int linea, int col) {
        super(visibilidad, tipo, idMetodo, lisParametros, sentencias, linea, col);
    }

    public MetodoFuncion(TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo, LinkedList<Parametros> lisParametros, Instruccion sentencias, int linea, int col) {
        super(tipo, listaCorchetes, idMetodo, lisParametros, sentencias, linea, col);
    }

    public MetodoFuncion(TipoContenedor tipo, String idMetodo, LinkedList<Parametros> lisParametros, Instruccion sentencias, int linea, int col) {
        super(tipo, idMetodo, lisParametros, sentencias, linea, col);
    }
    
    public MetodoFuncion(LinkedList<String> visibilidad, TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo, LinkedList<Parametros> lisParametros, Instruccion sentencias, int linea, int col, Boolean over) {
        super(visibilidad, tipo, listaCorchetes, idMetodo, lisParametros, sentencias, linea, col, over);
    }

    public MetodoFuncion(LinkedList<String> visibilidad, TipoContenedor tipo, String idMetodo, LinkedList<Parametros> lisParametros, Instruccion sentencias, int linea, int col, Boolean over) {
        super(visibilidad, tipo, idMetodo, lisParametros, sentencias, linea, col, over);
    }

    public MetodoFuncion(TipoContenedor tipo, LinkedList<Integer> listaCorchetes, String idMetodo, LinkedList<Parametros> lisParametros, Instruccion sentencias, int linea, int col, Boolean over) {
        super(tipo, listaCorchetes, idMetodo, lisParametros, sentencias, linea, col, over);
    }

    public MetodoFuncion(TipoContenedor tipo, String idMetodo, LinkedList<Parametros> lisParametros, Instruccion sentencias, int linea, int col, Boolean over) {
        super(tipo, idMetodo, lisParametros, sentencias, linea, col, over);
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {
            for (Integer listaCorchete : getListaCorchetes()) {
                cantCorchetes++;
            }

            firma_metodo_guardado = getIdMetodo();
            for (Parametros pa : getListaParametros()) {
                tamanio = 0;
                boolean entro = false;
                LinkedList<NodoAST> variableArreglo = pa.getVariable();
                for (Object object : variableArreglo) {
                    Expresion obj = (Expresion) object;
                    if (obj instanceof Identificador) {

                    } else if (obj instanceof Corchetee) {
                        tamanio++;
                        entro = true;
                    }
                }

                TipoContenedor tipo = (TipoContenedor) pa.getTipoParametro();

                Object t = tipo.ejecutar(lista, impresion);

                if (t instanceof String) {
                    firma_metodo_guardado += "_" + String.valueOf(t);
                } else {
                    Simbolo.Tipo h = (Simbolo.Tipo) t;
                    switch (h) {
                        case INT:
                            firma_metodo_guardado += "_int";
                            break;

                        case STRING:
                            firma_metodo_guardado += "_string";
                            break;

                        case DOUBLE:
                            firma_metodo_guardado += "_double";
                            break;

                        case CHAR:
                            firma_metodo_guardado += "_char";
                            break;

                        case BOOLEAN:
                            firma_metodo_guardado += "_boolean";
                            break;
                    }
                    if (entro == true) {
                        firma_metodo_guardado += String.valueOf(tamanio);
                    }
                }
            }
            //ya tengo la firma

            for (String v : getVisivilidad()) {
                if (v.equals("abstract")) {
                    impresion.errores.add(new ast.Error("El metodo no puede ser abstract: " + getIdMetodo(), getLine(), getCol(), "Semantico"));
                    return null;
                }
            }
            Boolean paso = revisarVisibilidad(impresion);
            if (paso == false) {
                impresion.errores.add(new ast.Error("El metodo no tiene una visibilidad valida: " + getIdMetodo(), getLine(), getCol(), "Semantico"));
                return null;
            }

            //aqui ya tengo la visibilidad la firma falta solo meterlo en la tabla de simbolos
            String tipoRetorno = "";
            if (cantCorchetes >0) {
                tipoRetorno = "arreglo";
            }else{
                tipoRetorno = "variable";
            }
            
            Simbolo hola = new Simbolo(firma_metodo_guardado, this, publico, privado, protegido, estatico, finall, getLinea(), getCol(), Simbolo.Rol.METODO, getTipo(), cantCorchetes, new LinkedList<>(), tipoRetorno);
            lista.setSimbolo(firma_metodo_guardado, hola);
            cantCorchetes = 0;
        } catch (Exception e) {
            System.out.println("Error en la clase MetodoFuncion, getValue");
        }
        return null;
    }

    protected Boolean revisarVisibilidad(ListaErrorPrinter impresion) {
        int cont_publico = 0;
        int cont_privado = 0;
        int cont_protegido = 0;

        int cont_estatico = 0;
        int cont_finall = 0;
        for (String string : getVisivilidad()) {
            switch (string) {
                case "public":
                    publico = true;
                    cont_publico++;
                    break;

                case "protected":
                    protegido = true;
                    cont_protegido++;
                    break;

                case "private":
                    privado = true;
                    cont_privado++;
                    break;

                case "static":
                    estatico = true;
                    cont_estatico++;
                    break;

                case "final":
                    finall = true;
                    cont_finall++;
                    break;
            }
        }

        if (cont_estatico <= 1 && cont_finall <= 1 && cont_privado <= 1 && cont_protegido <= 1 && cont_publico <= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        return getTipo();
    }

    @Override
    public int getLine() {
        return this.getLinea();
    }
}

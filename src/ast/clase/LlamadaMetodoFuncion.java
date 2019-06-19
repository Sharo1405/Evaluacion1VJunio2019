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
import ast.expresiones.primitivos.Booleano;
import ast.expresiones.primitivos.Cadena;
import ast.expresiones.primitivos.Caracter;
import ast.expresiones.primitivos.Decimal;
import ast.expresiones.primitivos.Entero;
import ast.instrucciones.Instruccion;
import ast.instrucciones.ciclos.RetCont.Returnn;
import ast.instrucciones.declaraciones.Corchetee;
import ast.instrucciones.declaraciones.Declaraciones;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class LlamadaMetodoFuncion implements Expresion {

    private String id;
    private LinkedList<Expresion> parametros = new LinkedList<>();
    private int linea;
    private int col;

    //protected String firma_metodo = "";
    protected int tamanio = 0;
    protected String nombreId = "";

    public LlamadaMetodoFuncion(String id, LinkedList<Expresion> parametros, int linea, int col) {
        this.id = id;
        this.parametros = parametros;
        this.linea = linea;
        this.col = col;
    }

    public LlamadaMetodoFuncion(String id, int linea, int col) {
        this.id = id;
        this.linea = linea;
        this.col = col;
    }

    private String CrearFirma(Entorno lista, ListaErrorPrinter impresion, String firma) {
        try {

            for (Expresion parametro : parametros) {

                Object tipo = parametro.getType(lista, impresion);
                if (tipo instanceof TipoContenedor) {
                    TipoContenedor ti = (TipoContenedor) tipo;
                    Object t = ti.ejecutar(lista, impresion);

                    if (t instanceof String) {
                        firma += "_" + String.valueOf(t);
                    } else {
                        Simbolo.Tipo h = (Simbolo.Tipo) t;
                        switch (h) {
                            case INT:
                                firma += "_int";
                                break;

                            case STRING:
                                firma += "_string";
                                break;

                            case DOUBLE:
                                firma += "_double";
                                break;

                            case CHAR:
                                firma += "_char";
                                break;

                            case BOOLEAN:
                                firma += "_boolean";
                                break;
                        }
                    }
                } else {
                    firma += "_" + String.valueOf(tipo);
                }
            }
            return firma;

        } catch (Exception e) {
            System.out.println("Error en la Clase LlamadaMetodo, crear Firma");
        }
        return null;
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        try {

            String firma_metodo = "";
            firma_metodo += id + CrearFirma(lista, impresion, firma_metodo);

            Simbolo metodoDevuelto = lista.get(firma_metodo, lista);
            if (metodoDevuelto != null) {

                //firma_metodfirma_metodo += metodoDevuelto.getId();
                MetodoFuncion metodo = (MetodoFuncion) metodoDevuelto.getValor();
                LinkedList<Parametros> listapara = metodo.getListaParametros();
                //hasta aqui llevo la firma del metodo ya hecha, el metodo de la llamada
                //ahora solo falta compararlo y luego ejecutarlo si cumple con las validaciones

                /*Object ret = ejecutaMetodo(lista, impresion, listapara, parametros, metodo.getListaContenidoSentencias());
                if (ret != null) {
                    return ret;
                }*/
                Entorno actual = new Entorno(lista);
                /*for (Expresion parametro : parametros) {
                    if (parametro instanceof LlamadaMetodoFuncion) {
                        declararVarsMetodo(actual.getPadreANTERIOR(), impresion, listapara, parametros);
                        Object ret = metodo.getListaContenidoSentencias().ejecutar(actual.getPadreANTERIOR(), impresion);
                        if (ret != null) {
                            return ret;
                        }
                    } else {
                        declararVarsMetodo(actual, impresion, listapara, parametros);
                        Object ret = metodo.getListaContenidoSentencias().ejecutar(actual, impresion);
                        if (ret != null) {
                            return ret;
                        }
                    }
                }*/
                declararVarsMetodo(actual, impresion, listapara, parametros);
                Object ret = metodo.getListaContenidoSentencias().ejecutar(actual, impresion);
                if (ret != null) {
                    return ret;
                }

            } else {
                impresion.errores.add(new ast.Error("El metodo no fue encontrado: " + id, linea, col, "Semantico"));
            }
        } catch (Exception e) {
            System.out.println("Error en la clase LlamadaFuncion, ejecutar");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {
        //System.out.println("Soy el retorno de la llamada a metodos y fuciones");
        try {

            String firma_metodo2 = "";
            firma_metodo2 = id + CrearFirma(lista, impresion, firma_metodo2);

            Simbolo metodoDevuelto = lista.get(firma_metodo2, lista);
            if (metodoDevuelto != null) {
                return metodoDevuelto.getTipo();
            }
        } catch (Exception e) {
        }
        return null;
    }

    private void declararVarsMetodo(Entorno lista, ListaErrorPrinter impresion,
            LinkedList<Parametros> paraMetodo, LinkedList<Expresion> paraLlamada) {
        //Declaraciones declarar = new Declaraciones(var.getTipoParametro(), lista, linea, col);

        int cantidad = paraMetodo.size();
        for (int i = 0; i < cantidad; i++) {

            Parametros var = paraMetodo.get(i);
            Expresion valor = paraLlamada.get(i);
            Boolean isFinal = var.getFinall();

            Boolean entro = false;
            int linea = 0;
            int col = 0;
            LinkedList<NodoAST> variableArreglo = var.getVariable();
            for (Object object : variableArreglo) {
                Expresion obj = (Expresion) object;
                if (obj instanceof Identificador) {
                    nombreId = ((Identificador) obj).getId();
                    linea = ((Identificador) obj).getLinea();
                    col = ((Identificador) obj).getColumna();
                } else if (obj instanceof Corchetee) {
                    tamanio++;
                    entro = true;
                }
            }

            //lista.setSimbolo(nombreId, new Simbolo(nombreId, valor.getValue(lista, impresion),false, false, false, false, linea, col, );
            if (tamanio <= 0) {
                if (valor instanceof LlamadaMetodoFuncion) {
                    Object val = valor.getValue(lista.getPadreANTERIOR(), impresion);
                    lista.setSimbolo(nombreId, new Simbolo(nombreId, val, false, false, false, false, entro, linea, col, Simbolo.Rol.VARIABLE, var.getTipoParametro(), tamanio, new LinkedList<>()));
                } else {
                    Object val = valor.getValue(lista, impresion);
                    lista.setSimbolo(nombreId, new Simbolo(nombreId, val, false, false, false, false, entro, linea, col, Simbolo.Rol.VARIABLE, var.getTipoParametro(), tamanio, new LinkedList<>()));
                }
            } else {
                lista.setSimbolo(nombreId, new Simbolo(nombreId, valor.getValue(lista, impresion), false, false, false, false, entro, linea, col, Simbolo.Rol.VECTOR, var.getTipoParametro(), tamanio, new LinkedList<>()));
            }
        }

    }

    private Object ejecutaMetodo(Entorno lista, ListaErrorPrinter impresion,
            LinkedList<Parametros> paraMetodo, LinkedList<Expresion> paraLlamada, Instruccion sentencias) {
        try {
            //primero declarar las variables de los parametros
            //ejecutar las sentencias            
            Entorno actual = new Entorno(lista);
            declararVarsMetodo(actual, impresion, paraMetodo, paraLlamada);
            Object ret = sentencias.ejecutar(actual, impresion);
            if (ret != null) {
                return ret;
            }

        } catch (Exception e) {
            System.out.println("Error en la clase llamadaMetodoFuncion, ejecutarmetodo");
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
     * @return the parametros
     */
    public LinkedList<Expresion> getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(LinkedList<Expresion> parametros) {
        this.parametros = parametros;
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

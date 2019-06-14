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
import ast.expresiones.Newww;
import ast.expresiones.arreglos.NodoNNario;
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

    private Boolean publico = false;
    private Boolean privado = false;
    private Boolean protegido = false;

    private Boolean estatico = false;
    private Boolean finall = false;

    public Declaraciones(LinkedList<String> visibilidad, TipoContenedor tipoo, LinkedList<Object> lista, int linea, int col) {
        this.visibilidad = visibilidad;
        this.tipoo = tipoo;
        this.lista = lista;
        this.linea = linea;
        this.col = col;
    }

    public Declaraciones(TipoContenedor tipoo, LinkedList<Object> lista, int linea, int col) {
        this.visibilidad = new LinkedList<>();
        this.visibilidad.add("public");
        this.tipoo = tipoo;
        this.lista = lista;
        this.linea = linea;
        this.col = col;
    }

    protected Boolean revisarVisibilidad(ListaErrorPrinter impresion) {
        int cont_publico = 0;
        int cont_privado = 0;
        int cont_protegido = 0;

        int cont_estatico = 0;
        int cont_finall = 0;
        for (String string : visibilidad) {
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

                            if (dimensiones == 0) { //variables normales de calquier tipo PRIMITIVAS OBJETOS
                                Object valorE = var.getValor().getValue(lista, impresion);
                                Object tipoE = var.getValor().getType(lista, impresion);
                                Object tipotipotipo = ((TipoContenedor) tipoE).ejecutar(lista, impresion);

                                if (tipotipotipo == Simbolo.Tipo.NULO) {

                                    if (tipoVariable == Simbolo.Tipo.CHAR || tipoVariable == Simbolo.Tipo.INT || tipoVariable == Simbolo.Tipo.DOUBLE) {
                                        impresion.errores.add(new ast.Error("No se puede declarar, El tipo de la variable no admite NULL", linea, col, "Semantico"));
                                        return null;
                                    } else {
                                        if (dimensiones <= 0) {
                                            lista.setSimbolo(nombreid, new Simbolo(nombreid, null, publico, privado, protegido, estatico,
                                                    finall, linea, col, Simbolo.Rol.VARIABLE, tipoo, dimensiones));
                                            nombreid = "";
                                            dimensiones = 0;
                                        }
                                    }

                                }
                                if (tipoVariable == tipotipotipo) {

                                    if (dimensiones <= 0) {
                                        //lista.setSimbolo(nombreid, new Simbolo(nombreid, valorE, visibilidad, linea, col, Simbolo.Rol.VARIABLE, tipoo, dimensiones));
                                        lista.setSimbolo(nombreid, new Simbolo(nombreid, valorE, publico, privado, protegido, estatico, finall,
                                                linea, col, Simbolo.Rol.VARIABLE, tipoo, dimensiones));
                                        nombreid = "";
                                        dimensiones = 0;
                                    }

                                } else {
                                    impresion.errores.add(new ast.Error("Tipos diferente para la variable no se puede guardar", linea, col, "Semantico"));
                                }
                            } else {
                                Object valorE = var.getValor().getValue(lista, impresion);
                                Object tipot = var.getValor().getType(lista, impresion);
                                int bandera = 0;                                
                                TipoContenedor tipoRegresado = (TipoContenedor) tipot;
                                
                                Object t = tipoRegresado.ejecutar(lista, impresion);
                                
                                if (tipoVariable == t) {
                                    
                                    if(valorE instanceof NodoNNario){
                                        NodoNNario n = (NodoNNario) valorE;
                                        if(dimensiones == n.dimensiones){
                                            lista.setSimbolo(nombreid, new Simbolo(nombreid, n, publico, privado, protegido, estatico, finall, linea, col, Simbolo.Rol.VECTOR, tipoo, dimensiones));
                                        }else{
                                            impresion.errores.add(new ast.Error("Las dimensiones de la variable con la instancia NO COINCIDEN", linea, col, "Semantico"));
                                        }
                                    }
                                    
                                }

                            }
                        }

                    } else if (object instanceof LinkedList) {
                        LinkedList<NodoAST> exp1 = (LinkedList<NodoAST>) object;
                        for (NodoAST exp : exp1) {
                            if (exp instanceof Corchetee) {

                                dimensiones++;
                            } else if (exp instanceof Identificador) {
                                Identificador ididid = (Identificador) exp;
                                nombreid = ididid.getId();
                            }
                        }

                        Object valorPre = valorPredeterminado(tipoo.ejecutar(lista, impresion));
                        if (dimensiones > 0) { //vector
                            lista.setSimbolo(nombreid, new Simbolo(nombreid, null, publico, privado, protegido, estatico, finall, linea, col, Simbolo.Rol.VECTOR, tipoo, dimensiones));
                            nombreid = "";
                            dimensiones = 0;
                        } else if (dimensiones <= 0) {
                            if (!tipoo.getTipoObjeto().equals("")) {//OBJETOS
                                lista.setSimbolo(nombreid, new Simbolo(nombreid, null, publico, privado, protegido, estatico, finall, linea, col, Simbolo.Rol.VARIABLE, tipoo, 0));
                                nombreid = "";
                                dimensiones = 0;
                            } else {//TIPOS PRIMITIVOS
                                lista.setSimbolo(nombreid, new Simbolo(nombreid, valorPre, publico, privado, protegido, estatico, finall, linea, col, Simbolo.Rol.VARIABLE, tipoo, 0));
                                nombreid = "";
                                dimensiones = 0;
                            }
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

    public Object valorPredeterminado(Object tipo) {

        if (tipo instanceof Simbolo.Tipo) {

            Simbolo.Tipo p = (Simbolo.Tipo) tipo;

            switch (p) {
                case INT:
                    return 0;

                case STRING:
                    return null;

                case DOUBLE:
                    return 0.0;

                case CHAR:
                    return '\0';

                case BOOLEAN:
                    return false;
            }

        } else if (tipo instanceof String) {
            return null;
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

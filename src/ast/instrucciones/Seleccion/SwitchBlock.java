/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.Seleccion;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import ast.expresiones.operacion.Operacion;
import ast.expresiones.operacion.Relacional;
import ast.instrucciones.Bloque;
import ast.instrucciones.Instruccion;
import ast.instrucciones.ciclos.RetCont.Breakk;
import ast.instrucciones.ciclos.RetCont.Continuee;
import ast.instrucciones.ciclos.RetCont.Returnn;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class SwitchBlock implements Instruccion {

    public Expresion condicion;
    public int linea;
    public int col;
    public LinkedList<NodoAST> listaGrupos;
    public LinkedList<NodoAST> listaCaseDefault;

    public SwitchBlock(Expresion condicion, int linea, int col, LinkedList<NodoAST> listaGrupos, LinkedList<NodoAST> listaCaseDefault) {
        this.condicion = condicion;
        this.linea = linea;
        this.col = col;
        this.listaGrupos = listaGrupos;
        this.listaCaseDefault = listaCaseDefault;
    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {

            Object valor = condicion.getValue(lista, impresion);

            if (valor == null) {
                impresion.errores.add(new ast.Error("Condicion del Swicht no es valida para evaluar", linea, col, "Semantico"));
                return null;
            }

            for (NodoAST listaGrupo : listaGrupos) {
                SwitchBlockStatement_Group casee = (SwitchBlockStatement_Group) listaGrupo;
                NodoAST caseeDefault = casee.getListaCasee().getLast();
                Bloque sentencias = (Bloque) casee.getListaSentencias();
                if (caseeDefault instanceof Instruccion) {
                    Instruccion instru = (Instruccion) caseeDefault;
                    if (instru instanceof Defaultt) {

                        Object retorno = sentencias.ejecutar(lista, impresion);

                        if (retorno instanceof Breakk) {
                            return null;
                        } else if (retorno instanceof Returnn) {
                            //AQUI VA EL RETURN 
                        }

                    }
                } else if (caseeDefault instanceof Expresion) {
                    Expresion instru = (Expresion) caseeDefault;
                    if (instru instanceof Casee) {
                        Relacional relacion = new Relacional(condicion, ((Casee) instru).getValorCase(), Operacion.Operador.IGUAL, linea, col, null);
                        if ((Boolean) relacion.getValue(lista, impresion)) {
                            Object retorno = sentencias.ejecutar(lista, impresion);

                            if (retorno instanceof Breakk) {
                                return null;
                            } else if (retorno instanceof Returnn) {
                                //AQUI VA EL RETURN 
                            }
                        }

                    }
                }

            }
            
            //la lista case default no se toma en cuenta porque siempre vienen vacios entonces no importa que vengan.

        } catch (Exception e) {
            System.out.println("Error en la clase SwichtBlock");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

}

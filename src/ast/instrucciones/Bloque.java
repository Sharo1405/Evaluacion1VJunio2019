/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.expresiones.Expresion;
import ast.instrucciones.ciclos.RetCont.Breakk;
import ast.instrucciones.ciclos.RetCont.Continuee;
import ast.instrucciones.ciclos.RetCont.Returnn;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Bloque implements Instruccion {

    public LinkedList<NodoAST> listaIns;

    public Bloque(LinkedList<NodoAST> lista) {

        this.listaIns = lista;

    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {

            Entorno tablaActual = new Entorno(lista);
            for (NodoAST nodo : listaIns) {

                if (nodo instanceof Instruccion) {
                    Object retorno = ((Instruccion) nodo).ejecutar(lista, impresion);
                    if (retorno instanceof Breakk) {
                        return retorno;
                    } else if (retorno instanceof Continuee){                        
                        return true;
                    } else if (retorno instanceof Returnn) {
                        //AQUI VA EL RETURN 
                    }else if (retorno instanceof Boolean){
                        if(retorno.equals(true)){
                            return true;
                        }
                    }
                        
                } else if (nodo instanceof Expresion) {
                    //estos son los pre y pos fijos
                    Object retorno = ((Expresion) nodo).getValue(lista, impresion);

                    //AQUI EL RETORNO
                }

            }

        } catch (Exception e) {
            System.out.println("Error en la clase Bloque, ejecutar");
        }
        return null;
    }

    @Override
    public int getLine() {
        return 0;
    }

}

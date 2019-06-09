/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones.ciclos;

import ast.ListaErrorPrinter;
import ast.NodoAST;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import ast.expresiones.operacion.TipoContenedor;
import ast.instrucciones.Bloque;
import ast.instrucciones.Instruccion;
import ast.instrucciones.Instruccion;
import ast.instrucciones.ciclos.RetCont.Breakk;
import ast.instrucciones.ciclos.RetCont.Continuee;
import ast.instrucciones.ciclos.RetCont.Returnn;
import com.sun.org.apache.bcel.internal.generic.ReturnInstruction;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class WhileCiclo implements Instruccion{
    
    private Expresion condicion;
    private Instruccion listaParaEjecutar;
    private int linea;
    private int col;

    public WhileCiclo(Expresion condicion, Instruccion listaParaEjecutar, int linea, int col) {
        this.condicion = condicion;
        this.listaParaEjecutar = listaParaEjecutar;
        this.linea = linea;
        this.col = col;
    }
    
    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            TipoContenedor tipo = (TipoContenedor) condicion.getType(lista, impresion);
            if(tipo.getTipoPrimitivo() == Simbolo.Tipo.BOOLEAN){
                while((Boolean) condicion.getValue(lista, impresion)){
                    boolean reiniciar = false;
                    for (NodoAST nodo : ((Bloque)listaParaEjecutar).listaIns) {                        
                        
                        if(nodo instanceof Instruccion){
                            Object retorno = ((Instruccion) nodo).ejecutar(lista, impresion);
                            if(retorno instanceof Breakk){
                                return null;
                            }else if(retorno instanceof Continuee || String.valueOf(retorno).equals("shar")){
                                reiniciar = true;
                                break;
                            }else if(retorno instanceof Returnn){
                                //AQUI VA EL RETURN 
                            }
                        }else if(nodo instanceof Expresion){
                            //AQUI EL RETORNO
                        }
                    }
                    if(reiniciar == true){
                        continue;
                    }
                }
            }else{
                impresion.errores.add(new ast.Error("Condicion del While no valida", linea, col, "Semantico"));
            }
            
        } catch (Exception e) {
            System.out.println("Error en la clase WhileCiclo ejecutar");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the condicion
     */
    public Expresion getCondicion() {
        return condicion;
    }

    /**
     * @param condicion the condicion to set
     */
    public void setCondicion(Expresion condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the listaParaEjecutar
     */
    public Instruccion getListaParaEjecutar() {
        return listaParaEjecutar;
    }

    /**
     * @param listaParaEjecutar the listaParaEjecutar to set
     */
    public void setListaParaEjecutar(Instruccion listaParaEjecutar) {
        this.listaParaEjecutar = listaParaEjecutar;
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

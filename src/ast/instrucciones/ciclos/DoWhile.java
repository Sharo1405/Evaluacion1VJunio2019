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
import ast.instrucciones.ciclos.RetCont.Breakk;
import ast.instrucciones.ciclos.RetCont.Continuee;
import ast.instrucciones.ciclos.RetCont.Returnn;

/**
 *
 * @author sharolin
 */
public class DoWhile implements Instruccion {

    private Instruccion sentenciasEjecutar;
    private Expresion condicion;
    private int linea;
    private int col;

    public DoWhile(Instruccion sentenciasEjecutar, Expresion condicion, int linea, int col) {
        this.sentenciasEjecutar = sentenciasEjecutar;
        this.condicion = condicion;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            do {
                boolean reiniciar = false;
                Object retorno = sentenciasEjecutar.ejecutar(lista, impresion);
                    
                    if (retorno instanceof Breakk) {
                        return null;
                    } else if (retorno instanceof Continuee){                        
                        continue;
                    } else if (retorno instanceof Returnn) {
                        //AQUI VA EL RETURN 
                    }else if (retorno instanceof Boolean){
                        if(retorno.equals(true)){
                            continue;
                        }
                    }

                //-----------------------------------------------------------------------------------------------
                TipoContenedor tipo = (TipoContenedor) condicion.getType(lista, impresion);
                if (tipo.getTipoPrimitivo() == Simbolo.Tipo.BOOLEAN) {
                        //todo cool
                } else {
                    impresion.errores.add(new ast.Error("Condicion del While no valida", linea, col, "Semantico"));
                }
                //-----------------------------------------------------------------------------------------------

            } while ((Boolean) condicion.getValue(lista, impresion));

        } catch (Exception e) {
            System.out.println("Error en la clase DoWhile, ejecutar");
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the sentenciasEjecutar
     */
    public Instruccion getSentenciasEjecutar() {
        return sentenciasEjecutar;
    }

    /**
     * @param sentenciasEjecutar the sentenciasEjecutar to set
     */
    public void setSentenciasEjecutar(Instruccion sentenciasEjecutar) {
        this.sentenciasEjecutar = sentenciasEjecutar;
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

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
public class Forr implements Instruccion {

    private Instruccion declaAsig;
    private Expresion condicion;
    private Expresion aumento; //ver que onda proque el a++ no esta en el E sino en PREPOSTFIJO
    private Instruccion sentencias;
    private int linea;
    private int col;

    public Forr(Instruccion declaAsig, Expresion condicion, Expresion aumento, Instruccion sentencias, int linea, int col) {
        this.declaAsig = declaAsig;
        this.condicion = condicion;
        this.aumento = aumento;
        this.sentencias = sentencias;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            //se debe crear un nuevo entorno porque sino se declararia mal el init del for
            //amenos que sea asignacion pero pues

            Entorno actual = new Entorno(lista);
            declaAsig.ejecutar(actual, impresion);

            TipoContenedor t = (TipoContenedor) condicion.getType(actual, impresion);
            if (t.getTipoPrimitivo() == Simbolo.Tipo.BOOLEAN) {

                //hace la validacion del for
                while ((Boolean) condicion.getValue(actual, impresion)) {
                    boolean reiniciar = false;
                    Entorno actualactual = new Entorno(actual);

                    //ejecuta sentencias
                    for (NodoAST nodo : ((Bloque) sentencias).listaIns) {

                        if (nodo instanceof Instruccion) {
                            Object retorno = ((Instruccion) nodo).ejecutar(actualactual, impresion);
                            if (retorno instanceof Breakk) {
                                return null;
                            } else if (retorno instanceof Continuee || String.valueOf(retorno).equals("shar")) {
                                reiniciar = true;
                                break;
                            } else if (retorno instanceof Returnn) {
                                //AQUI VA EL RETURN 
                            }
                        } else if (nodo instanceof Expresion) {
                            //estos son los pre y pos fijos
                            Object retorno = ((Expresion) nodo).getValue(actualactual, impresion);

                            //AQUI EL RETORNO
                        }

                        if (reiniciar == true) {
                            continue;
                        }
                    }

                    Object valor = aumento.getValue(actualactual, impresion);

                }

            } else {
                impresion.errores.add(new ast.Error("Condicion del FOR no es valida", linea, col, "Semantico"));
            }

        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public int getLine() {
        return linea;
    }

    /**
     * @return the declaAsig
     */
    public Instruccion getDeclaAsig() {
        return declaAsig;
    }

    /**
     * @param declaAsig the declaAsig to set
     */
    public void setDeclaAsig(Instruccion declaAsig) {
        this.declaAsig = declaAsig;
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
     * @return the aumento
     */
    public Expresion getAumento() {
        return aumento;
    }

    /**
     * @param aumento the aumento to set
     */
    public void setAumento(Expresion aumento) {
        this.aumento = aumento;
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

    /**
     * @return the sentencias
     */
    public Instruccion getSentencias() {
        return sentencias;
    }

    /**
     * @param sentencias the sentencias to set
     */
    public void setSentencias(Instruccion sentencias) {
        this.sentencias = sentencias;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.entorno.Entorno;
import ast.importaciones.Importaciones;
import ast.importaciones.Importar;
import ast.instrucciones.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class ArbolAST {

    public LinkedList<Importar> listaImportar = new LinkedList<>();
    public LinkedList<NodoAST> listaClasesEnArchivo = new LinkedList<>();

    public ArbolAST(LinkedList<Importar> listaImportar, LinkedList<NodoAST> listaClasesEnArchivo) {
        this.listaImportar = listaImportar;
        this.listaClasesEnArchivo = listaClasesEnArchivo;
    }
    
    public ArbolAST(LinkedList<Importar> listaImportar) {
        this.listaImportar = listaImportar;
    }
    
    public ArbolAST(LinkedList<NodoAST> listaClasesEnArchivo, int nada) {
        this.listaClasesEnArchivo = listaClasesEnArchivo;
    }

    public ArbolAST() {
    }

    public void ejecutar(Entorno entorno, ListaErrorPrinter listas) {
        /*for(NodoAST no: raiz){
            if(no instanceof Instruccion){
                Instruccion instruccion = (Instruccion) no;
                instruccion.ejecutar(entorno);
            }
        }*/
    }
}

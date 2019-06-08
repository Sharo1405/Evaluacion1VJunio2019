/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author sharolin
 */
public class ImprimirTS implements Instruccion {

    public ImprimirTS(String h) {
        System.out.println("soy el imprimir tabla\n\n");
    }

    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            int contador = 0;
            for (Entorno e = lista; e != null; e = e.getPadreANTERIOR()) {
                System.out.println("-Entorno----------------------------------\n");

                for (Map.Entry<String, Simbolo> en : e.getTabla().entrySet()) {
                    //Object key = en.getKey();
                    Simbolo val = en.getValue();
                    if (val.getValor() != null) {
                        System.out.println("--ID: " + val.getId() + " --VALOR: " + String.valueOf(val.getValor()));
                    } else {
                        System.out.println("--ID: " + val.getId() + " --VALOR: null");
                    }

                }
                contador++;
            }

        } catch (Exception e) {
            System.out.println("Error en clase ImprimirTS, ejecutar");
        }

        return null;
    }

    @Override
    public int getLine() {
        return -1; //menos uno porque no viene en la gram no tiene linea
    }

}

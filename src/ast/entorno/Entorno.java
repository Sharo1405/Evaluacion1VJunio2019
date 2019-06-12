/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.entorno;

import ast.ListaErrorPrinter;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author sharolin
 */
public class Entorno {

    private Entorno padreANTERIOR;

    private Hashtable<String, Simbolo> tabla;

    public Entorno() {
        this.padreANTERIOR = null;
        this.tabla = new Hashtable<>();
    }

    public Entorno(Entorno padreANTERIOR) {
        this.padreANTERIOR = padreANTERIOR;
        this.tabla = new Hashtable<String, Simbolo>();
    }

    public Simbolo getEnActual(String id) {
        try {

            Simbolo encontrado = tabla.get(id);
            if (encontrado != null) {
                return encontrado;
            }
        } catch (Exception e) {
            System.out.println("Error en la clase Entorno GetEnActual");
        }
        
        return null;
    }

    public Simbolo get(String id, Entorno actual) {
        try {

            for (Entorno e = actual; e != null; e = e.getPadreANTERIOR()) {

                Simbolo encontrado = e.tabla.get(id);
                if (encontrado != null) {
                    return encontrado;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la clase Entorno get");
        }       
        return null;
    }

    public void setSimbolo(String id, Simbolo nuevoSimbolo) {

        try {
            if (!this.tabla.containsKey(id)) {
                this.tabla.put(id, nuevoSimbolo);
            }
        } catch (Exception e) {
        }
    }

    public void setValorSimbolo(String id, Object valorNuevo) {
        try {
            for (Entorno e = this; e != null; e = e.getPadreANTERIOR()) {
                Simbolo encontrado = e.tabla.get(id);
                if (encontrado != null) {
                    encontrado.setValor(valorNuevo);
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * @return the padreANTERIOR
     */
    public Entorno getPadreANTERIOR() {
        return padreANTERIOR;
    }

    /**
     * @param padreANTERIOR the padreANTERIOR to set
     */
    public void setPadreANTERIOR(Entorno padreANTERIOR) {
        this.padreANTERIOR = padreANTERIOR;
    }

    /**
     * @return the tabla
     */
    public Hashtable<String, Simbolo> getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(Hashtable<String, Simbolo> tabla) {
        this.tabla = tabla;
    }

}

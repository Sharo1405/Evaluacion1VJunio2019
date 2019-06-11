/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operacion;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.instrucciones.Instruccion;

/**
 *
 * @author sharolin
 */
public class TipoContenedor implements Instruccion{
    
    private Simbolo.Tipo tipoPrimitivo;
    private String tipoObjeto;

    public TipoContenedor() {
        this.tipoObjeto = "";
        this.tipoPrimitivo = null;
    }
    
    public TipoContenedor(Simbolo.Tipo tipoPrimitivo) {
        this.tipoPrimitivo = tipoPrimitivo;
        this.tipoObjeto = "";
    }
    
    public TipoContenedor(String tipoObjeto) {
        this.tipoPrimitivo = null;
        this.tipoObjeto = tipoObjeto;
    }
    
    
    @Override
    public Object ejecutar(Entorno lista, ListaErrorPrinter impresion) {
        try {
            
            if(tipoPrimitivo != null){
                return tipoPrimitivo;
            }else if(!tipoObjeto.equals("")){
                //AQUI VAN OBJETOS
                return tipoObjeto;
            }
            
            
        } catch (Exception e) {
            System.out.println("Error en la clase TipoContenedor");
        }
        return null;
    }

    @Override
    public int getLine() {
        return -1;
    }

    
    public Boolean isString(TipoContenedor nodo){
        
        if(nodo.getTipoPrimitivo() == Simbolo.Tipo.STRING){
            return true;
        }        
        return false;
    }
    
    
    public Boolean isEntero(TipoContenedor nodo){
        
        if(nodo.getTipoPrimitivo() == Simbolo.Tipo.INT){
            return true;
        }        
        return false;
    }
    
    
    public Boolean isBool(TipoContenedor nodo){
        
        if(nodo.getTipoPrimitivo() == Simbolo.Tipo.BOOLEAN){
            return true;
        }        
        return false;
    }
    
    
    public Boolean isChar(TipoContenedor nodo){
        
        if(nodo.getTipoPrimitivo() == Simbolo.Tipo.CHAR){
            return true;
        }        
        return false;
    }
    
    
    public Boolean isDecimal(TipoContenedor nodo){
        
        if(nodo.getTipoPrimitivo() == Simbolo.Tipo.DOUBLE){
            return true;
        }        
        return false;
    }
    
    /**
     * @return the tipoPrimitivo
     */
    public Simbolo.Tipo getTipoPrimitivo() {
        return tipoPrimitivo;
    }

    /**
     * @param tipoPrimitivo the tipoPrimitivo to set
     */
    public void setTipoPrimitivo(Simbolo.Tipo tipoPrimitivo) {
        this.tipoPrimitivo = tipoPrimitivo;
    }

    /**
     * @return the tipoObjeto
     */
    public String getTipoObjeto() {
        return tipoObjeto;
    }

    /**
     * @param tipoObjeto the tipoObjeto to set
     */
    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }
    
}

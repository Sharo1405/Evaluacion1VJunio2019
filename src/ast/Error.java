/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author sharolin
 */
public class Error {
    
    
    private String descripcion;
    private int linea;
    private int col;
    private String tipoError;

    public Error(String descripcion, int linea, int col, String tipoError) {
        this.descripcion = descripcion;
        this.linea = linea;
        this.col = col;
        this.tipoError = tipoError;
    }    

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * @return the tipoError
     */
    public String getTipoError() {
        return tipoError;
    }

    /**
     * @param tipoError the tipoError to set
     */
    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }
    
    
}

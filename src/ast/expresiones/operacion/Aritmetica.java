/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operacion;

import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;

/**
 *
 * @author sharolin
 */
public class Aritmetica extends Operacion implements Expresion{

    public Aritmetica(Expresion exp1, Expresion exp2, Operador op, int line, int col, Simbolo.Tipo tipo) {
        super(exp1, exp2, op, line, col, tipo);
    }

    public Aritmetica(Expresion exp1, Operador op, int line, int col, Simbolo.Tipo tipo) {
        super(exp1, op, line, col, tipo);
    }
    
    

    @Override
    public Object getValue(Entorno lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getType(Entorno lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

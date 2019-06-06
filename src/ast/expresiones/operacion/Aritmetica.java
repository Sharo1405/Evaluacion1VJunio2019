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
public class Aritmetica extends Operacion implements Expresion {

    public Aritmetica(Expresion exp1, Expresion exp2, Operador op, int line, int col, Simbolo.Tipo tipo) {
        super(exp1, exp2, op, line, col, tipo);
    }

    public Aritmetica(Expresion exp1, Operador op, int line, int col, Simbolo.Tipo tipo) {
        super(exp1, op, line, col, tipo);
    }

    @Override
    public Object getValue(Entorno lista) {
        Object op1 = exp1.getValue(lista);
        Object op2 = exp2.getValue(lista);

        if (op1 != null && op2 != null) {
            tipo = tipoResultante(exp1, exp2, lista);

            switch (op) {
                case MAS:
                    switch (tipo) {
                        case STRING:
                            return String.valueOf(exp1.getValue(lista)) + " " + String.valueOf(exp1.getValue(lista));

                        case INT:
                            return Integer.parseInt(String.valueOf(exp1.getValue(lista))) + Integer.parseInt(String.valueOf(exp2.getValue(lista)));

                        case DOUBLE:
                            return Double.parseDouble(String.valueOf(exp1.getValue(lista))) + Double.parseDouble(String.valueOf(exp2.getValue(lista)));

                        default:
                            System.out.println("Error de tipos para SUMA");
                    }
                    break;

                case MENOS:
                    switch (tipo) {

                        case INT:
                            return Integer.parseInt(String.valueOf(exp1.getValue(lista))) - Integer.parseInt(String.valueOf(exp2.getValue(lista)));

                        case DOUBLE:
                            return Double.parseDouble(String.valueOf(exp1.getValue(lista))) - Double.parseDouble(String.valueOf(exp2.getValue(lista)));

                        default:
                            System.out.println("Error de tipos para MENOS");
                    }
                    break;

                case POR:
                    switch (tipo) {
                        case INT:
                            return Integer.parseInt(String.valueOf(exp1.getValue(lista))) * Integer.parseInt(String.valueOf(exp2.getValue(lista)));

                        case DOUBLE:
                            return Double.parseDouble(String.valueOf(exp1.getValue(lista))) * Double.parseDouble(String.valueOf(exp2.getValue(lista)));

                        default:
                            System.out.println("Error de tipos para MULTIPLICACION");
                    }
                    break;

                case DIVIDIR:
                    switch (tipo) {
                        case DOUBLE:
                            if (Double.parseDouble(String.valueOf(exp2.getValue(lista))) > 0) {
                                return Double.parseDouble(String.valueOf(exp1.getValue(lista))) / Double.parseDouble(String.valueOf(exp2.getValue(lista)));
                            } else {
                                System.out.println("Error division entre cero");
                            }

                        case INT:
                            if (Double.parseDouble(String.valueOf(exp2.getValue(lista))) > 0) {
                                return Integer.parseInt(String.valueOf(exp1.getValue(lista))) / Integer.parseInt(String.valueOf(exp2.getValue(lista)));
                            } else {
                                System.out.println("Error division entre cero");
                            }
                    }
                    break;

                case POTENCIA:
                    switch (tipo) {
                        case INT:
                            return Math.pow(Double.parseDouble(String.valueOf(exp1.getValue(lista))), Double.parseDouble(String.valueOf(exp2.getValue(lista))));

                        case DOUBLE:
                            return Double.parseDouble(String.valueOf(exp1.getValue(lista))) * Double.parseDouble(String.valueOf(exp2.getValue(lista)));

                        default:
                            System.out.println("Error de tipos para POTENCIA");
                    }
                    break;
            }
            
            return null;
        }

        return null;
    }

    @Override
    public Object getType(Entorno lista) {
        return tipoResultante(exp1, exp2, lista);
    }

    @Override
    public int getLine() {
        return line;
    }

}

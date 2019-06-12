/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operacion;

import ast.ListaErrorPrinter;
import ast.entorno.Entorno;
import ast.entorno.Simbolo;
import ast.expresiones.Expresion;
import ast.expresiones.Identificador;
import java.util.LinkedList;

/**
 *
 * @author sharolin
 */
public class Aritmetica extends Operacion implements Expresion {

    public Aritmetica(Expresion exp1, Expresion exp2, Operador op, int line, int col, TipoContenedor tipo) {
        super(exp1, exp2, op, line, col, tipo);
    }

    public Aritmetica(Expresion exp1, Operador op, int line, int col, TipoContenedor tipo) {
        super(exp1, op, line, col, tipo);
    }

    @Override
    public Object getValue(Entorno lista, ListaErrorPrinter impresion) {
        //Object op1 = exp1.getValue(lista);
        //Object op2 = exp2.getValue(lista);
        try {
            if (exp1 != null && exp2 != null) {
                
                if(exp1 instanceof Identificador){
                    Object existe = exp1.getValue(lista, impresion);
                    if(existe == null){
                        return null;
                    }
                }else if(exp2 instanceof Identificador){
                    Object existe = exp2.getValue(lista, impresion);
                    if(existe == null){
                        return null;
                    }
                }
                
                tipo = tipoResultante(exp1, exp2, lista, impresion);

                TipoContenedor tipo1 = (TipoContenedor) exp1.getType(lista, impresion);
                TipoContenedor tipo2 = (TipoContenedor) exp2.getType(lista, impresion);

                switch (op) {
                    case MAS:
                        switch (tipo.getTipoPrimitivo()) {
                            case STRING:
                                return String.valueOf(exp1.getValue(lista, impresion)) + " " + String.valueOf(exp2.getValue(lista, impresion));

                            case INT:
                                if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                    return (int) var[0] + Integer.parseInt(String.valueOf(exp2.getValue(lista, impresion)));
                                } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();
                                    return Integer.parseInt(String.valueOf(exp1.getValue(lista, impresion))) + (int) var[0];
                                } else if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();

                                    char var2[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();

                                    return (int) var[0] + (int) var2[0];

                                } else {
                                    return Integer.parseInt(String.valueOf(exp1.getValue(lista, impresion))) + Integer.parseInt(String.valueOf(exp2.getValue(lista, impresion)));
                                }

                            case DOUBLE:
                                if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                    return (int) var[0] + Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion)));
                                } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();
                                    return Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))) + (int) var[0];
                                } else {
                                    return Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))) + Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion)));
                                }

                            default:
                                System.out.println("Error de tipos para SUMA");
                                impresion.errores.add(new ast.Error("Error de tipo para Operar SUMA ", line, col, "Semantico"));
                        }
                        break;

                    case MENOS:
                        switch (tipo.getTipoPrimitivo()) {

                            case INT:
                                if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                    return (int) var[0] - Integer.parseInt(String.valueOf(exp2.getValue(lista, impresion)));
                                } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();
                                    return Integer.parseInt(String.valueOf(exp1.getValue(lista, impresion))) - (int) var[0];
                                } else if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();

                                    char var2[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();

                                    return (int) var[0] - (int) var2[0];

                                } else {
                                    return Integer.parseInt(String.valueOf(exp1.getValue(lista, impresion))) - Integer.parseInt(String.valueOf(exp2.getValue(lista, impresion)));
                                }

                            case DOUBLE:
                                if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                    return (int) var[0] - Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion)));
                                } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();
                                    return Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))) - (int) var[0];
                                } else {
                                    return Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))) - Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion)));
                                }

                            default:
                                System.out.println("Error de tipos para MENOS");
                                impresion.errores.add(new ast.Error("Error de tipo para Operar RESTA ", line, col, "Semantico"));
                        }
                        break;

                    case POR:
                        switch (tipo.getTipoPrimitivo()) {
                            case INT:
                                if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                    return (int) var[0] * Integer.parseInt(String.valueOf(exp2.getValue(lista, impresion)));
                                } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();
                                    return Integer.parseInt(String.valueOf(exp1.getValue(lista, impresion))) * (int) var[0];
                                } else if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();

                                    char var2[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();

                                    return (int) var[0] * (int) var2[0];

                                } else {
                                    return Integer.parseInt(String.valueOf(exp1.getValue(lista, impresion))) * Integer.parseInt(String.valueOf(exp2.getValue(lista, impresion)));
                                }

                            case DOUBLE:
                                if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                    return (int) var[0] * Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion)));
                                } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();
                                    return Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))) * (int) var[0];
                                } else {
                                    return Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))) * Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion)));
                                }

                            default:
                                System.out.println("Error de tipos para MULTIPLICACION");
                                impresion.errores.add(new ast.Error("Error de tipo para Operar MULTIPLICACION ", line, col, "Semantico"));
                        }
                        break;

                    case DIVIDIR:
                        switch (tipo.getTipoPrimitivo()) {
                            case DOUBLE:
                                if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                    if ((int) var[0] > 0) {
                                        return (int) var[0] / Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion)));
                                    } else {
                                        System.out.println("Error division entre cero");
                                        impresion.errores.add(new ast.Error("Error DIVISION entre cero", line, col, "Semantico"));
                                    }
                                } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();
                                    if ((int) var[0] > 0) {
                                        return Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))) / (int) var[0];
                                    } else {
                                        System.out.println("Error division entre cero");
                                        impresion.errores.add(new ast.Error("Error DIVISION entre cero", line, col, "Semantico"));
                                    }

                                } else if (Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion))) > 0) {
                                    return Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))) / Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion)));
                                } else {
                                    System.out.println("Error division entre cero");
                                    impresion.errores.add(new ast.Error("Error DIVISION entre cero", line, col, "Semantico"));
                                }

                            case INT:
                                if (Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion))) > 0) {
                                    if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                        char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                        return (int) var[0] / Integer.parseInt(String.valueOf(exp2.getValue(lista, impresion)));
                                    } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                        char var[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();
                                        return Integer.parseInt(String.valueOf(exp1.getValue(lista, impresion))) / (int) var[0];
                                    } else if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                        char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();

                                        char var2[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();

                                        return (int) var[0] / (int) var2[0];

                                    } else {
                                        return Integer.parseInt(String.valueOf(exp1.getValue(lista, impresion))) / Integer.parseInt(String.valueOf(exp2.getValue(lista, impresion)));
                                    }
                                } else {
                                    System.out.println("Error division entre cero");
                                    impresion.errores.add(new ast.Error("Error DIVISION entre cero", line, col, "Semantico"));
                                }

                        }
                        break;

                    case POTENCIA:
                        switch (tipo.getTipoPrimitivo()) {
                            case INT:
                                if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                    int v = (int) var[0];
                                    return (int) Math.pow(v, Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion))));
                                } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();
                                    int v = (int) var[0];
                                    return (int) Math.pow(Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))), v);
                                } else if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                    int v = (int) var[0];

                                    char var2[] = String.valueOf(exp2.getValue(lista, impresion)).toCharArray();
                                    int v2 = (int) var2[0];

                                    return (int) Math.pow(v, v2);

                                } else {
                                    return (int) Math.pow(Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))), Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion))));
                                }
                            case DOUBLE:
                                return Math.pow(Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))), Double.parseDouble(String.valueOf(exp2.getValue(lista, impresion))));

                            default:
                                System.out.println("Error de tipos para POTENCIA");
                                impresion.errores.add(new ast.Error("Error de tipos para POTENCIA", line, col, "Semantico"));
                        }
                        break;
                }

                return null;
            } else {

                if(exp1 == null){
                    impresion.errores.add(new ast.Error("No se puede efectuar operacion se encontro Valor nulo", line, col, "Semantico"));
                    return null;
                }
                
                TipoContenedor tipo1 = (TipoContenedor) exp1.getType(lista, impresion);

                switch (op) {
                    case POSITIVO:
                        switch (tipo1.getTipoPrimitivo()) {
                            case INT:
                                return Integer.parseInt(String.valueOf(exp1.getValue(lista, impresion))) * +1;

                            case DOUBLE:
                                return Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))) * +1;

                            case CHAR:
                                char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                int v = (int) var[0];
                                return v * +1;

                            default:
                                impresion.errores.add(new ast.Error("Error de tipos para +num POSITIVO", line, col, "Semantico"));
                        }
                        break;

                    case NEGATIVO:
                        switch (tipo1.getTipoPrimitivo()) {
                            case INT:
                                return Integer.parseInt(String.valueOf(exp1.getValue(lista, impresion))) * -1;

                            case DOUBLE:
                                return Double.parseDouble(String.valueOf(exp1.getValue(lista, impresion))) * -1;

                            case CHAR:
                                char var[] = String.valueOf(exp1.getValue(lista, impresion)).toCharArray();
                                int v = (int) var[0];
                                return v * -1;

                            default:
                                impresion.errores.add(new ast.Error("Error de tipos para -num NEGATIVO", line, col, "Semantico"));
                        }
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la clase Aritmetica getvalue");
        }
        return null;
    }

    @Override
    public Object getType(Entorno lista, ListaErrorPrinter impresion) {

        if (op == Operador.DIVIDIR) {
            return new TipoContenedor(Simbolo.Tipo.DOUBLE);
        } else if (exp1.getType(lista, impresion) == Simbolo.Tipo.CHAR && exp2.getType(lista, impresion) == Simbolo.Tipo.CHAR) {
            return new TipoContenedor(Simbolo.Tipo.INT);
        } else {
            return tipoResultante(exp1, exp2, lista, impresion);
        }
    }

    @Override
    public int getLine() {
        return line;
    }
   

}

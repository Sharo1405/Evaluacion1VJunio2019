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

    public Aritmetica(Expresion exp1, Expresion exp2, Operador op, int line, int col, TipoContenedor tipo) {
        super(exp1, exp2, op, line, col, tipo);
    }

    public Aritmetica(Expresion exp1, Operador op, int line, int col, TipoContenedor tipo) {
        super(exp1, op, line, col, tipo);
    }

    @Override
    public Object getValue(Entorno lista) {
        Object op1 = exp1.getValue(lista);
        Object op2 = exp2.getValue(lista);

        if (op1 != null && op2 != null) {
            tipo = tipoResultante(exp1, exp2, lista);

            TipoContenedor tipo1 = (TipoContenedor) exp1.getType(lista);
            TipoContenedor tipo2 = (TipoContenedor) exp2.getType(lista);

            switch (op) {
                case MAS:
                    switch (tipo.getTipoPrimitivo()) {
                        case STRING:
                            return String.valueOf(exp1.getValue(lista)) + " " + String.valueOf(exp2.getValue(lista));

                        case INT:
                            if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();
                                return (int) var[0] + Integer.parseInt(String.valueOf(exp2.getValue(lista)));
                            } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp2.getValue(lista)).toCharArray();
                                return Integer.parseInt(String.valueOf(exp1.getValue(lista))) + (int) var[0];
                            } else if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();

                                char var2[] = String.valueOf(exp2.getValue(lista)).toCharArray();

                                return (int) var[0] + (int) var2[0];

                            } else {
                                return Integer.parseInt(String.valueOf(exp1.getValue(lista))) + Integer.parseInt(String.valueOf(exp2.getValue(lista)));
                            }

                        case DOUBLE:
                            if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();
                                return (int) var[0] + Double.parseDouble(String.valueOf(exp2.getValue(lista)));
                            } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp2.getValue(lista)).toCharArray();
                                return Double.parseDouble(String.valueOf(exp1.getValue(lista))) + (int) var[0];
                            } else {
                                return Double.parseDouble(String.valueOf(exp1.getValue(lista))) + Double.parseDouble(String.valueOf(exp2.getValue(lista)));
                            }

                        default:
                            System.out.println("Error de tipos para SUMA");
                    }
                    break;

                case MENOS:
                    switch (tipo.getTipoPrimitivo()) {

                        case INT:
                            if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();
                                return (int) var[0] - Integer.parseInt(String.valueOf(exp2.getValue(lista)));
                            } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp2.getValue(lista)).toCharArray();
                                return Integer.parseInt(String.valueOf(exp1.getValue(lista))) - (int) var[0];
                            } else if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();

                                char var2[] = String.valueOf(exp2.getValue(lista)).toCharArray();

                                return (int) var[0] - (int) var2[0];

                            } else {
                                return Integer.parseInt(String.valueOf(exp1.getValue(lista))) - Integer.parseInt(String.valueOf(exp2.getValue(lista)));
                            }

                        case DOUBLE:
                            if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();
                                return (int) var[0] - Double.parseDouble(String.valueOf(exp2.getValue(lista)));
                            } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp2.getValue(lista)).toCharArray();
                                return Double.parseDouble(String.valueOf(exp1.getValue(lista))) - (int) var[0];
                            } else {
                                return Double.parseDouble(String.valueOf(exp1.getValue(lista))) - Double.parseDouble(String.valueOf(exp2.getValue(lista)));
                            }

                        default:
                            System.out.println("Error de tipos para MENOS");
                    }
                    break;

                case POR:
                    switch (tipo.getTipoPrimitivo()) {
                        case INT:
                            if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();
                                return (int) var[0] * Integer.parseInt(String.valueOf(exp2.getValue(lista)));
                            } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp2.getValue(lista)).toCharArray();
                                return Integer.parseInt(String.valueOf(exp1.getValue(lista))) * (int) var[0];
                            } else if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();

                                char var2[] = String.valueOf(exp2.getValue(lista)).toCharArray();

                                return (int) var[0] * (int) var2[0];

                            } else {
                                return Integer.parseInt(String.valueOf(exp1.getValue(lista))) * Integer.parseInt(String.valueOf(exp2.getValue(lista)));
                            }

                        case DOUBLE:
                            if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();
                                return (int) var[0] * Double.parseDouble(String.valueOf(exp2.getValue(lista)));
                            } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp2.getValue(lista)).toCharArray();
                                return Double.parseDouble(String.valueOf(exp1.getValue(lista))) * (int) var[0];
                            } else {
                                return Double.parseDouble(String.valueOf(exp1.getValue(lista))) * Double.parseDouble(String.valueOf(exp2.getValue(lista)));
                            }

                        default:
                            System.out.println("Error de tipos para MULTIPLICACION");
                    }
                    break;

                case DIVIDIR:
                    switch (tipo.getTipoPrimitivo()) {
                        case DOUBLE:
                            if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();
                                if ((int) var[0] > 0) {
                                    return (int) var[0] / Double.parseDouble(String.valueOf(exp2.getValue(lista)));
                                } else {
                                    System.out.println("Error division entre cero");
                                }
                            } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp2.getValue(lista)).toCharArray();
                                if ((int) var[0] > 0) {
                                    return Double.parseDouble(String.valueOf(exp1.getValue(lista))) / (int) var[0];
                                } else {
                                    System.out.println("Error division entre cero");
                                }

                            } else if (Double.parseDouble(String.valueOf(exp2.getValue(lista))) > 0) {
                                return Double.parseDouble(String.valueOf(exp1.getValue(lista))) / Double.parseDouble(String.valueOf(exp2.getValue(lista)));
                            } else {
                                System.out.println("Error division entre cero");
                            }

                        case INT:
                            if (Double.parseDouble(String.valueOf(exp2.getValue(lista))) > 0) {
                                if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();
                                    return (int) var[0] / Integer.parseInt(String.valueOf(exp2.getValue(lista)));
                                } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp2.getValue(lista)).toCharArray();
                                    return Integer.parseInt(String.valueOf(exp1.getValue(lista))) / (int) var[0];
                                } else if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                    char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();

                                    char var2[] = String.valueOf(exp2.getValue(lista)).toCharArray();

                                    return (int) var[0] / (int) var2[0];

                                } else {
                                    return Integer.parseInt(String.valueOf(exp1.getValue(lista))) / Integer.parseInt(String.valueOf(exp2.getValue(lista)));
                                }
                            } else {
                                System.out.println("Error division entre cero");
                            }

                    }
                    break;

                case POTENCIA:
                    switch (tipo.getTipoPrimitivo()) {
                        case INT:
                            if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();
                                int v = (int) var[0];
                                return Math.pow(v, Double.parseDouble(String.valueOf(exp2.getValue(lista))));
                            } else if (tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp2.getValue(lista)).toCharArray();
                                int v = (int) var[0];
                                return Math.pow(Double.parseDouble(String.valueOf(exp1.getValue(lista))), v);
                            } else if (tipo1.getTipoPrimitivo() == Simbolo.Tipo.CHAR && tipo2.getTipoPrimitivo() == Simbolo.Tipo.CHAR) {
                                char var[] = String.valueOf(exp1.getValue(lista)).toCharArray();
                                int v = (int) var[0];

                                char var2[] = String.valueOf(exp2.getValue(lista)).toCharArray();
                                int v2 = (int) var2[0];

                                return Math.pow(v, v2);

                            } else {
                                return Math.pow(Double.parseDouble(String.valueOf(exp1.getValue(lista))), Double.parseDouble(String.valueOf(exp2.getValue(lista))));
                            }
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
    public Object getType(Entorno lista
    ) {

        if (op == Operador.DIVIDIR) {
            return new TipoContenedor(Simbolo.Tipo.DOUBLE);
        } else if (exp1.getType(lista) == Simbolo.Tipo.CHAR && exp2.getType(lista) == Simbolo.Tipo.CHAR) {
            return new TipoContenedor(Simbolo.Tipo.INT);
        } else {
            return tipoResultante(exp1, exp2, lista);
        }
    }

    @Override
    public int getLine() {
        return line;
    }

}

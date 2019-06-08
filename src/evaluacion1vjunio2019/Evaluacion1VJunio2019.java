/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluacion1vjunio2019;

/**
 *
 * @author sharolin
 */
public class Evaluacion1VJunio2019 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        generarCompilador();
        Vista vista = new Vista();
        vista.setVisible(true);
        
        int a = 1;
        System.err.println(a++);
        a = 1;
        System.out.println(++a);
        
        int aa = 5==5 ? 10 : 0 ;
        System.out.println(aa);
        
        char hh = 'a';
        hh++;
        System.out.println(hh);
    }
    
    
    private static void generarCompilador() {
        try {
            String lexicoFlex[] = {"src/analizadores/" + "lexico.jflex", "-d", "src/analizadores/"};
            jflex.Main.generate(lexicoFlex);
            String sintacticoCup[] = {"-destdir", "src/analizadores/", "-parser", "Sintactico", "src/analizadores/" + "sintactico.cup"};
            java_cup.Main.main(sintacticoCup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

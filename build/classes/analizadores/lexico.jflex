package analizadores;

import java_cup.runtime.*;

%%
%{
    //cod
%}

%public
%class Lexico
%cup
%char
%column
%full
%ignorecase
%line
%unicode


//aritmeticas
masmas      = "++"
menosmenos  = "--"
mas         = "+"
menos       = "-"
por         = "*"
dividir     = "/"
potencia    = "^"


//relacionales
mayorq      = ">"
menorq      = "<"
mayorigualq = ">="
menorigualq = "<="


//logicas
diferenteunario = "!"
igualigual      = "=="
diferente       = "!="
oor             = "||"
aand            = "&&"

interrogacion   = "?"


//simbolos normales
apar        = "("
cpar        = ")"
puntoycoma  = ";"
dospuntos   = ":"
allave      = "{"
cllave      = "}"
igual       = "="
intt         = "int"
charr       = "char"
stringg     = "string"
doublee     = "double"
booleann    = "boolean"
truee       = "true"
falsee      = "false"
whilee      = "while"
print       = "print"
breakk      = "break"
continuee   = "continue"

imprimir    = "imprimir"


id          = [a-zA-ZñÑ"_"]+([a-zA-ZñÑ"_"]*|[0-9]*)*
decimal     = ([0-9]+"."[0-9]+|"-"[0-9]+"."[0-9]+)
entero      = ([0-9]+|"-"[0-9]+)
charER      = ("'" ~"'")
cadena      = ("\"" ~"\"")



espacio   = [\ \r\t\f\t]
enter   = [\ \n]

%state comentario1

%%
<YYINITIAL> "//"                {yybegin(comentario1);}
<comentario1> [^"\n"]         {}
<comentario1> "\n"            {yybegin(YYINITIAL);}


//ARITMETICAS
<YYINITIAL> {masmas}      {  System.out.println("Reconocido: <<"+yytext()+">>, masmas");
                                return new Symbol(sym.masmas, yyline, yycolumn, yytext()); }

<YYINITIAL> {menosmenos}      {  System.out.println("Reconocido: <<"+yytext()+">>, menosmenos");
                                return new Symbol(sym.menosmenos, yyline, yycolumn, yytext()); }



<YYINITIAL> {mas}      {  System.out.println("Reconocido: <<"+yytext()+">>, mas");
                                return new Symbol(sym.mas, yyline, yycolumn, yytext()); }

<YYINITIAL> {menos}      {  System.out.println("Reconocido: <<"+yytext()+">>, menos");
                                return new Symbol(sym.menos, yyline, yycolumn, yytext()); }

<YYINITIAL> {por}      {  System.out.println("Reconocido: <<"+yytext()+">>, por");
                                return new Symbol(sym.por, yyline, yycolumn, yytext()); }

<YYINITIAL> {dividir}      {  System.out.println("Reconocido: <<"+yytext()+">>, dividir");
                                return new Symbol(sym.dividir, yyline, yycolumn, yytext()); }

<YYINITIAL> {potencia}      {  System.out.println("Reconocido: <<"+yytext()+">>, potencia");
                                return new Symbol(sym.potencia, yyline, yycolumn, yytext()); }


//RELACIONALES
<YYINITIAL> {mayorq}      {  System.out.println("Reconocido: <<"+yytext()+">>, mayorq");
                                return new Symbol(sym.mayorq, yyline, yycolumn, yytext()); }

<YYINITIAL> {menorq}      {  System.out.println("Reconocido: <<"+yytext()+">>, menorq");
                                return new Symbol(sym.menorq, yyline, yycolumn, yytext()); }

<YYINITIAL> {mayorigualq}      {  System.out.println("Reconocido: <<"+yytext()+">>, mayorigualq");
                                return new Symbol(sym.mayorigualq, yyline, yycolumn, yytext()); }

<YYINITIAL> {menorigualq}      {  System.out.println("Reconocido: <<"+yytext()+">>, menorigualq");
                                return new Symbol(sym.menorigualq, yyline, yycolumn, yytext()); }


//LOGICAS
<YYINITIAL> {diferenteunario}      {  System.out.println("Reconocido: <<"+yytext()+">>, diferenteunario");
                                return new Symbol(sym.diferenteunario, yyline, yycolumn, yytext()); }

<YYINITIAL> {igualigual}      {  System.out.println("Reconocido: <<"+yytext()+">>, igualigual");
                                return new Symbol(sym.igualigual, yyline, yycolumn, yytext()); }

<YYINITIAL> {diferente}      {  System.out.println("Reconocido: <<"+yytext()+">>, diferente");
                                return new Symbol(sym.diferente, yyline, yycolumn, yytext());}

<YYINITIAL> {oor}      {  System.out.println("Reconocido: <<"+yytext()+">>, oor");
                                return new Symbol(sym.oor, yyline, yycolumn, yytext()); }

<YYINITIAL> {aand}      {  System.out.println("Reconocido: <<"+yytext()+">>, aand");
                                return new Symbol(sym.aand, yyline, yycolumn, yytext()); }

<YYINITIAL> {interrogacion}      {  System.out.println("Reconocido: <<"+yytext()+">>, interrogacion");
                                return new Symbol(sym.interrogacion, yyline, yycolumn, yytext()); }



<YYINITIAL> {apar}      {  System.out.println("Reconocido: <<"+yytext()+">>, apar");
                                return new Symbol(sym.apar, yyline, yycolumn, yytext()); }

<YYINITIAL> {cpar}      {  System.out.println("Reconocido: <<"+yytext()+">>, cpar");
                                return new Symbol(sym.cpar, yyline, yycolumn, yytext()); }

<YYINITIAL> {puntoycoma}      {  System.out.println("Reconocido: <<"+yytext()+">>, puntoycoma");
                                return new Symbol(sym.puntoycoma, yyline, yycolumn, yytext()); } 

<YYINITIAL> {dospuntos}      {  System.out.println("Reconocido: <<"+yytext()+">>, dospuntos");
                                return new Symbol(sym.dospuntos, yyline, yycolumn, yytext()); } 

<YYINITIAL> {allave}      {  System.out.println("Reconocido: <<"+yytext()+">>, allave");
                                return new Symbol(sym.allave, yyline, yycolumn, yytext()); } 

<YYINITIAL> {cllave}      {  System.out.println("Reconocido: <<"+yytext()+">>, cllave");
                                return new Symbol(sym.cllave, yyline, yycolumn, yytext()); } 

<YYINITIAL> {igual}       {  System.out.println("Reconocido: <<"+yytext()+">>, igual");
                                return new Symbol(sym.igual, yyline, yycolumn, yytext()); } 

<YYINITIAL> {intt}       {  System.out.println("Reconocido: <<"+yytext()+">>, intt");
                                return new Symbol(sym.intt, yyline, yycolumn, yytext()); } 

<YYINITIAL> {charr}       {  System.out.println("Reconocido: <<"+yytext()+">>, charr");
                                return new Symbol(sym.charr, yyline, yycolumn, yytext()); } 

<YYINITIAL> {stringg}       {  System.out.println("Reconocido: <<"+yytext()+">>, stringg");
                                return new Symbol(sym.stringg, yyline, yycolumn, yytext()); } 

<YYINITIAL> {booleann}       {  System.out.println("Reconocido: <<"+yytext()+">>, booleann");
                                return new Symbol(sym.booleann, yyline, yycolumn, yytext()); } 

<YYINITIAL> {doublee}       {  System.out.println("Reconocido: <<"+yytext()+">>, doublee");
                                return new Symbol(sym.doublee, yyline, yycolumn, yytext()); } 

<YYINITIAL> {truee}       {  System.out.println("Reconocido: <<"+yytext()+">>, truee");
                                return new Symbol(sym.truee, yyline, yycolumn, yytext()); } 

<YYINITIAL> {falsee}       {  System.out.println("Reconocido: <<"+yytext()+">>, falsee");
                                return new Symbol(sym.falsee, yyline, yycolumn, yytext()); } 

<YYINITIAL> {whilee}       {  System.out.println("Reconocido: <<"+yytext()+">>, whilee");
                                return new Symbol(sym.whilee, yyline, yycolumn, yytext()); } 

<YYINITIAL> {print}       {  System.out.println("Reconocido: <<"+yytext()+">>, print");
                                return new Symbol(sym.print, yyline, yycolumn, yytext()); } 

<YYINITIAL> {breakk}       {  System.out.println("Reconocido: <<"+yytext()+">>, breakk");
                                return new Symbol(sym.breakk, yyline, yycolumn, yytext()); } 

<YYINITIAL> {continuee}       {  System.out.println("Reconocido: <<"+yytext()+">>, continuee");
                                return new Symbol(sym.continuee, yyline, yycolumn, yytext()); } 



<YYINITIAL> {imprimir}       {  System.out.println("Reconocido: <<"+yytext()+">>, imprimir");
                                return new Symbol(sym.imprimir, yyline, yycolumn, yytext()); } 



<YYINITIAL> {id}       {  System.out.println("Reconocido: <<"+yytext()+">>, id");
                                return new Symbol(sym.id, yyline, yycolumn, yytext()); }

<YYINITIAL> {decimal}       {  System.out.println("Reconocido: <<"+yytext()+">>, decimal");
                                return new Symbol(sym.decimal, yyline, yycolumn, yytext()); } 

<YYINITIAL> {entero}       {  System.out.println("Reconocido: <<"+yytext()+">>, entero");
                                return new Symbol(sym.entero, yyline, yycolumn, yytext()); } 

<YYINITIAL> {charER}        { System.out.println("Reconocido: <<"+yytext()+">>, char");
                                return new Symbol(sym.charER, yyline, yycolumn, yytext()); } 

<YYINITIAL> {cadena}        { System.out.println("Reconocido: <<"+yytext()+">>, cadena");
                                return new Symbol(sym.cadena, yyline, yycolumn, yytext()); } 


<YYINITIAL> {espacio}      {  } 

<YYINITIAL> {enter}       { } 

<YYINITIAL>. {
        String errorLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+yycolumn;
        System.err.println(errorLex);
        System.out.println(errorLex);
}


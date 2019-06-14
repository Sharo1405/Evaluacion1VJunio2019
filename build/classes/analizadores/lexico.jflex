package analizadores;

import java_cup.runtime.*;

%%
%{
    //cod

    String paraCadena = "";
%}

%public
%class Lexico
%cup
%char
%column
%full
//%ignorecase
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
coma        = ","
apar        = "("
cpar        = ")"
puntoycoma  = ";"
dospuntos   = ":"
allave      = "{"
cllave      = "}"
igual       = "="
intt         = "int"
charr       = "char"
stringg     = "String"
doublee     = "double"
booleann    = "boolean"
truee       = "true"
falsee      = "false"
whilee      = "while"
print       = "print"
printLinea  = "println"
breakk      = "break"
continuee   = "continue"
iff         = "if"
elsee       = "else"
doo         = "do"
forr        = "for"
switchh     = "switch"
casee       = "case"
defaultt    = "default"
read_file   = "read_file"
write_file  = "write_file"
grafica     = "graph"
acorchete   = "["
ccorchete   = "]"
neww        = "new"
publico     = "public"
protegido   = "protected"
privado     = "private"
estatico    = "static"
finall      = "final"
nulo        = "null"
//------------------------------------------------------------------------
importa     = "import"
clase       = "class"
extender    = "extends"
mainn       = "main"
voidd       = "voidd"
abstracto   = "abstract"
retorno     = "return"
//------------------------------------------------------------------------




imprimir    = "imprimir"
cadena      = "cadena"  //esta es la que retorno pero no llevaria la cadena reconocida


id          = [a-zA-ZñÑ"_"]+([a-zA-ZñÑ"_"]*|[0-9]*)*
decimal     = ([0-9]+"."[0-9]+|"-"[0-9]+"."[0-9]+)
entero      = ([0-9]+|"-"[0-9]+)
charER      = ("'" ~"'")
//cadena      = ("\"" ~"\"")



espacio   = [\ \r\t\f\t]
enter   = [\ \n]

%state comentario1
%state comentariomultiple
%state cadenaEscapes
%state cadenaEscapes2

%%
<YYINITIAL> "//"                {yybegin(comentario1);}
<comentario1>  .                   {/* omitilo weeeeeee */}
<comentario1> [^"\n"]         {}
<comentario1> "\n"            {yybegin(YYINITIAL);}

<YYINITIAL> "/*"                       {yybegin(comentariomultiple);}
<comentariomultiple>  .                {/* omitilo weeeeeee */}
<comentariomultiple> "*/"              {yybegin(YYINITIAL);
                                        System.out.println("Comentario multiple: <<"+yytext()+">> Linea: "+yyline+" ,Columna: "+yycolumn);}
<comentariomultiple> [ \t\r\n\f]       {}


//---------------------------------------------------------------------------------------------------
<YYINITIAL> "\""                {System.out.println("\"");yybegin(cadenaEscapes); paraCadena ="";}
<cadenaEscapes> "\\"            {System.out.println("\\");yybegin(cadenaEscapes2);}
<cadenaEscapes> "\""            {System.out.println("\" finalfinalfinal"); System.out.println(paraCadena); yybegin(YYINITIAL); return new Symbol(sym.cadena, yyline, yycolumn, paraCadena);}
<cadenaEscapes> .               {paraCadena += yytext();}

//el yytext() llevaria la cadena si es solo una er

<cadenaEscapes2>{
    "'"                         {System.out.println("'"); paraCadena += "'"; yybegin(cadenaEscapes);}
    "\""                        {System.out.println("\""); paraCadena += "\""; yybegin(cadenaEscapes);}    
    "?"                         {System.out.println("?"); paraCadena += "?"; yybegin(cadenaEscapes);}
    "\\"                        {System.out.println("\\"); paraCadena += "\\" ; yybegin(cadenaEscapes);}
    "0"                         {System.out.println("0"); paraCadena += '\0'; yybegin(cadenaEscapes);}
    "a"                         {System.out.println("a");  yybegin(cadenaEscapes);}                                 //paraCadena += "\a";
    "b"                         {System.out.println("b"); paraCadena += "\b"; yybegin(cadenaEscapes);}
    "f"                         {System.out.println("f"); paraCadena += "\f"; yybegin(cadenaEscapes);}
    "n"                         {System.out.println("n"); paraCadena += "\n"; yybegin(cadenaEscapes);}
    "r"                         {System.out.println("r"); paraCadena += "\r"; yybegin(cadenaEscapes);}
    "t"                         {System.out.println("t"); paraCadena += "\t"; yybegin(cadenaEscapes);}
    "v"                         {System.out.println("v");  yybegin(cadenaEscapes);}                                 //paraCadena += "\v";
    "nnn"                       {System.out.println("nnn"); paraCadena += "\nnn"; yybegin(cadenaEscapes);}
    "Xnn"                       {System.out.println("Xnn");  yytext(); yybegin(cadenaEscapes);}                     //paraCadena += "\Xnn";
    "u"                         {System.out.println("u");  yytext(); yybegin(cadenaEscapes);}                       //paraCadena += "\u" ;
    "U"                         {System.out.println("U"); yytext(); yybegin(cadenaEscapes);}    //paraCadena += "\U" ;
}
//---------------------------------------------------------------------------------------------------

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

<YYINITIAL> {printLinea}       {  System.out.println("Reconocido: <<"+yytext()+">>, printLinea");
                                return new Symbol(sym.printLinea, yyline, yycolumn, yytext()); } 


<YYINITIAL> {breakk}       {  System.out.println("Reconocido: <<"+yytext()+">>, breakk");
                                return new Symbol(sym.breakk, yyline, yycolumn, yytext()); } 

<YYINITIAL> {continuee}       {  System.out.println("Reconocido: <<"+yytext()+">>, continuee");
                                return new Symbol(sym.continuee, yyline, yycolumn, yytext()); } 

<YYINITIAL> {iff}       {  System.out.println("Reconocido: <<"+yytext()+">>, iff");
                                return new Symbol(sym.iff, yyline, yycolumn, yytext()); }

<YYINITIAL> {elsee}       {  System.out.println("Reconocido: <<"+yytext()+">>, elsee");
                                return new Symbol(sym.elsee, yyline, yycolumn, yytext()); } 

<YYINITIAL> {doo}       {  System.out.println("Reconocido: <<"+yytext()+">>, doo");
                                return new Symbol(sym.doo, yyline, yycolumn, yytext()); } 

<YYINITIAL> {forr}       {  System.out.println("Reconocido: <<"+yytext()+">>, forr");
                                return new Symbol(sym.forr, yyline, yycolumn, yytext()); } 

<YYINITIAL> {switchh}       {  System.out.println("Reconocido: <<"+yytext()+">>, switchh");
                                return new Symbol(sym.switchh, yyline, yycolumn, yytext()); } 

<YYINITIAL> {casee}       {  System.out.println("Reconocido: <<"+yytext()+">>, casee");
                                return new Symbol(sym.casee, yyline, yycolumn, yytext()); } 

<YYINITIAL> {defaultt}       {  System.out.println("Reconocido: <<"+yytext()+">>, defaultt");
                                return new Symbol(sym.defaultt, yyline, yycolumn, yytext()); } 

<YYINITIAL> {read_file}       {  System.out.println("Reconocido: <<"+yytext()+">>, read_file");
                                return new Symbol(sym.read_file, yyline, yycolumn, yytext()); } 

<YYINITIAL> {write_file}       {  System.out.println("Reconocido: <<"+yytext()+">>, write_file");
                                return new Symbol(sym.write_file, yyline, yycolumn, yytext()); } 

<YYINITIAL> {coma}       {  System.out.println("Reconocido: <<"+yytext()+">>, coma");
                                return new Symbol(sym.coma, yyline, yycolumn, yytext()); } 

<YYINITIAL> {grafica}       {  System.out.println("Reconocido: <<"+yytext()+">>, grafica");
                                return new Symbol(sym.grafica, yyline, yycolumn, yytext()); } 

<YYINITIAL> {acorchete}       {  System.out.println("Reconocido: <<"+yytext()+">>, acorchete");
                                return new Symbol(sym.acorchete, yyline, yycolumn, yytext()); }

<YYINITIAL> {ccorchete}       {  System.out.println("Reconocido: <<"+yytext()+">>, ccorchete");
                                return new Symbol(sym.ccorchete, yyline, yycolumn, yytext()); }

<YYINITIAL> {neww}       {  System.out.println("Reconocido: <<"+yytext()+">>, neww");
                                return new Symbol(sym.neww, yyline, yycolumn, yytext()); }



<YYINITIAL> {publico}       {  System.out.println("Reconocido: <<"+yytext()+">>, publico");
                                return new Symbol(sym.publico, yyline, yycolumn, yytext()); }

<YYINITIAL> {protegido}       {  System.out.println("Reconocido: <<"+yytext()+">>, protegido");
                                return new Symbol(sym.protegido, yyline, yycolumn, yytext()); }

<YYINITIAL> {privado}       {  System.out.println("Reconocido: <<"+yytext()+">>, privado");
                                return new Symbol(sym.privado, yyline, yycolumn, yytext()); }

<YYINITIAL> {estatico}       {  System.out.println("Reconocido: <<"+yytext()+">>, estatico");
                                return new Symbol(sym.estatico, yyline, yycolumn, yytext()); }

<YYINITIAL> {finall}       {  System.out.println("Reconocido: <<"+yytext()+">>, finall");
                                return new Symbol(sym.finall, yyline, yycolumn, yytext()); }

<YYINITIAL> {nulo}       {  System.out.println("Reconocido: <<"+yytext()+">>, nulo");
                                return new Symbol(sym.nulo, yyline, yycolumn, yytext()); }



<YYINITIAL> {imprimir}       {  System.out.println("Reconocido: <<"+yytext()+">>, imprimir");
                                return new Symbol(sym.imprimir, yyline, yycolumn, yytext()); } 

 

<YYINITIAL> {importa}       {  System.out.println("Reconocido: <<"+yytext()+">>, importa");
                                return new Symbol(sym.importa, yyline, yycolumn, yytext()); } 

<YYINITIAL> {clase}       {  System.out.println("Reconocido: <<"+yytext()+">>, clase");
                                return new Symbol(sym.clase, yyline, yycolumn, yytext()); } 

<YYINITIAL> {extender}       {  System.out.println("Reconocido: <<"+yytext()+">>, extender");
                                return new Symbol(sym.extender, yyline, yycolumn, yytext()); } 

<YYINITIAL> {mainn}       {  System.out.println("Reconocido: <<"+yytext()+">>, mainn");
                                return new Symbol(sym.mainn, yyline, yycolumn, yytext()); } 

<YYINITIAL> {voidd}       {  System.out.println("Reconocido: <<"+yytext()+">>, voidd");
                                return new Symbol(sym.voidd, yyline, yycolumn, yytext()); } 

<YYINITIAL> {abstracto}       {  System.out.println("Reconocido: <<"+yytext()+">>, abstracto");
                                return new Symbol(sym.abstracto, yyline, yycolumn, yytext()); } 

<YYINITIAL> {retorno}       {  System.out.println("Reconocido: <<"+yytext()+">>, retorno");
                                return new Symbol(sym.retorno, yyline, yycolumn, yytext()); } 


<YYINITIAL> {id}       {  System.out.println("Reconocido: <<"+yytext()+">>, id");
                                return new Symbol(sym.id, yyline, yycolumn, yytext()); }

<YYINITIAL> {decimal}       {  System.out.println("Reconocido: <<"+yytext()+">>, decimal");
                                return new Symbol(sym.decimal, yyline, yycolumn, yytext()); } 

<YYINITIAL> {entero}       {  System.out.println("Reconocido: <<"+yytext()+">>, entero");
                                return new Symbol(sym.entero, yyline, yycolumn, yytext()); } 

<YYINITIAL> {charER}        { System.out.println("Reconocido: <<"+yytext()+">>, char");
                                return new Symbol(sym.charER, yyline, yycolumn, yytext()); } 

/*<YYINITIAL> {cadena}        { System.out.println("Reconocido: <<"+yytext()+">>, cadena");
                                return new Symbol(sym.cadena, yyline, yycolumn, yytext()); }*/


<YYINITIAL> {espacio}      {  } 

<YYINITIAL> {enter}       { } 

<YYINITIAL>. {
        String errorLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+yycolumn;
        System.err.println(errorLex);
        System.out.println(errorLex);
}


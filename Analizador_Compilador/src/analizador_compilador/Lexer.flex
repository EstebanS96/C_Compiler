package analizador_compilador;
import java_cup.runtime.Symbol;


%% 
%class Lexer
%line
%full
%cup
%column
%ignorecase
%unicode
%{
    private Lista_Tokens lista_Tokens = new Lista_Tokens();
    
    public Lista_Tokens getLista_Tokens(){
        return this.lista_Tokens;
    }

    public void crearToken(String tipo, String lexema, int linea, int columna){
        Token actual = new Token(tipo,lexema,linea,columna);
        revisarToken(actual); 
    }
    
    public void revisarToken(Token token){
        
        if (lista_Tokens.estaToken(token.getLexema())) {
            if (lista_Tokens.estaLineaToken(token.getLinea()+1, token.getLexema())) {
                lista_Tokens.aumentarCantidadLinea(token.getLinea()+1, token.getLexema());
            } else {
                lista_Tokens.crearNuevaLinea(token.getLinea()+1, token.getLexema());
            }
        } else {
            lista_Tokens.agregarNuevoToken(token);
            lista_Tokens.crearNuevaLinea(token.getLinea()+1, token.getLexema());
        }
    }

%}

%eofval{
  return new Symbol(sym.EOF);
%eofval}


/* Inicio de las expresiones regulares */

Letra=[A-Za-z]
Digito =[0-9]
Espacios = [ ,\t\r\n]
comentarioBloque = \/\*([^]*|{Espacios})\*\/
comentarioLinea = \/\/.*
comentario = {comentarioBloque}|{comentarioLinea}

letraE = [e|E]
digitoO = [0|1|2-7]
letraHexa = [A-Fa-f]

UnderS = ["_"]
Char = "'" {Letra} "'"
Charn = "'" "\\" {Digito}+ "'"

cientifico = ("."{Digito}+|{Digito}+"."{Digito}+|{Digito}+) {letraE} ("-"{Digito}+|{Digito}+)
deci = ({Digito}*"."{Digito}+)
Hexa = (0x|0X)({letraHexa}|{Digito})+
Octal = o {digitoO}+
entero = {Digito}+
String = "\"" ({Digito}|{Letra}|{Espacios}|{UnderS}|"$"|"."|"\\"|"{"|"}"|"("|")"|"["|"]"|";"|":"|"\,"|"?"|"¡"|"!"|"-"|"*"|"+"|"/"|"<"|">"|"="|"%"|"&"|"^"|"¿"|"#"|"|"|"'")* "\""

ErrorI = {Digito} ({Letra}*|{Digito}*|{UnderS})*
Identificador = ({Letra}|{UnderS})({Letra}*|{Digito}*|{UnderS}*)*


/* Fin de las expresiones regulares */




/* Inicio de la clasificacion de las expresiones */
%%
"\," {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.COMA,yycolumn,yyline + 1,yytext());}


{Espacios} {/*Ignore*/}
{comentario} {/*Ignore*/}

"write" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.WRITE,yycolumn,yyline + 1,yytext());}
"read" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.READ,yycolumn,yyline + 1,yytext());}
"auto" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"break" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.BREAK,yycolumn,yyline + 1,yytext());}
"case" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.CASE,yycolumn,yyline + 1,yytext());}
"char" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.CHAR,yycolumn,yyline + 1,yytext());}
"const" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.CONST,yycolumn,yyline + 1,yytext());}
"continue" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.CONTINUE,yycolumn,yyline + 1,yytext());}
"default" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.DEFAULT,yycolumn,yyline + 1,yytext());}
"do" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.DO,yycolumn,yyline + 1,yytext());}
"else" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.ELSE,yycolumn,yyline + 1,yytext());}
"enum" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"extern" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"float" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"for" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.FOR,yycolumn,yyline + 1,yytext());}
"goto" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"if" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.IF,yycolumn,yyline + 1,yytext());}
"int" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.INT,yycolumn,yyline + 1,yytext());}
"long" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.LONG,yycolumn,yyline + 1,yytext());}
"register" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"return" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.RETURN,yycolumn,yyline + 1,yytext());}
"short" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.SHORT,yycolumn,yyline + 1,yytext());}
"signed" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"sizeof" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"static" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"struct" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"switch" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.SWITCH,yycolumn,yyline + 1,yytext());}
"typedef" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"union" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"unsigned" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"void" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.VOID,yycolumn,yyline + 1,yytext());}
"volatile" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn);}
"while" {crearToken("Palabra Reservada",yytext(),yyline + 1,yycolumn); return new Symbol(sym.WHILE,yycolumn,yyline + 1,yytext());}

";" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.PCOMA,yycolumn,yyline + 1,yytext());}
"++" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MASMAS,yycolumn,yyline + 1,yytext());}
"--" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MENMEN,yycolumn,yyline + 1,yytext());}
"==" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.IGIG,yycolumn,yyline + 1,yytext());}
">=" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MENOIG,yycolumn,yyline + 1,yytext());}
">" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MENO,yycolumn,yyline + 1,yytext());}
"?" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"<=" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MAYIG,yycolumn,yyline + 1,yytext());}
"<" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MAY,yycolumn,yyline + 1,yytext());}
"!=" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.DIF,yycolumn,yyline + 1,yytext());}
"||" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.OR,yycolumn,yyline + 1,yytext());}
"&&" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.AND,yycolumn,yyline + 1,yytext());}
"!" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.NOT,yycolumn,yyline + 1,yytext());}
"=" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.IG,yycolumn,yyline + 1,yytext());}
"+" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MAS,yycolumn,yyline + 1,yytext());}
"-" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MEN,yycolumn,yyline + 1,yytext());}
"*" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MUL,yycolumn,yyline + 1,yytext());}
"/" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.DIV,yycolumn,yyline + 1,yytext());}
"%" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MOD,yycolumn,yyline + 1,yytext());}
"(" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.PARI,yycolumn,yyline + 1,yytext());}
")" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.PARD,yycolumn,yyline + 1,yytext());}
"[" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"]" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"{" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.LLAI,yycolumn,yyline + 1,yytext());}
"}" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.LLAD,yycolumn,yyline + 1,yytext());}
":" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.DP,yycolumn,yyline + 1,yytext());}
"." {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"+=" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MASIG,yycolumn,yyline + 1,yytext());}
"-=" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MENIG,yycolumn,yyline + 1,yytext());}
"*=" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.MULIG,yycolumn,yyline + 1,yytext());}
"/=" {crearToken("Operador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.DIVIG,yycolumn,yyline + 1,yytext());}
"&" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"^" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"|" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
">>" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"<<" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"~" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"%=" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"&=" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"^=" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"|=" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"<<=" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
">>=" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"->" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}
"#" {crearToken("Operador",yytext(),yyline + 1,yycolumn);}


{cientifico} {crearToken("Literal",yytext(),yyline + 1,yycolumn); return new Symbol(sym.CIENTIFICO,yycolumn,yyline + 1,yytext());}
{deci} {crearToken("Literal",yytext(),yyline + 1,yycolumn); return new Symbol(sym.DECIMAL,yycolumn,yyline + 1,yytext());}
{Hexa} {crearToken("Literal",yytext(),yyline + 1,yycolumn); return new Symbol(sym.HEXA,yycolumn,yyline + 1,yytext());}
{Octal} {crearToken("Literal",yytext(),yyline + 1,yycolumn); return new Symbol(sym.OCTAL,yycolumn,yyline + 1,yytext());}
{entero} {crearToken("Literal",yytext(),yyline + 1,yycolumn); return new Symbol(sym.ENTERO,yycolumn,yyline + 1,yytext());}
{Char} {crearToken("Literal",yytext(),yyline + 1,yycolumn); return new Symbol(sym.CARACTER,yycolumn,yyline + 1,yytext());}
{Charn} {crearToken("Literal",yytext(),yyline + 1,yycolumn); return new Symbol(sym.CARACTERN,yycolumn,yyline + 1,yytext());}
{Identificador} {crearToken("Identificador",yytext(),yyline + 1,yycolumn); return new Symbol(sym.IDENTIFICADOR,yycolumn,yyline + 1,yytext());}
{String} {crearToken("Literal",yytext(),yyline + 1,yycolumn); return new Symbol(sym.STRING,yycolumn,yyline + 1,yytext());}
{ErrorI} {crearToken("Error",yytext(),yyline + 1,yycolumn);}
. {crearToken("Error",yytext(),yyline + 1,yycolumn);}


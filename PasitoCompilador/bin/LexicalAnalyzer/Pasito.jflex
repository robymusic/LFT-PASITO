package LexicalAnalyzer; // pacote ao qual pertencerá a classe gerada pelo jflex

import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.ComplexSymbolFactory;
import java.io.IOException;
import java_cup.runtime.Symbol;
import SyntacticAnalyzer.sym;
import java.io.FileInputStream;
import java.util.regex.Pattern;

%%

// nome da classe a ser gerada pelo jflex
%class LexicalAnalyzer 

// implementa a classe sym gerada pelo cup
%implements sym 
// privacidade da classe a ser gerada
%public 
// codificação do arquivo
%unicode 
// habilita uso de yyline
%line  
// habilita uso de yycolumn
%column 
// habilita integração com o cup
%cup 
%char
%state ST_STRING
%state CODESEG
//define variaveis ?

/*
* yyline: linha analisada no momento atual pelo analisador léxico (lembre do automato) (começa na linha 0)
* yycolumn: coluna analisada no momento atual pelo analisador léxico (lembre do automato) (começa na coluna 0)
* yychar: caractere analisado no momento atual pelo analisador léxico (lembre do automato)
* yylenght(): retorna comprimento da palavra de entrada no momento atual do analisador léxico (lembre do automato)
* yytext(): palavra de entrada no momento atual do analisador léxico (lembre do automato)
*/

// início do código Java
%{
	
    private ComplexSymbolFactory symbolFactory;
    public StringBuffer string = new StringBuffer();

    /*
    * Construtor utilizado na chamada do analisador léxico pelo CUP
    * @param sf instância da fábrica de símbolos implementada pelo CUP que usaremos
    * @param is instância do arquivo de entrada em Pasito que será analisado
    */
    public LexicalAnalyzer(ComplexSymbolFactory sf, FileInputStream is) {
        this(is);
        symbolFactory = sf;
    }
    public LexicalAnalyzer(ComplexSymbolFactory sf, java.io.Reader reader) {
        this(reader);
        symbolFactory = sf;
    }
	
	 public Symbol ultimo;
	 public String tok;
    /*
    * Retorna de sÃ­mbolos identificados pelo analisador lexico
    * O simbolo retornado Ã© um objeto da classe ComplexSymbolFactory implementada pelo CUP
    * Tambem Ã© retornada a localizaÃ§Ã£o do sÃ­mbolo no arquivo, Ãºtil para fins de feedback ao usuÃ¡rio sobre a  posiÃ§Ã£o do erro (caso haja)
    * Voces provavelmente vao precisar sobrecarregar essa funcao para cobrir todos os tipos de simbolo q vcs precisam retornar
    */
    public Symbol symbol(String nome, int code) {
    	
    	System.out.println("<" + nome + ", " + yytext() + "> (" + yyline + " - " + yycolumn + ")");
    	
        ultimo = symbolFactory.newSymbol(nome, code,
                            new Location(yyline+1, yycolumn+1, yychar),
                            new Location(yyline+1, yycolumn+yylength(), yychar+yylength()));
                                  
       tok = yytext();
       return ultimo;
    }
	
	public Symbol symbol(String nome, int code, Object val) {
    	
    	System.out.println("<" + nome + ", " + yytext() + "> (" + yyline + " - " + yycolumn + ")");
    	
		ultimo = symbolFactory.newSymbol(nome, code,
                            new Location(yyline+1, yycolumn+1, yychar),
                            new Location(yyline+1, yycolumn+yylength(), yychar+yylength()));                            
        
	    tok = yytext();
	    return ultimo;
    }
    
    	public Symbol symbol(String nome, int code, String val) {
    	
    	System.out.println("<" + nome + ", " + val + "> (" + yyline + " - " + yycolumn + ")");
    	
        ultimo = symbolFactory.newSymbol(nome, code,
                            new Location(yyline+1, yycolumn+1, yychar),
                            new Location(yyline+1, yycolumn+yylength(), yychar+yylength()));
                                    
		tok = yytext();
		return ultimo;
	}

    /*
    * Emite um feedback no console com mensagem de erro, posição e caractere
    */
    protected void emit_warning(String message){
        System.out.println("lexicalAnalyzer warning: " + message + " at : 2 "+
                (yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
    
    
%}
// fim do código Java (esse código será criado no arquivo exatamente da forma escrita acima)

// início das expressões regulares que vão definir a classe de palavras identificadas (lembrem do automato)

/* Básico */
Newline = \n|\r|\n\r // fim de linha // era = LineTerminator
Whitespace = [ \t\f] | {Newline}  // espaco em branco era =  WhiteSpace
Number = [0-9]+ //era = Digit

/* Comentarios */
Comment = {TraditionalComment} | {EndOfLineComment} // era: Comment = {LineComment}|{BlockComment}
EndOfLineComment = "//"[^\r\n]*{Newline} // era: LineComment = ("//")++{LineTerminator} //Comentario Simples
TraditionalComment = "/*" {CommentContent} \*+ "/" 
CommentContent = ( [^*] | \*+[^*/] )* // BlockComment = ("/**") //+(?)+("**/") // Bloco de comentarios


// FAZER EXPRESSOES REGULARES:

Ignore = {Whitespace}|{Comment}// tudo isso sera ignorado
		// removi: Letter = [a-z|A-Z]|_ // letras


ident = ([:jletter:] | "_" ) ([:jletterdigit:] | [:jletter:] | "_" )* // era: Id = {Letter}+({Letter}|{Digit})*

		// removi Caracter = (Letter|Digit)*

//liteais
Int_Number = 0|[1-9]|[1-9]+{Number}+
Float_Number = {Number}+"."{Number}* | {Number}* "." {Number}+

Int32 = "Int32";
Float64= "Float64";

True = "true"
False = "false"
Boolean = {True} | {False}

			
OctalDigit = [0-7]
Octal = 0+ [1-3]? {OctalDigit} {1,15}

HexDigit = [0-9a-fA-F]
Hexa = 0 [xX] 0* {HexDigit} {1,8}


/* Operadores aritmÃ©ticos */
Plus = "+" // operador de soma
Minus = "-" // operador de subtraÃ§Ã£o
Times =  "*" // operador de multiplicacao (Star)
Div = "/" // operador de divisao (Slash)


/* Operadores lÃ³gicos */ 
And = "&&" // operador lÃ³gico And
Lt = "<" // operador logico menor que
Eq = "==" // operador logico igual a
Not = "!" // operador logico negação

Op_Logic = {And} | Lt | {Eq} | Not

/* Atribuição */
Assign = "=" // Var ("conjunto de variaveis") "type" := "expressão"
DAssign = ":=" // ("conjunto de variaveis") = "expressão"
//Assign = {Assign} | {DAssign} // Operador de atribuição 

/*delimitador*/
Limiter = OpenLimiter | CloseLimiter |SimpleLimiter

OpenLimiter = LPAR 
			|LSBRACK
			|LBRACK

CloseLimiter = RPAR 
			|RSBRACK
			|RBRACK
			
SimpleLimiter = Dot
			|Comma
			|Semicolon
			|DotDotDot
			|Colon


/* SÃ­mbolos */
Dot = "." // ponto
DotDotDot = "..." // ponto
Comma = "," // vÃ­rgula
Semicolon = ";" // ponto e virgula
LPAR =  "(" // parantese abrindo
RPAR =  ")" // parentese fechando
LSBRACK =  "[" // chaves abrindo
RSBRACK =  "]" // chaves fechando
LBRACK =  "{" // colchete abrindo
RBRACK =  "}" // colchete fechando
Colon = ":" // dois pontos




/* Tipos primitivos */
Boolean = "boolean" // boleano

/* Palavras reservadas */
Default = "default" //"default" // palavra reservada default //"(d|D)(e|E)(f|F)(a|A)(u|U)(l|L)(t|T)" case insensive
Func = "func" // palavra reservada func
Case = "case"
Else = "else"
Const = "const"
For = "for"
Struct = "struct"
Switch = "switch"
Fallthrough = "fallthrough"
Interface = "interface"
Type = "type"
If = "if"
Var = "var"
Return = "return"
Range = "range"

%eofval{
    return symbolFactory.newSymbol("EOF",sym.EOF);
%eofval}


// fim das expressÃµes regulares

%%

// inÃ­cio das aÃ§Ãµes de retorno
<YYINITIAL> {  
    //{Whitespace}
    {Newline}					{ 	String num = "[-]?\\d*[.]?\\d+";
    								String id = "\\b[_a-zA-Z][_a-zA-Z0-9]*\\b";
    								//System.out.println("Ultimo: "+tok);
    								if ( Pattern.matches(num, tok )){ return symbol("SEMICOLON",SEMICOLON);}
    								if ( Pattern.matches(id, tok )){ return symbol("SEMICOLON",SEMICOLON);}
    								if ( tok.equals("return")){ return symbol("SEMICOLON",SEMICOLON);}
    								if ( tok.equals("}")){ return symbol("SEMICOLON",SEMICOLON);}
    								if ( tok.equals("]")){ return symbol("SEMICOLON",SEMICOLON);}
    								if ( tok.equals(")")){ return symbol("SEMICOLON",SEMICOLON);}
    								if ( tok.equals("fallthrough")){ return symbol("SEMICOLON",SEMICOLON);}
    															
    							} 
    							       											
    						
	{Ignore}                    { }
    {Plus}                      { return symbol("PLUS", PLUS); }
    {Minus}			            { return symbol("MINUS", MINUS); }
    {Times} 					{ return symbol("TIMES", TIMES); }
    {Div}						{ return symbol("DIV", DIV); }
	
    //{Assign}					{ return symbol("ASSIGN", ASSIGN); }
	{Assign}					{ return symbol("ASSIGN", ASSIGN); }
	{DAssign}   				{ return symbol("DASSIGN", DASSIGN, (yytext())); }
	
	// {Op_Logic}					{ return symbol("OP_LOGIC", OP_LOGIC); }
    {And}				       	{ return symbol("AND", AND); }
    {Lt}     					{ return symbol("LT", LT); }	
    {Eq}						{ return symbol("EQ", EQ); }	
    {Not}		 				{ return symbol("NOT", NOT); }

    {Dot}			            { return symbol("DOT", DOT); }
    {DotDotDot}		            { return symbol("DOTDOTDOT", DOTDOTDOT); }
    {Comma}		        	    { return symbol("COMMA", COMMA); }
    {Semicolon}                 { return symbol("SEMICOLON", SEMICOLON, (yytext()) ); }
    {LPAR} 						{ return symbol("LPAR", LPAR); }
	{RPAR} 						{ return symbol("RPAR", RPAR); }
    {LSBRACK}					{ return symbol("LSBRACK", LSBRACK); }
    {RSBRACK} 					{ return symbol("RSBRACK", RSBRACK); }
    {LBRACK}					{ return symbol("LBRACK", LBRACK); }
    {RBRACK} 					{ return symbol("RBRACK", RBRACK); }
    {Range}						{ return symbol("RANGE", RANGE); }
    {Colon}						{ return symbol("COLON", COLON); }
    
    {Default}					{ return symbol("DEFAULT", DEFAULT); }
    {Func}				      	{ return symbol("FUNC", FUNC, (yytext()) ); }
    {For}                       { return symbol("FOR", FOR); }
    {If}                        { return symbol("IF", IF); }
    {Else}						{ return symbol("ELSE", ELSE); }
    {Struct}                    { return symbol("STRUCT", STRUCT); }
    {Switch}                    { return symbol("SWITCH", SWITCH); }
    {Fallthrough}               { return symbol("FALLTHROUGH", FALLTHROUGH); }
    {Interface}                 { return symbol("INTERFACE", INTERFACE); }
    {Type}                      { return symbol("TYPE", TYPE); }
    {If}                        { return symbol("IF", IF); }
    {Var}                       { return symbol("VAR", VAR, (yytext())); }
    {Const}						{ return symbol("CONST", CONST); }
    {Case}                      { return symbol("CASE",CASE); }
    {Return}					{ return symbol("RETURN", RETURN, (yytext())); }
   	
    {Float_Number}              { return symbol("FLOAT_NUMBER", FLOAT_NUMBER , new Float(yytext())); }   
    {Int_Number}                { return symbol("INT_NUMBER", INT_NUMBER , new Integer(Integer.parseInt(yytext()))); }
	{Int32} 					{ return symbol("INT32", INT32, (yytext()) ); }
	{Float64}					{ return symbol("FLOAT64", FLOAT64, (yytext()) ); }	
	
	{Octal}						{ return symbol ("OCTAL",NUMBER, (yytext())); }
	{Hexa}						{ return symbol ("HEXA",NUMBER, (yytext())); }
	{Boolean}                   { return symbol("BOOLEAN", BOOLEAN); }
	{True}						{ return symbol("BOOLEAN", TRUE, (yytext())); }
	{False}						{ return symbol("BOOLEAN", FALSE, (yytext())); }
	{ident}						{ return symbol("ID", ID, (yytext())); }
								
  // Retorna o "ponto e virgula" nos casos onde não são colocados  
  	    
   
    

// aviso de erro
	.|\n						{ emit_warning("Caracter não reconhecido '" + yytext() + "' -- ignorado"); }
}
<ST_STRING>{   
	[^\n\r\"\\]+                { string.append( yytext() ); }
	\\t                         { string.append('\t'); }// caracteres escapados
	\\n                         { string.append('\n'); }
	\\r                         { string.append('\r'); }
	\\\"                        { string.append('\"'); }
	\\                          { string.append('\\'); }
}	
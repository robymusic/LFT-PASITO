package CompiladorPasito;

import java.text.Format;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Pattern;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;
public class Test {
  /*public static void Test() {
	  List<Integer> ls = new LinkedList<>();
	  ls.add(3);
	  Object o = new LinkedList<>();
	  o.hashCode();
  }*/
public Test() {	
	
}
public static void main(String argv[]) {
	String a = "a2w5sd6545";
	if(a.contains("^[a-Z]"))System.out.println("Tem letras");
	else System.out.println("Apenas numeros");	
	String b = "A".intern();
	String c = "A".intern();
	
	System.out.println("B = "+b + "C = "+c);
SymbolFactory symbolFactory;	
	 ComplexSymbolFactory f = new ComplexSymbolFactory(); // cria instância do ComplexSymbolFactory
	 symbolFactory = f;	
	//Symbol s = f.newSymbol("", 2);
	String s1 = "";
	String s2 = "_";
	String s3 = "0.";
	String s4 = "0";
	String s5 = ".0";
	String s6 = "0.0";
	// [-]? com ou sem o sinal de subtração
	// \d* zero ou mais dígitos
	// [.]? com ou sem ponto
	// \d+ um ou mais números
	String p = "\\b[_a-zA-Z][_a-zA-Z0-9]*\\b";
	if(!s1.equals(null)) 
	
	// compara cada String com o padrão "p"
	System.out.println( Pattern.matches( p, s1 ) );
	System.out.println( Pattern.matches( p, s2 ) );
	System.out.println( Pattern.matches( p, s3 ) );
	System.out.println( Pattern.matches( p, s4 ) );
	System.out.println( Pattern.matches( p, s5 ) );
	System.out.println( Pattern.matches( p, s6 ) );
	
	LinkedList<BesteBola> bb = new LinkedList<>(); 
	LinkedList<BesteBola> td = new LinkedList<>(); 
	Iterator<BesteBola> it = td.iterator();
		while (it.hasNext()) {
		bb.add(it.next());  
		}
		String nom = "";
		
}}

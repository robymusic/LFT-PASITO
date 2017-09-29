package CompiladorPasito;

import SyntacticAnalyzer.Parser;
import java_cup.runtime.Symbol;
import pasito.ast.Program;
import pasito.ast.staticSemantics.Analyser;

class Driver {

	public static void main(String[] args) throws Exception {
		Parser parser = new Parser();
		parser.debug_parse();
		
		//x.intValue()
		//try {
		//Symbol p =  parser.parse();
		//Program prog = (Program) p.value;
		//PrettyPrint print = new PrettyPrint(prog);//prog);
		//Analyser an = new Analyser(...); Na semantica
		//p.acept(an);
		//}catch (Exception e) {}
		
	}
	
}
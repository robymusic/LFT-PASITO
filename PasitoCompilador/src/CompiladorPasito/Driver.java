package CompiladorPasito;

import SyntacticAnalyzer.Parser;

class Driver {

	public static void main(String[] args) throws Exception {
		Parser parser = new Parser();
		parser.debug_parse();
	}
	
}
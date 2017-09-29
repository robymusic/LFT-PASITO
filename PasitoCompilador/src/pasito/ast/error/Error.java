package pasito.ast.error;

public class Error {
	private String erro;
	static int qntErro = 0;
	
	public Error () {	}
	
	public void report(String e) {
		this.erro = e;
		this.qntErro++;
		System.out.println("ERRO : "+this.erro+" \nNº :"+this.qntErro);
	};	
}

package pasito.ast.staticSemantics.type;

/**
 * Created by Giovanny on 14/09/17.
 */

/* Underlying Types */

public abstract class Type {

	/**************
	 *  A implementa��o deste m�todo deve seguir a defini��o de type identity dada em
	 *   https://golang.org/ref/spec#Type_identity
	 */
	public abstract boolean equivalent(Type ty);

	
	/**********
	 * A implementa��o deste m�todo deve seguir a defini��o de assignability dada em
	 * https://golang.org/ref/spec#Assignability
	 */
	public abstract boolean assignableTo(Type ty);

}

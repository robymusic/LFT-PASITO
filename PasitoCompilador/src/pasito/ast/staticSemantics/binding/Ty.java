package pasito.ast.staticSemantics.binding;

import pasito.ast.staticSemantics.type.Type;

public class Ty implements Binding {
	public Type type;

	public Ty(Type type) {
		super();
		this.type = type;
	}	
}

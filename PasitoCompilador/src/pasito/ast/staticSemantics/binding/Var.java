package pasito.ast.staticSemantics.binding;

import pasito.ast.staticSemantics.type.Type;

public class Var implements Binding {
	public Type type;

	public Var(Type type) {
		super();
		this.type = type;
	}
}

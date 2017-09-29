package pasito.ast.staticSemantics.binding;

import pasito.ast.staticSemantics.type.Type;

public class Const implements Binding {
	public Object value;
	public Type type;
	
	public Const(Object value, Type type) {
		super();
		this.value = value;
		this.type = type;
	}
}

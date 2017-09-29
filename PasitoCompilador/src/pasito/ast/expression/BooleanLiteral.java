package pasito.ast.expression;

import pasito.ast.PasitoVisitor;

public class BooleanLiteral extends Expression{
	public Boolean value;

    public BooleanLiteral(Boolean value) {
        this.value = value;
    }
	@Override
	
    public Object accept(PasitoVisitor visitor) { return visitor.VisitBooleanLiteral(this); }

}

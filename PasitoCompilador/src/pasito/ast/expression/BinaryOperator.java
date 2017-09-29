package pasito.ast.expression;

import pasito.ast.PasitoVisitor;

/**
 * Created by ariel on 21/08/17.
 */
public enum BinaryOperator {
    AND, OR, TIMES, PLUS, MINUS, LT , /*LESS,*/ EQ, DIV;

    public Object accept(PasitoVisitor visitor) { return visitor.VisitBinaryOperator(this); }
}

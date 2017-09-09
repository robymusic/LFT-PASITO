package pasito.ast.declaration;

import java.util.LinkedList;

import pasito.ast.PasitoVisitor;
import pasito.ast.expression.Expression;
import pasito.ast.type.Type;
import pasito.ast.topLevelDecl.TopLevelDecl;

/**
 * Created by ariel on 20/08/17.
 */
public class ConstDecl extends Declaration{

    public String name;
    public Type type; // type can be null (exp??***)
    public Expression exp;

    public ConstDecl(String name, Type type, Expression exp) {
        this.name = name;
        this.type = type;
        this.exp = exp;
        
    }

    @Override
    public Object accept (PasitoVisitor visitor) { return visitor.VisitConstDecl(this); }
}

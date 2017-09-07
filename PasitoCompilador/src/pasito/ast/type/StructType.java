package pasito.ast.type;

import pasito.ast.PasitoVisitor;

import java.util.List;

/**
 * Created by ariel on 21/08/17.
 */
public class StructType extends Type {

    List<FieldDecl> fieldDecls;

    public StructType(List<FieldDecl> fieldDecls) {
        this.fieldDecls = fieldDecls;
    }


    @Override
    public Object accept(PasitoVisitor visitor) { return visitor.VisitStructType(this); }
}

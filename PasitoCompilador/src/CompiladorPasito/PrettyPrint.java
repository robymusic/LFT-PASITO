package CompiladorPasito;

import pasito.ast.*;
import pasito.ast.declaration.*;
import pasito.ast.element.*;
import pasito.ast.expression.*;
import pasito.ast.methodSpecOrInterfaceName.*;
import pasito.ast.signature.*;
import pasito.ast.statement.*;
import pasito.ast.topLevelDecl.*;
import pasito.ast.type.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by ariel on 27/08/17.
 */
public class PrettyPrint implements PasitoVisitor {

    private int indentLevel = 0;
    private StringBuilder result = new StringBuilder();

    public PrettyPrint (){}
    public PrettyPrint(Program program) {
        System.out.println(this.VisitProgram(program));
    }

    private void indent() { ++indentLevel; }
    private void unindent() { --indentLevel; }

    /**
     * Método auxiliar para impressão de listas de strings
     * @param list - lista de strings
     * @param parentheses - vai haver parenteses?
     * @return
     */
    private String printList(List<?> list, boolean parentheses) {
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (Object item : list.subList(1, list.size())) sb.append(", " + item);
        if (parentheses) {sb.insert(0, '('); sb.append(')');}
        return sb.toString();
    }

    /**
     * Método auxiliar para impressão de strings
     * @param str - string
     * @param indent - identação?
     * @return
     */
    private Object print (Object str, boolean indent) {
        String tabs = indent && indentLevel > 0 ?
                String.join("", Collections.nCopies(indentLevel, "   ")) : "";
        return tabs + str;
    }

    @Override
    public Object VisitProgram(Program program) {
        StringBuilder result = new StringBuilder();
            for (TopLevelDecl declaration: program.declarations) {
                result.append(print(declaration.accept(this) + ";", true));
            }
        return result.toString();
    }

    @Override
    public Object VisitDec(Dec dec) {
        return null;
    }

    @Override
    public Object VisitFunctionDecl(FunctionDecl functionDecl) {
        StringBuilder sb = new StringBuilder();

        // @TO-DO: CONFERIR!!
        sb.append("func " + functionDecl.name + " ");
        sb.append(functionDecl.sig.accept(this));
        sb.append(functionDecl.body.accept(this));

        return sb.toString();
    }

    @Override
    public Object VisitMethodDecl(MethodDecl methodDecl) {
        return null;
    }

    @Override
    public Object VisitSignature(Signature signature) {
        return null;
    }

    @Override
    public Object VisitConstDecl(ConstDecl constDecl) {
        return null;
    }

    @Override
    public Object VisitVarDecl(VarDecl varDecl) {
        return null;
    }

    @Override
    public Object VisitTypeDecl(TypeDecl typeDecl) {
        return null;
    }

    @Override
    public Object VisitFormalParameter(FormalParameter formalParameter) {
        return null;
    }

    @Override
    public Object VisitTypeName(TypeName typeName) {
        return null;
    }

    @Override
    public Object VisitArrayType(ArrayType arrayType) {
        return null;
    }

    @Override
    public Object VisitBaseType(PointerType pointerType) {
        return null;
    }

    @Override
    public Object VisitStructType(StructType structType) {
        return null;
    }

    @Override
    public Object VisitInterfaceType(InterfaceType interfaceType) {
        return null;
    }

    @Override
    public Object VisitSliceType(SliceType sliceType) {
        return null;
    }

    @Override
    public Object VisitFieldDecl(FieldDecl fieldDecl) {
        return null;
    }

    @Override
    public Object VisitMethodSpec(MethodSpec methodSpec) {
        return null;
    }

    @Override
    public Object VisitInterfaceName(InterfaceName interfaceName) {
        return null;
    }

    @Override
    public Object VisitUnaryExpression(UnaryExpression unaryExpression) {
        return null;
    }

    @Override
    public Object VisitBinaryExpression(BinaryExpression binaryExpression) {
        return null;
    }

    @Override
    public Object VisitIntLiteral(IntLiteral intLiteral) {
        return null;
    }

    @Override
    public Object VisitFloatLiteral(FloatLiteral floatLiteral) {
        return null;
    }

    @Override
    public Object VisitFunctionLiteral(FunctionLiteral functionLiteral) {
        return null;
    }

    @Override
    public Object VisitCompositLit(CompositeLit compositeLit) {
        return null;
    }

    @Override
    public Object VisitIdExpression(IdExpression idExpression) {
        return null;
    }

    @Override
    public Object VisitMethodExpression(MethodExpression methodExpression) {
        return null;
    }

    @Override
    public Object VisitSelectorExpression(SelectorExpression selectorExpression) {
        return null;
    }

    @Override
    public Object VisitIndexExpression(IndexExpression indexExpression) {
        return null;
    }

    @Override
    public Object VisitSliceExpression(SliceExpression sliceExpression) {
        return null;
    }

    @Override
    public Object VisitFullSliceExpression(FullSliceExpression fullSliceExpression) {
        return null;
    }

    @Override
    public Object VisitCallExpression(CallExpression callExpression) {
        return null;
    }

    @Override
    public Object VisitKeyedElement(KeyedElement keyedElement) {
        return null;
    }

    @Override
    public Object VisitExpressionElement(ExpressionElement expressionElement) {
        return null;
    }

    @Override
    public Object VisitLiteralElement(LiteralElement literalElement) {
        return null;
    }

    @Override
    public Object VisitDeclarationStm(DeclarationStm declarationStm) {
        return null;
    }

    @Override
    public Object VisitEmptyStmt(EmptyStmt emptyStmt) {
        return null;
    }

    @Override
    public Object VisitReturnStmt(ReturnStmt returnStmt) {
        return null;
    }

    @Override
    public Object VisitExpressionStmt(ExpressionStmt expressionStmt) {
        return null;
    }

    @Override
    public Object VisitAssignment(Assignment assignment) {
        StringBuilder sb = new StringBuilder();
        int sizeLeft = assignment.leftExps.size()-1;
        int sizeRight = assignment.rightExps.size()-1;

        // Pega todas as expressões da esquerda
        for (Expression exp : assignment.leftExps.subList(0, sizeLeft))
            sb.append((String) exp.accept(this) + ',');
        sb.append(assignment.leftExps.get(sizeLeft));

        // adiciona sinal =
        sb.append(" = ");

        // Pega todas as expressões da direita
        for (Expression exp : assignment.rightExps.subList(0, sizeRight))
            sb.append((String) exp.accept(this) + ',');
        sb.append(assignment.rightExps.get(sizeRight));

        return sb.toString();
    }

    @Override
    public Object VisitShortVarDecl(ShortVarDecl shortVarDecl) {
        return null;
    }

    @Override
    public Object VisitBlock(Block block) {
        StringBuilder result = new StringBuilder();

        indent();
        for (Statement stm : block.stmts) {
            if (stm instanceof EmptyStmt)
                continue; // ignora os stmts vazios
            result.append(stm.accept(this)+";"); // vocês podem melhorar isso para não imprimir ponto e vírgula depois de identificadores ;)
        }
        unindent();

        return result.toString();
    }

    @Override
    public Object VisitIfStmt(IfStmt ifStmt) {
        StringBuilder result = new StringBuilder();

        result.append("if ");
        if (ifStmt.initStmt != null)
            result.append(ifStmt.initStmt.accept(this) + " ");
        result.append(ifStmt.exp.accept(this));
        result.append(ifStmt.block.accept(this));

        return result.toString();
    }

    @Override
    public Object VisitIfElseStmt(IfElseStmt ifElseStmt) {
        return null;
    }

    @Override
    public Object VisitForStmt(ForStmt forStmt) {
        return null;
    }

    @Override
    public Object VisitForRange(ForRange forRange) {
        return null;
    }

    @Override
    public Object VisitBinaryOperator(BinaryOperator binaryOperator) {
        switch (binaryOperator.name()) {
            case "AND": return print(") AND (", false);
            case "OR": return print(") OR (", false);
            case "PLUS": return print(" + ", false);
            case "MINUS": return print(" - ", false);
            case "TIMES": return print(" * ", false);
            case "DIV": return print(" / ", false);
            case "LESS": return print(" < ", false);
            case "EQUAL": return print(" = ", false);
            default: return null;
        }
    }

    @Override
    public Object VisitUnaryOperator(UnaryOperator unaryOperator) {
        return null;
    }
}

package pasito.ast.staticSemantics;

import pasito.ast.PasitoVisitor;
import pasito.ast.Program;
import pasito.ast.declaration.ConstDecl;
import pasito.ast.declaration.TypeDecl;
import pasito.ast.declaration.VarDecl;
import pasito.ast.element.ExpressionElement;
import pasito.ast.element.KeyedElement;
import pasito.ast.element.LiteralElement;
import pasito.ast.expression.BinaryExpression;
import pasito.ast.expression.BinaryOperator;
import pasito.ast.expression.BooleanLiteral;
import pasito.ast.expression.CallExpression;
import pasito.ast.expression.CompositeLit;
import pasito.ast.expression.FloatLiteral;
import pasito.ast.expression.FullSliceExpression;
import pasito.ast.expression.FunctionLiteral;
import pasito.ast.expression.IdExpression;
import pasito.ast.expression.IndexExpression;
import pasito.ast.expression.IntLiteral;
import pasito.ast.expression.MethodExpression;
import pasito.ast.expression.SelectorExpression;
import pasito.ast.expression.SliceExpression;
import pasito.ast.expression.UnaryExpression;
import pasito.ast.expression.UnaryOperator;
import pasito.ast.methodSpecOrInterfaceName.InterfaceName;
import pasito.ast.methodSpecOrInterfaceName.MethodSpec;
import pasito.ast.signature.FormalParameter;
import pasito.ast.signature.Signature;
import pasito.ast.statement.Assignment;
import pasito.ast.statement.Block;
import pasito.ast.statement.DeclarationStm;
import pasito.ast.statement.EmptyStmt;
import pasito.ast.statement.ExpressionStmt;
import pasito.ast.statement.ForRange;
import pasito.ast.statement.ForStmt;
import pasito.ast.statement.IfElseStmt;
import pasito.ast.statement.IfStmt;
import pasito.ast.statement.ReturnStmt;
import pasito.ast.statement.ShortVarDecl;
import pasito.ast.topLevelDecl.Dec;
import pasito.ast.topLevelDecl.FunctionDecl;
import pasito.ast.topLevelDecl.MethodDecl;
import pasito.ast.topLevelDecl.TopLevelDecl;
import pasito.ast.type.ArrayType;
import pasito.ast.type.FieldDecl;
import pasito.ast.type.InterfaceType;
import pasito.ast.type.PointerType;
import pasito.ast.type.SliceType;
import pasito.ast.type.StructType;
import pasito.ast.type.TypeName;
import pasito.ast.staticEnvironment.AlreadyBoundException;
import pasito.ast.staticEnvironment.SymbolTable;
import pasito.ast.staticSemantics.binding.Binding;
import pasito.ast.staticSemantics.binding.Const;
import pasito.ast.staticSemantics.type.Type;
import pasito.ast.util.ErrorRegister;

public class Analyser implements PasitoVisitor {

	SymbolTable<Binding> env;
	ConstantExpressionEavaluator constEvaluator;
	
	public Analyser() {
		 env = new SymbolTable<>();
		 constEvaluator = new ConstantExpressionEavaluator(env);
		 /* inicializar env com bindings pr�definidos para int64, float64, boolean, ....
		  * 
 		 */
	}

	@Override
	public Object VisitProgram(Program program) {
		for (TopLevelDecl tpDec :  program.declarations )
			tpDec.accept(this);
		return null;
	}

	@Override
	public Object VisitDec(Dec dec) {
		dec.decl.accept(this);
		return null;
	}

	@Override
	public Object VisitFunctionDecl(FunctionDecl functionDecl) {
		// TODO Auto-generated method stub
		/*
		 * 1. criar o correspondente binding Fun e associar com o nome da fun��o no ambiente env
		 * 2. env.beginScope()
		 * 3. elaborar as declara��es correspondentes aos par�metros formais (i.e. visitar a signature)
		 * 4. checar o corpo da fun��o
		 * 5. env.endScope()
		 */
		return null;
		
	}

	@Override
	public Object VisitMethodDecl(MethodDecl methodDecl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitSignature(Signature signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitConstDecl(ConstDecl constDecl) {
		if (constDecl.exp == null) {
			ErrorRegister.report("error: ...");
			/* adicionar alguma entrada em env para recupera��o de erros */
			return null;
		} 
		
		Object value =  constDecl.exp.accept(constEvaluator);
		Type ty = (Type) constDecl.exp.accept(this);
		if (constDecl.type == null)
			try {
				env.put( constDecl.name, new Const(value, ty) );
			} catch (AlreadyBoundException e) {
				ErrorRegister.report("bla bla bla");
			}
		else {
		   Type declaredType = (Type) constDecl.type.accept(this);
		   try {
			env.put( constDecl.name, new Const(value, declaredType) );
		} catch (AlreadyBoundException e) {
			ErrorRegister.report("bla bla bla");
		}
		   if ( !ty.assignableTo(declaredType) )
			   ErrorRegister.report("...");
		}
		return null;
	}

	
	@Override
	public Object VisitVarDecl(VarDecl varDecl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitTypeDecl(TypeDecl typeDecl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitFormalParameter(FormalParameter formalParameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitTypeName(TypeName typeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitArrayType(ArrayType arrayType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitBaseType(PointerType pointerType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitStructType(StructType structType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitInterfaceType(InterfaceType interfaceType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitSliceType(SliceType sliceType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitFieldDecl(FieldDecl fieldDecl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitMethodSpec(MethodSpec methodSpec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitInterfaceName(InterfaceName interfaceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitUnaryExpression(UnaryExpression unaryExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitBinaryExpression(BinaryExpression binaryExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitIntLiteral(IntLiteral intLiteral) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitFloatLiteral(FloatLiteral floatLiteral) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitFunctionLiteral(FunctionLiteral functionLiteral) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitCompositLit(CompositeLit compositeLit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitIdExpression(IdExpression idExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitMethodExpression(MethodExpression methodExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitSelectorExpression(SelectorExpression selectorExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitIndexExpression(IndexExpression indexExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitSliceExpression(SliceExpression sliceExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitFullSliceExpression(FullSliceExpression fullSliceExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitCallExpression(CallExpression callExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitKeyedElement(KeyedElement keyedElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitExpressionElement(ExpressionElement expressionElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitLiteralElement(LiteralElement literalElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitDeclarationStm(DeclarationStm declarationStm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitEmptyStmt(EmptyStmt emptyStmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitReturnStmt(ReturnStmt returnStmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitExpressionStmt(ExpressionStmt expressionStmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitAssignment(Assignment assignment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitShortVarDecl(ShortVarDecl shortVarDecl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitBlock(Block block) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitIfStmt(IfStmt ifStmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitIfElseStmt(IfElseStmt ifElseStmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitForStmt(ForStmt forStmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitForRange(ForRange forRange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitBinaryOperator(BinaryOperator binaryOperator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitUnaryOperator(UnaryOperator unaryOperator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object VisitBooleanLiteral(BooleanLiteral booleanLiteral) {
		// TODO Auto-generated method stub
		return null;
	}

}

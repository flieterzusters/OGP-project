package asteroids.model.programs.parsing;
import asteroids.model.programs.Expression.*;

import java.util.List;
import asteroids.model.Ship;
import asteroids.model.programs.*;
import asteroids.model.*;
import asteroids.model.programs.Statement.*;
import asteroids.model.programs.Statement.actionStatement.*;
import asteroids.model.programs.Statement.standardStatement.*;
import asteroids.model.programs.Expression.booleanExpression.*;
import asteroids.model.programs.Expression.doubleExpression.*;


public class ProgramFactoryImplementation implements ProgramFactory<Expression, Statement, Type> {
	
	public Program getProgram() {
		return program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	private Program program;
	
	
	@Override
	public Expression createDoubleLiteral (int line, int column, double d)
	{
		return new DoubleLiteral(line, column, d);
	}
	
	@Override
	public Expression createBooleanLiteral (int line, int column, boolean b) {
		return new BooleanLiteral (line, column, b);
	}
	
	@Override
	public Expression createAnd(int line, int column, Expression e1, Expression e2) {
		return null;
	}
	
	@Override
	 public Expression createOr(int line, int column, Expression e1, Expression e2) {
		return null;
	}
	
	@Override
	public Expression createNot(int line, int column, Expression e) {
		return null;
	}
	
	@Override
	public Expression createNull (int line, int column)
	{
		return new EntityLiteral(line, column, null);
	}
	
	@Override
	public Expression createSelf (int line, int column)
	{
		return new EntityLiteral(line, column, getProgram().getShip());
	}
	
	@Override
	public Expression createGetX(int line, int column, Expression e) {
		return new GetX(line, column, e);
	}
	
	@Override
	public Expression createGetY(int line, int column, Expression e){
		return new GetY(line, column, e);
	}
	
	@Override
	public Expression createGetVX(int line, int column, Expression e) {
		return null;
	}
	
	@Override
	public Expression createGetVY(int line, int column, Expression e) {
		return null;
	}
	
	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		return new GetRadius(line, column, e);
	}
	
	@Override
	public Expression createVariable(int line, int column, String name) {
		return new Variable(line, column, name);
	}
	
	@Override
	public Expression createLessThan(int line, int column, Expression e1, Expression e2) {
		return new LessThan(line, column, e1, e2);
	}
	
	@Override
	public Expression createGreaterThan(int line, int column, Expression e1, Expression e2) {
		return new GreaterThan(line, column, e1, e2);
	}
	
	@Override
	public Expression createLessThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		return new LessThanOrEqualTo(line, column, e1, e2);
	}
	
	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		return new GreaterThanOrEqualTo(line, column, e1, e2);
	}
	
	@Override
	public Expression createEquality(int line, int column, Expression e1, Expression e2) {
		return new Equality(line, column, e1, e2);
	}
	
	@Override
	public Expression createInequality(int line, int column, Expression e1, Expression e2) {
		return new Inequality(line, column, e1, e2);
	}
	
	@Override
	public Expression createAdd(int line, int column, Expression e1, Expression e2) {
		return new Add(line, column, e1, e2);
	}
	
	@Override
	public Expression createSubtraction(int line, int column, Expression e1, Expression e2) {
		return new Substraction(line, column, e1, e2);
	}
	
	@Override
	public Expression createMul(int line, int column, Expression e1, Expression e2) {
		return new Multiplication(line, column, e1, e2);
	}
	
	@Override
	public Expression createDivision(int line, int column, Expression e1, Expression e2) {
		return new Division(line, column, e1, e2);
	}
	
	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		return new Sqrt(line, column, e);
	}
	
	@Override
	public Expression createGetDirection(int line, int column) {
		return new GetDir(line, column);
	}
	
	@Override
	public Expression createSin(int line, int column, Expression e) {
		return null;
	}
	
	@Override
	public Expression createCos(int line, int column, Expression e) {
		return null;
	}
	
	@Override
	public Statement createEnableThruster(int line, int column) {
		return new EnableThruster(line, column);
	}
	
	@Override
	public Statement createDisableThruster(int line, int column) {
		return new DisableThruster(line, column);
	}
	
	@Override
	public Statement createFire(int line, int column) {
		return new Fire(line, column);
	}
	
	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		return new Turn(line, column, angle);
	}
	
	@Override
	public Statement createAssignment(int line, int column, String variable, Expression rhs) {
		return new Assignment(line, column, variable, rhs);
	}
	
	@Override
	public Statement createIf(int line, int column, Expression condition, Statement then, Statement otherwise) {
		return new If(line, column, condition, then, otherwise);
	}
	
	@Override
	public Statement createWhile(int line, int column, Expression condition, Statement body) {
		return new While(line, column, condition, body);
	}
	
	@Override
	public Statement createForeach(int line, int column, ForeachType type, String variableName, Statement body) {
		return null;
	}
	
	@Override
	 public Statement createSkip(int line, int column) {
		return new Skip(line, column);
	}
	
	@Override
	public Statement createSequence(int line, int column, List<Statement> statements) {
		return new Sequence(line, column, statements);
	}
	
	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new Print(line, column, e);
	}
	
	@Override
	public Statement createForLoop(int line, int column, Expression counter, Statement body) {
		return null;
	}
	
	@Override
	public Type createDoubleType() {
		return new DoubleT();
	}
	
	@Override
	public Type createBooleanType() {
		return new BooleanT();
	}
	
	@Override
	 public Type createEntityType() {
		return new EntityT();
	}
	
	
	
}

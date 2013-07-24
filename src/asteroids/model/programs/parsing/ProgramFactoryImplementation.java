package asteroids.model.programs.parsing;

import java.util.List;
import asteroids.model.Ship;
import asteroids.model.programs.*;
import asteroids.model.*;

public class ProgramFactoryImplementation implements ProgramFactory<Expression, Statement, Type> {
	
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
	public Expression createNull (int line, int column)
	{
		return new EntityLiteral(line, column, null);
	}
	
	@Override
	public Expression createSelf (int line, int column)
	{
		Ship ship = getProgram().getShip();
		return new EntityLiteral(line, column, ship);
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
	public Expression createGetRadius(int line, int column, Expression e) {
		return new GetRadius(line, column, e);
	}
	
	@Override
	public Expression createVariable(int line, int column, String name) {
		
	}
	
	@Override
	public Expression createLessThan(int line, int column, Expression e1, Expression e2) {
		
	}
	
	@Override
	public Expression createGreaterThan(int line, int column, Expression e1, Expression e2) {
		
	}
	
	@Override
	public Expression createLessThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		
	}
	
	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		
	}
	
	@Override
	public Expression createEquality(int line, int column, Expression e1, Expression e2) {
		
	}
	
	@Override
	public Expression createInequality(int line, int column, Expression e1, Expression e2) {
		
	}
	
	@Override
	public Expression createAdd(int line, int column, Expression e1, Expression e2) {
		
	}
	
	@Override
	public Expression createSubtraction(int line, int column, Expression e1, Expression e2) {
		
	}
	
	@Override
	public Expression createMul(int line, int column, Expression e1, Expression e2) {
		
	}
	
	@Override
	public Expression createDivision(int line, int column, Expression e1, Expression e2) {
		
	}
	
	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		
	}
	
	@Override
	public Expression createGetDirection(int line, int column) {
		
	}
	
	@Override
	public Statement createEnableThruster(int line, int column) {
		
	}
	
	@Override
	public Statement createDisableThruster(int line, int column) {
		
	}
	
	@Override
	public Statement createFire(int line, int column) {
		
	}
	
	@Override
	public Statement createAssignment(int line, int column, String variable, Expression rhs) {
		
	}
	
	@Override
	public Statement createIf(int line, int column, Expression condition, Statement then, Statement otherwise) {
		
	}
	
	@Override
	public Statement createWhile(int line, int column, Expression condition, Statement body) {
		
	}
	
	@Override
	 public Statement createSkip(int line, int column) {
		
	}
	
	@Override
	public Statement createSequence(int line, int column, List<Statement> statements) {
		
	}
	
	@Override
	public Statement createPrint(int line, int column, Expression e) {
		
	}
	
	@Override
	public Statement createForLoop(int line, int column, Expression counter, Statement body) {
		
	}
	
	@Override
	public Type createDoubleType() {
		
	}
	
	@Override
	public Type createBooleanType() {
		
	}
	
	@Override
	 public Type createEntityType() {
		
	}
	
	public Program getProgram() {
		return program;
	}
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	private Program program;
	
}

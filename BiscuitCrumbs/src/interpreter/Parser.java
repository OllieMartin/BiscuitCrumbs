package interpreter;

import java.util.ArrayList;

public class Parser {
	
	public Parser() {
	}
	
	public ArrayList<AST> parse(TokenStream ts) {
		
		try {
		
		ArrayList<AST> prog = new ArrayList<AST>();
		
		while (ts.hasNext()) {
			
			Token t = ts.peek();
			
			if ( t.getType().equals("VAR") ) {
				prog.add(parseAssignment(ts));
				
				System.out.println(prog.get(0).getType());
				System.out.println(prog.get(0).getLeft().get(0).getValue());
				System.out.println(prog.get(0).getOperator().getValue());
				System.out.println(prog.get(0).getRight().get(0).getValue());
			
			} else if (t.getType().equals("INT") || t.getType().equals("STR") || t.getType().equals("CHR") || t.getType().equals("BOO")) {
				prog.add(parseDeclaration(ts));
			} else if (t.getType().equals("IF")) {
				
			} else if (t.getType().equals("WHILE")) {
				
			} else if (t.getType().equals("DO")) {
				
			} else if (t.getType().equals("COUT")) {
				
			} else if (t.getType().equals("CIN")) {
				
			} else if (t.getType().equals("SEMICOLON")) {
				ts.next();
			}
			
		}
		
		return prog;
		
		} catch (InvalidSyntaxException e) {
			System.err.println("Fatal error during parsing!");
			return null;
		}
	}
	
	protected AST parseAssignment(TokenStream ts) throws InvalidSyntaxException {
		Token t = ts.next();
		AST assignment = new AST();
		assignment.setType("ASSIGNMENT");
		AST leftpart = new AST();
		ArrayList<AST> left = new ArrayList<AST>();
		leftpart.setValue(t.getValue());
		leftpart.setType(t.getType());
		left.add(leftpart);
		assignment.setLeft(left);
		if (!ts.peek().getType().equals("EQUALS") || !ts.hasNext()) {
			throw new InvalidSyntaxException("Variable Assignment", t.getLine(), t.getCol());
		}
		t = ts.next();
		AST equals = new AST();
		equals.setValue("=");
		equals.setType("EQUALS");
		assignment.setOperator(equals);

		if (ts.hasNext()) {
			assignment.setRight(parseExpression(ts));
		}
		return assignment;
	}
	
	protected ArrayList<AST> parseExpression(TokenStream ts) throws InvalidSyntaxException {
		ArrayList<AST> expression = new ArrayList<AST>();
		String lastType = "NULL";
		Token t;
		AST ast = new AST();
		
		while (ts.hasNext()) {
			t = ts.peek();
			if (t.getType().equals("SEMICOLON") || t.getType().equals("THEN") || t.getType().equals("DO")) {
				if (lastType.equals("NULL")) {
					throw new InvalidSyntaxException("Expression Parsing", t.getLine(), t.getCol());
				} else {
					return expression;
				}
			}
			if (!(lastType.equals("NULL") || lastType.equals("PLUS") || lastType.equals("MINUS") || lastType.equals("DIVIDE") || lastType.equals("MULTIPLY")) && (t.getType().equals("NUMBER") || t.getType().equals("CHAR") || t.getType().equals("STRING") || t.getType().equals("TRUE") || t.getType().equals("FALSE"))) {
				throw new InvalidSyntaxException("Expression Parsing", t.getLine(), t.getCol());
			}
			if ((lastType.equals("NULL") || lastType.equals("PLUS") || lastType.equals("MINUS") || lastType.equals("DIVIDE") || lastType.equals("MULTIPLY")) && !(t.getType().equals("NUMBER") || t.getType().equals("CHAR") || t.getType().equals("STRING") || t.getType().equals("TRUE") || t.getType().equals("FALSE"))) {
				throw new InvalidSyntaxException("Expression Parsing", t.getLine(), t.getCol());
			}
			lastType = t.getType();
			ast.setValue(t.getValue());
			ast.setType(t.getType());
			expression.add(ast);
			ts.next();
		}
		
		return expression;
	}
	
	protected AST parseDeclaration(TokenStream ts) throws InvalidSyntaxException {
		AST declaration = new AST();
		
		Token t = ts.next();
		declaration.setType(t.getType());
		if (!ts.peek().getType().equals("VAR") || !ts.hasNext()) {
			throw new InvalidSyntaxException("Variable Declaration", t.getLine(), t.getCol());
		}
		t = ts.next();
		AST var = new AST();
		var.setValue(t.getValue());
		var.setType(t.getType());
		declaration.setVariable(var);
		if (ts.hasNext() && ts.peek().getType().equals("EQUALS")) {
			ts.next();
			if (ts.hasNext()) {
				declaration.setInitialValue(parseExpression(ts));
			}
			return declaration;
		} else {
			return declaration;
		}
	}
}

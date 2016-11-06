package interpreter;

import java.util.ArrayList;

public class Parser {
	
	public Parser() {
	}
	
	public ArrayList<AST> parse(TokenStream ts) {
		
		ArrayList<AST> prog = new ArrayList<AST>();
		
		while (ts.hasNext()) {
			
			Token t = ts.peek();
			
			if ( t.getType().equals("VAR") ) {
				parseAssignment(ts);
			} else if (t.getType().equals("IF")) {
				
			} else if (t.getType().equals("INT") || t.getType().equals("STR") || t.getType().equals("CHR") || t.getType().equals("BOO")) {
				
			} else if (t.getType().equals("IF")) {
				
			} else if (t.getType().equals("IF")) {
				
			}
			
		}
		
		return prog;
	}
	
	protected AST parseAssignment(TokenStream ts) {
		Token t = ts.next();
		AST assignment = new AST();
		AST left = new AST();
		left.setValue(t.getValue());
		left.setType(t.getType());
		
		return assignment;
	}
	
}

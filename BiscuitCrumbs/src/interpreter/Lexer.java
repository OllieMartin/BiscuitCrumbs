package interpreter;

import java.util.ArrayList;

public class Lexer {

	protected Program program;
	protected ArrayList<Token> tokenStream = new ArrayList<Token>();
	protected int pc;
	protected ArrayList<String> keyWords = new ArrayList<String>();
	
	//
	public static void main(String args[]) {
		Program p = new Program();
		p.addLine("int x;");
		p.addLine("x = 3;");
		p.addLine("if x = 3 then {");
		p.addLine("x = x + 1;");
		p.addLine("}else{");
		p.addLine("x = x - 1;");
		p.addLine("}");
			
		Lexer l = new Lexer(p);
		ArrayList<Token> tokens = l.getTokenStream();
		
		@SuppressWarnings("unused")
		TokenStream ts = new TokenStream(tokens);
		
		//Time to parse!
		
		for (Token t : tokens) {
			System.out.println(t.getType() + " : " + t.getValue());
		}
		
		}
	//
	
	public Lexer(Program p) {
		
		program = p;
		
		keyWords.add("if");
		keyWords.add("int");
		keyWords.add("str");
		keyWords.add("chr");
		keyWords.add("boo");
		keyWords.add("dec");
		keyWords.add("for");
		keyWords.add("to");
		keyWords.add("do");
		keyWords.add("then");
		keyWords.add("else");
		keyWords.add("while");
		keyWords.add("cout");
		keyWords.add("cin");
		keyWords.add("sub");
		keyWords.add("fun");
		
	}
	
	public ArrayList<Token> getTokenStream() {
		pc = 0;
		
		while (pc + 1 < program.getLineCount()) {
			lex(program.getLine(pc));
			pc ++;
		}
		
		return tokenStream;
		
	}
	
	protected void lex(String line) {
		
		InputStream is = new InputStream(line);
		
		while (!is.eof()) {
			readNext(is);
		}
		
		
	}
	
	protected void readNext(InputStream is) {
		
		/*for (Token t : tokenStream) {
			System.out.println(t.getType() + " : " + t.getValue());
		}*/
		
		if (is.eof()) {
			return;
		}
		
		skipWhiteSpace(is);
		
		char ch = is.peek();
		if (ch == '#') {
			return;
		}
		
		if (ch == '"') {
			readString(is);
			return;
		}
		
		if (ch == '\'') {
			readChar(is);
			return;
		}
		
		if (isDigit(ch)) {
			readNumber(is);
			return;
		}
		
		if (isIdentifierStart(ch)) {
			readIdentifier(is);
			return;
		}
		
		if (isPunct(ch)) {
			Token t;
			switch (ch) {
			case ';':
				t = new Token("SEMICOLON", ";");
				break;
			case '{':
				t = new Token("LBRACE", "{");
				break;
			case '}':
				t = new Token("RBRACE", "}");
				break;
			case '(':
				t = new Token("LPAREN", "(");
				break;
			case ')':
				t = new Token("RPAREN", ")");
				break;
			default:
				t = new Token("NULL","null");
				break;
			}
			tokenStream.add(t);
			is.next();
			return;
		}
		
		if (isOperator(ch)) {
			Token t;
			switch (ch) {
			case '+':
				t = new Token("PLUS", "+");
				is.next();
				break;
			case '-':
				t = new Token("MINUS", "-");
				is.next();
				break;
			case '/':
				t = new Token("DIVIDE", "/");
				is.next();
				break;
			case '*':
				t = new Token("MULTIPLY", "*");
				is.next();
				break;
			case '=':
				t = new Token("EQUALS", "=");
				is.next();
				break;
			case '!':
				is.next();
				if (is.peek() == '=') {
					t = new Token("NOTEQUALS", "!=");
				} else {
					t = new Token("NOT", "!");
				}
				is.next();
				break;
			case '<':
				is.next();
				if (is.peek() == '=') {
					t = new Token("LESSTHANEQUAL", "<=");
				} else {
					t = new Token("LESSTHAN", "<");
				}
				is.next();
				break;
			case '>':
				is.next();
				if (is.peek() == '=') {
					t = new Token("GREATERTHANEQUAL", ">=");
				} else {
					t = new Token("GREATERTHAN", ">");
				}
				is.next();
				break;
			default:
				t = new Token("NULL","null");
				is.next();
				break;
			}
			tokenStream.add(t);
			return;
		}
		
		
	}
	
	protected void skipWhiteSpace(InputStream is) {
		
		if (!is.eof()) {
		while (isWhiteSpace(is.peek())) {
			is.next();
		}
		}
		
	}
	protected boolean isWhiteSpace(char ch) {
		return (" \t\n".indexOf(ch) >= 0);
	}
	protected boolean isDigit(char ch) {

		try {
			Integer.parseInt(String.valueOf(ch));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	protected boolean isIdentifierStart(char ch) {
		if (Character.isJavaIdentifierStart(ch)) {
			return true;
		} else {
			return false;
		}
	}
    protected boolean isPunct(char ch) {
    	if (ch == '}' || ch == '{' || ch == ';' || ch == '(' || ch == ')') {
    		return true;
    	} else {
    		return false;
    	}
    }
	protected boolean isOperator(char ch) {
		if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '=' || ch == '<' || ch == '>' || ch == '!') {
			return true;
		} else {
			return false;
		}
	}

	
    protected void readChar(InputStream is) {
		
		String newString = "";
		
		is.next();
		
		while (is.peek() != '\'') {
			newString = newString + is.next();
		}
		
		is.next();
		
		Token t = new Token("CHAR",newString);
		tokenStream.add(t);
		
		return;
		
	}
	protected void readString(InputStream is) {
		
		String newString = "";
		
		is.next();
		
		while (is.peek() != '"') {
			newString = newString + is.next();
		}
		
		is.next();
		
		Token t = new Token("STRING",newString);
		tokenStream.add(t);
		
		return;
		
	}
	protected void readNumber(InputStream is) {
		
		String newString = "";
		
		while (isDigit(is.peek())) {
			newString = newString + is.next();
		}
		
		Token t = new Token("NUMBER",newString);
		tokenStream.add(t);
		
		return;
		
	}
	protected void readIdentifier(InputStream is) {
		
		String newString = "";
		
		while (Character.isJavaIdentifierPart(is.peek())) {
			newString = newString + is.next();
		}
		
		Token t;
		
		if (keyWords.contains(newString)) {
			t = new Token(newString.toUpperCase(),newString);
		} else {
			t = new Token("VAR",newString);
		}
		
		tokenStream.add(t);
		
		return;
		
	}
}

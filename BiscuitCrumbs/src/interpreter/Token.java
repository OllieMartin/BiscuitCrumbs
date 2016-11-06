package interpreter;

public class Token {

	protected String type;
	protected String value;
	protected int lineNumber;
	protected int colIndex;
	
	public Token(String tokenType, String tokenValue) {
		type = tokenType;
		value = tokenValue;
		lineNumber = -1;
		colIndex = -1;
	}
	public Token(String tokenType, String tokenValue, int line, int indexInLine) {
		type = tokenType;
		value = tokenValue;
		lineNumber = line;
		colIndex = indexInLine;
	}
	
	public String getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getLine() {
		return lineNumber;
	}
	
	public int getCol() {
		return colIndex;
	}
	
}

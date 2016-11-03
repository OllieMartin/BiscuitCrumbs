package interpreter;

public class Token {

	protected String type;
	protected String value;
	
	public Token(String tokenType, String tokenValue) {
		type = tokenType;
		value = tokenValue;
	}
	
	public String getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	
}

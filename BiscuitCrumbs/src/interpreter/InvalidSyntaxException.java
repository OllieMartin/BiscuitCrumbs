package interpreter;

@SuppressWarnings("serial")
public class InvalidSyntaxException extends Exception {

	public InvalidSyntaxException(String value, int line, int colpos) {
		System.err.println("Error parsing " + value);
		System.err.println("Line: " + line + ", Column: "  + colpos);
		System.err.println("Invalid syntax");
	}
	
}

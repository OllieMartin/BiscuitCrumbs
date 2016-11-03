package interpreter;
import java.util.ArrayList;

public class TokenStream {

	protected ArrayList<Token> tokens;
	protected int pointer;
	
	public TokenStream(ArrayList<Token> tokenArrayList) {
		tokens = tokenArrayList;
		pointer = -1;
		
	}
	
	public Token next() {
		pointer++;
		return tokens.get(pointer);
	}

	public boolean hasNext() {
		if (pointer + 2 < tokens.size()) {
			return true;
		} else {
			return false;
		}
	}

	public Token peek() {
		return tokens.get(pointer + 1);
	}
}

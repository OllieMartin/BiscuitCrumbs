package interpreter;

public class InputStream {

	protected String line;
	protected int pointer;
	
	public InputStream(String lineToProcess) {
		line = lineToProcess;
		pointer = -1;
	}
	
	public char next() {
		pointer++;
		return line.charAt(pointer);
	}
	
	public char peek() {
		try {
		return line.charAt(pointer + 1);
		} catch (Exception e) {
			return '$';
		}
	}
	
	public boolean eof() {

		if (pointer + 1 == line.length()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public int getPointer() {
		return pointer;
	}
}

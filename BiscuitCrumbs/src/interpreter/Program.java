package interpreter;

import java.util.ArrayList;

public class Program {

	protected ArrayList<String> lines = new ArrayList<String>();
	protected int lineCount;
	
	public Program() {
		
		lineCount = 0;
		
	}
	
	public void addLine(String line) {
		lines.add(line);
		lineCount++;
	}
	
	/**
	 * Return the specified line of the program, starting at line 1
	 */
	public String getLine(int line) {
		return lines.get(line);
	}
	
	public int getLineCount() {
		return lineCount;
	}
	
}

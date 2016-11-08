package interpreter;

import java.util.ArrayList;
import java.util.HashMap;

public class Executor {
	
	protected HashMap<String,Variable> varList = new HashMap<String,Variable>();
	
	ArrayList<AST> tree;

	public Executor() {}
	
	public void execute(ArrayList<AST> programTree) {
		tree = programTree;
	}
	
}

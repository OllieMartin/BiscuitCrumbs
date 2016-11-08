package interpreter;

import java.util.ArrayList;
import java.util.HashMap;

public class Executor {
	
	protected HashMap<String,Variable> varList = new HashMap<String,Variable>();

	public Executor() {}
	
	public ArrayList<AST> toPostFix
	
	public int evaluateInt(ArrayList<AST> astarr) {
		
		int finalvalue = 0;
		
		
		
		return finalvalue;
		
	}
	
	public void execute(ArrayList<AST> tree) {
		int pc = 0;
		
		while (pc <= tree.size()) {
			
			if (tree.get(pc).getType().equals("INT")) {
				if (tree.get(pc).getInitialValue() != null) {
					varList.put(tree.get(pc).getValue(), new Variable(tree.get(pc).getValue(),evaluateInt(tree.get(pc).getInitialValue())));
				}
			}
			
		}
		
	}
	
}

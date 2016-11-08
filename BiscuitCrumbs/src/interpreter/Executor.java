package interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Executor {
	
	protected HashMap<String,Variable> varList = new HashMap<String,Variable>();

	public Executor() {}
	
	public int evaluateInt(ArrayList<AST> astarr) {
		
		int finalvalue = 0;
		
		Infix2Postfix i2p = new Infix2Postfix();
		ArrayList<AST> postFix = i2p.InToPost(astarr);
		
	    Stack<Integer> stack = new Stack<Integer>();

	    int counter = 0;
	    
	    while (counter <= postFix.size() - 1) {
	        if (postFix.get(counter).getType().equals("NUMBER")) {
	            stack.push(Integer.parseInt(postFix.get(counter).getValue()));
	            counter++;
	            continue;
	        } else if (postFix.get(counter).getType().equals("VAR")) {
	        	stack.push(varList.get(postFix.get(counter).getValue()).getIntValue());
	        	counter++;
	            continue;
	        }
	        int b = stack.pop();
	        int a = stack.pop();
	        String op = postFix.get(counter).getType();
	        if      (op.equals("PLUS")) stack.push(a + b);
	        else if (op.equals("MINUS")) stack.push(a - b);
	        else if (op.equals("MULTIPLY")) stack.push(a * b);
	        else if (op.equals("DIVIDE")) stack.push(a / b);
	        else if (op.equals("MOD")) stack.push(a % b);
	        counter++;
	    }

	    finalvalue = stack.pop();
	    //System.out.println("FINALVALUE: " + finalvalue);
		
		return finalvalue;
		
	}
	
	public void execute(ArrayList<AST> tree) throws Exception {
		int pc = 0;
		
		while (pc <= tree.size() - 1) {
			if (tree.get(pc).getType().equals("INT")) {
				if (tree.get(pc).getInitialValue() != null) {
					varList.put(tree.get(pc).getVariable().getValue(), new Variable(tree.get(pc).getVariable().getValue(),evaluateInt(tree.get(pc).getInitialValue())));
				} else {
					varList.put(tree.get(pc).getVariable().getValue(), new Variable(tree.get(pc).getVariable().getValue(),0));
				}
				varList.get(tree.get(pc).getVariable().getValue()).setType(varType.integer);
				pc++;
			} else if (tree.get(pc).getType().equals("STR")) {
				if (tree.get(pc).getInitialValue() != null) {
					varList.put(tree.get(pc).getVariable().getValue(), new Variable(tree.get(pc).getVariable().getValue(),tree.get(pc).getInitialValue().get(0).getValue()));
				} else {
					varList.put(tree.get(pc).getVariable().getValue(), new Variable(tree.get(pc).getVariable().getValue(),""));
				}
				varList.get(tree.get(pc).getVariable().getValue()).setType(varType.string);
				pc++;
			} else if (tree.get(pc).getType().equals("CHR")) {
				if (tree.get(pc).getInitialValue() != null) {
					varList.put(tree.get(pc).getVariable().getValue(), new Variable(tree.get(pc).getVariable().getValue(),tree.get(pc).getInitialValue().get(0).getValue()));
				} else {
					varList.put(tree.get(pc).getVariable().getValue(), new Variable(tree.get(pc).getVariable().getValue(), ' '));
				}
				varList.get(tree.get(pc).getVariable().getValue()).setType(varType.character);
				pc++;
			} else if (tree.get(pc).getType().equals("BOO")) {
				if (tree.get(pc).getInitialValue() != null) {
					varList.put(tree.get(pc).getVariable().getValue(), new Variable(tree.get(pc).getVariable().getValue(),Boolean.getBoolean(tree.get(pc).getInitialValue().get(0).getValue())));
				} else {
					varList.put(tree.get(pc).getVariable().getValue(), new Variable(tree.get(pc).getVariable().getValue(),false));
				}
				varList.get(tree.get(pc).getVariable().getValue()).setType(varType.bool);
				pc++;
			} else if (tree.get(pc).getType().equals("ASSIGNMENT")) {
				if (varList.get(tree.get(pc).getLeft().get(0).getValue()) != null) {
					switch (varList.get(tree.get(pc).getLeft().get(0).getValue()).getType()) {
					case bool:
						varList.get(tree.get(pc).getLeft().get(0).getValue()).setBoolvalue(Boolean.getBoolean(tree.get(pc).getRight().get(0).getValue()));
						break;
					case character:
						varList.get(tree.get(pc).getLeft().get(0).getValue()).setCharvalue((tree.get(pc).getRight().get(0).getValue().charAt(0)));
						break;
					case integer:
						varList.get(tree.get(pc).getLeft().get(0).getValue()).setIntvalue(evaluateInt(tree.get(pc).getRight()));
						break;
					case string:
						varList.get(tree.get(pc).getLeft().get(0).getValue()).setStringvalue((tree.get(pc).getRight().get(0).getValue()));
						break;
					default:
						break;
					}
				}
				pc++;
			} else if (tree.get(pc).getType().equals("IF")) {
					if (tree.get(pc).getCondition().getLeft().get(0).getType().equals("NUMBER") || (tree.get(pc).getCondition().getLeft().get(0).getType().equals("VAR") && varList.get(tree.get(pc).getCondition().getLeft().get(0).getValue()) != null)) {
						int leftside = evaluateInt(tree.get(pc).getCondition().getLeft());
						int rightside = evaluateInt(tree.get(pc).getCondition().getRight());
						
						if (tree.get(pc).getCondition().getOperator().getType().equals("EQUALS")) {
							if (leftside == rightside) {
								System.out.println("TEST");
								execute(tree.get(pc).getThenDo());
							} else {
								if (tree.get(pc).getElseDo() != null) {
									execute(tree.get(pc).getElseDo());
								}
							}
						}
						
					}
					
					pc++;
			}else if (tree.get(pc).getType().equals("COUT")) {
				if(tree.get(pc).getRight().get(0).getType().equals("STRING")) {
					System.out.println("CONSOLE OUTPUT > " + tree.get(pc).getRight().get(0).getValue());
				} else {
					int rightside = evaluateInt(tree.get(pc).getRight());
					System.out.println("CONSOLE OUTPUT > " + rightside);
				}
				
				pc++;
				} else {
					//
					pc++;
				}
			//pc++;
			
		}
		
	}
}

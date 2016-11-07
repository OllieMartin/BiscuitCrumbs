package interpreter;

import java.util.ArrayList;

public class AST extends Object {

	public ArrayList<AST> getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(ArrayList<AST> initialValue) {
		this.initialValue = initialValue;
	}

	public AST getVariable() {
		return variable;
	}

	public void setVariable(AST variable) {
		this.variable = variable;
	}

	protected String type;
	protected String value;
	protected ArrayList<AST> thenDo;
	protected ArrayList<AST> elseDo;
	protected ArrayList<AST> whileDo;
	protected ArrayList<AST> doWhile;
	protected ArrayList<AST> initialValue;
	protected AST variable;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ArrayList<AST> getThenDo() {
		return thenDo;
	}

	public void setThenDo(ArrayList<AST> thenDo) {
		this.thenDo = thenDo;
	}

	public ArrayList<AST> getElseDo() {
		return elseDo;
	}

	public void setElseDo(ArrayList<AST> elseDo) {
		this.elseDo = elseDo;
	}

	public ArrayList<AST> getWhileDo() {
		return whileDo;
	}

	public void setWhileDo(ArrayList<AST> whileDo) {
		this.whileDo = whileDo;
	}

	public ArrayList<AST> getDoWhile() {
		return doWhile;
	}

	public void setDoWhile(ArrayList<AST> doWhile) {
		this.doWhile = doWhile;
	}

	public AST getCondition() {
		return condition;
	}

	public void setCondition(AST condition) {
		this.condition = condition;
	}

	public AST getOperator() {
		return operator;
	}

	public void setOperator(AST operator) {
		this.operator = operator;
	}

	public ArrayList<AST> getLeft() {
		return left;
	}

	public void setLeft(ArrayList<AST> left) {
		this.left = left;
	}

	public ArrayList<AST> getRight() {
		return right;
	}

	public void setRight(ArrayList<AST> right) {
		this.right = right;
	}

	protected AST condition;
	protected AST operator;
	protected ArrayList<AST> left;
	protected ArrayList<AST> right;
	
	public AST() {
		
	}
	
}

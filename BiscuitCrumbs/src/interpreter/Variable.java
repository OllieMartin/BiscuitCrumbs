package interpreter;

enum varType {
	integer,
	character,
	string,
	bool
}

public class Variable {
	
	protected varType type;
	protected String id;
	protected int intvalue;
	protected char charvalue;
	protected String stringvalue;
	protected boolean boolvalue;

	public Variable(String identifier, int value) {
		type = varType.integer;
		intvalue = value;
	}
	
	public Variable(String identifier, char value) {
		type = varType.character;
		charvalue = value;
	}
	
	public Variable(String identifier, String value) {
		type = varType.string;
		stringvalue = value;
	}
	
	public Variable(String identifier, boolean value) {
		type = varType.bool;
		boolvalue = value;
	}
	
	public int getIntValue() {
		return intvalue;
	}
	
	public char getCharValue() {
		return charvalue;
	}
	
	public String getStringValue() {
		return stringvalue;
	}
	
	public boolean getBoolValue() {
		return boolvalue;
	}

	public varType getType() {
		return type;
	}

	public void setType(varType type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIntvalue() {
		return intvalue;
	}

	public void setIntvalue(int intvalue) {
		this.intvalue = intvalue;
	}

	public char getCharvalue() {
		return charvalue;
	}

	public void setCharvalue(char charvalue) {
		this.charvalue = charvalue;
	}

	public String getStringvalue() {
		return stringvalue;
	}

	public void setStringvalue(String stringvalue) {
		this.stringvalue = stringvalue;
	}

	public boolean isBoolvalue() {
		return boolvalue;
	}

	public void setBoolvalue(boolean boolvalue) {
		this.boolvalue = boolvalue;
	}
	
}

package com.realpage.clirpncalculator.userinput.enums;

public enum ArithmeticOperatorsEnum {

	ADDITION("+"), 
	SUBTRACTION("-"),
	MULTIPLICATION("*"),
	DIVISION("/"),
	SQUAREROOT("sqrt"),
	UNDO("undo"),
	CLEAR("clear");
	
	
	ArithmeticOperatorsEnum(final String code) {
		this.code = code;
	}
	
	private String code;
	
	public String getCode() {
		return this.code;
	}

	public static ArithmeticOperatorsEnum fromString(String userEntered) {
		for(ArithmeticOperatorsEnum operator : ArithmeticOperatorsEnum.values()) {
			if (operator.getCode().equalsIgnoreCase(userEntered)) {
				return operator;
			}
		}
		throw new IllegalArgumentException("userEntered cannot be " + userEntered);
	}
}

package com.realpage.clirpncalculator.userinput.operator;

import java.math.BigDecimal;
import java.util.function.Predicate;

import com.realpage.clirpncalculator.history.record.OperationRecord;
import com.realpage.clirpncalculator.storage.Storage;
import com.realpage.clirpncalculator.userinput.Calculator;
import com.realpage.clirpncalculator.userinput.enums.ArithmeticOperatorsEnum;

public class Undo implements Calculator {

	@Override
	public void calculate(Storage storage) {
		OperationRecord record = storage.popOperationRecord();
		
		Calculator operator = record.getOperator();
		if (isNeedClearUpResult().test(operator)) {
			//Pop out the last digit from stack
			//if the last operator is NOT Clear
			storage.popDigit();
		}
		if (null != record.getOperator()) {
			//Last operation has a operator,
			//Restore those parameters
			for(BigDecimal digit : record.getParameters()) {
				storage.pushDigit(digit);
			}
		}
	}

	/**
	 * This method return true unless the operator is a
	 * Clear class
	 * 
	 * @return true if the opeartor is NOT a Clear class.
	 *  For example:
	 * 			isNeedClearUpResult.test(null) => true
	 * 			isNeedClearUpResult.test(Addition) => true
	 * 			isNeedClearUpResult.test(Subtraction) => true
	 * 			isNeedClearUpResult.test(Multiplication) => true
	 * 			isNeedClearUpResult.test(Division) => true
	 * 			isNeedClearUpResult.test(SquareRoot) => true
	 * 			isNeedClearUpResult.test(Undo) => true
	 * 			isNeedClearUpResult.test(Clear) => false
	 */
	static Predicate<Calculator> isNeedClearUpResult() {
		return e -> ((null == e) || (!(e instanceof Clear)));
	}

	@Override
	public String getEmptyStackErrorMessage(int counter) {
		StringBuilder buf = new StringBuilder("Operator: ");
		
		buf.append(ArithmeticOperatorsEnum.UNDO.getCode());
				
		buf.append(" (position: ");
		buf.append(counter);
		buf.append("): insufficient parameters");
		
		return buf.toString();
	}
}

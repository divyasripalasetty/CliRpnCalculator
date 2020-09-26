package com.realpage.clirpncalculator.userinput.operator;

import java.math.BigDecimal;

import com.realpage.clirpncalculator.history.record.OperationRecord;
import com.realpage.clirpncalculator.storage.Storage;
import com.realpage.clirpncalculator.userinput.Calculator;
import com.realpage.clirpncalculator.userinput.enums.ArithmeticOperatorsEnum;

public class Division extends BiOperator {

	@Override
	protected void performDetailOperation(Storage storage) {
		BigDecimal first = storage.popDigit();
		if (BigDecimal.ZERO.equals(first)) {
			storage.pushDigit(first);
			System.err.println("Divisor cannot be ZERO!");
			return;
		}
		BigDecimal second = storage.popDigit();
		BigDecimal total = second.divide(first, Calculator.DECIMAL_PLACES, BigDecimal.ROUND_DOWN);
		storage.pushDigit(total);
		OperationRecord record = this.getOperationRecord(first, second);
		storage.pushOperationRecord(record);
	}

	@Override
	public String getEmptyStackErrorMessage(int counter) {
		StringBuilder buf = new StringBuilder("Operator: ");
		
		buf.append(ArithmeticOperatorsEnum.DIVISION.getCode());
				
		buf.append(" (position: ");
		buf.append(counter);
		buf.append("): insufficient parameters");
		
		return buf.toString();
	}

}

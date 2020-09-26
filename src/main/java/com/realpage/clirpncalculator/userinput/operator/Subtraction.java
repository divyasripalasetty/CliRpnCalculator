package com.realpage.clirpncalculator.userinput.operator;

import java.math.BigDecimal;

import com.realpage.clirpncalculator.history.record.OperationRecord;
import com.realpage.clirpncalculator.storage.Storage;
import com.realpage.clirpncalculator.userinput.enums.ArithmeticOperatorsEnum;

public class Subtraction extends BiOperator {

	@Override
	protected void performDetailOperation(Storage storage) {
		BigDecimal first = storage.popDigit();
		BigDecimal second = storage.popDigit();
		BigDecimal result = second.subtract(first);
		storage.pushDigit(result);
		OperationRecord record = this.getOperationRecord(first, second);
		storage.pushOperationRecord(record);
	}

	@Override
	public String getEmptyStackErrorMessage(int counter) {
		StringBuilder buf = new StringBuilder("Operator: ");
		
		buf.append(ArithmeticOperatorsEnum.SUBTRACTION.getCode());
				
		buf.append(" (position: ");
		buf.append(counter);
		buf.append("): insufficient parameters");
		
		return buf.toString();
	}

}

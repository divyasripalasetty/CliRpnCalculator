package com.realpage.clirpncalculator.userinput.operator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

import com.realpage.clirpncalculator.history.record.OperationRecord;
import com.realpage.clirpncalculator.storage.Storage;
import com.realpage.clirpncalculator.userinput.Calculator;

public abstract class BiOperator implements Calculator {

	private static final int TWO = 2;

	protected OperationRecord getOperationRecord(BigDecimal first, BigDecimal second) {
		//Reverse the order of Parameters since
		//those parameters come from reverse order.
		List<BigDecimal> params = Arrays.asList(second, first);
		return new OperationRecord(params, this);
	}

	protected boolean isValidOperation(Storage storage) {
		if (storage.getDigitsStackSize() < TWO) {
			throw new EmptyStackException();
		}
		return true;
	}
	
	@Override
	public void calculate(Storage storage) {
		if (isValidOperation(storage)) {
			performDetailOperation(storage);
		}
	}

	protected abstract void performDetailOperation(Storage storage);
}

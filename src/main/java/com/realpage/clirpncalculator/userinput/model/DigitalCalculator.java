package com.realpage.clirpncalculator.userinput.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.realpage.clirpncalculator.history.record.OperationRecord;
import com.realpage.clirpncalculator.storage.Storage;
import com.realpage.clirpncalculator.userinput.Calculator;

public class DigitalCalculator implements Calculator {

	private BigDecimal digits;
	
	public DigitalCalculator(final String userInput) {
		this.digits = new BigDecimal(userInput);
	}
	
	@Override
	public void calculate(Storage storage) {
		storage.pushDigit(digits);
		OperationRecord record = toOperationRecord().apply(digits);
		storage.pushOperationRecord(record);
	}

	static Function<BigDecimal, OperationRecord> toOperationRecord() {
		return d -> {List<BigDecimal> params = Arrays.asList(d);
			return new OperationRecord(params, null);
		};
	}

	@Override
	public String getEmptyStackErrorMessage(int counter) {
		return "";
	}

}

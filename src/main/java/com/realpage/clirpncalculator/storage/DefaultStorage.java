package com.realpage.clirpncalculator.storage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.realpage.clirpncalculator.history.record.OperationRecord;
import com.realpage.clirpncalculator.userinput.UserInput;

public class DefaultStorage implements Storage {
	
	private static final int TEN = 10;

	private static final int ZERO = 0;
	
	private Stack<BigDecimal> digitStack = new Stack<>();
	private Stack<OperationRecord> history = new Stack<>();
	
	@Override
	public void pushDigit(BigDecimal userEntry) {

		this.digitStack.push(userEntry);
	}

	@Override
	public BigDecimal popDigit() {

		return this.digitStack.pop();
	}

	@Override
	public void printStack() {
		List<BigDecimal> elements = new ArrayList<>(this.digitStack);
		elements.stream().forEach(e -> {
			System.out.print((ZERO == e.scale()) ? e : format10Digits(e));
			System.out.print(UserInput.SPACE);
		});
		System.out.println("");
		System.out.print("> ");
	}

	protected String format10Digits(BigDecimal digit) {
		String result;
		if (TEN <= digit.scale()) {
			result = String.format("%.10f", digit.floatValue());
		}
		else {
			result = digit.toString();
		}
		return result;
	}

	@Override
	public void pushOperationRecord(OperationRecord record) {
		this.history.push(record);
	}

	@Override
	public OperationRecord popOperationRecord() {
		return this.history.pop();
	}

	@Override
	public int getDigitsStackSize() {
		return this.digitStack.size();
	}

}

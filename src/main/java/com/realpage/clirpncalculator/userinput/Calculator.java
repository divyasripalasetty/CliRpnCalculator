package com.realpage.clirpncalculator.userinput;

import com.realpage.clirpncalculator.storage.Storage;

public interface Calculator {

	public int DECIMAL_PLACES = 15;

	void calculate(Storage storage);
	
	String getEmptyStackErrorMessage(final int counter);
}

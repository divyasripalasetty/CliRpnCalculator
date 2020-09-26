package com.realpage.clirpncalculator;

import java.io.InputStream;
import java.util.EmptyStackException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.realpage.clirpncalculator.storage.DefaultStorage;
import com.realpage.clirpncalculator.storage.Storage;
import com.realpage.clirpncalculator.userinput.UserInput;
import com.realpage.clirpncalculator.userinput.Calculator;
import com.realpage.clirpncalculator.userinput.factory.UserInputFactory;

public class CliRpnCalculator {

	private static final int ONE = 1;
	private UserInput userInput;
	private Storage storage;
	
	public CliRpnCalculator() {
		this(System.in);
	}
	
	public CliRpnCalculator(InputStream in) {
		if (null == in) {
			throw new IllegalArgumentException("InputStream cannot be null!");
		}
		
		this.userInput = new UserInputFactory(in);
		this.storage = new DefaultStorage();
	}
	
	public void start() {
		List<Calculator> userEntries = null;
		AtomicInteger counter = new AtomicInteger(ONE);
		while( null != (userEntries = this.userInput.getUserInput())) {
			for(Calculator e : userEntries) {
				try {
					e.calculate(this.storage);
					counter.incrementAndGet();
				}
				catch (EmptyStackException ese) {
					System.err.println(formatErrorMessage(e, counter.get()));
					break;
				}
			}
			storage.printStack();
		}
	}

	protected String formatErrorMessage(Calculator e, int counter) {
		return e.getEmptyStackErrorMessage(counter);
	}

	public Storage getStorage() {
		return storage;
	}

	public static void main(String[] argv) {
		System.out.print("> ");
		new CliRpnCalculator().start();
	}
}

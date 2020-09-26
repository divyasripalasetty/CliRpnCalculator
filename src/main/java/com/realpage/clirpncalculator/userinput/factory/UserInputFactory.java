package com.realpage.clirpncalculator.userinput.factory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.realpage.clirpncalculator.userinput.UserInput;
import com.realpage.clirpncalculator.userinput.Calculator;
import com.realpage.clirpncalculator.userinput.model.DigitalCalculator;

public class UserInputFactory implements UserInput {

	private static final String EXIT = "q";
	private static final String REGEX_PATTERN ="[+-]?([0-9]*[.])?[0-9]+";

	private Scanner scanner;

	public UserInputFactory() {
		this(System.in);
	}

	public UserInputFactory(InputStream in) {
		this.scanner = new Scanner(in);
	}

	@Override
	public List<Calculator> getUserInput() {
		List<Calculator> userEntries = new ArrayList<>();
		String userEntered = scanner.nextLine();
		if(EXIT.equalsIgnoreCase(userEntered)){
        System.out.println("EXIT successfully from Calculator");
			Runtime.getRuntime().exit(0);
		}

		
		if (StringUtils.isNoneBlank(userEntered)) {
			String[] strings = userEntered.split(UserInput.SPACE);
			for (String string : strings) {
				Optional<Calculator> userEntry = this.constructUserEntry(string);
				if (userEntry.isPresent()) {
					userEntries.add(userEntry.get());
				}
			}
		}
		return userEntries;
	}

	public Optional<Calculator> constructUserEntry(String userEntered) {
		Optional<Calculator> userEntry = Optional.empty();
		
		if (StringUtils.isNotBlank(userEntered)) {
			if (userEntered.matches(REGEX_PATTERN)) {
				userEntry = getDigitalUserEntry(userEntered);
			}
			else {
				userEntry = getOperatorUserEntry(userEntered);
			}
		}
		return userEntry;
	}

	protected Optional<Calculator> getOperatorUserEntry(String userEntered) {
		Optional<Calculator> userEntry = Optional.empty();
		
		try {
			userEntry = ArithmeticOperatorFactory.getOperator(userEntered);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return userEntry;
	}

	protected Optional<Calculator> getDigitalUserEntry(final String userEntered) {
		Optional<Calculator> userEntry = Optional.empty();
		
		try {
			userEntry = Optional.of(new DigitalCalculator(userEntered));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return userEntry;
	}


}

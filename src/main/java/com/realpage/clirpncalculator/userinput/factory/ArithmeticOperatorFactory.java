package com.realpage.clirpncalculator.userinput.factory;

import java.util.Optional;

import com.realpage.clirpncalculator.userinput.Calculator;
import com.realpage.clirpncalculator.userinput.enums.ArithmeticOperatorsEnum;
import com.realpage.clirpncalculator.userinput.operator.Addition;
import com.realpage.clirpncalculator.userinput.operator.Clear;
import com.realpage.clirpncalculator.userinput.operator.Division;
import com.realpage.clirpncalculator.userinput.operator.Multiplication;
import com.realpage.clirpncalculator.userinput.operator.SquareRoot;
import com.realpage.clirpncalculator.userinput.operator.Subtraction;
import com.realpage.clirpncalculator.userinput.operator.Undo;

public class ArithmeticOperatorFactory {

	public static Optional<Calculator> getOperator(final String userEntered) {
		Optional<Calculator> userEntry = Optional.empty();
		
		try {
			ArithmeticOperatorsEnum operator = ArithmeticOperatorsEnum.fromString(userEntered);
			switch (operator) {
				case ADDITION:
					userEntry = Optional.of(new Addition());
					break;
				case SUBTRACTION:
					userEntry = Optional.of(new Subtraction());
					break;
				case MULTIPLICATION:
					userEntry = Optional.of(new Multiplication());
					break;
				case DIVISION:
					userEntry = Optional.of(new Division());
					break;
				case SQUAREROOT:
					userEntry = Optional.of(new SquareRoot());
					break;
				case CLEAR:
					userEntry = Optional.of(new Clear());
					break;
				case UNDO:
					userEntry = Optional.of(new Undo());
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return userEntry;
	}
}

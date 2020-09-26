package com.realpage.clirpncalculator.history.record;

import java.math.BigDecimal;
import java.util.List;

import com.realpage.clirpncalculator.userinput.Calculator;
import org.apache.commons.collections4.CollectionUtils;

public class OperationRecord {

	private List<BigDecimal> parameters;
	private Calculator operator;
	
	public List<BigDecimal> getParameters() {
		return parameters;
	}

	public Calculator getOperator() {
		return operator;
	}

	public OperationRecord(List<BigDecimal> parameters, Calculator operator) {
		if (CollectionUtils.isEmpty(parameters)) {
			throw new IllegalArgumentException("parameters cannot be null");
		}
		this.parameters = parameters;
		this.operator = operator;
	}
}

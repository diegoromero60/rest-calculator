package co.com.restcalculator.domain;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Operator {
	PLUS, SUBTRACT, MULTIPLY, DIVISION, EXPONENTIATION;

	public BigDecimal doIt(BigDecimal n1, BigDecimal n2) {
		switch (this) {
		case PLUS:
			return n1.add(n2, MathContext.DECIMAL32);
		case SUBTRACT:
			return n1.subtract(n2, MathContext.DECIMAL32);
		case MULTIPLY:
			return n1.multiply(n2, MathContext.DECIMAL32);
		case DIVISION:
			return n1.divide(n2, MathContext.DECIMAL32);
		case EXPONENTIATION:
			return BigDecimal.valueOf(Math.pow(n1.doubleValue(), n2.doubleValue()));
		}
		return null;
	}
}

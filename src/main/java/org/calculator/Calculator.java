package org.calculator;

import javax.ejb.Stateless;

@Stateless
public class Calculator {
	public int sum(int a, int b) {
		return a + b;
	}
}

package org.calculator;

import javax.ejb.Stateless;

/**
 * 
 * @author valdinei
 *
 */
@Stateless
public class Calculator {
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int sum(int a, int b) {
		return a + b;
	}
}

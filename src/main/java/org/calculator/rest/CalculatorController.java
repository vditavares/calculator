package org.calculator.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.calculator.Calculator;
/**
 * 
 * @author valdinei
 *
 */
@Path("/calculatorController")
public class CalculatorController {

	@Inject
	private Calculator calculator;

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	@GET
	@Path("/sum")
	@Produces("text/plain")
	public String doGet(@QueryParam("a") Integer a, @QueryParam("b") Integer b) {
		return String.valueOf(calculator.sum(a, b));
	}
}
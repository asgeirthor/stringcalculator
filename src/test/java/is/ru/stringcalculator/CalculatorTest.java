package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class CalculatorTest {

	public static void main(String args[]) {
      		org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    	}

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}

	@Test
  	public void testMultipleNumbers(){
    		assertEquals(6, Calculator.add("1,2,3"));
    	}

	@Test
	public void testEndLineSplit(){
		assertEquals(6, Calculator.add("1\n2,3"));
	}

	@Test
	public void testEndLineSplit2(){
		assertEquals(6, Calculator.add("1\n2\n3"));
	}

	@Test
	public void testCustomDeliminator(){
		assertEquals(3, Calculator.add("//;\n1;2"));
	}

	@Rule
        public ExpectedException e = ExpectedException.none();

	@Test
	public void testNegitiveNumbers(){
		e.expect(IllegalArgumentException.class);
		e.expectMessage("Negatives not allowed: -1");
		Calculator.add("1,-1");
	}

}

package practice.testng;

import org.testng.annotations.Test;

public class OrderTest {
	
	@Test (invocationCount = 10)
	public void createOrderTest() {
		System.out.println("Execute create order test = 123");
		/*
		 * String str=null; System.out.println(str);
		 * System.out.println(str.equals("123"));
		 */
	}
	
	@Test (enabled = false)
	public void billOrderTest() {
		System.out.println("Execute billOrderTest = 123");
	}
	
	

}

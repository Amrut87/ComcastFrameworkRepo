package practice.testng;

import org.testng.annotations.Test;

public class ContactTest {

	@Test
	public void createContactTest() {
		System.out.println("execute createContactTest");
	}
	
	@Test (dependsOnMethods = "createContactTest")
	public void modifyContactTest () {
		System.out.println("execute modifyContactTest");
	}
	
	@Test (dependsOnMethods = "modifyContactTest")
	public void deleteContactTest() {
		System.out.println("execute deleteContactTest");
	}
}
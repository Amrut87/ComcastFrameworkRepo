package practice.suite.ExecutionOrder;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class BaseTest {

	@BeforeSuite
	public void beforeSuite1() {
		System.out.println("BaseTest - Before Suite 1");
	}

	@BeforeSuite
	public void beforeSuite2() {
		System.out.println("BaseTest - Before Suite 2");
	}

	@AfterSuite
	public void afterSuite1() {
		System.out.println("BaseTest - After Suite 1");
	}
	
	@AfterMethod	
	public void afterMethod1() {
		System.out.println("BaseTest - After Method1");
	}
	
	@AfterMethod	
	public void afterMethod2() {
		System.out.println("BaseTest - After Method2");
	}

	@AfterSuite
	public void afterSuite2() {
		System.out.println("BaseTest - After Suite 2");
	}

	@BeforeClass
	public void beforeClass1() {
		System.out.println("BaseTest - Before Class 1");
	}
	
	@BeforeMethod	
	public void beforeMethod1() {
		System.out.println("BaseTest - Before Method1");
	}
	
	@BeforeMethod	
	public void beforeMethod2() {
		System.out.println("BaseTest - Before Method2");
	}

	@BeforeClass
	public void beforeClass2() {
		System.out.println("BaseTest - Before Class 2");
	}

	@AfterClass
	public void afterClass1() {
		System.out.println("BaseTest - After Class 1");
	}

	@AfterClass
	public void afterClass2() {
		System.out.println("BaseTest - After Class 2");
	}
}
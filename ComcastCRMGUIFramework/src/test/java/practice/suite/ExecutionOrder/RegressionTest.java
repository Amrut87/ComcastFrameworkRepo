package practice.suite.ExecutionOrder;

import org.testng.annotations.Test;

public class RegressionTest extends BaseTest {

	@Test
	public void test1() {
		System.out.println("RegressionTest - Test 1");
	}

	@Test
	public void test2() {
		System.out.println("RegressionTest - Test 2");
	}

	@Test
	public void test3() {
		System.out.println("RegressionTest - Test 3");
	}
}
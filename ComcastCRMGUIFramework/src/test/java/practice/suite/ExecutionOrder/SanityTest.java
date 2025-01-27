package practice.suite.ExecutionOrder;

import org.testng.annotations.Test;

public class SanityTest extends BaseTest {

	@Test
	public void test1() {
		System.out.println("SanityTest - Test 1");
	}

	@Test
	public void test2() {
		System.out.println("SanityTest - Test 2");
	}

	@Test
	public void test3() {
		System.out.println("SanityTest - Test 3");
	}
}
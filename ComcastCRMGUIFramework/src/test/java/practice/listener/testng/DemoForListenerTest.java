package practice.listener.testng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClassForListener;

@Listeners(practice.listener.testng.TestListener.class)
public class DemoForListenerTest extends BaseClassForListener {

	@Test
	public void testSuccess() {
		System.out.println("Executing testSuccess...");
		Assert.assertTrue(true); // Simulates a passing test case
	}

	@Test
	public void testFailure() {
		System.out.println("Executing testFailure...");
		Assert.assertTrue(false); // Simulates a failing test case
	}

	@Test
	public void testSkipped() {
		System.out.println("Executing testSkipped...");
		throw new RuntimeException("This test is skipped intentionally.");
	}
}
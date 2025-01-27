package practice.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClassForListener;

public class InvoiceTest {
		@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImp.class)
		public void createOrderTest() {
			System.out.println("execute InvoiceTest");
			Assert.assertEquals("", "Login");
			System.out.println("Step 1");
			System.out.println("Step 2");
			System.out.println("Step 3");
			System.out.println("Step 4");
		}


}

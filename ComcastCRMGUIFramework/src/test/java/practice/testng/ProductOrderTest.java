package practice.testng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClassForListener;

//@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
//Using xml instead (declaring listener at suite level)

public class ProductOrderTest extends BaseClassForListener {
		@Test
		public void createOrderTest() {
			System.out.println("execute createOrderTest");
			String actTitle=driver.getTitle();
			Assert.assertEquals(actTitle, "Login");
			System.out.println("Step 1");
			System.out.println("Step 2");
			System.out.println("Step 3");
			System.out.println("Step 4");
		}

		@Test
		public void createOrderWithContactTest() {
			System.out.println("execute createOrderWithContactTest");
			System.out.println("Step 1");
			System.out.println("Step 2");
			System.out.println("Step 3");
			System.out.println("Step 4");
		}
	}
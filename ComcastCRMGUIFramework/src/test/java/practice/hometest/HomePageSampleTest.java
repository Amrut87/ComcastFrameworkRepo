package practice.hometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageSampleTest {

//To show verification with and without using the Assert class in TestNG
	@Test
	public void homePageTest(Method mtd) {
		Reporter.log("mtd.getName()" + "Test start");
		SoftAssert sa=new SoftAssert();
		Reporter.log("Step 1", true);
		Reporter.log("Step 2", true);
		Assert.assertEquals("Home", "Home");
		Reporter.log("Step 3", true);
		sa.assertEquals("Title", "Title");
		Reporter.log("Step 4", true);
		sa.assertAll();
		Reporter.log(mtd.getName() + " Test end");
	}

	@Test
	public void verifyLogoHomePageTest(Method mtd) {
		Reporter.log("mtd.getName()" + "Test start");
		SoftAssert sa=new SoftAssert();
		Reporter.log("Step 1", true);
		Reporter.log("Step 2", true);
		sa.assertTrue(true);
		Reporter.log("Step 3", true);
		Reporter.log("Step 4", true);
		Reporter.log(mtd.getName() + " Test end");
	}
}
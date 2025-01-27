package practice.hometest;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageVerificationTest {

//To show verification with and without using the Assert class in TestNG
	@Test
	public void homePageTest(Method mtd) {
		System.out.println("mtd.getName()" + "Test start");
		String expectedPage = "Home page";

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://49.249.28.218:8888/");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		String actTitle = driver.findElement(By.xpath("//a[contains(.,'Home')]")).getText();

		/*
		 * if (actTitle.trim().equals(expectedPage)) { System.out.println(expectedPage +
		 * " page is verified. PASS"); } else { System.out.println(expectedPage +
		 * " page is not verified. FAIL"); }
		 */
		//Hard Assert
		Assert.assertEquals(actTitle, expectedPage); //Use instead of if-else
		driver.close();
		System.out.println(mtd.getName() + " Test end");
	}

	@Test
	public void verifyLogoHomePageTest(Method mtd) {
		System.out.println("mtd.getName()" + "Test start");

		WebDriver driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://49.249.28.218:8888/");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		/*
		 * if (status) { System.out.println("Logo is verified. PASS"); } else {
		 * System.out.println("Logo not verified. FAIL"); }
		 */
		//Hard Assert
		Assert.assertTrue(status);
		driver.close();
		System.out.println(mtd.getName() + " Test end");
	}
}
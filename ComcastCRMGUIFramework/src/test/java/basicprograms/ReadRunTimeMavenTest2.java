package basicprograms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ReadRunTimeMavenTest2 {
@Test
public void signup()
{
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.facebook.com");
	String ExpectedTitle = "log in or sign up";
	String ActualTitle = driver.getTitle();
	SoftAssert s = new SoftAssert();
	s.assertEquals(ActualTitle, ExpectedTitle);
	System.out.println(ActualTitle);
	driver.quit();
	//s.assertAll();
}
}

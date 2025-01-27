package practice.testng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SampleTestForScreenshot {

	@Test
	public void amazonTest() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("http://amazon.com");

//Step 1: Create an object of EventFiring WebDriver
		TakesScreenshot ts= (TakesScreenshot) driver;

//Step 2: Use getScreenshotAs method to get file type of the screenshot
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

//Step 3: Store screenshot on local driver
		FileUtils.copyFile(srcFile, new File("./photos/test.png"));
	}
}

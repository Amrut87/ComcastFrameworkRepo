package basicprograms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
//to pass parameters to @Test via maven command line
public class ReadRunTimeMavenParameterTest {
	WebDriver driver;
	//String url1= null;
	@Test
	public void runTimeParameterTest() throws IOException
	{
		driver=new ChromeDriver();
		FileInputStream fis=new FileInputStream("C:\\Users\\Smitha\\eclipse-workspace\\ComcastCRMGUIFramework\\src\\test\\resources\\Properties\\data.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String url=System.getProperty("baseurl");
		driver.get(url);
		System.out.println("test ng test");
		System.out.println("xyz abc");
	}
	@Test
	public void runAnotherTest()
	{
		System.out.println("one more test");
		System.out.println("This is good");
	}
}
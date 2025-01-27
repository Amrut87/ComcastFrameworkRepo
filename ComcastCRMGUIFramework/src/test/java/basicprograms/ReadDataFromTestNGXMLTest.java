package basicprograms;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadDataFromTestNGXMLTest {
	@Test
	public void XMLTestParamater(XmlTest test) throws IOException {
		WebDriver driver = null;
		/*
		 * FileInputStream fls=new
		 * FileInputStream("./src/test/resources/Properties/data.properties");
		 * Properties p=new Properties(); p.load(fls);
		 */
		String baseurl = test.getParameter("baseurl");
		String browser = test.getParameter("browser");
		String un = test.getParameter("username");
		String pwd = test.getParameter("password");

		Random R = new Random();
		int num = R.nextInt(1000);

		FileInputStream fis1 = new FileInputStream(
				"C:\\Users\\Smitha\\Documents\\TekPyramid\\Projects\\Data Driven Testing\\VTiger CRM.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String orgName = wb.getSheet("VTiger").getRow(1).getCell(2).toString() + num;
		wb.close();
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.get(baseurl);

		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(un);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		// driver.findElement(By.xpath("//a[.='Sign Out']")).click();

		driver.quit();
	}

}

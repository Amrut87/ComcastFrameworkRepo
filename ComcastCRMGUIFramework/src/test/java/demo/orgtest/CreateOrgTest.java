package demo.orgtest;

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

public class CreateOrgTest {

	public static void main(String[] args) throws IOException {
		WebDriver driver = null;
		  FileInputStream fls=new FileInputStream("./src/test/resources/Properties/data.properties"); 
		  Properties p=new Properties(); 
		  p.load(fls);
		  String baseurl = p.getProperty("baseurl");
		  String browser = p.getProperty("browser");
		  String un = p.getProperty("username");
		  String pwd = p.getProperty("password");
		  
		  //generate random number
		  Random R=new Random();
		  int num = R.nextInt(1000);
		  
		  //Read test script data from Excel
		  FileInputStream fis1=new FileInputStream("C:\\\\Users\\\\Smitha\\\\Documents\\\\TekPyramid\\\\Projects\\\\Data Driven Testing\\\\VTiger test cases\\VTiger test script data.xlsx");
		  Workbook wb = WorkbookFactory.create(fis1);
		  String orgName = wb.getSheet("Org").getRow(1).getCell(2).toString() + num;
		  wb.close();
		  if(browser.equals("chrome"))
		  {
			  driver=new ChromeDriver();
		  }
		  else if(browser.equals("firefox"))
		  {
			  driver=new FirefoxDriver();
		  }
		  else
		  {
			  driver=new ChromeDriver();
		  }
		  //Step 1: Login to the app
		  driver.get(baseurl);
		  
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(un);
		  driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(pwd);
		  driver.findElement(By.id("submitButton")).click();
		  
		  //Step 2: Navigate to the organization module
		  driver.findElement(By.xpath("//a[.='Organizations']")).click();
		  
		  //Step 3: Click on the Create Organization button
		  driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		  
		  //Step 4: Enter all the details & create new organization
		  driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		  //Verify header msg expected result
		  String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		  if(headerInfo.contains(orgName)) {
			  System.out.println(orgName + "  is created and header verified. Test case PASS");
		  }else
		  {
			  System.out.println(orgName + " is not created. Test case FAIL");
		  }
		  
		  //Verify org name in the expected result
		  String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		  if(actOrgName.equals(orgName)) {
			  System.out.println(orgName + " org name verified. Test case PASS");
		  }else {
			  System.out.println(orgName + " name not verified. Test case FAIL");
		  }
		  
		  //Step 5: Log out
		  Actions act=new Actions(driver);
		  act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		  driver.findElement(By.linkText("Sign Out")).click();
		  //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		  driver.quit();
	}
}
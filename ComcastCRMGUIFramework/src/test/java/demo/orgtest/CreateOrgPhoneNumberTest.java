package demo.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgPhoneNumberTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
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
		  FileInputStream fis1=new FileInputStream("C:\\Users\\Smitha\\Documents\\TekPyramid\\Projects\\Data Driven Testing\\VTiger test cases\\VTiger test script data.xlsx");
		  Workbook wb = WorkbookFactory.create(fis1);
		  Sheet sh = wb.getSheet("Org");
		  Row row = sh.getRow(1);
		  String OrgName = wb.getSheet("Org").getRow(7).getCell(2).toString() + num;
		  String phoneNumber = wb.getSheet("Org").getRow(7).getCell(3).toString();
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
		  
		  //Step 2: Navigate to the Org module
		  driver.findElement(By.xpath("//a[.='Organizations']")).click();
		  
		  //Step 3: Click on the Create Org button
		  driver.findElement(By.xpath("//img[@title='Create Organization...\']")).click();
		  
		  //Step 4: Enter the org name and the phone number, and create new Org
		  driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(OrgName);
		  driver.findElement(By.id("phone")).sendKeys(phoneNumber);
		  
		  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		  //Verify the phone number
		  String actPhoneNumber = driver.findElement(By.xpath("//span[@id='dtlview_Phone\']")).getText();
		  System.out.println(actPhoneNumber);
		  if(actPhoneNumber.equals(phoneNumber)) {
			  System.out.println(phoneNumber + " is verified. Test PASS");
		  }else
		  {
			  System.out.println(phoneNumber + " is not verified. Test FAIL");
		  }
		  
		  //Step 5: Log out
		  Actions act=new Actions(driver);
		  act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		  driver.findElement(By.linkText("Sign Out")).click();
		  //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		  driver.quit();
	}
}
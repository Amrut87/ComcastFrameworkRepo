package demo.contacttest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.utility.ContactsPage;
import com.comcast.crm.objectrepository.utility.CreatingNewContactPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;
import com.mysql.cj.log.Log;

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws Exception {
		//Create object
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();

WebDriver driver = null;

//Read data from properties file using file utility classes
  String URL = fLib.getDataFromPropertiesFile("baseurl");
  String BROWSER = fLib.getDataFromPropertiesFile("browser");
  String UN = fLib.getDataFromPropertiesFile("username");
  String PWD = fLib.getDataFromPropertiesFile("password");
		  
		  //generate random number
		  Random R=new Random();
		  int num = R.nextInt(1000);
		  
		  //Read test script data from Excel
		  String lastName = eLib.getDataFromExcel("Contact", 4, 2).toString() + jLib.getRandomNumber();

		  if(BROWSER.equals("chrome"))
		  {
			  driver=new ChromeDriver();
		  }
		  else if(BROWSER.equals("firefox"))
		  {
			  driver=new FirefoxDriver();
		  }
		  else
		  {
			  driver=new ChromeDriver();
		  }
		  //Step 1: Login to the app
		  driver.get(URL);
		  
		  wLib.maximizeWindow(driver);
		  wLib.waitForPageToLoad(driver);
		  LoginPage lp=new LoginPage(driver);
		  lp.loginToApp(UN, PWD);
		  
		  //Step 2: Navigate to the Contacts module
			HomePage hp=new HomePage(driver);
			hp.getContactsLink().click();
		  
		  /*
			 * ContactsPage cp=new ContactsPage(driver); cp.getCreateContactBtn().click();
			 */
		  
		  //Step 3: Click on the Create Contacts button
		  ContactsPage cp=new ContactsPage(driver);
		  cp.getCreateContactBtn().click();
		  //driver.findElement(By.xpath("//img[@title='Create Contact...\']")).click();
		  
		  //Step 4: Enter the start, end date, and create new contact
		  String startDate = jLib.getSystemDateYYYYMMDD();
			/*
			 * Date dateObj = new Date(); SimpleDateFormat sim = new
			 * SimpleDateFormat("yyyy-MM-dd"); String startDate = sim.format(dateObj);
			 */
		  String endDate = jLib.getRequiredDateYYYYMMDD(30);
			/*
			 * Calendar cal = sim.getCalendar(); cal.add(Calendar.DAY_OF_MONTH, 30); String
			 * endDate = sim.format(cal.getTime());
			 */
		  
		  CreatingNewContactPage cNP=new CreatingNewContactPage(driver);
		  cNP.getLastName().sendKeys(lastName);
	
		  //driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
		  driver.findElement(By.name("support_start_date")).clear();
		  driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		  
		  driver.findElement(By.name("support_end_date")).clear();
		  driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		  
		  cNP.getSaveBtn().click();
		  //driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		  //Verify start date and end date
		  String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		  System.out.println(actStartDate);
		  if(actStartDate.equals(startDate)) {
			  System.out.println(startDate + " is verified. Test PASS");
		  }else
		  {
			  System.out.println(startDate + " is not verified. Test FAIL");
		  }
		  String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		  System.out.println(actEndDate);
		  if(actEndDate.equals(endDate)) {
			  System.out.println(endDate + " is verified. Test PASS");
		  }else
		  {
			  System.out.println(endDate + " is not verified. Test FAIL");
		  }
		  
		  //Step 5: Log out
		  hp.signOut(driver);
			/*
			 * Actions act=new Actions(driver);
			 * act.moveToElement(driver.findElement(By.xpath(
			 * "//img[@src='themes/softed/images/user.PNG']"))).perform();
			 * driver.findElement(By.linkText("Sign Out")).click();
			 *///driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		  driver.quit();
	}
}
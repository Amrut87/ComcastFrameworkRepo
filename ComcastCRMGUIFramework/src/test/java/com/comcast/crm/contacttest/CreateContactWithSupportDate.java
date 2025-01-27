package com.comcast.crm.contacttest;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.utility.ContactInformationPage;
import com.comcast.crm.objectrepository.utility.ContactsPage;
import com.comcast.crm.objectrepository.utility.CreatingNewContactPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws Exception {
		
		//Create object
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
				
		WebDriver driver = null;
		String baseurl = fLib.getDataFromPropertiesFile("baseurl");
		String browser = fLib.getDataFromPropertiesFile("browser");
		String un = fLib.getDataFromPropertiesFile("username");
		String pwd = fLib.getDataFromPropertiesFile("password");
		  
		  //Read test script data from Excel
		  String lastName = eLib.getDataFromExcel("Contact", 4, 2) + jLib.getRandomNumber();
		  String orgName = eLib.getDataFromExcel("Contact", 7, 2) + jLib.getRandomNumber();
		  
		  String startDate = jLib.getSystemDateYYYYMMDD(); 
		  String endDate = jLib.getRequiredDateYYYYMMDD(30);

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
		  
		  wLib.maximizeWindow(driver);
		  wLib.waitForPageToLoad(driver);
		  LoginPage lp=new LoginPage(driver);
		  lp.loginToApp(un, pwd);
		  
			/*
			 * driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(un);
			 * driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(pwd);
			 * driver.findElement(By.id("submitButton")).click();
			 */
		  
		  //Step 2: Navigate to the Contacts module
		  HomePage hp=new HomePage(driver);
			hp.getContactsLink().click();
		  //driver.findElement(By.xpath("//a[.='Contacts']")).click();
		  
		  //Step 3: Click on the Create Contacts button
			ContactsPage cp=new ContactsPage(driver);
			  cp.getCreateContactBtn().click();
		  //driver.findElement(By.xpath("//img[@title='Create Contact...\']")).click();
		  
		  //Step 4: Enter the start, end date, and create new contact
			  CreatingNewContactPage cNCP=new CreatingNewContactPage(driver);
			  cNCP.createContactWithSupportDate(driver,orgName, 30);
			
			 /* 
			 * CreatingNewContactPage cNP=new CreatingNewContactPage(driver);
			 * cNP.getLastName().sendKeys(lastName);
			 * //driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName
			 * );
			 * 
			 * driver.findElement(By.name("support_start_date")).clear();
			 * driver.findElement(By.name("support_start_date")).sendKeys(startDate);
			 * 
			 * driver.findElement(By.name("support_end_date")).clear();
			 * driver.findElement(By.name("support_end_date")).sendKeys(endDate);
			 * 
			 * cNP.getSaveBtn().click();
			 */
		  //driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		  //Verify start date and end date
			  ContactInformationPage cIP=new ContactInformationPage(driver);
			  cIP.verifyStartDate(driver, startDate);
			  cIP.verifyEndDate(driver, endDate);
			/*
			 * String actStartDate =
			 * driver.findElement(By.id("dtlview_Support Start Date")).getText();
			 * System.out.println(actStartDate); if(actStartDate.equals(startDate)) {
			 * System.out.println(startDate + " is verified. Test PASS"); }else {
			 * System.out.println(startDate + " is not verified. Test FAIL"); } String
			 * actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
			 * System.out.println(actEndDate); if(actEndDate.equals(endDate)) {
			 * System.out.println(endDate + " is verified. Test PASS"); }else {
			 * System.out.println(endDate + " is not verified. Test FAIL"); }
			 */
		  
		  //Step 5: Log out
		  hp.signOut(driver);
		  /*
			 * WebElement
			 * ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"
			 * )); wLib.mouseMoveOnElement(driver, ele);
			 * driver.findElement(By.linkText("Sign Out")).click();
			 */
		  //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		  driver.quit();
	}
}
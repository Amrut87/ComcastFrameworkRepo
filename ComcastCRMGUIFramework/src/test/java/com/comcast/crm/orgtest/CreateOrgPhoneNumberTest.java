package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
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
import com.comcast.crm.objectrepository.utility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;
import com.comcast.crm.objectrepository.utility.OrganizationInfoPage;
import com.comcast.crm.objectrepository.utility.OrganizationsPage;

public class CreateOrgPhoneNumberTest {

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
		  
		  //Read test script data from Excel
		  String orgName = eLib.getDataFromExcel("Org", 7, 2) + jLib.getRandomNumber();
		  String phoneNumber = eLib.getDataFromExcel("Org", 7, 3).toString();
		  
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
		  
		  //Step 2: Navigate to the Org module
		  HomePage hp=new HomePage(driver);
		  hp.getorgLink().click();
		  //driver.findElement(By.xpath("//a[.='Organizations']")).click();
		  
		  //Step 3: Click on the Create Org button
		  OrganizationsPage op=new OrganizationsPage(driver);
		  op.getCreateOrgBtn().click();
		  //driver.findElement(By.xpath("//img[@title='Create Organization...\']")).click();
		  
		  //Step 4: Enter the org name and the phone number, and create new Org
		  CreateNewOrganizationPage cOP=new CreateNewOrganizationPage(driver);
		  cOP.getOrgNameField().sendKeys(orgName);
		  //driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		  cOP.getOrgPhoneNoField().sendKeys(phoneNumber);
		  //driver.findElement(By.id("phone")).sendKeys(phoneNumber);
		  
		  cOP.getsaveBtn().click();
		  //driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		  //Verify the org name
		  OrganizationInfoPage oIP=new OrganizationInfoPage(driver);
		  oIP.verifyOrgName(orgName);
		  
		//Verify the phone number
		  oIP.verifyPhoneNumber(phoneNumber);
		  
			/*
			 * String actPhoneNumber = oIP.getPhoneInfo().getText(); //String actPhoneNumber
			 * = driver.findElement(By.xpath("//span[@id='dtlview_Phone\']")).getText();
			 * System.out.println(actPhoneNumber); if(actPhoneNumber.equals(phoneNumber)) {
			 * System.out.println(phoneNumber + " is verified. Test PASS"); }else {
			 * System.out.println(phoneNumber + " is not verified. Test FAIL"); }
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
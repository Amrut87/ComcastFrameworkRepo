package com.comcast.crm.orgtest;

import java.io.FileInputStream;
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
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.utility.ContactsPage;
import com.comcast.crm.objectrepository.utility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.utility.CreatingNewContactPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;
import com.comcast.crm.objectrepository.utility.OrganizationsPage;

public class CreateOrgWithIndustriesTest {
	
	public static void main(String[] args) throws Exception {
		
		//Create object
				FileUtility fLib=new FileUtility();
				ExcelUtility eLib=new ExcelUtility();
				JavaUtility jLib=new JavaUtility();
				WebDriverUtility wLib=new WebDriverUtility();
				  
			WebDriver driver = null;

			//Read system config data from properties file
			String URL = fLib.getDataFromPropertiesFile("baseurl");
			  String BROWSER = fLib.getDataFromPropertiesFile("browser");
			  String UN = fLib.getDataFromPropertiesFile("username");
			  String PWD = fLib.getDataFromPropertiesFile("password");
			  
			  //Read test script data from Excel
			  String orgName = eLib.getDataFromExcel("Org", 1, 2) + jLib.getRandomNumber();
			  String industry=eLib.getDataFromExcel("Org", 4, 3).toString();
			  System.out.println(industry);
			  String type = eLib.getDataFromExcel("Org", 4, 4).toString();
			  System.out.println(type);
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
			  
			  //Step 2: Navigate to the organization module
			  HomePage hp=new HomePage(driver);
			  hp.getorgLink().click();
			  //driver.findElement(By.xpath("//a[.='Organizations']")).click();
			  
			  //Step 3: Click on the Create Organization button
			  OrganizationsPage op=new OrganizationsPage(driver);
			  op.getCreateOrgBtn().click();
			  //driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			  
			  //Step 4: Enter all the details & create new organization
			  CreateNewOrganizationPage cOP=new CreateNewOrganizationPage(driver);
			 //cOP.getorgNameField().sendKeys(orgName);
			  //driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
			  
			  //Create org by selecting industry and type options from dropdown
			  WebElement ele1 =driver.findElement(By.xpath("//select[@name='industry']"));
			  WebElement ele2 =driver.findElement(By.xpath("//select[@name='accounttype']"));
			  cOP.createOrgWithIndustry(orgName, ele1, industry, ele2, type);
			  //cOP.createOrgWithType(ele2, type);
			  
				/*
				 * WebElement dropDown1 =
				 * driver.findElement(By.xpath("//select[@name='industry']"));
				 * wLib.selectVisibleText(dropDown1,industry);
				 * 
				 * //Selecting the type option WebElement dropDown2 =
				 * driver.findElement(By.xpath("//select[@name='accounttype']"));
				 * wLib.selectVisibleText(dropDown2,type);
				 * 
				 * driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				 */
			  
			  //Verify industries
			  cOP.verifyIndustry(industry);
				/*
				 * String actIndustry = cOP.getIndustryData().getText();
				 * System.out.println(actIndustry); //String actIndustry =
				 * driver.findElement(By.id("dtlview_Industry")).getText();
				 * //System.out.println(actIndustry); if(actIndustry.equals(industry)) {
				 * System.out.println(industry + " name is verified. Test PASS"); }else {
				 * System.out.println(industry + " name is not verified. Test FAIL"); }
				 */
			  
			//Verify type
			  cOP.verifyType(type);
			/*
			 * String actType = cOP.getTypeData().getText(); System.out.println(actType);
			 * //String actType = driver.findElement(By.id("dtlview_Type")).getText(); //
			 * System.out.println(actType); if(actType.equals(type)) {
			 * System.out.println(type + " is verified. Test PASS"); }else {
			 * System.out.println(type + " is not verified. Test FAIL"); }
			 */
			  
			  //Step 5: Log out
			  hp.signOut(driver);
			  
			  /*
				 * WebElement ele=(driver.findElement(By.xpath(
				 * "//img[@src='themes/softed/images/user.PNG']")));
				 * wLib.mouseMoveOnElement(driver, ele);
				 * driver.findElement(By.linkText("Sign Out")).click();
				 */
			  //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
			
			  driver.quit();
	}
}
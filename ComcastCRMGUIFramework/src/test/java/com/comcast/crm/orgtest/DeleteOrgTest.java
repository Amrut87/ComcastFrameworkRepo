package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.utility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;
import com.comcast.crm.objectrepository.utility.OrganizationInfoPage;
import com.comcast.crm.objectrepository.utility.OrganizationsPage;

public class DeleteOrgTest {
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
		  String orgName = eLib.getDataFromExcel("Org", 10, 2) + jLib.getRandomNumber();

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
		  
			/* If we want to call individual element
			 * LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
			 * lp.getUsernameEdt().sendKeys("admin"); lp.getPasswordEdt().sendKeys("admin");
			 * lp.getLoginBtn().click();
			 */
		  
		  //If we want to directly call the action class - helps in optimizing the code
		  
		  LoginPage lp=new LoginPage(driver);
		  lp.loginToApp("admin", "admin");
		  
		  //Step 2: Navigate to the organization module
		  HomePage hp=new HomePage(driver);
		  hp.getorgLink().click();
		  
		  //Step 3: Click on the Create Organization button
		  OrganizationsPage op=new OrganizationsPage(driver);
		  op.getCreateOrgBtn().click();
		  
		  //Step 4: Enter all the details & create new organization
		  CreateNewOrganizationPage cOP=new CreateNewOrganizationPage(driver);
		  cOP.createOrg(driver, orgName);
		  //driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		  //driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		  //Verify header msg expected result
		  OrganizationInfoPage oIP=new OrganizationInfoPage(driver);
		  oIP.verifyHeaderInfo(orgName);
			/*
			 * String headerInfo=oIP.getheaderInfo().getText(); //String headerInfo =
			 * driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			 * if(headerInfo.contains(orgName)) { System.out.println(orgName +
			 * "  is created and header verified. Test case PASS"); }else {
			 * System.out.println(orgName + " is not created. Test case FAIL"); }
			 */		  
		  //Verify org name in the expected result
		  oIP.verifyOrgName(orgName);
			/*
			 * String actOrgName =oIP.getorgInfoData().getText(); //String actOrgName =
			 * driver.findElement(By.id("dtlview_Organization Name")).getText();
			 * if(actOrgName.equals(orgName)) { System.out.println(orgName +
			 * " org name verified. Test case PASS"); }else { System.out.println(orgName +
			 * " name not verified. Test case FAIL"); }
			 */
		  
		  //Go back to the Organization page
		  hp.getorgLink().click();
		  
		  //Search for Organization
		  op.getSearchEdt().sendKeys(orgName);
		  wLib.selectVisibleText(op.getSearchDD(), "Organization Name");
		  op.getSearchBtn().click();
		  		  
		  //In dynamic webtable select and delete org
		  driver.findElement(By.xpath("//a[.='"+orgName+"']/../../td[8]/a[.='del']")).click();
		  wLib.swithchToAlertAndAccept(driver);
		  System.out.println(orgName);
		  System.out.println(orgName + " is deleted");
		  
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

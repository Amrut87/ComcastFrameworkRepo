package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.basetest.BaseClassForListener;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.listenerutility.ListImpClass;
import com.comcast.crm.objectrepository.utility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;
import com.comcast.crm.objectrepository.utility.OrganizationInfoPage;
import com.comcast.crm.objectrepository.utility.OrganizationsPage;

@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class CreateOrgTest extends BaseClassForListener {

	// public static void main(String[] args) throws Exception { ... Converted to
	// @Test
	@Test(groups = "smokeTest")
	public void CreateORgTest() throws EncryptedDocumentException, IOException, Throwable {
		/*
		 * Moved to the base class //Create object FileUtility fLib=new FileUtility();
		 * ExcelUtility eLib=new ExcelUtility(); JavaUtility jLib=new JavaUtility();
		 * WebDriverUtility wLib=new WebDriverUtility();
		 * 
		 * WebDriver driver = null;
		 * 
		 * Moved to the base class //Read data from properties file using file utility
		 * classes String URL = fLib.getDataFromPropertiesFile("baseurl"); String
		 * BROWSER = fLib.getDataFromPropertiesFile("browser"); String UN =
		 * fLib.getDataFromPropertiesFile("username"); String PWD =
		 * fLib.getDataFromPropertiesFile("password");
		 */
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
		// Read test script data from Excel
		String orgName = eLib.getDataFromExcel("Org", 1, 2)+jLib.getRandomNumber();

		/*
		 * Moved to the base class if(BROWSER.equals("chrome")) { driver=new
		 * ChromeDriver(); } else if(BROWSER.equals("firefox")) { driver=new
		 * FirefoxDriver(); } else { driver=new ChromeDriver(); } //Step 1: Login to the
		 * app driver.get(URL);
		 * 
		 * wLib.maximizeWindow(driver); wLib.waitForPageToLoad(driver);
		 * 
		 * If we want to call individual element LoginPage lp =
		 * PageFactory.initElements(driver, LoginPage.class);
		 * lp.getUsernameEdt().sendKeys("admin"); lp.getPasswordEdt().sendKeys("admin");
		 * lp.getLoginBtn().click();
		 * 
		 * 
		 * //If we want to directly call the action class - helps in optimizing the code
		 * 
		 * LoginPage lp=new LoginPage(driver); lp.loginToApp("admin", "admin");
		 */

		// Step 2: Navigate to the organization module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to the organization page");
		HomePage hp = new HomePage(driver);
		hp.getorgLink().click();

		// Step 3: Click on the Create Organization button
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to the create organization page");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgBtn().click();

		// Step 4: Enter all the details & create new organization
		UtilityClassObject.getTest().log(Status.INFO, "create a new organization page");
		CreateNewOrganizationPage cNP = new CreateNewOrganizationPage(driver);
		cNP.createOrg(driver, orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName + "===>create a new organization page");
		/*
		 * We prefer using business method in the above 2 lines instead of calling the
		 * elements like below cNP.getorgNameField().sendKeys(orgName);
		 * cNP.getsaveBtn().click();
		 */

		// Verify header msg expected result without using POM class action method
		/*
		 * String headerInfo =
		 * driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		 * if(headerInfo.contains(orgName)) { System.out.println(orgName +
		 * "  is created and header verified. Test case PASS"); }else {
		 * System.out.println(orgName + " is not created. Test case FAIL"); }
		 */

		// Verify org name in the expected result
		OrganizationInfoPage oIP = new OrganizationInfoPage(driver);
		String actOrgName = oIP.getorgInfoData().getText();
		Assert.assertEquals(true, actOrgName.contains(orgName));
		//oIP.verifyOrgName(orgName);

		// Verify header info in the expected result
		oIP.verifyHeaderInfo(orgName);
		/*
		 * String actHeaderInfo = oIP.getheaderInfo().getText();
		 * System.out.println(actHeaderInfo); if(actHeaderInfo.equals(headerInfo)) {
		 * System.out.println(headerInfo + " org name verified. Test case PASS"); }else
		 * { System.out.println(headerInfo + " name not verified. Test case FAIL"); }
		 */

		/*
		 * Moved to the base class // Step 5: Log out hp.signOut(driver); // Using
		 * business method/action class from the POM class-Homepage.java
		 * 
		 * Logging out using webdriver utility method WebElement
		 * ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"
		 * )); wLib.mouseMoveOnElement(driver, ele);
		 * 
		 * driver.findElement(By.linkText("Sign Out")).click(); //
		 * driver.findElement(By.xpath("//a[.='Sign Out']")).click(); driver.quit();
		 */
	}

	@Test(groups = "regressionTest")
	public void CreateOrgWithPhoneNoTest() throws EncryptedDocumentException, IOException {

//Read test script data from Excel
		String orgName = eLib.getDataFromExcel("Org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("Org", 7, 3).toString();

//Step 2: Navigate to the Org module
		HomePage hp = new HomePage(driver);
		hp.getorgLink().click();
//driver.findElement(By.xpath("//a[.='Organizations']")).click();

//Step 3: Click on the Create Org button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgBtn().click();
//driver.findElement(By.xpath("//img[@title='Create Organization...\']")).click();

//Step 4: Enter the org name and the phone number, and create new Org
		CreateNewOrganizationPage cOP = new CreateNewOrganizationPage(driver);
		cOP.getOrgNameField().sendKeys(orgName);
//driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		cOP.getOrgPhoneNoField().sendKeys(phoneNumber);
//driver.findElement(By.id("phone")).sendKeys(phoneNumber);

		cOP.getsaveBtn().click();
//driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

//Verify the org name
		OrganizationInfoPage oIP = new OrganizationInfoPage(driver);
		oIP.verifyOrgName(orgName);

//Verify the phone number
		oIP.verifyPhoneNumber(phoneNumber);
	}

	@Test(groups = "regressionTest")
	public void CreateOrgWithIndTest() throws Exception, IOException {
		// Read test script data from Excel
		String orgName = eLib.getDataFromExcel("Org", 1, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("Org", 4, 3).toString();
		System.out.println(industry);
		String type = eLib.getDataFromExcel("Org", 4, 4).toString();
		System.out.println(type);

		// Step 2: Navigate to the organization module
		HomePage hp = new HomePage(driver);
		hp.getorgLink().click();

		// Step 3: Click on the Create Organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgBtn().click();

		// Step 4: Enter all the details & create new organization
		CreateNewOrganizationPage cOP = new CreateNewOrganizationPage(driver);

		// Create org by selecting industry and type options from dropdown
		WebElement ele1 = driver.findElement(By.xpath("//select[@name='industry']"));
		WebElement ele2 = driver.findElement(By.xpath("//select[@name='accounttype']"));
		cOP.createOrgWithIndustry(orgName, ele1, industry, ele2, type);

		// Verify industries
		cOP.verifyIndustry(industry);

		// Verify type
		cOP.verifyType(type);
	}

}
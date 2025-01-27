package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.utility.ContactInformationPage;
import com.comcast.crm.objectrepository.utility.ContactsPage;
import com.comcast.crm.objectrepository.utility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.utility.CreatingNewContactPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;
import com.comcast.crm.objectrepository.utility.OrganizationInfoPage;
import com.comcast.crm.objectrepository.utility.OrganizationsPage;

public class CreateContactWithOrgTest {

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
		 
		  String orgName = eLib.getDataFromExcel("Contact", 7, 2) + jLib.getRandomNumber();
		  String lastName = eLib.getDataFromExcel("Contact", 7, 3) + jLib.getRandomNumber();
		  
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
		  wLib.maximizeWindow(driver);
		  wLib.waitForPageToLoad(driver);
		  driver.get(baseurl);
		  LoginPage lp=new LoginPage(driver);
		  lp.loginToApp(un, pwd);
			/*
			 * driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(un);
			 * driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(pwd);
			 * driver.findElement(By.id("submitButton")).click();
			 */
		  
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
		  cOP.createOrg(driver, orgName);
			/*
			 * cOP.createOrg().sendKeys(orgName); cOP.getsaveBtn().click();
			 */
			/*
			 * driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName
			 * ); driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			 */
		  
		  //Step 5: Navigate to Contacts module
			
			  for (int i = 0; i < 3; i++) { // Retry mechanism
			  try { WebElement contactsLink = driver.findElement(By.xpath("//a[.='Contacts']"));
			  contactsLink.click(); 
			  break; // Exit loop if successful 
			  } catch
			  (StaleElementReferenceException e) {
			  System.out.println("Element went stale. Retrying..."); } }
			 
		  //Step 6: Click on the Create Contacts button
			  ContactsPage cP=new ContactsPage(driver);
			  cP.getCreateContactBtn().click();
			  //driver.findElement(By.xpath("//img[@title='Create Contact...\']")).click();
		  
		  //Step 7: Enter all the details & create new contact
			  CreatingNewContactPage cNCP=new CreatingNewContactPage(driver);
			  cNCP.createContactWithOrg(driver, orgName, lastName);
			/*
			 * cNCP.getLastName().sendKeys(lastName); cNCP.getSaveBtn().click();
			 */
				/*
				 * driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
				 * driver.findElement(By.xpath(
				 * "//input[@name='account_name']/following-sibling::img")).click();
				 */
		  
		  //Switching control to window popup (child) on adding org name
			/*
			 * wLib.switchToTabOnURL(driver, "module=Accounts");
			 * 
			 * driver.findElement(By.id("search_txt")).sendKeys(orgName);
			 * driver.findElement(By.name("search")).click();
			 * 
			 * WebElement ele = driver.findElement(By.xpath("//a[.='" + orgName + "']"));
			 * 
			 * wLib.waitForElementPresent(driver, ele, 10); ele.click();
			 * 
			 * //Switching the control back to the parent window
			 * wLib.switchToTabOnURL(driver, "Contacts&action");
			 * 
			 * cOP.getsaveBtn().click();
			 */
		  //driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		  //Verify last name
		  ContactInformationPage cIP=new ContactInformationPage(driver);
		  cIP.verifyContact(driver, lastName);
			/*
			 * String actLastName = cIP.getActLastName().getText(); //String actLastName =
			 * driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
			 * System.out.println(actLastName); if(actLastName.equals(lastName)) {
			 * System.out.println(lastName + " name is verified. Test PASS"); }else {
			 * System.out.println(lastName + " name is not verified. Test FAIL"); }
			 */
		  
		  //Verify org name in orgname field
		  
		  //Verify header msg
		  OrganizationInfoPage oIP=new OrganizationInfoPage(driver);
		  oIP.verifyHeaderInfo(orgName);
		  
			/*
			 * String headerInfo=oIP.getheaderInfo().getText();
			 * System.out.println(headerInfo); //String headerInfo =
			 * driver.findElement(By.id("mouseArea_Organization Name")).getText();
			 * if(headerInfo.contains(orgName)) { System.out.println(orgName +
			 * "  is created and header verified. Test case PASS"); }else {
			 * System.out.println(orgName + " is not created. Test case FAIL"); }
			 */
		  
		  //Step 8: Log out
		  hp.signOut(driver);
		  /*
			 * WebElement ele1 =
			 * driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			 * wLib.mouseMoveOnElement(driver, ele1);
			 * driver.findElement(By.linkText("Sign Out")).click();
			 */
		  //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		  driver.quit();
	}
}
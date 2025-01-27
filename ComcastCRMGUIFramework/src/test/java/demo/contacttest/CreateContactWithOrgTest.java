package demo.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.harmony.pack200.CpBands;
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
import com.mysql.cj.log.Log;

public class CreateContactWithOrgTest {

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
		  String lastName = eLib.getDataFromExcel("Contact", 7, 3) + jLib.getRandomNumber();
		  String orgName=eLib.getDataFromExcel("Contact", 7, 2) +jLib.getRandomNumber();
		 
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
		  
		  //Step 3: Click on the Create Organization button
		  OrganizationsPage op=new OrganizationsPage(driver);
		  op.getCreateOrgBtn().click();
		  
		  //Step 4: Enter all the details & create new organization 
		  CreateNewOrganizationPage cOP=new CreateNewOrganizationPage(driver);
		  cOP.getOrgNameField().sendKeys(orgName);
		  //cOP.getorgNameField().sendKeys(orgName);
		  cOP.getsaveBtn().click();
		  
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
		  
		  //Step 7: Enter all the details & create new contact
			CreatingNewContactPage cNCP=new CreatingNewContactPage(driver);
			cNCP.getLastName().sendKeys(lastName);
			cNCP.getSaveBtn().click();
		  
		  //Switching control to window popup on adding org name
		  Set<String> allWh = driver.getWindowHandles();
		  Iterator<String> it = allWh.iterator();
		  
		  while(it.hasNext())
		  {
			  String windowId=it.next();
			  driver.switchTo().window(windowId);
			  String actUrl = driver.getCurrentUrl(); //Example for where we used current url in Selenium
			  if(actUrl.contains("module=Accounts"))
			  {
				  break;
			  }
		  }
		 
		  driver.findElement(By.id("search_txt")).sendKeys(orgName);
		  driver.findElement(By.name("search")).click();
		  WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		  WebElement orgLink2 = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='" + orgName + "']")));
		  orgLink2.click();

		  //Switching the control back to the parent window
		  
		  Set<String> allWh1 = driver.getWindowHandles();
		  Iterator<String> it1 = allWh1.iterator();
		  
		  while(it1.hasNext()) {
			  String windowId1 = it1.next();
			  driver.switchTo().window(windowId1);
			  String p_url = driver.getCurrentUrl();
			  if(p_url.contains("Contacts&action"));
			  {
				  break;
			  }  
		  }
		  
		  cOP.getsaveBtn().click();
		  //driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		  //Verify last name contact
		  ContactInformationPage cIP=new ContactInformationPage(driver);
		  String actLastName = cIP.getActLastName().getText();
		  System.out.println(actLastName);
		  if(actLastName.equals(lastName)) {
			  System.out.println(lastName + " name is verified. Test PASS");
		  }else
		  {
			  System.out.println(lastName + " name is not verified. Test FAIL");
		  }
		  
		  //Verify org name
		  OrganizationInfoPage oIP=new OrganizationInfoPage(driver);
		  String headerInfo=oIP.getheaderInfo().getText();
		  if(headerInfo.contains(orgName)) {
			  System.out.println(orgName + "  is created and header verified. Test case PASS");
		  }else
		  {
			  System.out.println(orgName + " is not created. Test case FAIL");
		  }
		  
		  //Step 8: Log out
			/*
			 * WebElement ele =
			 * driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			 * wLib.mouseMoveOnElement(driver, ele);
			 */
		  
		  hp.signOut(driver);

		  //driver.findElement(By.linkText("Sign Out")).click();
		  //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		  driver.quit();
	}
}
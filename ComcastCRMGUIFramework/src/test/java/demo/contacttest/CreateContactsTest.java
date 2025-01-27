package demo.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
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
import com.comcast.crm.objectrepository.utility.ContactInformationPage;
import com.comcast.crm.objectrepository.utility.ContactsPage;
import com.comcast.crm.objectrepository.utility.CreatingNewContactPage;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;

public class CreateContactsTest {

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
					  String lastName = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();

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
					  hp.getContactsLink();
					  
					  //Step 3: Click on the Create Contacts button
					  ContactsPage cp=new ContactsPage(driver);
					  cp.getCreateContactBtn().click();
					  
					  //Step 4: Enter all the details & create new contact
					  CreatingNewContactPage cNP=new CreatingNewContactPage(driver);
					  cNP.getLastName().sendKeys(lastName);
					  cNP.getSaveBtn().click();
					  
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
					  
					  //Step 5: Log out
					  hp.signOut(driver);
					  
					  /*
						 * Actions act=new Actions(driver);
						 * act.moveToElement(driver.findElement(By.xpath(
						 * "//img[@src='themes/softed/images/user.PNG']"))).perform();
						 * driver.findElement(By.linkText("Sign Out")).click();
						 */
					  //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
					
					  driver.quit();
	}
}
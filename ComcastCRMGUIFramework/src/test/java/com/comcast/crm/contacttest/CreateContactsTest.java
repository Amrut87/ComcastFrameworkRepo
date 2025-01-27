package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
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

public class CreateContactsTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {
		//public static void main(String[] args) throws Exception { ... converted to @Test

		/*
		 * Moved to the base class 
		 * //Create object FileUtility fLib=new FileUtility();
		 * ExcelUtility eLib=new ExcelUtility(); JavaUtility jLib=new JavaUtility();
		 * WebDriverUtility wLib=new WebDriverUtility();
		 */

		/*
		 * Moved to the base class 
		 * //Read data from properties file using file utility
		 * classes String URL = fLib.getDataFromPropertiesFile("baseurl"); String
		 * BROWSER = fLib.getDataFromPropertiesFile("browser"); String UN =
		 * fLib.getDataFromPropertiesFile("username"); String PWD =
		 * fLib.getDataFromPropertiesFile("password");
		 */

		// Read test script data from Excel
		String lastName = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();

		/*
		 * Moved to the base class 
		 * WebDriver driver = null; if(BROWSER.equals("chrome")) {
		 * driver=new ChromeDriver(); } else if(BROWSER.equals("firefox")) { driver=new
		 * FirefoxDriver(); } else { driver=new ChromeDriver(); } //Step 1: Login to the
		 * app wLib.waitForPageToLoad(driver); driver.get(URL);
		 * wLib.maximizeWindow(driver);
		 * 
		 * LoginPage lp=new LoginPage(driver); lp.loginToApp(UN, PWD);
		 */
		/*
		 * lp.getUsernameEdt().sendKeys(UN); lp.getPasswordEdt().sendKeys(PWD);
		 * lp.getLoginBtn().click();
		 */

		// Step 2: Navigate to the Contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		// Step 3: Click on the Create Contacts button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactBtn().click();

		// Step 4: Enter all the details & create new contact
		CreatingNewContactPage cn = new CreatingNewContactPage(driver);
		cn.getLastName().sendKeys(lastName);

		cn.getSaveBtn().click();

		// Verify last name contact
		ContactInformationPage cIP = new ContactInformationPage(driver);
		cIP.verifyContact(driver, lastName);
		/*
		 * String actLastName = cIP.getActLastName().getText();
		 * System.out.println(actLastName);
		 * 
		 * if(actLastName.equals(lastName)) { System.out.println(lastName +
		 * " name is verified. Test PASS"); }else { System.out.println(lastName +
		 * " name is not verified. Test FAIL"); }
		 */

		// Step 5: Log out ... Moved to the base class

		// hp.signOut(driver);
		/*
		 * driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		 * wLib.mouseMoveOnElement(driver,
		 * driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
		 * driver.findElement(By.linkText("Sign Out")).click();
		 */
		// driver.findElement(By.xpath("//a[.='Sign Out']")).click();

		// driver.quit(); //... Moved to the base class
	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDate() throws Exception, IOException {
		// Combined with the main test case in this class instead of
		// having a separate class for this test case

		// Read test script data from Excel
		String lastName = eLib.getDataFromExcel("Contact", 4, 2) + jLib.getRandomNumber();
		String orgName = eLib.getDataFromExcel("Contact", 7, 2) + jLib.getRandomNumber();

		String startDate = jLib.getSystemDateYYYYMMDD();
		String endDate = jLib.getRequiredDateYYYYMMDD(30);

		// Step 2: Navigate to the Contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		// Step 3: Click on the Create Contacts button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactBtn().click();

		// Step 4: Enter the start, end date, and create new contact
		CreatingNewContactPage cNCP = new CreatingNewContactPage(driver);
		cNCP.createContactWithSupportDate(driver, orgName, 30);

		// Verify start date and end date
		ContactInformationPage cIP = new ContactInformationPage(driver);
		cIP.verifyStartDate(driver, startDate);
		cIP.verifyEndDate(driver, endDate);
	}

	@Test (groups = "regressionTest")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException {
		// Combined with the main test case in this class instead of
		// having a separate class for this test case

		// Read test script data from Excel

		String orgName = eLib.getDataFromExcel("Contact", 7, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("Contact", 7, 3) + jLib.getRandomNumber();

		// Step 2: Navigate to the organization module
		HomePage hp = new HomePage(driver);
		hp.getorgLink().click();

		// Step 3: Click on the Create Organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgBtn().click();

		// Step 4: Enter all the details & create new organization
		CreateNewOrganizationPage cOP = new CreateNewOrganizationPage(driver);
		cOP.createOrg(driver, orgName);

		// Step 5: Navigate to Contacts module

		for (int i = 0; i < 3; i++) { // Retry mechanism
			try {
				WebElement contactsLink = driver.findElement(By.xpath("//a[.='Contacts']"));
				contactsLink.click();
				break; // Exit loop if successful
			} catch (StaleElementReferenceException e) {
				System.out.println("Element went stale. Retrying...");
			}
		}

		// Step 6: Click on the Create Contacts button
		ContactsPage cP = new ContactsPage(driver);
		cP.getCreateContactBtn().click();

		// Step 7: Enter all the details & create new contact
		CreatingNewContactPage cNCP = new CreatingNewContactPage(driver);
		cNCP.createContactWithOrg(driver, orgName, lastName);
		System.out.println(orgName);

		// Verify last name
		ContactInformationPage cIP = new ContactInformationPage(driver);
		cIP.verifyContact(driver, lastName);

		// Verify org name on the contact information page
		cIP.verifyOrgName(driver, orgName);
		/*
		 * OrganizationInfoPage oIP = new OrganizationInfoPage(driver);
		 * oIP.verifyOrgName(orgName);
		 */

		// Verify header msg
		//OrganizationInfoPage oIP = new OrganizationInfoPage(driver);
		//oIP.verifyHeaderInfo(orgName);

	}
}
package com.comcast.crm.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.utility.HomePage;
import com.comcast.crm.objectrepository.utility.LoginPage;

public class BaseClass {
	// Create object
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public DataBaseUtility dLib = new DataBaseUtility();

	public WebDriver driver;
	public static WebDriver sDriver;

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configBS() {
		System.out.println("===Connect to DB. Report configured====");
		dLib.getDbConnection();
	}

	
	  @Parameters("BROWSER")
	  
	  @BeforeClass(groups = { "smokeTest", "regressionTest" })
	 
	public void configBC( ) throws Exception {
		System.out.println("===Launch the browser===");
		//String BROWSER = fLib.getDataFromPropertiesFile("browser"); //To accept values from the property file
		String BROWSER = System.getProperty("browser"); //To accept mvn parameters from cmd
				//= browser;
		//String URL = fLib.getDataFromPropertiesFile("baseurl");
		String URL = System.getProperty("url"); //To accept mvn parameters from cmd

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		//sDriver=driver;
		driver.get(URL);
		
		wLib.waitForPageToLoad(driver);
	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	 public void configBM() throws Exception {
		System.out.println("===Login====");
		//String UN = fLib.getDataFromPropertiesFile("username");
		String UN = System.getProperty("username"); //To accept mvn parameters from cmd
		//String PWD = fLib.getDataFromPropertiesFile("password");
		String PASSWORD = System.getProperty("password"); //To accept mvn parameters from cmd
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(UN, PWD);
	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAM() {
		System.out.println("====Log out===");
		HomePage hp = new HomePage(driver);
		hp.signOut(driver);
	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void configAC() {
		System.out.println("====Close the broswer===");
		driver.quit();
	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() throws SQLException {
		System.out.println("===Close the db. Report backup===");
		dLib.closeDbConnection();
	}
}
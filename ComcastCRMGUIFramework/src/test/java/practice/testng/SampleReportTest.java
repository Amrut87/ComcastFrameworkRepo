package practice.testng;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.listenerutility.ListImpClass;

@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)

public class SampleReportTest extends ListImpClass {
	
		
	/*
	 * @BeforeSuite public void configBS() {
	 * 
	 * }
	 * 
	 * @AfterSuite public void configAS() { }
	 */
	
	@Test
	public void createContactTest() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");
		
		
		//Create test using the ExtentReports object (report)
		ExtentTest test=report.createTest("createContactTest");
		
		test.log(Status.INFO, "login to the app");
		test.log(Status.INFO, "navigate to the contact page");
		test.log(Status.INFO,"create contact");
		if("HDFC".equals("HFDC")) {
			test.log(Status.PASS,"contact is created");
		}
		else {
			test.addScreenCaptureFromBase64String(filePath, "Error file");
		}
		driver.close();
	}
	
	/*
	 * @Test public void createContactWithOrgTest() {
	 * 
	 * ExtentTest test=report.createTest("createContactWithOrgTest");
	 * 
	 * test.log(Status.INFO, "login to the app"); test.log(Status.INFO,
	 * "navigate to the contact page"); test.log(Status.INFO,"create contact");
	 * if("HDFC".equals("HDFC")) { test.log(Status.PASS,"contact is created"); }
	 * else { test.log(Status.FAIL,"contact is not created"); }
	 * System.out.println("login to the app"); }
	 * 
	 * @Test public void createContactWithPhoneNumberTest() {
	 * 
	 * ExtentTest test=report.createTest("createContactWithPhoneNumberTest");
	 * 
	 * test.log(Status.INFO, "login to the app"); test.log(Status.INFO,
	 * "navigate to the contact page"); test.log(Status.INFO,"create contact");
	 * if("HDFC".equals("HDFC")) { test.log(Status.PASS,"contact is created"); }
	 * else { test.log(Status.FAIL,"contact is not created"); }
	 * System.out.println("login to the app"); }
	 */
}
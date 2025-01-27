package practice.listener.testng;

	import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

import com.comcast.crm.basetest.BaseClassForListener;

	public class TestListener implements ITestListener {

	    @Override
	    public void onTestStart(ITestResult result) {
	        System.out.println("Test Started: " + result.getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        System.out.println("Test Passed: " + result.getName());
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        System.out.println("Test Failed: " + result.getName());
			String testName = result.getMethod().getMethodName();

			TakesScreenshot ts= (TakesScreenshot) BaseClassForListener.browserAlt;

			File srcFile = ts.getScreenshotAs(OutputType.FILE);

			String time=new Date().toString().replace(" ", "_").replace(":", "_");
			
			try {
				FileUtils.copyFile(srcFile, new File("./photos/"+testName+"+"+time+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("Test Skipped: " + result.getName());
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        System.out.println("Test Suite Started: " + context.getName());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        System.out.println("Test Suite Finished: " + context.getName());
	    }
	}

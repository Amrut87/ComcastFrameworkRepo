package basicprograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.mysql.jdbc.Driver;

public class AddPlusReadDataNinzaHRMTest {
	WebDriver driver;
	String projectName=null;
	@BeforeMethod
	public void LaunchNinzaHRM(XmlTest test)
	{
	driver=null;
	String URL=test.getParameter("url");
	String BROWSER = test.getParameter("browser");
	String UN = test.getParameter("username");
	String PWD = test.getParameter("password");
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
	driver.get(URL);
	driver.manage().window().maximize();
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.id("username")).sendKeys(UN);
	driver.findElement(By.id("inputPassword")).sendKeys(PWD);
	driver.findElement(By.xpath("//button[.='Sign in']")).click();
	}
	@Test
	public void AddData(XmlTest test)
	{
		String projectname=test.getParameter("projectname");
		String projectmanager=test.getParameter("projectmanager");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys(projectname);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys(projectmanager);
		WebElement ele = driver.findElement(By.xpath("//option[.='Select Value']/.."));
		Select s=new Select(ele);
		List<WebElement> opt = s.getOptions();
		for (WebElement we : opt) {
			String str = we.getText();
			if(str.equals("Created"))
			{
				s.selectByVisibleText("Created");
			}
		}
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
	}
	@AfterMethod
	public void ReadAddedData() throws SQLException
	{
		Connection conn= null;
		try {
		//Step 1: Load/register the database driver
		Driver driverSQL=new Driver();
		DriverManager.registerDriver(driverSQL);
		//Step 2: Connect to the database
		conn=DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/Ninza_Hrm", "root", "root");
		System.out.println("---------------Connected successfully----------");
		//Step 3: Create SQL statement
		Statement stat=conn.createStatement();
		//Step 4: Execute SQL query and get the result
		ResultSet resultset = stat.executeQuery("Select * from project where created_by='Avinash Kavi'");
		while(resultset.next()) {
			System.out.println(resultset.getString(1) + "\t" + resultset.getString(2)
			+ "\t" + resultset.getString(3));
		}
		}
		catch(Exception e) {
			System.out.println("Handle the exception");
		}
		finally {
			//Step 5: Close the connection
			conn.close();			
			System.out.println("---------------Close the connection---------------");
		}
	}
	}
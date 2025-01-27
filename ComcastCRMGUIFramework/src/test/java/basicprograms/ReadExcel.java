package basicprograms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ReadExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * //Reading data from MS Excel FileInputStream fis=new
		 * FileInputStream("./src/test/resources/Properties/DataForDataDrivenTesting.xlsx"); 
		 * String str =
		 * WorkbookFactory.create(fis).getSheet("Facebook").getRow(1).getCell(2).
		 * getStringCellValue(); System.out.println(str);
		 */
		 
		  //Reading data from properties file
		 //and changing the browser during execution using run time Polymorphism
		  WebDriver driver = null;
		  FileInputStream fls=new FileInputStream("./src/test/resources/Properties/data.properties"); 
		  Properties p=new Properties(); 
		  p.load(fls);
		  String baseurl = p.getProperty("baseurl");
		  String browser = p.getProperty("browser");
		  String un = p.getProperty("email");
		  String pwd = p.getProperty("password");
		  if(browser.equals("chrome"))
		  {
			  driver=new ChromeDriver();
			  driver.get(baseurl);
			  driver.manage().window().maximize();
			  driver.findElement(By.id("email")).sendKeys(un);
			  driver.findElement(By.id("pass")).sendKeys(pwd);
			  driver.findElement(By.xpath("//button[@name='login']")).click();
		  }
		  else if(browser.equals("firefox"))
		  {
			  driver=new FirefoxDriver();
			  driver.get(baseurl);
			  driver.manage().window().maximize();
			  driver.findElement(By.id("email")).sendKeys(un);
			  driver.findElement(By.id("pass")).sendKeys(pwd);
			  driver.findElement(By.xpath("//button[@name='login']")).click();
		  }
		  else
		  {
			  driver=new ChromeDriver();
			  driver.get(baseurl);
			  driver.manage().window().maximize();
			  driver.findElement(By.id("email")).sendKeys(un);
			  driver.findElement(By.id("pass")).sendKeys(pwd);
			  driver.findElement(By.xpath("//button[@name='login']")).click();
		  }
		  driver.close();
	}
}
package basicprograms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SampleTestWithPOM {

	@FindBy(name="user_name")
	WebElement ele1;
	
	@FindBy(name="user_password")
	WebElement ele2;
	
	//@FindBy(id="submitButton")
	
	  @FindAll({@FindBy(id="submit"), @FindBy(xpath="//input[@type='submit']")})
	  WebElement ele3;
	 
	
	//@FindBys({@FindBy(id="submitButton"), @FindBy(xpath="//input[@type='submit']")}) 
	//WebElement ele4;
	  
	//FindBy performs auto healing but we usually prefer @FindAll Because @FindAll uses OR condition 
	//to locate the element whereas @FindBy uses the AND condition
	
	@Test 
	
	public void sampleTest()
	{
		WebDriver driver =new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");
		
		SampleTestWithPOM s = PageFactory.initElements(driver, SampleTestWithPOM.class);

		
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("manager");
		
		driver.navigate().refresh();

		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("manager");
		
		s.ele3.click();
		//s.ele4.click();
		
	}
}
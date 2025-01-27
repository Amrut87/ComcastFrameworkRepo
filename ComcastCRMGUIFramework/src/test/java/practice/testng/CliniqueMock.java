package practice.testng;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CliniqueMock {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.clinique.in/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().window().maximize();

		// Inspect and switching to the frame ("Sign up")
		WebElement ele1 = driver.findElement(By.name("ibmOptinFrame"));
		driver.switchTo().frame(ele1);
		driver.findElement(By.id("dismissBtn")).click();
		System.out.println("Frame inspected");

		// Switching back to the main content
		driver.switchTo().defaultContent();

		// WebElement dismissButton =
		// wait.until(ExpectedConditions.elementToBeClickable(By.id("dismissBtn")));
		Actions act = new Actions(driver);
		// act.sendKeys(Keys.ENTER).perform();
		// act.click(dismissButton).perform();

		// Skincare element
		WebElement ele = driver.findElement(By.xpath("//nav[@class='gnav-desktop-top-level-container']/div[1]/div[4]"));
		act.moveToElement(ele).perform();
		// List<WebElement> opt =
		// driver.findElements(By.xpath("//nav[@class='gnav-desktop-top-level-container']/div[1]/div[4]/div[2]/div"));

		/*
		 * Below pgm yet to execute successfully. Expected result: List of all options
		 * under SKincare menu
		 */
		List<WebElement> opt = driver.findElements(By.xpath(
				"//nav[@class='gnav-desktop-top-level-container']/div[1]/div[4]/div[2]/div[@class='gnav-desktop-sub-nav-content']/div[2]/a"));

		/*
		 * System.out.println("Size of list: " + opt.size()); for (WebElement we : opt)
		 * { System.out.println("Displayed: " + we.isDisplayed()); } for (WebElement we
		 * : opt) { System.out.println("HTML: " + we.getAttribute("outerHTML")); }
		 */
		for (WebElement we : opt) {

			String str = we.getText();
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.textToBePresentInElement(we, str));
			System.out.println(str);
		}
		driver.quit();
	}
}
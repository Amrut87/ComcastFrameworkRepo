package basicprograms;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartDataExcel {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https:www.flipkart.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']")).sendKeys("iPhone 15 pro");
		driver.findElement(By.xpath("//button[@title='Search for Products, Brands and More']")).click();
		//List<WebElement> prod_names = driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
		//List<WebElement> price_names = driver.findElements(By.xpath("//div[@class='Nx9bqj _4b5DiR']"));
		//Try using combing the two xpaths
		List<WebElement> prod_names = driver.findElements(By.xpath("//div[@class='KzDlHZ'] | //div[@class='Nx9bqj _4b5DiR']"));
		XSSFWorkbook book=new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("Flipkart");
		int size = prod_names.size();
		for(int i=0; i<size; i++)
		{
			XSSFRow row = sheet.createRow(i);
			XSSFCell cel = row.createCell(0);
			
			    WebElement str_prod = prod_names.get(i);
			    String products=str_prod.getText();
				cel.setCellValue(products);
			
				/*
				 * WebElement price = price_names.get(i); String str_price = price.getText();
				 * cel2.setCellValue(str_price);
				 */

		}
		FileOutputStream fos=new FileOutputStream("./src/test/resources/Properties/Flipkart.xlsx");
		book.write(fos);
		Thread.sleep(2000);
		book.close();
		fos.close();
	}
}

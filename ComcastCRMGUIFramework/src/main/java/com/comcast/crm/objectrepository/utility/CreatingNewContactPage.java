package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewContactPage {
	//WebDriver driver;
	public CreatingNewContactPage (WebDriver driver) {
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath=("//input[@name='lastname']"))
	private WebElement last_Name;
	
	public WebElement getLastName() {
		return last_Name;
	}
	@FindBy(xpath=("//input[@title='Save [Alt+S]']"))
	private WebElement saveBtn;
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	@FindBy(xpath=("//input[@name='account_name']/following-sibling::img"))
	private WebElement plusBtn;

	public WebElement getPlusBtn() {
		return plusBtn;
	}
	
	
	/*
	 * public WebElement getEle() { return ele; }
	 */
	
	@FindBy(id="search_txt")
	private WebElement searchField;
	
	 
	  public WebElement getSearchField() {
		return searchField;
	}
	  
	  @FindBy(xpath="//input[@name='search']")
	  private WebElement searchNowBtn;
	  
	  public WebElement getSearchNowBtn() {
		  return searchNowBtn;
	  }
	  
		@FindBy(name="support_start_date")
		private WebElement start_Date;
		
		 
		  public WebElement getStartDate() {
			return start_Date;
		}
		  
			@FindBy(name="support_end_date")
			private WebElement end_Date;
			
			 
			  public WebElement getEndDate() {
				return end_Date;
			}
	  
	public void createContactWithOrg (WebDriver driver, String orgName, String lastName) {
		last_Name.sendKeys(lastName);
		plusBtn.click();
		WebDriverUtility wLib=new WebDriverUtility();
		wLib.switchToTabOnURL(driver, "module=Accounts");
		searchField.sendKeys(orgName);
		searchNowBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		wLib.switchToTabOnURL(driver, "Contacts&action");
		saveBtn.click();
	}
	
	public void createContactWithSupportDate (WebDriver driver, String orgName, int days) {
		last_Name.sendKeys(orgName);
		JavaUtility jLib=new JavaUtility();
		String startDate = jLib.getSystemDateYYYYMMDD();
		String endDate = jLib.getRequiredDateYYYYMMDD(days);
		start_Date.clear();
		start_Date.sendKeys(startDate);
		end_Date.clear();
		end_Date.sendKeys(endDate);
		saveBtn.click();
	}
}
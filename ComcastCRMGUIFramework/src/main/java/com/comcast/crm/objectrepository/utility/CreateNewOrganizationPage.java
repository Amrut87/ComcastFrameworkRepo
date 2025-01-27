package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewOrganizationPage {

	//WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@name='accountname']")
	private WebElement orgNameField;
	
	@FindBy(id="phone")
	private WebElement orgPhoneNoField;
	
	public WebElement getOrgPhoneNoField() {
		return orgPhoneNoField;
	}
		@FindBy(xpath="//input[@title='Save [Alt+S]']")
		private WebElement saveBtn;
		
		public WebElement getsaveBtn() {
			return saveBtn;
}
		@FindBy(name="industry")
		private WebElement industryDB;
		
		@FindBy(xpath=("//select[@name='accounttype']"))
		private WebElement typeInfo;
		
		public WebElement getOrgNameField() {
			return orgNameField;
		}


		public WebElement getIndustryDB() {
			return industryDB;
		}

		public WebElement getTypeInfo() {
			return typeInfo;
		}

		public void createOrg(WebDriver driver, String orgName) {
			WebDriverUtility wLib = new WebDriverUtility();
			wLib.waitForPageToLoad(driver);
			orgNameField.sendKeys(orgName);
			//wLib.waitForElementClickable(driver, saveBtn);
			saveBtn.click();
		}
		
		@FindBy(id="dtlview_Industry")
		private WebElement industryData;
		
		@FindBy(id="dtlview_Type")
		private WebElement typeData;


public WebElement getIndustryData() {
			return industryData;
		}

		public WebElement getTypeData() {
			return typeData;
		}
		
		  public void createOrgWithIndustry(String orgName, WebElement ele1, String industry, WebElement ele2, String type) {
				WebDriverUtility wLib=new WebDriverUtility();
			  orgNameField.sendKeys(orgName); 
		  Select sel=new Select(industryDB);
		  sel.selectByVisibleText(industry); 
			wLib.selectValue(ele2, type);
			saveBtn.click();
		  }
		 
		
		public void createOrgWithType(WebElement ele2, String type) {
			WebDriverUtility wLib=new WebDriverUtility();
			/*
			 * orgNameField.sendKeys(orgString); wLib.selectValue(ele1, industry);
			 */
			/*
			 * Select sel1=new Select(industryDB); sel1.selectByVisibleText(industry);
			 */
			wLib.selectValue(ele2, type);
			/*
			 * Select sel2=new Select(typeInfo); sel2.selectByVisibleText(type);
			 */
			saveBtn.click();
		}
		
		public void verifyIndustry(String industry) {
			String actIndustry = industryData.getText();
			/*
			 * cOP.getIndustryData().getText(); System.out.println(actIndustry);
			 */  if(actIndustry.equals(industry)) {
				  System.out.println(industry + " name is verified. Test PASS");
			  }else
			  {
				  System.out.println(industry + " name is not verified. Test FAIL");
			  }
		}
		
		public void verifyType(String type) {
			String actType = typeData.getText();
			/*
			 * cOP.getIndustryData().getText(); System.out.println(actIndustry);
			 */  if(actType.equals(type)) {
				  System.out.println(type + " name is verified. Test PASS");
			  }else
			  {
				  System.out.println(type + " name is not verified. Test FAIL");
			  }
		}
		
}
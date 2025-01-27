package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	//WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerInfoData;
	
	public WebElement getheaderInfo() {
		return headerInfoData;
	}
		 
	@FindBy(id="dtlview_Organization Name")
	//@FindBy(xpath="//td[.='Organization Name']/../td[2]/a")
	private WebElement orgInfoData;
	
	public WebElement getorgInfoData() {
		return orgInfoData;
	}
	
	@FindBy(xpath="//span[@id='dtlview_Phone']")
	private WebElement phoneInfo;
	
	public WebElement getPhoneInfo() {
		return phoneInfo;
	}
	
	public void verifyOrgName (String orgName) {
		String actOrgName =orgInfoData.getText();
		  System.out.println(actOrgName);
		  if(actOrgName.equals(orgName)) {
			  System.out.println(orgName + " org name verified. Test case PASS");
		  }
		  else {
			  System.out.println(orgName + " name not verified. Test case FAIL");
		  }
	}
	
	public void verifyHeaderInfo (String orgName) {
		String actHeaderInfo=headerInfoData.getText();		 
		System.out.println(actHeaderInfo);
		 if(actHeaderInfo.contains(orgName)) {
		 System.out.println(actHeaderInfo + " org name verified. Test case PASS");
		 }
		 else {
	     System.out.println(actHeaderInfo + " name not verified. Test case FAIL");
		 }
	}
	
	public void verifyPhoneNumber (String phoneNumber) {
		String actPhoneNumber = phoneInfo.getText();
		System.out.println(actPhoneNumber);
		if(actPhoneNumber.equals(phoneNumber)) {
			 System.out.println(phoneNumber + " is verified. Test PASS"); }
		else {
			 System.out.println(phoneNumber + " is not verified. Test FAIL"); 
			 }
		/*
		 * String actPhoneNumber = oIP.getPhoneInfo().getText(); //String actPhoneNumber
		 * = driver.findElement(By.xpath("//span[@id='dtlview_Phone\']")).getText();
		 * System.out.println(actPhoneNumber); if(actPhoneNumber.equals(phoneNumber)) {
		 * System.out.println(phoneNumber + " is verified. Test PASS"); }else {
		 * System.out.println(phoneNumber + " is not verified. Test FAIL"); }
		 */
	}
}

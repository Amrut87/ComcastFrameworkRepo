package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class ContactInformationPage {
	// WebDriver driver;
	public ContactInformationPage(WebDriver driver) {
		// this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ("//span[@id='dtlview_Last Name']"))
	private WebElement LastName;

	public WebElement getActLastName() {
		return LastName;
	}
	
	public void verifyContact(WebDriver driver, String lastName) {
		String actLastName = LastName.getText();
		System.out.println(actLastName);

		if (actLastName.equals(lastName)) {
			System.out.println(lastName + " name is verified. Test PASS");
		} else {
			System.out.println(lastName + " name is not verified. Test FAIL");
		}
	}

	public void verifyOrgName(WebDriver driver, String orgName) {
		String actOrgName = driver.findElement(By.xpath("//td[.='Organization Name']/../td[2]/a[.='" + orgName + "']"))
				.getText();
		System.out.println(actOrgName);
		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + " is verified. Test PASS");
		} else {
			System.out.println(orgName + " is not verified. Test FAIL");
		}

	}

	public void verifyStartDate(WebDriver driver, String startDate) {

		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		System.out.println(actStartDate);
		if (actStartDate.equals(startDate)) {
			System.out.println(startDate + " is verified. Test PASS");
		} else {
			System.out.println(startDate + " is not verified. Test FAIL");
		}
	}

	public void verifyEndDate(WebDriver driver, String endDate) {
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		System.out.println(actEndDate);
		if (actEndDate.equals(endDate)) {
			System.out.println(endDate + " is verified. Test PASS");
		} else {
			System.out.println(endDate + " is not verified. Test FAIL");
		}
	}
}
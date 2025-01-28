package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {

	// WebDriver driver;
	public HomePage(WebDriver driver) {
		// this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[.='Organizations']")
	private WebElement orgLink;

	public WebElement getorgLink() {
		return orgLink;
	}
	@FindBy(linkText="Products")
	private WebElement productLink;

	@FindBy(xpath = ("//a[.='Contacts']"))
	private WebElement contactsLink;

	public WebElement getContactsLink() {
		return contactsLink;
	}

	@FindBy(xpath = ("//img[@src='themes/softed/images/user.PNG']"))
	private WebElement imgSrc;

	public WebElement getImgSrc() {
		return imgSrc;
	}

	@FindBy(linkText = ("Sign Out"))
	private WebElement signOutLink;

	public WebElement getSignOutLink() {
		return signOutLink;
	}

	public void signOut(WebDriver driver) {
		WebDriverUtility wLib = new WebDriverUtility();
		wLib.mouseMoveOnElement(driver, imgSrc);
		//wLib.waitForElementClickable(driver, signOutLink, 10);
		/*
		 * Actions act=new Actions(driver); act.moveToElement(imgSrc).perform();
		 */
		signOutLink.click();
	}
}
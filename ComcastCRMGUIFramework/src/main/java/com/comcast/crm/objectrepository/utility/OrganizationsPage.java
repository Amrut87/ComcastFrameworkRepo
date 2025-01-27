package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	//WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement createOrgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search_field")
	private WebElement searchDD;
	
	@FindBy(xpath="//input[@name='submit']")
	private WebElement searchBtn;
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}
	public WebElement getCreateOrgBtn() {
		return createOrgBtn;
	}
	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}
}

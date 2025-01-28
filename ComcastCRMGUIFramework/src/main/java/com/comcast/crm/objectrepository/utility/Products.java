package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products {

	@FindBy(xpath="//input[@alt='Create Product...']")
	private WebElement createProductImgBtn;
	
	@FindBy(name="search")
	private WebElement ele2;
}
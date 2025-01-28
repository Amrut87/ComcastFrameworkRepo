package com.comcast.crm.objectrepository.utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products {

	@FindBy(xpath="//input[@alt='Create Product...']")
	private WebElement createProductImgBtn;
	
<<<<<<< HEAD
	@FindBy(name="search")
	private WebElement ele2;
	
=======
	@FindBy(name="searchBtn")
	private WebElement ele3;
>>>>>>> branch 'master' of https://github.com/Amrut87/ComcastFrameworkRepo.git
}
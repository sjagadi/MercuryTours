package com.mercurytours.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mercurytours.base.BaseTest;
import com.mercurytours.helpers.ProjectHelpers;

public class SignOnPage extends BaseTest
{
	@FindBy(linkText = "SIGN-ON")
	private WebElement signOnLink;

	@FindBy(name = "userName")
	private WebElement userNameTxtBox;

	@FindBy(name = "password")
	private WebElement passwordTxtBox;

	@FindBy(name = "login")
	private WebElement loginButton;
	
	@FindBy(linkText = "SIGN-OFF")
	private WebElement signOffLink;

	public SignOnPage()
	{
		//AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(driver, this);
	}

	public FlightsPage login(String userName, String password)
	{
		signOnLink.click();
		ProjectHelpers.enterText(userNameTxtBox, userName);
		ProjectHelpers.enterText(passwordTxtBox, password);
		ProjectHelpers.clickOn(loginButton);
		Assert.assertTrue(ProjectHelpers.isElementPresent(signOffLink));
		return new FlightsPage();
	}
	
	public boolean logoutFromApplication()
	{
		ProjectHelpers.clickOn(signOffLink);
		return ProjectHelpers.isElementPresent(signOnLink);
	}
}
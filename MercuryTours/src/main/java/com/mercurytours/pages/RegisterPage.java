package com.mercurytours.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mercurytours.base.BaseTest;
import com.mercurytours.helpers.ProjectHelpers;

public class RegisterPage extends BaseTest
{
	@FindBy(linkText = "REGISTER")
	public WebElement registerLink;

	@FindBy(name = "firstName")
	public WebElement firstNameTxtBox;

	@FindBy(name = "lastName")
	public WebElement lastNameTxtBox;

	@FindBy(name = "phone")
	public WebElement phoneTxtBox;

	@FindBy(name = "address1")
	public WebElement addressTxtBox;

	@FindBy(name = "city")
	public WebElement cityTxtBox;

	@FindBy(name = "state")
	public WebElement stateTxtBox;

	@FindBy(name = "postalCode")
	public WebElement postalCodeTxtBox;

	@FindBy(name = "country")
	public WebElement countryDropDown;

	@FindBy(id = "email")
	public WebElement userNameTxtBox;

	@FindBy(name = "password")
	public WebElement passwordTxtBox;

	@FindBy(name = "confirmPassword")
	public WebElement confirmPasswordTxtBox;

	@FindBy(name = "register")
	public WebElement submitButton;
	
	@FindBy(xpath = "//img[contains(@src, 'register')]/../../..")
	public WebElement successMessageHolder;

	public RegisterPage()
	{
		//AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(driver, this);
	}

	public void registerNewUser(String fName, String lName, String phoneNo, String address, String city, String state,
			String postalCode, String country, String userName, String password, String confirmPassword)
	{
		registerLink.click();
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "Register: Mercury Tours");
		ProjectHelpers.enterText(firstNameTxtBox, fName);
		ProjectHelpers.enterText(lastNameTxtBox, lName);
		ProjectHelpers.enterText(phoneTxtBox, phoneNo);
		ProjectHelpers.enterText(addressTxtBox, address);
		ProjectHelpers.enterText(cityTxtBox, city);
		ProjectHelpers.enterText(stateTxtBox, state);
		ProjectHelpers.enterText(postalCodeTxtBox, postalCode);
		ProjectHelpers.selectFromDropDown(countryDropDown, country);
		ProjectHelpers.enterText(userNameTxtBox, userName);
		ProjectHelpers.enterText(passwordTxtBox, password);
		ProjectHelpers.enterText(confirmPasswordTxtBox, confirmPassword);
		ProjectHelpers.clickOn(submitButton);
		String actMsg = successMessageHolder.getAttribute("textContent");
		actMsg = actMsg.trim();
		logger.info("Success message: " + actMsg);
		String expMsg1 = "Dear " + fName + " " + lName;
		String expMsg2 = "Thank you for registering.";
		String expMsg3 = "You may now  sign-in using the user name and password you've just entered.";
		String expMsg4 = "Note: Your user name is " + userName;
		if(actMsg.contains(expMsg1) && actMsg.contains(expMsg2) && actMsg.contains(expMsg3) && actMsg.contains(expMsg4))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
}

package com.mercurytours.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.mercurytours.base.BaseTest;
import com.mercurytours.helpers.ExcelHelper;
import com.mercurytours.pages.RegisterPage;

public class RegisterUserTest extends BaseTest
{
	RegisterPage registerPage;
	@BeforeMethod
	public void setUp()
	{
		initConfigurations();
		registerPage = new RegisterPage();
	}
	
	@Test(dataProvider = "getRegistrationData", description = "Register new user test")
	public void registerNewUserTest(String fName, String lName, String phoneNo, String address, String city, String state, String postalCode, String country, String userName, String password, String confirmPassword)
	{
		registerPage.registerNewUser(fName, lName, phoneNo, address, city, state, postalCode, country, userName, password, confirmPassword);
	}
	
	@DataProvider
	public Object[][] getRegistrationData()
	{
		Object data[][] = ExcelHelper.getTestData("Register");
		return data;
	}
	
	@AfterMethod
	public void tearDown()
	{
		quitBrowser();
	}
}

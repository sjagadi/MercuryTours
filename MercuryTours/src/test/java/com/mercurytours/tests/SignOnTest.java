package com.mercurytours.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.mercurytours.base.BaseTest;
import com.mercurytours.base.Constants;
import com.mercurytours.pages.SignOnPage;

public class SignOnTest extends BaseTest
{
	SignOnPage signOnPage;
	@BeforeMethod
	public void setUp()
	{
		initConfigurations();
		signOnPage = new SignOnPage();
	}
	
	@Test(description = "Sign-on and sogn-off test")
	public void loginTest()
	{
		signOnPage.login(Constants.username, Constants.password);
	}
	
	@AfterMethod
	public void tearDown()
	{
		Assert.assertTrue(signOnPage.logoutFromApplication());
		quitBrowser();
	}
}
package com.mercurytours.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.mercurytours.base.BaseTest;
import com.mercurytours.base.Constants;
import com.mercurytours.pages.FlightsPage;
import com.mercurytours.pages.SignOnPage;

public class FlightsTest extends BaseTest
{
	SignOnPage signOnPage;
	FlightsPage flightsPage;
	
	@BeforeMethod
	public void setUp()
	{
		initConfigurations();
		signOnPage = new SignOnPage();
		flightsPage = signOnPage.login(Constants.username, Constants.password);
	}
	
	@Test(description = "One way trip with economy class test")
	public void oneWayTripWithEconomyClassTest() 
	{
		flightsPage.oneWayTripWithEconomyClass();
	}
	
	@Test(description = "One way trip with airline preference test")
	public void oneWayTripWithAirlinePreferenceTest()
	{
		flightsPage.oneWayTripWithAirlinePreference();
	}
	
	@Test(description = "Round trip with economy class test")
	public void roundTripWithEconomyClassTest()
	{
		flightsPage.roundTripWithEconomyClass();
	}
	
	@Test(description = "Round trip with airline preference test")
	public void roundTripWithAirlinePreferenceTest()
	{
		flightsPage.roundTripWithAirlinePreference();
	}
	
	@AfterMethod
	public void tearDown()
	{
		Assert.assertTrue(signOnPage.logoutFromApplication());
		quitBrowser();
	}
}

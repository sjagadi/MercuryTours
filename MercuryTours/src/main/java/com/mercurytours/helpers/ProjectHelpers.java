package com.mercurytours.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mercurytours.base.BaseTest;

public class ProjectHelpers extends BaseTest
{
	//Clear the text-box and enter the value
	public static void enterText(WebElement textbox, String value)
	{
		textbox.clear();
		textbox.sendKeys(value);
		logger.debug("Entering " + value + " into " + "textbox: " + textbox);
	}
	
	//Clicking the elements
	public static void clickOn(WebElement element)
	{
		element.click();
		logger.debug("Clicking on: " + element);
	}

	//Select the value from the drop-down
	public static void selectFromDropDown(WebElement dropDown, String valueToSelect)
	{
		Select select = new Select(dropDown);
		select.selectByVisibleText(valueToSelect);
		logger.debug("Selecting " + valueToSelect + " from drop-down: " + dropDown);
	}
	
	//Click the radio button only if it is not selected already
	public static void selectRadioButton(WebElement radioButton)
	{
		if(!radioButton.isSelected())
		{
			radioButton.click();
		}
		logger.debug("Selecting the radio-button: " + radioButton);
	}
	
	//Select the flight by just providing the flight name	
	public static void selectFlight(String flightName)
	{
		WebElement flightRadioButton = driver.findElement(By.xpath("//b[contains(text(), '" + flightName + "')]/../../..//input"));
		selectRadioButton(flightRadioButton);
		logger.debug("Choosing " + flightName + " radio-button");
	}
	
	//Get the price of the flight which is chosen by just passing the flight name
	public static String getPrice(String flightName)
	{
		WebElement pricePlaceHolder = driver.findElement(By.xpath("//b[contains(text(), '" + flightName + "')]/../../../td[3]/font"));
		return pricePlaceHolder.getText();
	}
	
	//Check if the element present in the web-page or not
	public static boolean isElementPresent(WebElement element)
	{
		try
		{
			element.isDisplayed();
			logger.info("Web element " + element + " is displayed in the web page");
			return true;
		} 
		catch (NoSuchElementException  e)
		{
			logger.error("Web element " + element + " not displayed ", e);
			return false;
		}		
	}
	
	public static String getDate(String date, String month)
	{
		LocalDate currentDate = LocalDate.now();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
		Date varDate;
		try
		{
			varDate = dateFormat.parse(date + "-" + month + "-" + currentDate.getYear());
			dateFormat = new SimpleDateFormat("M/dd/yyyy");
			return dateFormat.format(varDate);
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String readExcelData(String sheetName, String ColumnName)
	{
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\TestData.xlsx");
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(file);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		Workbook wb = null;
		try
		{
			wb = WorkbookFactory.create(fis);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheet(sheetName);
		// it will take value from first row
		Row row = sheet.getRow(0);
		// it will give you count of row which is used or filled
		short lastcolumnused = row.getLastCellNum();
		int colnum = 0;
		for (int i = 0; i < lastcolumnused; i++)
		{
			if (row.getCell(i).getStringCellValue().equalsIgnoreCase(ColumnName))
			{
				colnum = i;
				break;
			}
		}
		// it will take value from Second row
		row = sheet.getRow(1);
		Cell column = row.getCell(colnum);
		String CellValue = column.getStringCellValue();
		try
		{
			wb.close();
			fis.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return CellValue;
	}
}

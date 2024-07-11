package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.generic.fileutility.ExcelUtility;
import com.comcast.generic.fileutility.PropertyFileUtility;
import com.comcast.generic.webdriverutility.JavaUtility;
import com.comcast.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithSupportDate 
{
	@Test
	public void CreateContact() throws IOException
	{
		PropertyFileUtility p=new PropertyFileUtility();
		ExcelUtility e=new ExcelUtility();
		JavaUtility j=new JavaUtility();
		WebDriverUtility w=new WebDriverUtility();
		
		String browser=p.getDataFromPropertyFile("browser");
		String url=p.getDataFromPropertyFile("url"); 
		String un=p.getDataFromPropertyFile("username");
		String pwd=p.getDataFromPropertyFile("password");
		
		//To read data from Excel
		String lastName=e.getDataFromExcel("contact", 4, 2)+j.getRandomNumber();
		
		WebDriver driver=null;
		if(browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		
		//implicitwait
		w.waitforPageToLoad(driver);
		
		//selenium code to login
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();
		
		//required date
		String startDate = j.getSystemDateYYYYMMDD();
		String endDate = j.getREquiredDate(30);
		
		//navigate to Contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on create Contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		//enter all details and create new ContactWithSupportDate		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify the expected result
		//verify   startDate in Expected Result
		String startdateinfo = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(startdateinfo.contains(startDate))
		{
			System.out.println(startDate + " is created==>PASS");
		}
		else
		{
			System.out.println(startDate + " is not Created==>FAIL");
		}
		
		//verify   endDate in Expected Result
				String enddateinfo = driver.findElement(By.id("dtlview_Support End Date")).getText();
				if(enddateinfo.contains(endDate))
				{
					System.out.println(endDate + " is created==>PASS");
				}
				else
				{
					System.out.println(endDate + " is not Created==>FAIL");
				}
		//Close Application
		driver.close();
	}
}

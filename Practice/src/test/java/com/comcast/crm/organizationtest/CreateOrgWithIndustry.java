package com.comcast.crm.organizationtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.generic.fileutility.ExcelUtility;
import com.comcast.generic.fileutility.PropertyFileUtility;
import com.comcast.generic.webdriverutility.JavaUtility;
import com.comcast.generic.webdriverutility.WebDriverUtility;

public class CreateOrgWithIndustry
{
	@Test
	public void CreateOrgInd() throws IOException
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
		String orgName=e.getDataFromExcel("org", 4, 2)+j.getRandomNumber();
		String Industry=e.getDataFromExcel("org", 4, 3);
		String Type=e.getDataFromExcel("org", 4, 4);
		
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
		
		//navigate to Organization module
		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		
		//Click on create Organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//enter all details and create new organisation
		driver.findElement(By.className("detailedViewTextBox")).sendKeys(orgName);
		WebElement dd1 = driver.findElement(By.name("industry"));
		Select ss1=new Select(dd1);
		ss1.selectByVisibleText(Industry);
		
		WebElement dd2 = driver.findElement(By.name("accounttype"));
		Select ss2=new Select(dd2);
		ss2.selectByVisibleText(Type);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify the expected result
		//verify header orgName header Expected Result
		String headerinfo = driver.findElement(By.className("dvHeaderText")).getText();
		if(headerinfo.contains(orgName))
		{
			System.out.println(orgName + "is created==>PASS");
		}
		else
		{
			System.out.println(orgName + "is not Created==>FAIL");
		}
		
		//verify Industry and Type
		String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actualIndustry.contains(Industry))
		{
			System.out.println(Industry + "Information is Verified==>PASS");
		}
		else
		{
			System.out.println(Industry + "Information is not Verified==>FAIL");
		}
		
		String actualType = driver.findElement(By.id("dtlview_Type")).getText();
		if(actualType.contains(Type))
		{
			System.out.println(Type + "Information is Verified==>PASS");
		}
		else
		{
			System.out.println(Type + "Information is not Verified==>FAIL");
		}
		//Close Application
		driver.close();
	}
}

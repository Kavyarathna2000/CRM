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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.generic.fileutility.ExcelUtility;
import com.comcast.generic.fileutility.PropertyFileUtility;
import com.comcast.generic.webdriverutility.JavaUtility;
import com.comcast.generic.webdriverutility.WebDriverUtility;

public class CreateOrgWithPhoneNumber
{
	@Test
	public void CreateOrgphno() throws IOException
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
		String orgName=e.getDataFromExcel("org", 7, 2)+j.getRandomNumber();
		String phno=e.getDataFromExcel("org", 7, 3);
		
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
		driver.findElement(By.id("phone")).sendKeys(phno);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify the expected result
		//verify phoneNumber
		String phnoinfo = driver.findElement(By.id("mouseArea_Phone")).getText();
		if(phnoinfo.contains(phno))
		{
			System.out.println(phno + "is created==>PASS");
		}
		else
		{
			System.out.println(phno + "is not Created==>FAIL");
		}
		
		
		//Close Application
		driver.close();
	}
}

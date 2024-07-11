package com.comcast.crm.contacttest;

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

public class CreateContacttest 
{
	@Test(groups = "Smoke")
	public void con()
	{
		System.out.println("===Execute Con===");
	}
	@Test(groups = "Regression")
	public void connnect()
	{
		System.out.println("===Execute Connnection===");
	}
	@Test(groups = "Regression")
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
		String lastName=e.getDataFromExcel("contact", 1, 2)+j.getRandomNumber();
		
		
		
		
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
		
		//navigate to Contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on create Contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//enter all details and create new Contact
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify the expected result
		//verify   lastName in Expected Result
		String lastnameinfo = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(lastnameinfo.contains(lastName))
		{
			System.out.println(lastName + " is verified==>PASS");
		}
		else
		{
			System.out.println(lastName + " is not verified==>FAIL");
		}
		
		
		//Close Application
		driver.close();
	}
}

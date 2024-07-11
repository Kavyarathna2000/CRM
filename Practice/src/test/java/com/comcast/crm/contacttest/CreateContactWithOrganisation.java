package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateContactWithOrganisation
{
	@Test
	public void CreateOrg() throws IOException
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
		String orgName=e.getDataFromExcel("contact", 7, 2)+j.getRandomNumber();
		String lastName=e.getDataFromExcel("contact", 7, 3)+j.getRandomNumber();
	
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
		
		/*
		 * //verify header orgName info Expected Result String actualOrgName =
		 * driver.findElement(By.id("dtlview_Organization Name")).getText();
		 * if(actualOrgName.contains(orgName)) { System.out.println(orgName +
		 * "is created==>PASS"); } else { System.out.println(orgName +
		 * "is not Created==>FAIL"); }
		 */
		
		//navigate to Contact module
				driver.findElement(By.linkText("Contacts")).click();
				
				//Click on create Contact button
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				//enter all details and create new Contact
				driver.findElement(By.name("lastname")).sendKeys(lastName);
				driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				
				//switch to child window
				w.switchToTabOnUrl(driver,"module=Accounts");
				/*
				 * Set<String> set = driver.getWindowHandles(); Iterator<String> it =
				 * set.iterator(); while(it.hasNext()) { String windowid = it.next();
				 * driver.switchTo().window(windowid); String acturl = driver.getCurrentUrl();
				 * if(acturl.contains("module=Accounts")) { break; } }
				 */
				driver.findElement(By.name("search_text")).sendKeys(orgName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
				
				//switch to parent
				w.switchToTabOnUrl(driver,"module=Contacts&action");
				/*
				 * Set<String> set1 = driver.getWindowHandles(); Iterator<String> it1 =
				 * set1.iterator(); while(it1.hasNext()) { String windowid = it1.next();
				 * driver.switchTo().window(windowid); String acturl = driver.getCurrentUrl();
				 * if(acturl.contains("module=Contacts&action")) { break; } }
				 */
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//verify header orgName header Expected Result
				headerinfo = driver.findElement(By.className("dvHeaderText")).getText();
				if(headerinfo.contains(lastName))
				{
					System.out.println(lastName + " header is verified==>PASS");
				}
				else
				{
					System.out.println(lastName + "header is verified==>FAIL");
				}
				
				//verify header orgName info Expected Result 
				String actualOrgName =driver.findElement(By.id("mouseArea_Organization Name")).getText();
				if(actualOrgName.contains(orgName))
				{ 
					 System.out.println(orgName + "is created==>PASS");
				} 
				else 
				{ 
					 System.out.println(orgName +"is not Created==>FAIL");
				}
				//verify the expected result
				/*
				 * //verify lastName in Expected Result String lastnameinfo =
				 * driver.findElement(By.id("dtlview_Last Name")).getText();
				 * if(lastnameinfo.contains(lastName)) { System.out.println(lastName +
				 * " is created==>PASS"); } else { System.out.println(lastName +
				 * " is not Created==>FAIL"); }
				 */
		//Close Application
		driver.close();
	}
}

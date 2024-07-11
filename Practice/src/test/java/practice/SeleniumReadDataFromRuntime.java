package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SeleniumReadDataFromRuntime 
{
	@Test
	public void seleniumTest() throws EncryptedDocumentException, IOException
	{
		//read data from Commandline
		String url = System.getProperty("url");//to read the data from commandline
		String browser = System.getProperty("browser");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
				
				//To generate random number
						Random ran=new Random();
						int random=ran.nextInt(1000);
				
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//selenium code to login
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//navigate to Organization module
		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		
		//Click on create Organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		/*
		 * //enter all details and create new organisation
		 * driver.findElement(By.className("detailedViewTextBox")).sendKeys(orgName);
		 * driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 */
	}
}

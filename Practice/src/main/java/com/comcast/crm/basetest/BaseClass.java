package com.comcast.crm.basetest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.generic.fileutility.ExcelUtility;
import com.comcast.generic.fileutility.PropertyFileUtility;
import com.comcast.generic.webdriverutility.JavaUtility;
import com.comcast.generic.webdriverutility.UtilityClassObject;
import com.comcast.generic.webdriverutility.WebDriverUtility;

public class BaseClass 
{
	public PropertyFileUtility p=new PropertyFileUtility();
	public ExcelUtility e=new ExcelUtility();
	public JavaUtility j=new JavaUtility();
	public WebDriverUtility w=new WebDriverUtility();
	public WebDriver driver=null;
	public  static WebDriver sdriver=null;
	
	@BeforeSuite(groups = {"Smoke","Regression"})
	public void configBS() throws EncryptedDocumentException, IOException
	{
		System.out.println("Connectto DB,Report Config");
		
	}
	//@Parameters("BROWSER") use in cross browser testing
	@BeforeClass(groups = {"Smoke","Regression"})
	public void configBC() throws IOException //provide parameter String browser while cross browser execution
	{
		System.out.println("Launch Browser");
		//String BROWSER=browser; use in cross browser testing
		String Browser1 = p.getDataFromPropertyFile("browser");
		if(Browser1.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(Browser1.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}
	@BeforeMethod(groups = {"Smoke","Regression"})
	public void configBM() throws IOException
	{
		System.out.println("Login");
		String url=p.getDataFromPropertyFile("url"); 
		String un=p.getDataFromPropertyFile("username");
		String pwd=p.getDataFromPropertyFile("password");
		LoginPage l=new LoginPage(driver);
		l.LoginToApp2(url, un, pwd);
	}
	
	
	@AfterMethod(groups = {"Smoke","Regression"})
	public void configAM()
	{
		System.out.println("Logout");
		HomePage h= new HomePage(driver);
		h.logOut();
	}
	@AfterClass(groups = {"Smoke","Regression"})
	public void configAC()
	{
		System.out.println("Close Browser");
		driver.close();
	}
	@AfterSuite(groups = {"Smoke","Regression"})
	public void configAS()
	{
		System.out.println("CloseDB,Report backup");
		
	}
}

package com.comcast.crm.organizationtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.generic.fileutility.ExcelUtility;
import com.comcast.generic.fileutility.PropertyFileUtility;
import com.comcast.generic.webdriverutility.JavaUtility;
import com.comcast.generic.webdriverutility.WebDriverUtility;

public class CreateAndDeleteOrganization 
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
		String orgName=e.getDataFromExcel("org", 10, 2)+j.getRandomNumber();
	
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
		
		driver.get(url);
		
		LoginPage lp= new LoginPage(driver);
		lp.LoginToApp(un,pwd);
		
		//navigate to Organization module
		HomePage h=new HomePage(driver);
		h.getOrglink().click();
		
		//Click on create Organization button
		OrganizationsPage o=new OrganizationsPage(driver);
		o.getCreateNewOrg().click();
		
		//enter all details and create new organisation
		CreateNewOrganizationPage co=new CreateNewOrganizationPage(driver);
		co.createOrg(orgName);
		
		//verify header orgName info Expected Result
		OrganizationInformationPage oi=new OrganizationInformationPage(driver);
		String actualOrgName = oi.getHeadermsg().getText();
		if(actualOrgName.contains(orgName))
		{
			System.out.println(orgName + "is verified==>PASS");
		}
		else
		{
			System.out.println(orgName + "is not verified==>FAIL");
		}
		
		//go back to organization page
		h.getOrglink().click();
		//search for Organization
		o.getSearchOrg().sendKeys(orgName);
		w.select(o.getSearchDD(),"Organization Name");
		o.getSearchNowBtn().click();
		//In dynamic webtable select and delete organizatio
		driver.findElement(By.xpath("//a[.='"+orgName+"']/../../td[8]/a[.='del']")).click();
		w.switchToAlertAccept(driver);
		//Close Application
		h.logOut();
		driver.close();
	}
}

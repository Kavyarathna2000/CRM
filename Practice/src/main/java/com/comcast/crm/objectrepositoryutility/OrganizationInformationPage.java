package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage 
{
	WebDriver driver;
	public OrganizationInformationPage(WebDriver driver)
	{
		this.driver=driver;                            //object Initialization
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement headermsg;
	
	public WebElement getHeadermsg()
	{
		return headermsg;
	}
	
}

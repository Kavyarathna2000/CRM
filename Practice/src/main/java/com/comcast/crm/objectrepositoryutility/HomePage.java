package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;                            //object Initialization
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText ="Organizations")
	private WebElement orglink;
	
	@FindBy(linkText ="Contacts")
	private WebElement contactlink;
	

	@FindBy(linkText ="Campaigns")
	private WebElement compaignlink;
	
	@FindBy(linkText ="More")
	private WebElement morelink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement profile;
	
	@FindBy(linkText ="Sign Out")
	private WebElement signout;
	
	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContactlink() {
		return contactlink;
	}

	public WebElement getCompaignlink() {
		return compaignlink;
	}

	public WebElement getMorelink() {
		return morelink;
	}

	public void navigateToCompaignPage()
	{
		Actions a=new Actions(driver);
		a.moveToElement(morelink).perform();
		compaignlink.click();
	}
	public void logOut()
	{
		Actions a=new Actions(driver);
		a.moveToElement(profile).perform();
		signout.click();
	}
}

package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage
{
	WebDriver driver;
	public OrganizationsPage(WebDriver driver)
	{
		this.driver=driver;                            //object Initialization
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createNewOrg;
	
	@FindBy(name = "search_text")
	private WebElement searchOrg;
	
	@FindBy(name = "search_field")
	private WebElement searchDD;
	
	@FindBy(name = "submit")
	private WebElement searchNowBtn;

	public WebElement getCreateNewOrg() {
		return createNewOrg;
	}
	public WebElement getSearchOrg() {
		return searchOrg;
	}
	public WebElement getSearchDD() {
		return searchDD;
	}
	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}
	
	
}

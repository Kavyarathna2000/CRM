package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage
{
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;                            //object Initialization
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "accountname")
	private WebElement orgName;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(name = "industry")
	private WebElement industry;
	
	@FindBy(id="phone")
	private WebElement phno;
	
	@FindBy(name = "accounttype")
	private WebElement type;
	
	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	public void createOrg(String orgname)
	{
		orgName.sendKeys(orgname);
		savebtn.click();
	}
	public WebElement getIndustry() {
		return industry;
	}
	
	public WebElement getType() {
		return type;
	}
	
	

	public WebElement getPhno() {
		return phno;
	}

	public void createOrg(String orgname,String Industries,String Type)
	{
		orgName.sendKeys(orgname);
		Select s=new Select(industry);
		s.selectByVisibleText(Industries);
		Select s1=new Select(type);
		s1.selectByVisibleText(Type);
		savebtn.click();
	}
	public void createOrg(String orgname,String phhno)
	{
		orgName.sendKeys(orgname);
		phno.sendKeys(phhno);
		savebtn.click();
	}
}

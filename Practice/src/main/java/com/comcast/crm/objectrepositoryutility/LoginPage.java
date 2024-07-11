package com.comcast.crm.objectrepositoryutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility
{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement username;
	
	@FindBy(name="user_password")
	private WebElement password;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;

	//Object Encapsulation
	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}
	
	//Provide Action
	public void LoginToApp(String uusername,String ppassword)
	{
		waitforPageToLoad(driver);
		driver.manage().window().maximize();
		username.sendKeys(uusername);
		password.sendKeys(ppassword);
		loginbtn.click();
	}
	public void LoginToApp2(String url, String uusername,String ppassword)
	{
		waitforPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		username.sendKeys(uusername);
		password.sendKeys(ppassword);
		loginbtn.click();
	}
	
}

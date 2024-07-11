package com.comcast.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility 
{
	public void waitforPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void waitforElementToPresent(WebDriver driver,WebElement element)
	{
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(20));
		w.until(ExpectedConditions.visibilityOf(element));
	}
	public void switchToTabOnUrl(WebDriver driver,String partialUrl)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String windowid = it.next();
			driver.switchTo().window(windowid);
			String acturl = driver.getCurrentUrl();
			if(acturl.contains(partialUrl))
			{
				break;
			}
		}
	}
	public void switchToTabOnTitle(WebDriver driver,String partialTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String windowid = it.next();
			driver.switchTo().window(windowid);
			String acturl = driver.getTitle();
			if(acturl.contains(partialTitle))
			{
				break;
			}
		}
	}
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String name)
	{
		driver.switchTo().frame(name);
	}
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	public void switchToAlertAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void switchToAlertCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	public void select(WebElement element,String text)
	{
		Select s=new Select(element);
		s.selectByVisibleText(text);
	}
	public void select(WebElement element,int index)
	{
		Select s=new Select(element);
		s.selectByIndex(index);
	}
	public void selectValue(WebElement element,String value)
	{
		Select s=new Select(element);
		s.selectByValue(value);
	}
	public void mouseMoveOnElement(WebDriver driver,WebElement element)
	{
		Actions a= new Actions(driver);
		a.moveToElement(element).perform();
	}
	public void Doubleclick(WebDriver driver,WebElement element)
	{
		Actions a= new Actions(driver);
		a.doubleClick(element).perform();
	}
}

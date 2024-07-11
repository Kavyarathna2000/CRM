package practice;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.generic.fileutility.ExcelUtility;

public class GetProductInfo 
{
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandname,String productname)
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in");
		
		//search Product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandname,Keys.ENTER);
		//Capture product info
		WebElement path = driver.findElement(By.xpath("//span[.='"+productname+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span/span[2]/span[2]"));
		String price=path.getText();
		System.out.println(price);
	}
	

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		ExcelUtility e=new ExcelUtility();
		int count = e.getRowCount("Sheet1");
		
		Object[][] a=new Object[3][2];
		for(int i=0;i<count;i++)
		{
		a[i][0]=e.getDataFromExcel("Sheet1", i+1, 0);
		a[i][1]=e.getDataFromExcel("Sheet1", i+1, 1);
		}
		return a;
	}
}

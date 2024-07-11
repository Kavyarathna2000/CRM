package contacts;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class CreateContacttest 
{
	@Test
	public void CreateContact() throws IOException, ParseException
	{
		/*
		 * //read data from Property file FileInputStream fis=new
		 * FileInputStream("E:\\Desktop\\Notepad++\\CommonData.properties"); Properties
		 * pobj=new Properties(); pobj.load(fis);
		 * 
		 * String browser=pobj.getProperty("browser"); String
		 * url=pobj.getProperty("url"); String un=pobj.getProperty("username"); String
		 * pwd=pobj.getProperty("password");
		 */
		
		//read data from jsonfile
		FileReader flir = new FileReader("./configAppData/appCommonData.json");
		JSONParser parser=new JSONParser();
		Object obj = parser.parse(flir);
		JSONObject map=(JSONObject)obj;
		
		String browser=map.get("browser").toString();
		String url=map.get("url").toString();
		String un=map.get("username").toString();
		String pwd=map.get("password").toString();
		
		//To generate random number
				Random ran=new Random();
				int random=ran.nextInt(1000);
		
		//To read data from Excel
		FileInputStream fis1=new FileInputStream("C:\\Users\\Admin\\Desktop\\Maven\\TestScriptdata.xlsx");
		Workbook w=WorkbookFactory.create(fis1);
		Sheet s=w.getSheet("contact");
		Row r = s.getRow(1);
		String lastName=r.getCell(2).toString()+random;
		w.close();
		
		
		
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
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();
		
		//navigate to Contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on create Contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//enter all details and create new Contact
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify the expected result
		//verify   lastName in Expected Result
		String lastnameinfo = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(lastnameinfo.contains(lastName))
		{
			System.out.println(lastName + " is created==>PASS");
		}
		else
		{
			System.out.println(lastName + " is not Created==>FAIL");
		}
		
		
		//Close Application
		driver.close();
	}
}

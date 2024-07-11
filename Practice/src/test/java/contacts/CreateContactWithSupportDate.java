package contacts;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class CreateContactWithSupportDate 
{
	@Test
	public void CreateContact() throws IOException
	{
		//read data from Property file
		FileInputStream fis=new FileInputStream("E:\\Desktop\\Notepad++\\CommonData.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		
		String browser=pobj.getProperty("browser");
		String url=pobj.getProperty("url");
		String un=pobj.getProperty("username");
		String pwd=pobj.getProperty("password");
		
		//To generate random number
				Random ran=new Random();
				int random=ran.nextInt(1000);
		
		//To read data from Excel
		FileInputStream fis1=new FileInputStream("C:\\Users\\Admin\\Desktop\\Maven\\TestScriptdata.xlsx");
		Workbook w=WorkbookFactory.create(fis1);
		Sheet s=w.getSheet("contact");
		Row r = s.getRow(4);
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
		
		//required date
		Date d= new Date();
		
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sim.format(d);
		
		Calendar c=sim.getCalendar();
		c.add(Calendar.DAY_OF_MONTH, 30);
		String endDate = sim.format(c.getTime());
		
		//navigate to Contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on create Contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		//enter all details and create new ContactWithSupportDate		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify the expected result
		//verify   startDate in Expected Result
		String startdateinfo = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(startdateinfo.contains(startDate))
		{
			System.out.println(startDate + " is created==>PASS");
		}
		else
		{
			System.out.println(startDate + " is not Created==>FAIL");
		}
		
		//verify   endDate in Expected Result
				String enddateinfo = driver.findElement(By.id("dtlview_Support End Date")).getText();
				if(enddateinfo.contains(endDate))
				{
					System.out.println(endDate + " is created==>PASS");
				}
				else
				{
					System.out.println(endDate + " is not Created==>FAIL");
				}
		//Close Application
		driver.close();
	}
}

package practice;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentreportsClasss
{
	ExtentReports report;
	@BeforeSuite
	public void configBS()
	{
		//Spark report configuration
				ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
				spark.config().setDocumentTitle("CRM test suite result");
				spark.config().setReportName("CRM Report");
				spark.config().setTheme(Theme.DARK);
				
				//add environment information and create test
				report=new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows-10");
				report.setSystemInfo("Browser", "Chrome");
	}
	@AfterSuite
	public void configAS()
	{
		report.flush();
	}
	@Test
	public void createContactTest()
	{
		WebDriver driver=new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");
		
		TakesScreenshot edriver=(TakesScreenshot)driver;
		String filepath = edriver.getScreenshotAs(OutputType.BASE64);
		
		ExtentTest test=report.createTest("createContactTest");
		test.log(Status.INFO,"Login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if("HDFC".equals("HHDFC"))
		{
			test.log(Status.PASS, "contact is created");
		}
		else
		{
			test.addScreenCaptureFromBase64String(filepath,"ErrorFile");
		}
		
		driver.close();
	}
	@Test
	public void createContactWithOrgTest()
	{
		
		
		ExtentTest test=report.createTest("createContactTestWithOrg");
		test.log(Status.INFO,"Login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if("HDFC".equals("HDFC"))
		{
			test.log(Status.PASS, "contact is created");
		}
		else
		{
			test.log(Status.FAIL, "contact is not created");
		}
		
		System.out.println("login to app");
	}
	@Test
	public void createContactWithphnoTest()
	{
		
		
		ExtentTest test=report.createTest("createContactWithphno");
		test.log(Status.INFO,"Login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if("HDFC".equals("HDFC"))
		{
			test.log(Status.PASS, "contact is created");
		}
		else
		{
			test.log(Status.FAIL, "contact is not created");
		}
		
		System.out.println("login to app");
	}
}

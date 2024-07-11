package practice;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class Pscreenshot 
{
	@Test
	public void amazonTest() throws IOException
	{
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.amazon.in");
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./screenshot/test.png");
		FileHandler.copy(src, dest);
		
	}
}

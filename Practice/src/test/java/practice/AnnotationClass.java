package practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AnnotationClass
{
	@BeforeSuite
	public void configBS()
	{
		System.out.println("BS");
	}
	@BeforeClass
	public void configBC()
	{
		System.out.println("BC");
	}
	@BeforeMethod
	public void configBM()
	{
		System.out.println("BM");
	}
	@Test
	public void createContact()
	{
		System.out.println("Contact");
	}
	@Test
	public void createContactWithPhno()
	{
		System.out.println("ContactPhno");
	}
	
	@AfterMethod
	public void configAM()
	{
		System.out.println("AM");
	}
	@AfterClass
	public void configAC()
	{
		System.out.println("AC");
	}
	@AfterSuite
	public void configAS()
	{
		System.out.println("AS");
	}
}

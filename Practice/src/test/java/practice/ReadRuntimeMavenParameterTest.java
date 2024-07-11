package practice;

import org.testng.annotations.Test;

public class ReadRuntimeMavenParameterTest 
{
	@Test
	public void runtimeParameterTest()
	{
		String url = System.getProperty("url");//to read the data from commandline
		String browser = System.getProperty("browser");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
		System.out.println("Env Data==>"+url);
		System.out.println("Env Data==>"+browser);
		System.out.println("Env Data==>"+username);
		System.out.println("Env Data==>"+password);
	}
}

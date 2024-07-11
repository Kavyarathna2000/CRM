package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContactDataProviderTest
{
	@Test(dataProvider = "getData")
	public void createContact(String FirstName,String LastName,long phno)
	{
		System.out.println("Firstname : "+ FirstName +",  Lastname : "+ LastName + ", Phonenumber : "+phno);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] a=new Object[3][3];
		a[0][0]="Deepak";
		a[0][1]="HR";
		a[0][2]=9876575234l;//l-->long data
		
		a[1][0]="Sam";
		a[1][1]="HD";
		a[1][2]=9876575211l;
		
		a[2][0]="John";
		a[2][1]="Stev";
		a[2][2]=9876575222l;
		return a;
	}
}

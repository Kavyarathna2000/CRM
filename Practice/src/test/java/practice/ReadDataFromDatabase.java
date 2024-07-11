package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class ReadDataFromDatabase
{
	@Test
	public void readDAta() throws SQLException
	{
		//register/load the database driver
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		
		//connect to database
		Connection conn = DriverManager.getConnection("Jdbc:mysql://localhost:3306/project","root","Mummy#143");
		System.out.println("===> Done <===");
		
		//create sql statement
		Statement stat = conn.createStatement();
		
		//execute select query and get result
		int result = stat.executeUpdate("insert into students values('01','Anu','puc')");
		System.out.println(result);
		
		//close the connection
		conn.close();
	}
}

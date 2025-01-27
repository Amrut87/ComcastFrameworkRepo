package basicprograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class SampleUnitTestCheckProjectInBackEnd {

	@Test
	public void projectchecktest () throws SQLException
	{
	String expectedResult="TC_4";
	boolean flag=false;
	Driver driverSQL=new Driver();
	DriverManager.registerDriver(driverSQL);
	
	//Step 2: Connect to the database
	//Use the below statement to connect to the database created in the local machine
	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/dummydatabase", "root", "root");
	//Use the below statement to connect to the ninza_hrm database created in workbench
	//Connection conn=DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
	System.out.println("--------Done----------");
	//Step 3: Create SQL statement
	Statement stat=conn.createStatement();
	//Step 4: Execute SQL query and get the result
	ResultSet resultset = stat.executeQuery("Select * from firsttable");
	while(resultset.next()) {
		String actTaskName=resultset.getString(1);
		if(expectedResult.equals(actTaskName))
		{
			flag=true;
		System.out.println(resultset.getString(1) + "\t" + resultset.getString(2)
		+ "\t" + resultset.getString(3)+ "\t" 
				+ resultset.getString(4));
		}
	}
	if(flag==false)
	{
		System.out.println(expectedResult + "  is not available ---- Fail");
		Assert.fail();
	}
	//Step 5: Close the connection
	conn.close();
}
}

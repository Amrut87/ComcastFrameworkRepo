package basicprograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;
//Program to execute select query

public class JDBCTestSelectQuery {

	public static void main(String[] args) throws SQLException {
		Connection conn= null;
		try {
		//Step 1: Load/register the database driver
		Driver driverSQL=new Driver();
		DriverManager.registerDriver(driverSQL);
		//Step 2: Connect to the database
		//Use the below statement to connect to the database created in the local machine
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/dummydatabase", "root", "root");
		//Use the below statement to connect to the ninza_hrm database created in workbench
		//Connection conn=DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
		System.out.println("---------------Connected successfully----------");
		//Step 3: Create SQL statement
		Statement stat=conn.createStatement();
		//Step 4: Execute SQL query and get the result
		ResultSet resultset = stat.executeQuery("Select * from firsttable");
		while(resultset.next()) {
			System.out.println(resultset.getString(1) + "\t" + resultset.getString(2)
			+ "\t" + resultset.getString(3)+ "\t" 
					+ resultset.getString(4));
		}
		}
		catch(Exception e) {
			System.out.println("Handle the exception");
		}
		finally {
			//Step 5: Close the connection
			conn.close();			
			System.out.println("---------------Close the connection---------------");
		}
	}
}


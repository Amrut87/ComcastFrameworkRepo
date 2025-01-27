package basicprograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;
//To execute a non-select query
public class JDBCTestNonSelectQuery {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//Step 1: Load/register the database driver
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
		int result = stat.executeUpdate("Insert into FirstTable(TestCase, Owner, Mob_no, Status) VALUES ('TC_4', 'Sunil', 955654454, 'Delayed')");
			System.out.println(result);
		//Step 5: Close the connection
		conn.close();
	}
}
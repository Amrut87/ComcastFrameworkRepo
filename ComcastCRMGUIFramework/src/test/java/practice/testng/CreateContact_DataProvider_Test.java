package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContact_DataProvider_Test {
	@Test (dataProvider = "getData")
	public void createContactTest(String firstName, String lastName) {
		System.out.println("First name: " + firstName + " Last name: " + lastName);
	}
	@DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][2];
		objArr[0][0] = "Gaurav";
		objArr[0][1] = "admin";
		
		objArr[1][0] = "Amogh";
		objArr[1][1] = "user";
		
		objArr[2][0] = "Tilak";
		objArr[2][1] = "Varma";
		
		return objArr;
	}
}
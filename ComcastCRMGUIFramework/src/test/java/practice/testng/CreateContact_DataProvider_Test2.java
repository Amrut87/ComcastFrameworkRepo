package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContact_DataProvider_Test2 {
	@Test (dataProvider = "getData")
	public void createContactTest(String firstName, String lastName, long phoneNumber) {
		System.out.println("First name: " + firstName + " Last name: " + lastName + " phone number: " + phoneNumber);
	}
	@DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][3];
		objArr[0][0] = "Gaurav";
		objArr[0][1] = "admin";
		objArr[0][2] = 954545445l;
		
		objArr[1][0] = "Amogh";
		objArr[1][1] = "user";
		objArr[1][2] = 954545442l;
		
		objArr[2][0] = "Tilak";
		objArr[2][1] = "Varma";
		objArr[2][2] = 9545325445l;
		
		return objArr;
	}
}
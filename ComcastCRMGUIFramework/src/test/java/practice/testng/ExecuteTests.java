package practice.testng;

import org.testng.annotations.Test;

public class ExecuteTests {
	
	    @Test(dataProvider = "userData", dataProviderClass = practice.testng.TestDataProvider.class)
	    public void testUserDetails(String name, int age, String profession) {
	        System.out.println("Name: " + name);
	        System.out.println("Age: " + age);
	        System.out.println("Profession: " + profession);
	        System.out.println("-------------");
	    }
	}
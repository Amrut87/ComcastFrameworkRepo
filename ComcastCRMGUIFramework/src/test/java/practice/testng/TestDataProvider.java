package practice.testng;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
	
	    @DataProvider(name = "userData")
	    public Object[][] getUserData() {
	        return new Object[][] {
	            {"John", 25, "Engineer"},
	            {"Alice", 30, "Doctor"},
	            {"Bob", 22, "Designer"}
	        };
	    }
	}
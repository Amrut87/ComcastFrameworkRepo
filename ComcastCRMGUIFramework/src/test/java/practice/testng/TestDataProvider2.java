package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class TestDataProvider2 {
	
	    @DataProvider(name = "excelData")
	    public Object[][] provideExcelData() throws Exception {
	    	ExcelUtility eLib=new ExcelUtility();
			int rowCount=eLib.getRowCount("DataProvider Example");
			
			Object[][] objArr = new Object[rowCount][3];
			
			for(int i=0; i<rowCount; i++)
			{
				objArr[i][0] = eLib.getDataFromExcel("DataProvider Example", i+1, 0);
				objArr[i][1] = Integer.parseInt(eLib.getDataFromExcel("DataProvider Example", i+1, 1));
				objArr[i][2] = eLib.getDataFromExcel("DataProvider Example", i+1, 2);
			}
			return objArr;       
	    }
	    
	        @Test(dataProvider = "excelData")
	        public void testUserDetails(String name, int age, String profession) {
	            System.out.println("Name: " + name);
	            System.out.println("Age: " + age);
	            System.out.println("Profession: " + profession);
	            System.out.println("-------------");
	        }
	}
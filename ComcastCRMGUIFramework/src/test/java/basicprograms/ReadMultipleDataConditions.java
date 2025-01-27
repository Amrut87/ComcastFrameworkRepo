package basicprograms;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataConditions {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
	String expectedCondition="TC_35";
	String data1="";
	String data2="";
	String data3="";
	boolean flag= false;
	FileInputStream fis=new FileInputStream("./src/test/resources/Properties/Recruiters hiring preference.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sheet = wb.getSheet("Test cases");
	int rowCount = sheet.getLastRowNum();
	for(int i=0; i<=rowCount; i++)
	{
		String data=sheet.getRow(i).getCell(0).toString();
		if(data.equals(expectedCondition))
		{
			flag=true;
			 data1 = sheet.getRow(i).getCell(0).toString();
			 data2 = sheet.getRow(i).getCell(1).toString();
			 data3 = sheet.getRow(i).getCell(2).toString();
		}
	}
	if(flag==true)
	{
		System.out.println(data1);
		System.out.println(data2);
		System.out.println(data3);
	}
	}
}

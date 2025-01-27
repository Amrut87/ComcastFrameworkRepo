package basicprograms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataBackToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\Smitha\\Documents\\TekPyramid\\Projects\\Data Driven Testing\\Recruiters hiring preference.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet("Test cases");
		Row row = sheet.getRow(1);
		Cell cel = row.getCell(3);
		cel.setCellType(CellType.STRING);
		cel.setCellValue("Hired");
		System.out.println(cel);
		FileOutputStream fos=new FileOutputStream("C:\\Users\\Smitha\\Documents\\TekPyramid\\Projects\\Data Driven Testing\\Recruiters hiring preference.xlsx");
		wb.write(fos);
		wb.close();
	}
}

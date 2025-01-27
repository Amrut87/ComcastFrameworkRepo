package basicprograms;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

//for n number of rows and n number of columns
public class ReadMultipleRow {

	/*
	 * public static void main(String[] args) throws EncryptedDocumentException,
	 * IOException { FileInputStream fis=new
	 * FileInputStream("./src/test/resources/Properties/Recruiters hiring preference.xlsx"
	 * ); Workbook wb = WorkbookFactory.create(fis); Sheet sheet =
	 * wb.getSheet("Recruiter hiring"); int rowCount = sheet.getLastRowNum();
	 * for(int i=1; i<=rowCount; i++) { Row row = sheet.getRow(i); short cel =
	 * row.getLastCellNum(); for(int j=0; j<cel; j++) { Cell cel1 =
	 * sheet.getRow(i).getCell(j); System.out.println(cel1); } } wb.close(); }
	 */

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// Path to the Excel file
		FileInputStream fis = new FileInputStream("./src/test/resources/Properties/Recruiters hiring preference.xlsx");

		// Load the workbook
		Workbook wb = WorkbookFactory.create(fis);
		// Select the sheet
		Sheet sheet = wb.getSheet("Recruiter hiring");

		// Get the total number of rows
		int rowCount = sheet.getLastRowNum();

		// Loop through rows
		for (int i = 0; i <= rowCount; i++) {
			Row row = sheet.getRow(i); // Fetch the current row

			if (row != null) { // Ensure the row is not null
				short cellCount = row.getLastCellNum(); // Get the total number of cells in the row

				// Loop through cells
				for (int j = 0; j < cellCount; j++) {
					Cell cell = row.getCell(j); // Fetch the current cell

					if (cell != null) { // Ensure the cell is not null
						// Get the cell value based on its type
						switch (cell.getCellType()) {
						case STRING:
							System.out.print(cell.getStringCellValue() + "\t");
							break;
						case NUMERIC:
							System.out.print(cell.getNumericCellValue() + "\t");
							break;
						case BOOLEAN:
							System.out.print(cell.getBooleanCellValue() + "\t");
							break;
						case FORMULA:
							System.out.print(cell.getCellFormula() + "\t");
							break;
						default:
							System.out.print("UNKNOWN\t");
						}
					} else {
						System.out.print("NULL\t"); // Handle empty cells
					}
				}
				System.out.println(); // Move to the next line after printing all cells in the row
			}
		}

		// Close the workbook
		wb.close();
		fis.close();
	}
}
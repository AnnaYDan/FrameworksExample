package utility;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WritingToExcelFile {

	public void writeExcel(String filePath, String sheetName, String[] dataToWrite) throws IOException {

		// Create a object of File class to open xlsx file

		File file = new File(filePath);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workBook = null;

		// Find the file extension by spliting file name in substing and getting only
		// extension name

		String fileExtensionName = filePath.substring(filePath.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			workBook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class

			workBook = new HSSFWorkbook(inputStream);

		}

		// Read excel sheet by sheet name

		Sheet sheet = workBook.getSheet(sheetName);

		// Get the current count of rows in excel file

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		// Get the first row from the sheet

		Row row = sheet.getRow(0);

		// Create a new row and append it at last of sheet

		Row newRow = sheet.createRow(rowCount + 1);

		// Create a loop over the cell of newly created Row

		for (int j = 0; j < row.getLastCellNum(); j++) {

			// Fill data in row

			Cell cell = newRow.createCell(j);

			cell.setCellValue(dataToWrite[j]);

		}

		// Close input stream

		inputStream.close();

		// Create an object of FileOutputStream class to create write data in excel file

		FileOutputStream outputStream = new FileOutputStream(file);

		// write data in the excel file

		workBook.write(outputStream);

		// close output stream

		outputStream.close();

	}

	public static void main(String... strings) throws IOException {

		// Create an array with the data in the same order in which you expect to be
		// filled in excel file

		String[] valueToWrite = { "Donal", "DC" };

		// Create an object of current class

		WritingToExcelFile objExcelFile = new WritingToExcelFile();

		// Write the file using file name , sheet name and the data to be filled

		objExcelFile.writeExcel(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\ExportExcel.xlsx",
				"ExcelDemo", valueToWrite);
		System.out.println("(" + valueToWrite[0] + " , " + valueToWrite[1] + ") is Addes Successfully");

	}

}
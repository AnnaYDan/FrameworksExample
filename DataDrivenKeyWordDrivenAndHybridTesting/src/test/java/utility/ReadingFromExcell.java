package utility;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingFromExcell {

	public static String[][] readExcel(String filePath, String sheetName, int startRow, int endRow, int startColumn,
			int endColumn) throws IOException {

		// Create a object of File class to open xlsx file
		File file = new File(filePath);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workBook = null;

		// Find the file extension by spliting file name in substring and getting only
		// extension name
		String fileExtensionName = filePath.substring(filePath.indexOf("."));

		// Check condition if the file is xlsx for xls type
		if (fileExtensionName.equals(".xlsx")) {
			workBook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			workBook = new HSSFWorkbook(inputStream);
		}

		// Read sheet inside the workbook by its name
		Sheet workSheet = workBook.getSheet(sheetName);
		// Find number of rows in excel file
		int rowSize = endRow - startRow + 1; // workSheet.getLastRowNum()-workSheet.getFirstRowNum();
		int columnSize = endColumn - startColumn + 1;
		// Create a loop over all the rows of excel file to read it
		String[][] dataFromExcel = new String[rowSize][columnSize];
		// for (int r = startRow-1; r <= endRow-1; r++) {
		for (Row row : workSheet) {
			int currentRowNumber = row.getRowNum(); // gives current row index
			if (currentRowNumber < startRow - 1)
				continue;
			if (currentRowNumber > endRow - 1)
				break;
			int currentColumnNumber = 0;

			for (Cell cell : row) {
				currentColumnNumber = cell.getColumnIndex();
				if (currentColumnNumber < startColumn - 1)
					continue;
				if (currentColumnNumber > endColumn - 1)
					break;
				dataFromExcel[currentRowNumber - (startRow - 1)][currentColumnNumber - (startColumn - 1)] = cell
						.getStringCellValue();
			}
		}
		return dataFromExcel;
	}

	// Main function is calling readExcel function to read data from excel file
	public static void main(String[] Args) throws IOException {
		// Create a object of ReadExcelFile class
		// Prepare the path of excel file
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\TestCase.xlsx";
		// Call read file method of the class to read data
		String result[][] = readExcel(filePath, "testSteps", 1, 5, 1, 5);
		for (int r = 0; r < result.length; r++) {
			for (int c = 0; c < result[r].length; c++) {
				System.out.print("--" + result[r][c]);
			}
			System.out.println();
		}
		System.out.println();

	}

}

package test1.test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;


public class ExcelOperation {
	public void writeExcel(String filePath, String fileName, String sheetName, ArrayList<InputData> dataToWrite)
			throws IOException {

		// Create an object of File class to open xlsx file

		File file = new File(filePath + "\\" + fileName);
		boolean fileExist = file.createNewFile();

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook wbk = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			wbk = new XSSFWorkbook();

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class

			wbk = new HSSFWorkbook();

		}

		// Read excel sheet by sheet name

		Sheet sheet = wbk.createSheet(sheetName);

		// Create a new row and append it at last of sheet

		Row newRow = null;
		// Create a loop over the cell of newly created Row

		for (int j = 0; j < dataToWrite.size(); j++) {

			// Fill data in row
			newRow = sheet.createRow(j + 1);
			org.apache.poi.ss.usermodel.Cell cell = newRow.createCell(0);
			 Reporter.log("Writing data in " + (j+1) + " row and 1st column as" + dataToWrite.get(j).getLink());
			cell.setCellValue(dataToWrite.get(j).getLink());

		}

		// Close input stream

		// Create an object of FileOutputStream class to create write data in excel file

		FileOutputStream outputStream = new FileOutputStream(file);

		// write data in the excel file

		wbk.write(outputStream);

		// close output stream

		outputStream.close();

	}

	public void InitiateExcel(ArrayList<InputData> data) {

		// Create an object of current class

		// Write the file using file name, sheet name and the data to be filled

		try
		{
			Reporter.log("In Excel Operatiob Initiate method");
			this.writeExcel("D:\\vijiworkspace\\TestFiles", "ExportExcel.xlsx", "GoogleLinks", data);
			Reporter.log("Excel writing completed");
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("Excel opertaion throws exception " + e);
		}

	}

}

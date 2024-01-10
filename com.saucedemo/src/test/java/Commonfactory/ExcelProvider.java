package Commonfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class ExcelProvider {
	XSSFWorkbook workbook;
	static WebDriver driver;
	

	public ExcelProvider() throws IOException {
	
		File file = new File("./TestData/saucelogin.xlsx");
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(fileInputStream);
		} catch (Exception e) {
			System.out.println("unable to excel file"+e.getMessage());
			
		} 
	}

	public String getstringdata(String Sheetname, int Row, int column) {
		XSSFCell cell = workbook.getSheet(Sheetname).getRow(Row).getCell(column);
		DataFormatter dataFormatter = new DataFormatter();
		String data = dataFormatter.formatCellValue(cell);
		return data;
	}

	public double getnumericaldata(String Sheetname, int Row, int coloumn) {
		return workbook.getSheet(Sheetname).getRow(Row).getCell(coloumn).getNumericCellValue();
	}
}

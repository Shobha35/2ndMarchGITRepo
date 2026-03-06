package Utilities2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ConstantData.ConstantsData;

public class FetchDataFromExcelFile 
{
	public static String getURL(int x, int y) throws IOException
	{
		FileInputStream fs=new FileInputStream(ConstantsData.EXCEL_PATH);
		XSSFWorkbook workbook=new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFCell val = sheet.getRow(x).getCell(y);
		String URL=val.toString();
		return URL;
	}

	public static void writeCustomerID(String custID) throws IOException {
	    FileInputStream fs = new FileInputStream(ConstantsData.EXCEL_PATH);
	    XSSFWorkbook workbook = new XSSFWorkbook(fs);
	    XSSFSheet sheet = workbook.getSheetAt(0);

	    XSSFRow row = sheet.getRow(1);
	    if (row == null) row = sheet.createRow(1);

	    row.createCell(1).setCellValue(custID);

	    XSSFCell extraCell = row.getCell(2);
	    if (extraCell != null) {
	        row.removeCell(extraCell);
	    }

	    int lastRow = sheet.getLastRowNum();
	    for (int i = lastRow; i >= 2; i--) {
	        XSSFRow oldRow = sheet.getRow(i);
	        if (oldRow != null) sheet.removeRow(oldRow);
	    }

	    fs.close();
	    FileOutputStream fos = new FileOutputStream(ConstantsData.EXCEL_PATH);
	    workbook.write(fos);
	    fos.close();
	    workbook.close();
	}
	
	// Fetch customer id
	
	public static String getCustomerID(int rowNum) throws IOException {
	    FileInputStream fs = new FileInputStream(ConstantsData.EXCEL_PATH);
	    XSSFWorkbook workbook = new XSSFWorkbook(fs);
	    XSSFSheet sheet = workbook.getSheetAt(0);
	    
	    // Always read from Column B (Index 1)
	    String id = sheet.getRow(rowNum).getCell(1).getStringCellValue();
	    
	    workbook.close();
	    return id;
	}
	
	public static void writeAccountID(String accID) throws IOException {
	    FileInputStream fs = new FileInputStream(ConstantsData.EXCEL_PATH);
	    XSSFWorkbook workbook = new XSSFWorkbook(fs);
	    XSSFSheet sheet = workbook.getSheetAt(0);

	    // Ensure headers exist
	    XSSFRow header = sheet.getRow(0);
	    if (header == null) {
	        header = sheet.createRow(0);
	    }
	    
	    // Create URL header if missing
	    if (header.getCell(0) == null) {
	        header.createCell(0).setCellValue("URL");
	    }
	    
	    // Create Customer_ID header if missing
	    if (header.getCell(1) == null) {
	        header.createCell(1).setCellValue("Customer_ID");
	    }
	    
	    // CREATE Account_ID header (Column C/Index 2) - THIS IS KEY!
	    if (header.getCell(2) == null) {
	        header.createCell(2).setCellValue("Account_ID");
	    }

	    // Target Row 1 (The data row)
	    XSSFRow row = sheet.getRow(1);
	    if (row == null) {
	        row = sheet.createRow(1);
	    }

	    // Ensure URL from row 0, col 0 is preserved
	    XSSFCell urlCell = sheet.getRow(0).getCell(0);
	    if (urlCell != null && row.getCell(0) == null) {
	        row.createCell(0).setCellValue(urlCell.getStringCellValue());
	    }

	    // Ensure Customer_ID from row 0, col 1 is preserved
	    XSSFCell custIdCell = sheet.getRow(0).getCell(1);
	    if (custIdCell != null && row.getCell(1) == null) {
	        row.createCell(1).setCellValue(custIdCell.getStringCellValue());
	    }

	    // Write Account ID to Column C (Index 2)
	    row.createCell(2).setCellValue(accID);

	    // DELETE all other rows (Row 2 and below)
	    int lastRow = sheet.getLastRowNum();
	    for (int i = lastRow; i >= 2; i--) {
	        XSSFRow oldRow = sheet.getRow(i);
	        if (oldRow != null) {
	            sheet.removeRow(oldRow);
	        }
	    }

	    fs.close();
	    FileOutputStream fos = new FileOutputStream(ConstantsData.EXCEL_PATH);
	    workbook.write(fos);
	    fos.close();
	    workbook.close();
	    System.out.println("Account ID: " + accID + " stored in Column C");
	}
    
  
	
//	public static void initializeExcelFile() throws IOException {
//	    FileInputStream fs = new FileInputStream(ConstantsData.EXCEL_PATH);
//	    XSSFWorkbook workbook = new XSSFWorkbook(fs);
//	    XSSFSheet sheet = workbook.getSheetAt(0);
//	    
//	    // Create headers
//	    XSSFRow header = sheet.getRow(0);
//	    if (header == null) {
//	        header = sheet.createRow(0);
//	    }
//	    
//	    // Set all three headers
//	    header.createCell(0).setCellValue("URL");
//	    header.createCell(1).setCellValue("Customer_ID");
//	    header.createCell(2).setCellValue("Account_ID");
//	    
//	    // Create data row with URL from your screenshot
//	    XSSFRow dataRow = sheet.getRow(1);
//	    if (dataRow == null) {
//	        dataRow = sheet.createRow(1);
//	    }
//	    
//	    // Set URL
//	    dataRow.createCell(0).setCellValue("https://demo.guru99.com/V4/index.php");
//	    
//	    // Set Customer ID from your screenshot
//	    dataRow.createCell(1).setCellValue("6417");
//	    
//	    // Account_ID will be added later
//	    
//	    fs.close();
//	    FileOutputStream fos = new FileOutputStream(ConstantsData.EXCEL_PATH);
//	    workbook.write(fos);
//	    fos.close();
//	    workbook.close();
//	    System.out.println("Excel initialized with URL, Customer_ID, and Account_ID columns");
//	}
	
	public static void initializeExcelFile() throws IOException {
	    FileInputStream fs = new FileInputStream(ConstantsData.EXCEL_PATH);
	    XSSFWorkbook workbook = new XSSFWorkbook(fs);
	    XSSFSheet sheet = workbook.getSheetAt(0);
	    
	    // 1. Handle Headers (Row 0)
	    XSSFRow header = sheet.getRow(0);
	    if (header == null) header = sheet.createRow(0);
	    
	    header.createCell(0).setCellValue("URL");
	    header.createCell(1).setCellValue("Customer_ID");
	    header.createCell(2).setCellValue("Account_ID");
	    
	    // 2. Handle Data (Row 1)
	    XSSFRow dataRow = sheet.getRow(1);
	    if (dataRow == null) dataRow = sheet.createRow(1);
	    
	    // Set URL (Column A)
	    dataRow.createCell(0).setCellValue("https://demo.guru99.com/V4/index.php");
	    
	    // --- FIX: GET CUSTOMER ID FROM SHEET INSTEAD OF HARDCODING ---
	    XSSFCell custIdCell = dataRow.getCell(1);
	    String existingCustID = "";
	    
	    if (custIdCell != null) {
	        // Read existing ID (handles both String and Numeric cells)
	        existingCustID = custIdCell.toString(); 
	        System.out.println("Existing Customer ID found in sheet: " + existingCustID);
	    } else {
	        // If cell is null, create it so it's ready for future steps
	        dataRow.createCell(1);
	        System.out.println("No Customer ID found, cell B2 initialized as empty.");
	    }
	    // -------------------------------------------------------------

	    fs.close();
	    FileOutputStream fos = new FileOutputStream(ConstantsData.EXCEL_PATH);
	    workbook.write(fos);
	    fos.close();
	    workbook.close();
	}
	
	// Add this temporary method to your FetchDataFromExcelFile class to debug
	public static void debugWriteAccountID(String accID) throws IOException {
	    System.out.println("Attempting to write Account ID: " + accID + " to Excel");
	    
	    FileInputStream fs = new FileInputStream(ConstantsData.EXCEL_PATH);
	    XSSFWorkbook workbook = new XSSFWorkbook(fs);
	    XSSFSheet sheet = workbook.getSheetAt(0);
	    
	    // Show current data before writing
	    System.out.println("Before writing:");
	    printRowData(sheet, 0); // Headers
	    printRowData(sheet, 1); // Data row
	    
	    // Write to Column C (Index 2)
	    XSSFRow row = sheet.getRow(1);
	    if (row == null) row = sheet.createRow(1);
	    
	    row.createCell(2).setCellValue(accID);
	    
	    // Show after writing
	    System.out.println("After writing:");
	    printRowData(sheet, 1);
	    
	    fs.close();
	    FileOutputStream fos = new FileOutputStream(ConstantsData.EXCEL_PATH);
	    workbook.write(fos);
	    fos.close();
	    workbook.close();
	    
	    System.out.println("Debug write complete");
	}

	private static void printRowData(XSSFSheet sheet, int rowNum) {
	    XSSFRow row = sheet.getRow(rowNum);
	    if (row != null) {
	        System.out.print("Row " + rowNum + ": ");
	        for (int i = 0; i <= 2; i++) {
	            XSSFCell cell = row.getCell(i);
	            String value = (cell == null) ? "EMPTY" : cell.toString();
	            System.out.print("Col" + i + "=[" + value + "] ");
	        }
	        System.out.println();
	    }
	}
}

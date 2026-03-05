//package PageRepogitory;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import ConstantData.ConstantsData;
//import Utilities2.BaseClass2;
//import Utilities2.FetchDataFromExcelFile;
////import io.cucumber.messages.types.Duration;
//import java.time.Duration; 
//
//public class NewAccount extends BaseClass2 {
//	
//	WebDriver driver;
//	
//	public NewAccount(WebDriver driver)
//	{
//		this.driver = driver;
//		PageFactory.initElements(driver, this);
//	}
//	
//	@FindBy(xpath="//a[text()='New Account']")
//	WebElement accountLink;
//	
//	@FindBy(xpath="//input[@name='cusid']")
//	WebElement custmer_ID;
//	
//	@FindBy(xpath="//select[@name='selaccount']")
//	WebElement account_type;
//	
//	@FindBy(xpath="//input[@name='inideposit']")
//	WebElement amt_deposit;
//	
//	@FindBy(xpath="//input[@type='submit']")
//	WebElement asubmit_btn;
//	
//	@FindBy(xpath="//td[text()='Account ID']/following-sibling::td")
//	WebElement accountID;
//	
//	
//	public void clickOnNewAccount()
//	{
//		accountLink.click();
//	}
//	
//	public void enterCustID(String custid) throws IOException
//	{
//		custmer_ID.sendKeys(custid);
//	}
//	
//	public void selectAccountType(String type)
//	{
//		Select select = new Select(account_type);
//		select.selectByVisibleText(type);
//	}
//	
//	public void initialDiposite(String amt)
//	{
//		amt_deposit.sendKeys(amt);
//	}
//	
//	public void clickOnSubmit()
//	{
//		asubmit_btn.click();
//	}
//	
//	 public static String getAccountID(int rowNum) throws IOException {
//	        FileInputStream fs = new FileInputStream(ConstantsData.EXCEL_PATH);
//	        XSSFWorkbook workbook = new XSSFWorkbook(fs);
//	        XSSFSheet sheet = workbook.getSheetAt(0);
//	        
//	        // Read from Column C (Index 2)
//	        XSSFCell cell = sheet.getRow(rowNum).getCell(2);
//	        String id = cell.getStringCellValue();
//	        
//	        workbook.close();
//	        return id;
//	    }
//	
//	public String getGeneratedAccountID() {
//	    // We use the full path to avoid the "Duration" error
//	    org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
//	    return wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(accountID)).getText();
//	}
////	
////	public String getAccountID() {
////	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
////	    return wait.until(ExpectedConditions.visibilityOf(accountID)).getText();
////	}
//
//}


package PageRepogitory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ConstantData.ConstantsData;
import Utilities2.BaseClass2;
import Utilities2.FetchDataFromExcelFile;

public class NewAccount extends BaseClass2 {
	
	WebDriver driver;
	
	public NewAccount(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='New Account']")
	WebElement accountLink;
	
	@FindBy(xpath="//input[@name='cusid']")
	WebElement custmer_ID;
	
	@FindBy(xpath="//select[@name='selaccount']")
	WebElement account_type;
	
	@FindBy(xpath="//input[@name='inideposit']")
	WebElement amt_deposit;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement asubmit_btn;
	
	@FindBy(xpath="//td[text()='Account ID']/following-sibling::td")
	WebElement accountID;
	
	
	public void clickOnNewAccount()
	{
		accountLink.click();
	}
	
	public void enterCustID(String custid) throws IOException
	{
		custmer_ID.sendKeys(custid);
	}
	
	public void selectAccountType(String type)
	{
		Select select = new Select(account_type);
		select.selectByVisibleText(type);
	}
	
	public void initialDiposite(String amt)
	{
		amt_deposit.sendKeys(amt);
	}
	
	public void clickOnSubmit()
	{
		asubmit_btn.click();
	}
	
	// THIS IS THE METHOD YOU NEED - UNCOMMENT AND USE THIS
	
	
	// In your NewAccount class
	// In your NewAccount class
	public String getGeneratedAccountID() {
	    try {
	        // Wait for the Account ID element to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement accountIdElement = wait.until(ExpectedConditions.visibilityOf(accountID));
	        String accountId = accountIdElement.getText();
	        System.out.println("📋 Account ID extracted from UI: " + accountId);
	        return accountId;
	    } catch (Exception e) {
	        System.out.println("❌ Failed to get Account ID: " + e.getMessage());
	        return "";
	    }
	}
	
	public static String getAccountID(int rowNum) throws IOException {
	    FileInputStream fs = new FileInputStream(ConstantsData.EXCEL_PATH);
	    XSSFWorkbook workbook = new XSSFWorkbook(fs);
	    XSSFSheet sheet = workbook.getSheetAt(0);
	    
	    // Read from Column C (Index 2)
	    XSSFCell cell = sheet.getRow(rowNum).getCell(2);
	    String id = cell.getStringCellValue();
	    
	    workbook.close();
	    return id;
	}
}

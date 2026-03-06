package Utilities2;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BaseClass2 {
	
	static String browserName;
	public static WebDriver driver;
	
	public static WebDriver initializeDriver() 
	{
		try {
			browserName=FetchDataFromPropertyFile.getDataFromProperty().getProperty("browser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			try {
				driver.get(FetchDataFromExcelFile.getURL(1, 0));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.manage().window().maximize();
			
		}
		if (browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
			try {
				driver.get(FetchDataFromExcelFile.getURL(1, 0));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.manage().window().maximize();
			
		}
		
		return driver;
	}
	
	public static String getTitle()
	{
		String title = driver.getTitle();
		return title;
	}
	
	public static String getCurrentURL()
	{
		String CURL = driver.getCurrentUrl();
		return CURL;
	}
	
	public static void scrollpage()
	{
		JavascriptExecutor js =  (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)", "");
		
	}
	
	public static String getEmail()
	{
		String email = "tom" + java.util.UUID.randomUUID().toString().replace("-", "").substring(0,8) + "@gmail.com";
		return email;
		
	}
	
	
	public static void closeBrowser() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.quit();
	}
	
	
	public static void addExplicitWait()
	{
		
		WebDriverWait x=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		WebElement ele=	x.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#Skills")));
	}
}


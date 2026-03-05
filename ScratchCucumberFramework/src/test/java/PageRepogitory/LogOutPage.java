package PageRepogitory;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOutPage {
	
WebDriver driver;
	
	public LogOutPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//a[text()='Log out']")
	WebElement logoutLink;

	@FindBy(name = "uid")
	WebElement userIdField;

	public void clickLogout() {
		  try {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		        
		        // Scroll to top to ensure logout link is visible
		        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		        
		        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
		        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);
		        System.out.println("Clicked Logout using JavaScript");
		        
		    } catch (Exception e) {
		        System.out.println("Failed to click logout: " + e.getMessage());
		    }
	}

	public boolean isLoginFormDisplayed() {
	    return userIdField.isDisplayed();
	}

}

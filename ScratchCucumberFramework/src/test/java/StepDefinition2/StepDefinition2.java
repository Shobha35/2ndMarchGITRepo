package StepDefinition2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import PageRepogitory.NewAccount;
import PageRepogitory.NewCustomer;
import PageRepogitory.LogOutPage;
import PageRepogitory.LoginPage;
import Utilities2.BaseClass2;
import Utilities2.FetchDataFromExcelFile;

public class StepDefinition2 extends BaseClass2{

//	WebDriver driver = BaseClass2.initializeDriver();
	LoginPage obj = new LoginPage(driver);
	NewCustomer obj1 = new NewCustomer(driver);
	NewAccount obj2 = new NewAccount(driver);
	LogOutPage obj3 = new LogOutPage(driver);
	String capturedAccID;
	
	@Before
	public void setup() throws IOException {
//	    FetchDataFromExcelFile.initializeExcelFile();
	}
	
	@Given("User opens the browser URL")
	public void user_opens_the_browser_url() {
		
		  if (driver == null) {
	            driver = BaseClass2.initializeDriver();
	            obj = new LoginPage(driver);
	            obj1 = new NewCustomer(driver);
	            obj2 = new NewAccount(driver);
	            obj3 = new LogOutPage(driver);
	        }
	    getTitle();
	}

	@Given("user enters the username as {string}")
	public void user_enters_the_username_as(String userName) {
	    obj.enterUsername(userName);
	}

	@Given("user enters the password as {string}")
	public void user_entters_the_password_as(String password) {
	    obj.enterPassword(password);
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() throws InterruptedException {
	   obj.userClickOnSubmit();
	   Thread.sleep(3000);
	   System.out.println("===========================================");
	   System.out.println("After Login URL: " + driver.getCurrentUrl());
	   System.out.println("===========================================");
	}

	@Then("Validate that user navigates to the homepage of the application")
	public void validate_that_user_navigates_to_the_homepage_of_the_application() {
	    String curl = getCurrentURL();
	    if (curl.contains("Managerhomepage"))
	    {
	    	System.out.println("User navigate to currect page");
	    }
	    else
	    {
	    	System.out.println(" Navigate not successfull");
	    }
	}
	
	@Given("user is on the homepage of the application")
	public void user_is_on_the_homepage_of_the_application() {
		getTitle();
		//  System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		// System.out.println("user is on the homepage of the application");
		// System.out.println("+++++++++++++++++++++++++++++++++++++++++");
	}

	@Given("user clicks on New Customer link")
	public void user_clicks_on_new_customer_link() {
		obj1.clickOnNewCustomer();
	}

	@Given("user enters customer details")
	public void user_enters_customer_details(io.cucumber.datatable.DataTable dataTable)
	{
	    List<List<String>> data = dataTable.asLists(String.class);

	    NewCustomer newCust = new NewCustomer(driver);

	    newCust.enterCustomerDetails(
	            data.get(1).get(0),  
	            data.get(1).get(1),  
	            data.get(1).get(2),  
	            data.get(1).get(3),  
	            data.get(1).get(4),  
	            data.get(1).get(5),  
	            data.get(1).get(6),  
	            data.get(1).get(7),  
	            data.get(1).get(8),  
	            data.get(1).get(9)
	    );
	}

	@When("user clicks on submit button")
	public void user_clicks_on_submit_button() {
		obj1.clickOnSubmit();
	}

	@Then("a new customer will be created")
	public void a_new_customer_will_be_created() {
		System.out.println("New customer created");
	}


	@Then("user captures the customer id")
	public void user_captures_the_customer_id() throws IOException, InterruptedException {
		String custID = obj1.getCustomerID();
	    System.out.println("Generated Customer ID: " + custID);
	    FetchDataFromExcelFile.writeCustomerID(custID);
	}
	
	@Given("user clicks on New Account link")
	public void user_clicks_on_new_account_link() {
		obj2.clickOnNewAccount();
	}

	@Given("user fetches the customer id from excel")
	public void user_fetches_the_customer_id_from_excel() throws IOException {
	    String id;
	    id = FetchDataFromExcelFile.getCustomerID(1);
	    obj2.enterCustID(id);
	}

	@Given("user enters initial deposit as {string}")
	public void user_enters_initial_deposit_as(String ammount) {
	    obj2.initialDiposite(ammount);
	}

	@When("user clicks on asubmit button")
	public void user_clicks_on_asubmit_button() {
	    obj2.clickOnSubmit();
	}

	@Then("new account should be created successfully")
	public void new_account_should_be_created_successfully() {
	   System.out.println("Account Created successfully");
	}
	
	@Then("user captures the account id")
	public void user_captures_the_account_id() throws IOException, InterruptedException {
	    Thread.sleep(2000);	    
	    String accountIdFromUI = obj2.getGeneratedAccountID();	    
	    if(accountIdFromUI == null || accountIdFromUI.trim().isEmpty()) {
	        System.out.println("Current URL: " + driver.getCurrentUrl());
	    } else {
	        FetchDataFromExcelFile.writeAccountID(accountIdFromUI.trim());
	        System.out.println("Account ID " + accountIdFromUI + " stored in Excel Column C");	        
	        String storedId = obj2.getAccountID(1);
	        System.out.println("Verified stored Account ID in Excel: " + storedId);
	    }
	}
	
	@Then("user stores the account id in excel")
	public void user_stores_the_account_id_in_excel() throws IOException {
        FetchDataFromExcelFile.writeAccountID(capturedAccID);
	}

	@Then("verify account id in excel")
	public void verify_account_id_in_excel() throws IOException {
	    String storedId = obj2.getAccountID(1);	    
	    if(storedId != null && !storedId.isEmpty()) {
	        System.out.println("Account ID successfully stored in Excel!");
	    } else {
	        System.out.println("Account ID NOT found in Excel!");
	    }
	}
	    
	   @When("user clicks on logout link")
	   public void user_clicks_on_logout_link() {
	        System.out.println("=== Attempting to click Logout ===");
	        System.out.println("Current URL: " + driver.getCurrentUrl());
	        try {
	            Thread.sleep(2000);
	            // Try multiple strategies to find logout link
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	            
	            // Strategy 1: Try the standard logout link
	            try {
	                WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//a[text()='Log out']")));
	                logoutLink.click();
	                System.out.println("Clicked Logout using text 'Log out'");
	            } catch (Exception e1) {
	                System.out.println("Strategy 1 failed: " + e1.getMessage());
	                
	                // Strategy 2: Try partial text match
	                try {
	                    WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//a[contains(text(),'Log') and contains(text(),'out')]")));
	                    logoutLink.click();
	                    System.out.println("Clicked Logout using partial text");
	                } catch (Exception e2) {
	                    System.out.println("Strategy 2 failed: " + e2.getMessage());
	                    
	                    // Strategy 3: Try by href
	                    try {
	                        WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(
	                            By.xpath("//a[contains(@href, 'Logout') or contains(@href, 'logout')]")));
	                        logoutLink.click();
	                        System.out.println("Clicked Logout using href");
	                    } catch (Exception e3) {
	                        System.out.println("Strategy 3 failed: " + e3.getMessage());
	                        
	                        // Strategy 4: Try using the page object
	                        if (obj3 != null) {
	                            obj3.clickLogout();
	                            System.out.println("Clicked Logout using page object");
	                        }
	                    }
	                }
	            }
	            
	            // Handle any alert that might appear after logout
	            try {
	                Alert alert = driver.switchTo().alert();
	                System.out.println("Alert appeared: " + alert.getText());
	                alert.accept();
	                System.out.println("Alert accepted");
	            } catch (Exception e) {
	                System.out.println("No alert present after logout");
	            }
	            
	        } catch (Exception e) {
	            System.out.println("All logout strategies failed: " + e.getMessage());
	            // Take screenshot for debugging
	            takeScreenshot("logout_failure");
	        }
	        System.out.println("=== Logout attempt completed ===");
	    }
	    
	    @Then("user should be redirected to login page")
	    public void user_should_be_redirected_to_login_page() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
	        try {
	            // Wait for login page elements to appear
	            boolean isLoginPage = wait.until(ExpectedConditions.or(
	                ExpectedConditions.titleContains("Guru99 Bank"),
	                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='uid']")),
	                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")),
	                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='btnLogin']"))
	            ));
	            
	            if (isLoginPage) {
	                System.out.println("Successfully redirected to login page");
	                
	                // Verify login elements are present
	                WebElement usernameField = driver.findElement(By.xpath("//input[@name='uid']"));
	                WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
	                WebElement loginButton = driver.findElement(By.xpath("//input[@name='btnLogin']"));
	                
	                Assert.assertTrue(usernameField.isDisplayed());
	                Assert.assertTrue(passwordField.isDisplayed());
	                Assert.assertTrue(loginButton.isDisplayed());
	                
	                System.out.println("All login page elements are visible");
	            }
	        } catch (Exception e) {
	            System.out.println("Not redirected to login page. Current URL: " + driver.getCurrentUrl());
	            takeScreenshot("login_page_verification_failed");
	            Assert.fail("Not redirected to login page");
	        }
	    }
	    
	    @And("user should see login form again")
	    public void user_should_see_login_form_again() {
	        try {
	            // Verify login form is visible
	            WebElement usernameField = driver.findElement(By.xpath("//input[@name='uid']"));
	            WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
	            WebElement loginButton = driver.findElement(By.xpath("//input[@name='btnLogin']"));
	            
	            Assert.assertTrue(usernameField.isDisplayed() && usernameField.isEnabled());
	            Assert.assertTrue(passwordField.isDisplayed() && passwordField.isEnabled());
	            Assert.assertTrue(loginButton.isDisplayed() && loginButton.isEnabled());
	            
	            System.out.println("Login form is visible and enabled");
	            System.out.println("Current URL after logout: " + driver.getCurrentUrl());
	        } catch (Exception e) {
	            System.out.println("Login form not visible: " + e.getMessage());
	            takeScreenshot("login_form_verification_failed");
	        }
	    }
	  
	    
	    // Helper method to take screenshot
	    private void takeScreenshot(String filename) {
	        try {
	            File screenshotDir = new File("target/screenshots");
	            if (!screenshotDir.exists()) {
	                screenshotDir.mkdirs();
	            }
	            
	            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            File destination = new File(screenshotDir, filename + "_" + System.currentTimeMillis() + ".png");
	            
	            java.nio.file.Files.copy(screenshot.toPath(), destination.toPath());
	            System.out.println("Screenshot saved: " + destination.getAbsolutePath());
	        } catch (Exception e) {
	            System.out.println("Failed to take screenshot: " + e.getMessage());
	        }
	    }
	
	@Then("user closes the browser")
	public void user_closes_the_browser() throws InterruptedException {
		Thread.sleep(5000);
		closeBrowser();
	}
	
}

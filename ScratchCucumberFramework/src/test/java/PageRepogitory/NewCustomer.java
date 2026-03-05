package PageRepogitory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import Utilities2.BaseClass2;

public class NewCustomer extends BaseClass2{
	
	WebDriver driver;


	public NewCustomer(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[text()='New Customer']")
	WebElement newCustLink;
	
	// Customer Name
	@FindBy(name="name")
	WebElement customerName;

	// Gender - 
	@FindBy(xpath="//input[@value='m']")
	WebElement mgender;

	// Gender - Female
//	@FindBy(xpath="//input[@value='f']")
//	WebElement fGender;

	// Date Of Birth
	@FindBy(name="dob")
	WebElement dob;

	// Address
	@FindBy(name="addr")
	WebElement address;

	// City
	@FindBy(name="city")
	WebElement city;

	// State
	@FindBy(name="state")
	WebElement state;

	// PIN
	@FindBy(name="pinno")
	WebElement pin;

	// Mobile Number
	@FindBy(name="telephoneno")
	WebElement mobileNumber;

	// Email
	@FindBy(name="emailid")
	WebElement email;

	// Password
	@FindBy(name="password")
	WebElement password;

	// Submit Button
	@FindBy(name="sub")
	WebElement submitBtn;

	@FindBy(xpath="//td[text()='Customer ID']/following-sibling::td")
	WebElement customerID;

	
	public void clickOnNewCustomer()
	{
		newCustLink.click();
	}
	
	public void enterUserName(String uname)
	{
		customerName.sendKeys(uname);
	}
	
	public void selectGender()
	{
		mgender.click();
	}
	
	public void getDOB(String bdate)
	{
		dob.sendKeys(bdate);
	}
	
	public void enterAddress(String addr)
	{
		address.sendKeys(addr);
	}
	
	public void enterCity(String ccity)
	{
		city.sendKeys(ccity);
	}
	
	public void enterState(String sstate)
	{
		state.sendKeys(sstate);
	}
	
	public void enterPin(String pinno)
	{
		pin.sendKeys(pinno);
	}
	
	public void enterMobileNo(String mno)
	{
		mobileNumber.sendKeys(mno);
	}
	
	public void enterEmail(String mail)
	{
		email.sendKeys(mail);
	}
	
	public void enterPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	
	public void clickOnSubmit()
	{
		submitBtn.click();
	}
	
	public void enterCustomerDetails(String cname, String gender, String bdate,
	        String addr, String ccity, String sstate, String pinno,
	        String mobile, String mail, String pwd)
	{
		customerName.sendKeys(cname);
		mgender.sendKeys(gender);
	    dob.sendKeys(bdate);
	    address.sendKeys(addr);
	    city.sendKeys(ccity);
	    state.sendKeys(sstate);
	    pin.sendKeys(pinno);
	    mobileNumber.sendKeys(mobile);
//	    email.sendKeys(mail);
	    email.sendKeys(getEmail());
	    password.sendKeys(pwd);
	}
	
//	public String getCustomerID() {
//	    return customerID.getText();
//	}
	
	public String getCustomerID()
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    WebElement custIdElement = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td")
	        )
	    );

	    return custIdElement.getText();
	}
	
}

package com.freecrm.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.freecrm.base.TestBase;
public class Login extends TestBase {
	WebDriver driver;
	//page factory 
	public Login(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(id = "hs-login")
	WebElement form;
	
	@FindBy(id = "username")
	WebElement EmailAddress;
	
	@FindBy(id = "password")
	WebElement Password;
	
	@FindBy(id = "loginBtn")
	WebElement Log_In;
	
	
	public WebElement getForm() {
		return form;
	}
	
	public Home login(String usr, String pwd) {
		log.info("Entering Email Address on Login Page.");
		EmailAddress.sendKeys(usr);
		log.info("Entering Password on Login Page.");
		Password.sendKeys(pwd);
		log.info("Clicking on Login button of Login Page.");
		Log_In.click();
		log.info("******************Navigating to Home Page******************");
		return new Home(driver);
	}

}

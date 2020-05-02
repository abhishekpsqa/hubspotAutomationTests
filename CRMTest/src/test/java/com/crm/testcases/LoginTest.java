package com.crm.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.freecrm.base.TestBase;
import com.freecrm.pages.Login;
public class LoginTest extends TestBase {
	
	WebDriver driver;
	Login loginPage;
	
	public  LoginTest() {
		super();
	}
	
	
	@BeforeMethod(alwaysRun=true)
	@Parameters("myBrowser")
	public void setUp(String myBrowser) {
		driver = initialization(myBrowser);
		loginPage = new Login(driver);		
	}
	
	@Test(priority = 1,groups={"Smoke"})
	public void testLoginInCRM() {
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		log.info("Quitting browser...");
		driver.quit();
	}
	
}

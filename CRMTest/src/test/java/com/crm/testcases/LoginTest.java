package com.crm.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.freecrm.base.TestBase;
import com.freecrm.pages.Home;
import com.freecrm.pages.Login;



public class LoginTest extends TestBase {
	Login loginPage;
	Home homePage;
	
	public  LoginTest() {
		super();
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {
		initialization();
		loginPage = new Login();		
	}
	
	@Test(priority = 1,groups={"Smoke"})
	public void testLoginInCRM() {
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		log.info("Quitting browser...");
		driver.quit();
	}
	
}

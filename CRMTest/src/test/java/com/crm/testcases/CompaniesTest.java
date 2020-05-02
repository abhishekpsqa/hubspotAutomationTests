package com.crm.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.freecrm.base.TestBase;
import com.freecrm.pages.Companies;
import com.freecrm.pages.Contacts;
import com.freecrm.pages.Home;
import com.freecrm.pages.Login;
import com.freecrm.utils.TestUtils;

public class CompaniesTest extends TestBase {
	WebDriver driver;
	Login loginPage;
	Home homePage;
	Contacts contactPage;
	Companies compniesPage;
	TestUtils utils;
	public CompaniesTest() {
		
		super();
	}
	
	@BeforeMethod(alwaysRun=true)
	@Parameters("myBrowser")
	public void setUp(String myBrowser) {
		driver = initialization(myBrowser);
		loginPage = new Login(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage.clickOnContactDD();
		contactPage = homePage.clickOnContactsOfContactDD();
		compniesPage = contactPage.goToCompaniesPage();
	}
	
	@Test
	public void testSignOut() throws InterruptedException{
		compniesPage.clickOnUserAccount();
		compniesPage.clickOnSignOut();
		log.info("Checking if Form on Login page is displayed.");
		Assert.assertTrue(loginPage.getForm().isDisplayed());
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		log.info("Quitting browser...");
		driver.quit();
	}
	
}

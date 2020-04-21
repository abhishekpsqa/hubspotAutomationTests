package com.crm.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.freecrm.base.TestBase;
import com.freecrm.pages.Companies;
import com.freecrm.pages.Contacts;
import com.freecrm.pages.Home;
import com.freecrm.pages.Login;
import com.freecrm.utils.TestUtils;

public class CompaniesTest extends TestBase {
	Login loginPage;
	Home homePage;
	Contacts contactPage;
	Companies compniesPage;
	TestUtils utils;
	public CompaniesTest() {
		
		super();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setUp() {
		initialization();
		loginPage = new Login();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage.clickOnContactDD();
		contactPage = homePage.clickOnContactsOfContactDD();
		compniesPage = contactPage.goToCompaniesPage();
	}
	
	@Test
	public void testSignOut() {
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

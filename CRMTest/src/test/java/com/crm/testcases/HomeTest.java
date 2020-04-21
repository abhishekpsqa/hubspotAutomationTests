package com.crm.testcases;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.freecrm.base.TestBase;
import com.freecrm.pages.Home;
import com.freecrm.pages.Login;
import com.freecrm.utils.TestUtils;

public class HomeTest extends TestBase {
	TestUtils utils = new TestUtils();
	public HomeTest() {
		super();
	}
	
	
	Login loginPage;
	Home homePage;
	SoftAssert sa= new SoftAssert();
	@BeforeMethod(alwaysRun=true)
	public void setUp() {
		initialization();
		loginPage = new Login();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		log.info("Quitting browser...");
		driver.quit();
	}
	
	@Test (priority = 1)
	public void testHomePageHeaderDropdownLinks() {
		List<String> ActualLinks = homePage.getHeaderDropdownLinks();
		List<String> ExpectedLinks = Arrays.asList("Contacts", "Conversations", "Marketing", "Sales", "Service","Automation","Reports");
		log.info("Checking if drop down links found on home page matches with expected link list. ");
		for(int i =0; i< ExpectedLinks.size(); i++) {
			sa.assertEquals(ExpectedLinks.get(i), ActualLinks.get(i));	
		}
		sa.assertAll();		
	}
	
	@Test (priority = 2, groups= {"Smoke"})
	public void testHomePageUrl() throws InterruptedException {
		log.info("Waiting for 2 seconds.");
		Thread.sleep(2000);
		log.info("Asserting Home page URL.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.urlContains("getting-started"));
		Assert.assertTrue(driver.getCurrentUrl().contains("getting-started"));
	}
	
}

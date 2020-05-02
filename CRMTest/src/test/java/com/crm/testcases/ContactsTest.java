package com.crm.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.freecrm.base.TestBase;
import com.freecrm.pages.Contacts;
import com.freecrm.pages.Home;
import com.freecrm.pages.Login;
import com.freecrm.utils.TestUtils;

public class ContactsTest extends TestBase {
	WebDriver driver;
	TestUtils utils = new TestUtils();
	Login loginPage;
	Home homePage;
	Contacts contactPage;
	SoftAssert sa = new SoftAssert();
	@BeforeMethod(alwaysRun=true)
	@Parameters("myBrowser")
	public void setUp(String myBrowser) {
		driver = initialization(myBrowser);
		loginPage = new Login(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage.clickOnContactDD();
		contactPage = homePage.clickOnContactsOfContactDD();
	}
	
	
	public ContactsTest() {
		super();
	}
	
	@DataProvider
	public Object[][] getContactsDetails() {
		return utils.getData("Contacts");
	}
	
	
	@Test(priority =2, dataProvider = "getContactsDetails")
	public void testcreateContacts(String EmailAddress, String FirstName,String LastName, String JobTitle, String PhoneNumber, String LeadStatus) throws InterruptedException {
		contactPage.clickOnCreateContactButton();
		contactPage.enterEmailAddress(EmailAddress);
		contactPage.enterFirstName(FirstName);
		contactPage.enterLastName(LastName);
		contactPage.enterJobTitle(JobTitle);
		contactPage.enterPhoneNumber(PhoneNumber);
		contactPage.enterLeadStatus(LeadStatus);
		contactPage.clickOnCreateContactButtonOfCurtain();
		Thread.sleep(1500);
	}
	@Test(priority = 1)
	public void testContactsTitle() {
		Assert.assertEquals(contactPage.getTitle(), "Contacts","Contacts title was there on Contacts page.");
		
	}
	@Test(priority =3, dependsOnMethods = {"testcreateContacts"})
	public void testIfContactsCreated() throws InterruptedException {
		
		utils.getData("Contacts");
		int totalRows = utils.sheet.getLastRowNum();
		String firstName;
		
		for(int i =0; i< totalRows; i++) {
			firstName = utils.sheet.getRow(i+1).getCell(1).getStringCellValue();
			contactPage.enterContactToSearch(firstName);
			Thread.sleep(2000);
			log.info("Asserting if "+firstName+" present in contacts table of contacts page.");
			sa.assertTrue(contactPage.isContactsFirstNamePresent(firstName),firstName+" was found under created contacts table as expected.");
			
		}
		sa.assertAll();
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearUp() {
		log.info("Quitting browser...");
		driver.quit();
	}
	
}

package com.freecrm.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.freecrm.base.TestBase;
import com.freecrm.utils.TestUtils;

public class Contacts extends TestBase {

	Home homePage = new Home();
	TestUtils utils = new TestUtils();

	public Contacts() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//i18n-string[contains(text(),'Contacts')]")
	WebElement Title;
	
	@FindBy(xpath = "//button//*[contains(text(), 'Create contact')]")
	WebElement CreateContactButton;
	
	@FindBy(xpath = "//input[@data-field='email']")
	WebElement inputEmail;
	
	@FindBy(xpath = "//input[@data-field='firstname']")
	WebElement inputFirstName;
	
	@FindBy(xpath = "//input[@data-field='lastname']")
	WebElement inputLastName;
	
	@FindBy(xpath = "//input[@data-field='jobtitle']")
	WebElement inputJobtitle;
	
	@FindBy(xpath = "//input[@data-field='phone']")
	WebElement inputPhoneNumber;
	
	@FindBy(xpath="//label[contains(text(), 'Lead status')]/parent::div/../div[2]")
	WebElement LeadStatusDD;
	
	@FindBy(xpath = "//div[@class='private-search-control__wrapper']//input[@placeholder='Search']")
	WebElement LeadStatusSearchInput;
	
	@FindBy(xpath = "//button//*[contains(text(), 'Create and add another')]")
	WebElement CreateAndAddAnotherButton;
	
	@FindBy(xpath = "//button[@type='button']//span[text() = 'Cancel']")
	WebElement CancelButton;
	
	@FindBy(xpath = "//input[@placeholder = 'Search']")
	WebElement SearchBox;
	
	@FindBy(xpath = "//td[2]")
	WebElement CellWithFirstName;
	
	@FindBy(xpath="//li[contains(@class, 'uiListItem')]//*[contains(text(), 'Create contact')]/parent::button")
	WebElement createContactsButtonOnCurtain;
	
	public void clickOnCreateContactButtonOfCurtain() {
		log.info("Clicking on creat contacts button on curtain of Contacts page.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.elementToBeClickable(createContactsButtonOnCurtain));
		createContactsButtonOnCurtain.click();
	}
	
	
	public void enterContactToSearch(String FName) {
		log.info("Clearing input searchbox on added contacts table on Contacts page.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.visibilityOf(SearchBox));
		SearchBox.clear();
		log.info("Adding first name in search box to find added contacts on Contacts page.");
		SearchBox.sendKeys(FName);	
	}
	
	
	public boolean isContactsFirstNamePresent(String Fname) throws InterruptedException {
		Thread.sleep(1000);
		String NameFound = driver.findElement(By.xpath("//td[2]//*[contains(text(), '"+Fname+"')]")).getText();
		if(NameFound.contains(Fname)) {
			return true;
		}
			return false;
	}
	
	public void clickOnCancelButton() {
		
		CancelButton.click();
	}
	
	public void clickOnCreateAndAddAnother() {
		CreateAndAddAnotherButton.click();
	}
	
	public void enterLeadStatus(String LeadStatus) {
		log.info("Clicking on Lead status drop down of Contacts page curtain.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.elementToBeClickable(LeadStatusDD));
		LeadStatusDD.click();
		log.info("Entering Lead status in curtain of Contacts page.");
		LeadStatusSearchInput.sendKeys(LeadStatus);
		log.info("Hitting enter key after entering lead status in curtain of Contacts page.");
		LeadStatusSearchInput.sendKeys(Keys.ENTER);
	}
	
	
	public void enterPhoneNumber(String PhoneNumber) {
		log.info("Entering phone no in curtain of Contacts Page.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.visibilityOf(inputPhoneNumber));
		inputPhoneNumber.sendKeys(PhoneNumber);
	}
	
	public void enterJobTitle(String JobTitle ) {
		log.info("Entering Job title in curtain of Contacts Page.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.elementToBeClickable(inputJobtitle));
		inputJobtitle.sendKeys(JobTitle);
	}
	
	public void enterLastName(String LastName) {
		log.info("Entering Last name in curtain of Contacts Page.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.visibilityOf(inputLastName));
		inputLastName.sendKeys(LastName);
	}
	
	public void enterFirstName(String FirstName) {
		log.info("Entering first name in curtain of Contacts Page.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.visibilityOf(inputFirstName));
		inputFirstName.sendKeys(FirstName);
	}
	
	
	
	public void enterEmailAddress(String EmailAddress) {
		log.info("Entering Email address in curtain of Contacts Page.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.visibilityOf(inputEmail));
		inputEmail.sendKeys(EmailAddress);
	}
	
	public void clickOnCreateContactButton() {
		log.info("Clicking on create contact button in curtain of Contacts Page.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.elementToBeClickable(CreateContactButton));
		CreateContactButton.click();
	}
	
	public String getTitle() {
		return Title.getText();
	}
	
	public Companies goToCompaniesPage() {
		//click on contacts
		log.info("Clicking on contacts drop down.");
		homePage.clickOnContactDD();
		//click on companies
		log.info("******************Navigating to Companies Page******************");
		driver.findElement(By.xpath("//li[@class='expandable currentPage active']//*[contains(text(), 'Companies')]")).click();
		return new Companies();
		
	}
	
}

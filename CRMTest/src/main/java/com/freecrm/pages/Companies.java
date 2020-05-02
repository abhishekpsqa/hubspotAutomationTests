package com.freecrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.freecrm.base.TestBase;
import com.freecrm.utils.TestUtils;

public class Companies extends TestBase {
	WebDriver driver;
	TestUtils utils = new TestUtils();
	public Companies(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(id = "account-menu")
	WebElement UserAccount;
	
	@FindBy(className = "signout")
	WebElement SignOut;
	
	public void clickOnUserAccount() {
		log.info("Clicking on User Account on Companies Page.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.elementToBeClickable(UserAccount));
		UserAccount.click();
	}
	
	public void clickOnSignOut() throws InterruptedException {
		Thread.sleep(5000);
		log.info("Clicking on SignOut link on Companies Page.");
		utils.getWait(driver, TestUtils.EXPLICITWAIT_TIMEOUT, TestUtils.POLLINGINMILLIS).until(ExpectedConditions.elementToBeClickable(SignOut));
		SignOut.click();
	}
	
}

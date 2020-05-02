package com.freecrm.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.base.TestBase;

public class Home extends TestBase {
	
	WebDriver driver;
	public Home(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	
	@FindBy(xpath = "//li//a[@data-tracking = 'click hover']")
	List<WebElement> DropdownLinks;
	
	@FindBy(id = "nav-primary-contacts-branch")
	WebElement ContactDD;
	
	@FindBy(xpath = "//li[@class='expandable active']//a//div[contains(text(), 'Contacts')]")
	WebElement ContactsOfContactDD;
	
	
	
	public void clickOnContactDD() {
		log.info("Clicking on Contact drop down on Home Page.");
		ContactDD.click();	
	}
	
	public Contacts clickOnContactsOfContactDD() {
		log.info("Clicking on Contacts option of Contacts  drop down on Home page.");
		ContactsOfContactDD.click();
		log.info("******************Navigating to Contacts Page******************");
		return new Contacts(this.driver);
	}
	
	
	
	public List<String> getHeaderDropdownLinks() {
		
		log.info("Getting All the header drop down links on Home Page and storing it in list.");
		List<String> LinksPresentOnHomePage = new ArrayList<String>();
		for(int i =1; i< DropdownLinks.size(); i++) {
			LinksPresentOnHomePage.add(DropdownLinks.get(i).getText());	  
		}
			return LinksPresentOnHomePage;
	}
	
	
}

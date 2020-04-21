package com.freecrm.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.freecrm.utils.TestUtils;

public class TestBase {
	
	public Properties prop;
	public static String path = System.getProperty("user.dir");
	public static WebDriver driver = null;
	public Logger log;
	
	public TestBase() {
		log = LogManager.getLogger(TestBase.class);
		prop = new Properties();
		FileInputStream ip;
		try {	
			ip = new FileInputStream(path+"/src/main/java/com/freecrm/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void initialization() {
		log.info("Reading browser name from config.properties file.");
		String browser = prop.getProperty("browser");
		if(browser.equals("chrome")) {
			log.info("Opening chrome browser.");
			System.setProperty("webdriver.chrome.driver", path+"/chromedriver_win32/chromedriver.exe");
			log.info("Initializing chrome driver.");
			driver = new ChromeDriver();
		}else if(browser.equals("FF")) {
			log.info("Opening firefox browser.");
			System.setProperty("webdriver.gecko.driver", path+"/geckodriver-v0.26.0-win64/geckodriver");
			log.info("Initializing Firefox driver.");
			driver = new FirefoxDriver();
		}
		log.info("Maximizing windows.");
		driver.manage().window().maximize();
		log.info("Deleting all the existing cookies before running tests.");
		driver.manage().deleteAllCookies();
		log.info("Reading url properties from config.properties file and navigating to it.");
		driver.get(prop.getProperty("url"));
		log.info("Page load time out...");
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
		log.info("Implicit wait...");
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICITWAIT_TIMEOUT, TimeUnit.SECONDS);	
	}
	
	
	
}

package com.freecrm.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.freecrm.base.TestBase;

public class TestUtils {
	public final static long PAGELOAD_TIMEOUT = 30;
	public final static long IMPLICITWAIT_TIMEOUT = 10;
	public final static long EXPLICITWAIT_TIMEOUT = 10;
	public final static int	POLLINGINMILLIS = 100;
	public Workbook workbook;
	public Sheet sheet;
	
	
	
	public WebDriverWait getWait(WebDriver driver, long timeToWaitInSec, int pollingTimeInMillis) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.pollingEvery(Duration.ofMillis(pollingTimeInMillis));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	
	
	public Object[][] getData(String sheetName) {
		
		FileInputStream ExcelFile;
		
		//reading is FileInputStream
		try {
			 ExcelFile = new FileInputStream(TestBase.path+"\\src\\main\\java\\com\\freecrm\\testdata\\testDataCRM.xlsx");
			 workbook = WorkbookFactory.create(ExcelFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet = workbook.getSheet(sheetName);
		
		//extract everything all data from sheet.
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println("Number of rows:"+sheet.getLastRowNum());
		for(int i =0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(i).getLastCellNum();j++) {
				data[i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
}



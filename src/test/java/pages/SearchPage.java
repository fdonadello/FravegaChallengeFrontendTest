package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class SearchPage extends TestBase{
	private WebDriver driver;
	
	// Selectors
	
	protected static By heladeraBttn = By.cssSelector("div:nth-child(4)>ul>li:nth-child(1)>h4>a");
	
	
	protected static int TIME_OUT = 5;
	
	public SearchPage (WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Clickar en heladeras.
	 * 
	 */
	public SearchPage heladerasBttn() {	
		logger.info("Clickando en botón heladeras");
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
		WebElement heladerasBttn = wait.until(ExpectedConditions.presenceOfElementLocated(heladeraBttn));
		heladerasBttn.click();		
		return this;
	}
}

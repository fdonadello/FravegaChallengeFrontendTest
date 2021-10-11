package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import testBase.TestBase;


public class HomePage extends TestBase{
	private WebDriver driver;
	
	// Selectors
	
	protected static By pc = By.cssSelector("body>div>div>div>button>svg");
	protected static By searchBox = By.name("keyword");
	
	
	
	protected static int TIME_OUT = 5;
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	* Cerrar alerta de código postal
	*
	*/
	public HomePage codigoPostal(){
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
		WebElement pCode = wait.until(ExpectedConditions.presenceOfElementLocated(pc));
		pCode.click();	
		return this;
		
	}
	
	/**
	 * Realizar búsqueda.
	 * 
	 */
	public HomePage busqueda(String search) {	
		logger.info("Buscando "+search);
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
		WebElement searchB = wait.until(ExpectedConditions.presenceOfElementLocated(searchBox));
		searchB.sendKeys(search);
		searchB.submit();
		return this;
	}
	
	
	
}

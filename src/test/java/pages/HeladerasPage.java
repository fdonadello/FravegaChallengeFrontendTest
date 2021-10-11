package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class HeladerasPage extends TestBase{
	
	private WebDriver driver;
	
	// Selectors 
	
	protected static By firstTrademark = By.cssSelector("li[name='brandAggregation']:nth-child(1)>a>div>div");	
	protected List<WebElement> articles;	 
	protected static By aux = By.cssSelector("ul>li:nth-child(1)>article>a>div>div>h3");
	protected static By pageTwo = By.cssSelector("li:nth-child(3)>a[data-type='page']");
	protected static By results = By.cssSelector("div>div:nth-child(4)>div>div>span");
	protected static By breadscrum = By.cssSelector("li>span[itemprop='name']");
	
	protected static int TIME_OUT = 5;
	
	public HeladerasPage (WebDriver driver) {
		this.driver = driver;		
	}
	
	/**
	 * Seleccionar primera marca.
	 * 
	 */
	public HeladerasPage FrstTrademark() {	
		logger.info("Seleccionando primera marca.");
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
		WebElement selecTrademark = wait.until(ExpectedConditions.elementToBeClickable(firstTrademark));
		selecTrademark.click();
		return this;
	}
	
	/**
	 * Obtener primera marca.
	 */
	public String TrademarkName() {
		String tName = driver.findElement(firstTrademark).getText();
		String[] cutName = tName.split("\\s\\(");
		return cutName[0];
	}
	
	
	/**
	 * Contador de artículos.
	 */
	
	public int ArtNumber() {
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
		wait.until(ExpectedConditions.elementToBeClickable(aux));
		articles = driver.findElements(By.cssSelector("ul>li>article>a>div>div>h3"));		
		int art = articles.size();
		return art;
	}
	
	/**
	 * Obtener título de los artículos. 
	 * 
	 */
	public String article(int i) {
		articles = driver.findElements(By.cssSelector("ul>li>article>a>div>div>h3"));
		String title = articles.get(i).getText();		
		return title;
	}
	
	/**
	 * Comparando título de los artículos.
	 */
	public boolean articles() {
		boolean titleStatus=true;
		
		for(int i = 0;i<ArtNumber();i++) {
		if(!article(i).contains(TrademarkName())) {
			titleStatus=false;
			}			
		}	
	
		return titleStatus;
	}
	
	
	
	/**
	 * Cambiar a página 2.
	 */
	public HeladerasPage pageTwo() {
		logger.info("Cambiando a la página 2.");
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
		WebElement secondPage = wait.until(ExpectedConditions.elementToBeClickable(pageTwo));
		secondPage.click();
		return this;
	}
	
	/**
	 * Cantidad de resultados.
	 */
	public int amResult() {
		String amount = driver.findElement(results).getText();
		int result = Integer.parseInt(amount);
		
		return result;
	}
	
	/**
	 * Obtener artículo del breadscrum.
	 */
	public String getArticle() {
		String article =driver.findElement(breadscrum).getText();
		
		return article;
	}
}

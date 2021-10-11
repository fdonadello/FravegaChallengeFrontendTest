package testBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestBase {
	protected WebDriver driver;
	protected static Logger logger = LogManager.getLogger(TestBase.class);
	protected final String url="http://www.fravega.com/";
	protected ExtentReports extent = new ExtentReports();
	protected ExtentSparkReporter testReport = new ExtentSparkReporter("test-output/testReport.html");
	protected ExtentTest test = extent.createTest("Busqueda de heladeras", "Buscar y filtrar heladeras de la primera marca de la lista");
	


	@BeforeTest
	public void setUp() throws Exception {
		extent.attachReporter(testReport);
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(url);
		logger.info("Abriendo página web {}",url);	
		test.info("Accediendo a la web "+url+".");
	}

	
	@AfterMethod
	public void results(ITestResult result) {
		
		
		if (result.getStatus() == 1) {
			test.log(Status.PASS, "El test -" + result.getMethod().getDescription() + "- Pasó satisfactoriamente.");			
		} else {
			test.log(Status.FAIL, "El test -" + result.getMethod().getDescription() + "- No pasó satisfactoriamente.");
		}
		test.info("Cerrando el navegador.");
		extent.flush();
	}
	
	@AfterTest
	public void tearDown() {
		
		driver.quit();

	}
}

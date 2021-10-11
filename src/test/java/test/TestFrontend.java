package test;


import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HeladerasPage;
import pages.HomePage;
import pages.SearchPage;
import testBase.TestBase;

import com.aventstack.extentreports.Status;

import helpers.Helper;


public class TestFrontend extends TestBase{
	
	@Test(testName = "Busqueda de heladeras", description = "Buscar y filtrar heladeras de la primera marca de la lista")
	public void fridgeSearchTest() {
		
		
		// Arrange
		final String searchHeladera = "Heladera";
		int aux=0;
		int aux2=0;
		String breadscrumText = "Heladeras";
		

		// Act
		test.info("Accediendo a la web "+url+".");
		HomePage home = new HomePage(driver);
		home.codigoPostal();
		test.info("Cerrada alerta para ingresar c�digo postal.");
		home.busqueda(searchHeladera);
		test.info("Realizada la b�squeda de heladera.");
		SearchPage search = new SearchPage(driver);
		search.heladerasBttn();
		test.info("Clickado el bot�n heladeras.");
		HeladerasPage fridge = new HeladerasPage(driver);
		fridge.FrstTrademark();	
		test.info("Filtrado por la primera marca.");
		

		// Assert		
		test.info("Comprobando marca en el t�tulo de cada art�culo en la p�gina 1.");
		logger.info("Comprobando marca en el t�tulo de cada art�culo en la p�gina 1.");
		
		try {
		Assert.assertTrue(fridge.articles(), 
			"Al menos una heladera en la p�gina 1 no contiene la marca en su t�tulo");
		} catch(AssertionError e) {	
			test.log(Status.FAIL, "Al menos una heladera en la p�gina 1 no contiene la marca en su t�tulo");
			Assert.fail();
		}
			
		aux=fridge.ArtNumber();			
		
		test.info("Redirigiendo a la p�gina 2.");		
		
		fridge.pageTwo();
		
		test.info("Comprobando marca en el t�tulo de cada art�culo en la p�gina 2.");
		logger.info("Comprobando marca en el t�tulo de cada art�culo en la p�gina 2.");		
		
		try {
		Assert.assertTrue(fridge.articles(), 
			"Al menos una heladera en la p�gina 2 no contiene la marca en su t�tulo");
		} catch (AssertionError e){
		test.log(Status.FAIL, "Al menos una heladera en la p�gina 2 no contiene la marca en su t�tulo");
		Assert.fail();
		}
			
		aux2=fridge.ArtNumber();
		
		int totalArt = aux+aux2;
		
		test.info("Comprobando cantidad de art�culos.");
		logger.info("Comprobando cantidad de art�culos.");
		
		try {
		Assert.assertEquals(fridge.amResult(), totalArt, 
				"La cantidad hallada: " +fridge.amResult()+" no coincide con la mostrada: "+totalArt+".");
		} catch (AssertionError e){	
		test.log(Status.FAIL, "La cantidad hallada: " +fridge.amResult()+" no coincide con la mostrada: "+totalArt+".");
		Assert.fail();
		}		
		
		test.info("Comprobando que el art�culo buscado aparezca en el breadscrum.");
		logger.info("Comprobando que el art�culo buscado aparezca en el breadscrum.");
		try {
		Assert.assertEquals(fridge.getArticle(), breadscrumText ,				
				"El art�culo esperado: "+ breadscrumText+", no coincide con el obtenido: "+fridge.getArticle()+".");
		} catch (AssertionError e){
		test.log(Status.FAIL, "El art�culo buscado no aparece en el breadscrum.");
		Assert.fail();
		}
				
		Helper helper = new Helper();
		
		try {
			test.addScreenCaptureFromPath(helper.getScreenshot(driver, "Screenshot"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

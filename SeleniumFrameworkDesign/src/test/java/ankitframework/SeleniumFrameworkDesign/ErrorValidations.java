package ankitframework.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import ankitframework.TestComponents.BaseTest;
import ankitframeworkSeleniumFrameworkDesign.pageobjects.CartPage;
import ankitframeworkSeleniumFrameworkDesign.pageobjects.CheckoutPage;
import ankitframeworkSeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import ankitframeworkSeleniumFrameworkDesign.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{

		@Test(groups= {"ErrorHandling"})
		public void LoginErrorValidation() throws IOException, InterruptedException
		{
			
		
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("sharma.aankit1124@gmail.com", "Testing@12");
		Assert.assertEquals("Incorrect email or password", landingPage.getErrorMessage());
		
		}
		
		@Test
		public void ProductErrorValidation() throws IOException, InterruptedException
		{
			
		
		String productName = "ZARA COAT 3";
		ProductCatalogue  productcatalogue = landingPage.loginApplication("sharma.aankit1124@gmail.com", "Testing@123");
		List<WebElement>products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartPage =productcatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		}
		
}

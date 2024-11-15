package ankitframework.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ankitframework.TestComponents.BaseTest;
import ankitframeworkSeleniumFrameworkDesign.pageobjects.CartPage;
import ankitframeworkSeleniumFrameworkDesign.pageobjects.CheckoutPage;
import ankitframeworkSeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import ankitframeworkSeleniumFrameworkDesign.pageobjects.LandingPage;
import ankitframeworkSeleniumFrameworkDesign.pageobjects.OrderPage;
import ankitframeworkSeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";


		@Test(dataProvider="getData", groups= {"Purchase"})
		public void submitOrder(HashMap<String, String>input) throws IOException, InterruptedException
		{
			
		
		ProductCatalogue  productcatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartPage =productcatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("product");
		Assert.assertTrue(match);
		CheckoutPage checkoutPage= cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage =checkoutPage.submitOrder();

		// Actions class is an ability provided by Selenium for handling keyboard and mouse events
		
		String confirmMessage =	confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		}
		
		@Test(dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest()
		{
			ProductCatalogue  productcatalogue = landingPage.loginApplication("sharma.aankit1124@gmail.com", "Testing@123");
			OrderPage ordersPage =productcatalogue.goToOrdersPage();
			Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		}
		
		
		public String getScreenshot(String testCaseName) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file  = new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
		}
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
	
			
			List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\ankitframework\\data\\PurchaseOrder.json");

			return new Object[][]  {{data.get(0)},{data.get(1)}};
			/*we send object bcz we are not sure about the kind
			 * of value we will be sending it can be string, numeric so useing
			 * object makes it generic
			 */
		}
		
		/*		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "sharma.aankit1124@gmail.com");
		map.put("password", "Testing@123");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "sharma.ankit1124@gmail.com");
		map1.put("password", "Testing@123");
		map1.put("product", "ADIDAS ORIGINAL");*/
}

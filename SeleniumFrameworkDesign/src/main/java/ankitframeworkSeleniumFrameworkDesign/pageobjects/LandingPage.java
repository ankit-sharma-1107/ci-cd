package ankitframeworkSeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ankitframeworkSeleniumFramework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	/* extends is an oops concept called inheritance
	every method, variable field declared inside this class
	can be used automatically by landingpage*/
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) // create constructor for executing chromedriver, constructor run at the begin before any other method is executed
	{
		super(driver);
		// super keyword --  helps to send driver from child class to parent 
		// initialization
		this.driver= driver;
		//this refers to current/local class instance variable
		PageFactory.initElements(driver, this);
	}
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	/* interview q.  how annotation know about driver
	thr is a method name initelement which we write 1st it take care of
	constructing that using driver argument we send in the method*/
	

	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue  productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
		
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");

	}
}

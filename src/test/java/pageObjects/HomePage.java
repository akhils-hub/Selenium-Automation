package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver); //
	}

	@FindBy(xpath = "//a[@title='My Account']")
	private WebElement myAccountDropDown;
	
	@FindBy(xpath = "//a[text()='Register']")
	private WebElement registerLink;
	
	
	public void clickOnMyAccountDropDown() {
		myAccountDropDown.click();
	}
	
	public RegisterAccountPage clickOnRegisterLink() {
		registerLink.click();
		
		return new RegisterAccountPage(driver);
	}
}

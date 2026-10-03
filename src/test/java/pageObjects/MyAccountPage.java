package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath = "//h2[text()='My Account']")
	private WebElement myAccount;
	
	@FindBy(linkText = "Logout")
	private WebElement logoutButton;
	
	
	public boolean isMyAccountIsDisplayed() {
		System.out.println(myAccount.getText());
		return myAccount.isDisplayed();
	}
	
	public boolean isLogoutButtonDisplayed() {
		System.out.println(logoutButton.getText());
		return logoutButton.isDisplayed();
	}
	
	public void clickOnLogoutButton() {
		logoutButton.click();
	}
}

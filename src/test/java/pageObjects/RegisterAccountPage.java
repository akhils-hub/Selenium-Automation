package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterAccountPage extends BasePage {

	public RegisterAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='content']//h1")
	private WebElement registerAccountHeader;	
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id = "input-lastname")
	private WebElement lastNameField;
	
	@FindBy(name = "email")
	private WebElement emailField;
	
	@FindBy(xpath = "//input[@placeholder = 'Telephone']")
	private WebElement telephoneField;
	
	@FindBy(name = "password")
	private WebElement passwordField;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath = "//div[text() = 'I have read and agree to the ']//child::input[@type='checkbox']")
	private WebElement privacyPolicyck;
	
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	private WebElement acConfirmation;
	
	//Account Confiramtion Continue Buttone
	@FindBy(xpath = "//a[text()='Continue']")
	private WebElement acContinueButton;
	
	public boolean isRegisterAccountPageDisplayed() {
		log.info(registerAccountHeader.getText());
		return registerAccountHeader.isDisplayed();
	}
	
	public void setFirstName(String firstname) {
		firstNameField.sendKeys(firstname);
	}

	public void setLastName(String lastname) {
		lastNameField.sendKeys(lastname);
	}
	
	public void setEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void setTelephone(String telephone) {
		telephoneField.sendKeys(telephone);
	}
	
	public void setPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void setConfirmPassword(String conPassword) {
		confirmPasswordField.sendKeys(conPassword);
	}
	
   public void clickPrivicyPolicy() {
	   privacyPolicyck.click();
   }
   
   public void clickOnContinueButton() {
	   continueButton.click();
   }
   
   public boolean isAccountConfirmationDisplayed() {
	   System.out.println(acConfirmation.getText());
	  return acConfirmation.isDisplayed();
   }
 
   public MyAccountPage clickOnAcContinueButton() {
      acContinueButton.click();
      return new MyAccountPage(driver);
   }

}

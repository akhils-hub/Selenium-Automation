package pageObjects;

import java.util.List;

import org.jspecify.annotations.Nullable;
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

	@FindBy(xpath = "//input[@name='firstname']//following-sibling::div")
	private WebElement firstNameFieldError;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(xpath = "//input[@id='input-lastname']//following-sibling::div")
	private WebElement lastNameFieldError;

	@FindBy(name = "email")
	private WebElement emailField;

	@FindBy(xpath = "//div[contains(text(),'E-Mail Address')]")
	private WebElement emailFieldError;

	@FindBy(xpath = "//input[@placeholder = 'Telephone']")
	private WebElement telephoneField;

	@FindBy(xpath = "//div[contains(text(),'Telephone must')]")
	private WebElement telephoneFieldError;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//div[contains(text(),'Password ')]")
	private WebElement passwordFieldError;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//div[text() = 'I have read and agree to the ']//child::input[@type='checkbox']")
	private WebElement privacyPolicyck;

	@FindBy(xpath = "//div[contains(@class,'alert-danger')]")
	private WebElement privacyPolicyckError;

	@FindBy(xpath = "//label[@class='radio-inline']")
	private List<WebElement> newsletterbuttons;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	private WebElement acConfirmation;

	// Account Confiramtion Continue Buttone
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

	public void selectNewsletterOption(String target) {
		for (WebElement button : newsletterbuttons) {
			String text = button.getText().trim();
			if (target.equalsIgnoreCase(text)) {
				button.click();
				break;
			} else if (target.equalsIgnoreCase(text)) {
				button.click();
				break;
			}
		}

	}

	public void clickPrivicyPolicy() {
		privacyPolicyck.click();
	}

	public void clickOnContinueButton() {
		continueButton.click();
	}

	public boolean isAccountConfirmationDisplayed() {
		// System.out.println(acConfirmation.getText());
		return acConfirmation.isDisplayed();
	}

	public MyAccountPage clickOnAcContinueButton() {
		acContinueButton.click();
		return new MyAccountPage(driver);
	}

	// Warning Message Public Methods

	public String getFirstNameErrorMsg() {
		return firstNameFieldError.getText();
	}

	public String getLastNameErrorMsg() {
		return lastNameFieldError.getText();
	}

	public String getEmailErrorMsg() {
		return emailFieldError.getText();
	}

	public String getTelephoneErrorMsg() {
		return telephoneFieldError.getText();
	}

	public String getPasswordErrorMsg() {
		return passwordFieldError.getText();
	}

	public String getPrivacyPolicyErrorMsg() {
		int yCoordinate  = privacyPolicyckError.getLocation().getY();
		
		    
		return privacyPolicyckError.getText();
	}
	
	public boolean isPrivacyPolicyBannerAtTheTop() {
		int yCoordinate = privacyPolicyckError.getLocation().getY();
		int xCoordinate = privacyPolicyckError.getLocation().getX();
		 log.info("Privacy Policy Banner vertical X-coordinate position is: " + xCoordinate);
		 log.info("Privacy Policy Banner vertical Y-coordinate position is: " + yCoordinate);
		 return yCoordinate > 0 && yCoordinate < 300;
	}
}

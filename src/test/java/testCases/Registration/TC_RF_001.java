package testCases.Registration;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTestClass.BaseTest;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.RegisterAccountPage;

public class TC_RF_001 extends BaseTest {

	HomePage homePage;
	RegisterAccountPage registerAccountPage;
	MyAccountPage myAccountPage;

	// private final String Base_URL = "https://tutorialsninja.com/demo/";

	@Test(description = "Verify New User Registration with Valid details")
	public void verify_registration_TC01() {
		try {

			logStep("Navigating to target application home page.");
		//	driver.get(p.getProperty("BASE_URL"));

			homePage = new HomePage(driver);
			logStep("Clicking on the 'My Account' dropdown navigation header.");
			homePage.clickOnMyAccountDropDown();
			// captureScreen("Registration_Link");
			logStep("Selecting the 'Register' account redirection link.");
			registerAccountPage = homePage.clickOnRegisterLink();

			//No Need below line since 'RegisterAccountPage' is already returned above line.
		//	registerAccountPage = new RegisterAccountPage(driver);

			Assert.assertTrue(registerAccountPage.isRegisterAccountPageDisplayed(),
					"Validation Failed: Registration Account Failed to Load");
			// captureScreen("Register_AC_Page");
			logStep("Successfully navigated to Register Account Page...!");
			
			registerAccountPage.setFirstName("ABBbba123");
			registerAccountPage.setLastName("SAIIIEE");
			registerAccountPage.setEmail(randomeAlphaNumberic() + "@gmail.com");
			registerAccountPage.setTelephone("9098660906");
			registerAccountPage.setPassword("P@ssword123");
			registerAccountPage.setConfirmPassword("P@ssword123");
			registerAccountPage.clickPrivicyPolicy();
			registerAccountPage.clickOnContinueButton();

			logStep("Account Registration Completed for New User.");
			Assert.assertTrue(registerAccountPage.isAccountConfirmationDisplayed(),
					"Validateion Failed: Failure to Display Account Confirmation");

			myAccountPage = registerAccountPage.clickOnAcContinueButton();

			//myAccountPage = new MyAccountPage(driver); //(No need this line.)

			Assert.assertTrue(myAccountPage.isMyAccountIsDisplayed(),
					"Validation Failed: 'My Account' Header is missing after new user Registration.");
			Assert.assertTrue(myAccountPage.isLogoutButtonDisplayed(),
					"Validation Failed: 'Logout' Option is missing in My Account Page.");
			myAccountPage.clickOnLogoutButton();
			
			logStep("User Successfully Logged out..!");
		} catch (Exception e) {
			log.info("Test Faild: " + e.getMessage());
			Assert.fail("Test Failed: " + e.getMessage());
		}

	}
}
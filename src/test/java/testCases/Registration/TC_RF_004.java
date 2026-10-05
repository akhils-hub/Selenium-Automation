package testCases.Registration;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTestClass.BaseTest;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.RegisterAccountPage;

public class TC_RF_004 extends BaseTest {

	HomePage homePage;
	RegisterAccountPage registerAccountPage;
	MyAccountPage myAccountPage;

	@Test(description = "Validation of Warning Messages Test.")
	public void verify_registration_TC04() {

		try {
			logStep("Navigating to the Target Application Home Page");
			// driver.get(p.getProperty("BASE_URL"));

			homePage = new HomePage(driver);
			homePage.clickOnMyAccountDropDown();
			logStep("User clicked on MyAccount Dropdown");
			registerAccountPage = homePage.clickOnRegisterLink();
			logStep("Navigating to the Register Account Page.");

			Assert.assertTrue(registerAccountPage.isRegisterAccountPageDisplayed(),
					"Failed to Nagivate Register Account Page");

			registerAccountPage.clickOnContinueButton();

			logStep("Validating warning messages for Respective fields: ");

			Assert.assertEquals(registerAccountPage.getFirstNameErrorMsg(), p.getProperty("expectedFirstNameErrorMsg"),
					"First Name Error Mismatch.!");
			Assert.assertEquals(registerAccountPage.getLastNameErrorMsg(), p.getProperty("expectedLastNameErrorMsg"),
					"Last Name Error Mismatch.!");
			Assert.assertEquals(registerAccountPage.getEmailErrorMsg(), p.getProperty("expectedEmailErrorMsg"),
					"Email Error Mismatch.!");
			Assert.assertEquals(registerAccountPage.getTelephoneErrorMsg(), p.getProperty("expectedTelephoneErrorMsg"),
					"Telephone Error Mismatch.!");
			Assert.assertEquals(registerAccountPage.getPasswordErrorMsg(), p.getProperty("expectedPasswordErrorMsg"),
					"Password Error Mismatch.!");
			Assert.assertEquals(registerAccountPage.getPrivacyPolicyErrorMsg(),
					p.getProperty("expectedPrivacyPolicyErrorMsg"), "Privacy Policy Error Mismatch.!");

			Assert.assertTrue(registerAccountPage.isPrivacyPolicyBannerAtTheTop(),
					"Validation Failed: Privacy Policy warning banner is NOT displayed at the top of the page layout!");

			logStep("Validation of warning messages completed successfully for all respective fields.");
		} catch (Exception e) {
			log.error("Test Case Failed: " + e.getMessage());
			Assert.fail("Test Case Failed: " + e.getMessage());
		}

	}

}
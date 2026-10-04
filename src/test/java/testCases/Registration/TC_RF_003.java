package testCases.Registration;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTestClass.BaseTest;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.RegisterAccountPage;

public class TC_RF_003 extends BaseTest {

	HomePage homePage;
	RegisterAccountPage registerAccountPage;
	MyAccountPage myAccountPage;

	@Test(description ="Verify user registration workflow including newsletter subscription mapping")
	public void verify_registration_TC03() {
		try {
		//	driver.get(p.getProperty("BASE_URL"));

			homePage = new HomePage(driver);

			homePage.clickOnMyAccountDropDown();
			logStep("User Clicked on 'My Account' Dropdown");
			registerAccountPage = homePage.clickOnRegisterLink();
			logStep("User Clicked on Register Link");

			registerAccountPage = new RegisterAccountPage(driver);

			Assert.assertTrue(registerAccountPage.isRegisterAccountPageDisplayed(),
					"Failed to Navigate Register Account Page..!");
			logStep("Successfully navigated to Register Account Page...!");

			registerAccountPage.setFirstName(p.getProperty("FirstName"));
			registerAccountPage.setLastName(p.getProperty("LastName"));
			registerAccountPage.setEmail(randomeAlphaNumberic() + "@gmail.com");
			registerAccountPage.setTelephone(p.getProperty("PhoneNumber"));
			registerAccountPage.setPassword(p.getProperty("Password"));
			registerAccountPage.setConfirmPassword(p.getProperty("ConfirmPassword"));
			Thread.sleep(10000);
			registerAccountPage.selectNewsletterOption(p.getProperty("NewsLetter"));
			
			Thread.sleep(10000);
			registerAccountPage.clickPrivicyPolicy();
			logStep("User Entered All Mandatory Details...!");

			registerAccountPage.clickOnContinueButton();
			Assert.assertTrue(registerAccountPage.isAccountConfirmationDisplayed());
			logStep("Account Confirmation Page Displayed...!");
			myAccountPage = registerAccountPage.clickOnAcContinueButton();

			myAccountPage = new MyAccountPage(driver);

			Assert.assertTrue(myAccountPage.isMyAccountIsDisplayed(), "Failed to Display 'My Account Page'");
			logStep("Successfully Navigated to 'My Account Page'");

			Assert.assertTrue(myAccountPage.isLogoutButtonDisplayed(), "Failed to Display Logout Button");
			myAccountPage.clickOnLogoutButton();
			logStep("Successfully Logged Out...!");
		} catch (Exception e) {
			log.error("Test Case Failed: " + e.getMessage());
			Assert.fail();
		}

	}

}

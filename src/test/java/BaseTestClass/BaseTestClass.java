package BaseTestClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.Status;

import utilities.ExtentReportManager;

public class BaseTestClass {
	protected WebDriver driver;
	protected static Properties p;

	// Centralized Logger instance accessible across your framework classes
	protected Logger log = LogManager.getLogger(this.getClass());

	@BeforeClass
	public void setUp(ITestContext context) throws IOException {

		// System.out.println("Charome Driver Opening...!");
		//log.info("Charome Driver Opening...!");
		logStep("Chrome Driver Opening...!");
		ChromeOptions options = new ChromeOptions();

		// Anti-bot flags to help bypass CAPTCHA issues
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.addArguments(
				"user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

		// Initialize driver with options
		driver = new ChromeDriver(options);
		// System.out.println("Charome Driver Opened...!");
		//log.info("Charome Driver Opened...!");
		logStep("Charome Driver Opened...!");
		
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		context.setAttribute("WebDriver", this.driver);

	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			// System.out.println(driver + " -- Driver is closed");
			//log.info(driver + " -- Driver is closed Successfully...!");
			logStep(driver + " -- Driver is closed Successfully...!");
		} else {
			log.error(driver + " is not closed.....!");
		}
	}

	public String randomeAlphaNumberic() {
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring + generatednumber);
	}

	static {

		try {
			// Configuration Property Management
			// FileReader fileReader = new
			// FileReader("./src//test//resources//config.properties");
			FileReader fileReader = new FileReader(
					System.getProperty("user.dir") + "./src/test/resources/config.properties");
			p = new Properties();
			p.load(fileReader);
			System.out.println(">> Global Configuration Properties Loaded Successfully.");
		} catch (Exception e) {
			System.out.println("CRITICAL: Failed to load config file: " + e.getMessage());
			throw new RuntimeException(e);
		}

	}

//	@AfterMethod()
	public void captureFailureScreen(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			String testName = result.getName();
			log.error("Test Failed: " + testName + " AND Capturing Screen...!");

			try {
				Date date = new Date();
				SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				String currentTime = simpleDate.format(date);

				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				String destinationPath = System.getProperty("user.dir") + "/Screenshots/" + testName + "_" + currentTime
						+ ".png";
				File destination = new File(destinationPath);
				FileUtils.copyFile(source, destination);
				log.info("Capture Screenshot Successfully and Saved in: " + destinationPath);

			} catch (Exception e) {
				log.error("Failed to Save ScreenShot: " + e.getMessage());
			}
		}
	}

	/*
	 * public void captureScreen(String screenCap) {
	 * 
	 * try { Date date = new Date(); SimpleDateFormat simpleDate = new
	 * SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); String currentTime =
	 * simpleDate.format(date);
	 * 
	 * TakesScreenshot ts = (TakesScreenshot) driver; File source =
	 * ts.getScreenshotAs(OutputType.FILE); String destinationPath =
	 * System.getProperty("user.dir") + "/Screenshots/" + screenCap + "_" +
	 * currentTime + ".png"; File destination = new File(destinationPath);
	 * FileUtils.copyFile(source, destination);
	 * log.info("Capture Screenshot Successfully and Saved in: " + destinationPath);
	 * 
	 * } catch (Exception e) { log.error("Failed to Save ScreenShot: " +
	 * e.getMessage()); }
	 * 
	 * }
	 */

	public String captureScreenshotForReport(String testName) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String relativePath = "Screenshots/" + testName + "_" + timestamp + ".png";
		String destinationPath = System.getProperty("user.dir") + "/Reports/" + relativePath;

		try {
			FileUtils.copyFile(sourceFile, new File(destinationPath));
			log.info("Screenshot archived successfully for ExtentReport.");
		} catch (IOException e) {
			log.error("Failed to copy screenshot file: " + e.getMessage());
		}

		// Return the path so the listener can attach it directly to the HTML view
		return relativePath;

	}

	public void logStep(String message) {
		// 1. Send the step message to Log4j (Console + automation.log text file)
		log.info(message);

		// 2. Safely check if the ExtentReport engine is currently active, then append
		// it to the HTML row
		if (ExtentReportManager.test != null) {
			ExtentReportManager.test.log(Status.INFO, message);
		}
	}

}

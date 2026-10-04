package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseTestClass.BaseTest;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public static ExtentTest test;

	String reportName;

	@Override
	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		reportName = "Automation-Test-Reprot_" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" + reportName);
		sparkReporter.config().setDocumentTitle("Automation Execution Reports");
		sparkReporter.config().setReportName("Functional Testing Status");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Application URL", "https://tutorialsninja.com/demo/");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Execution Engineer", "AkhilBabu");
		extent.setSystemInfo("Environment", "QA");

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	@Override
	public void onTestStart(ITestResult result) {
		// Runs immediately when any individual test case initiates execution
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test Case PASSED: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Case FAILED: " + result.getName());
		try {
			BaseTest baseTestInstance = (BaseTest) result.getInstance();
			String captureScreenForReport = baseTestInstance.captureScreenshotForReport(result.getName());
			test.addScreenCaptureFromPath(captureScreenForReport, "Failure Screen Evidence");

		} catch (Exception e) {
			test.log(Status.WARNING,
					"Failed to attach failure screenshot stream to report dashboards: " + e.getMessage());
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Case SKIPPED: " + result.getName());

	}

	@Override
	public void onFinish(ITestContext context) {
		// Run once at the absolute conclusion of your test suites to flush data onto
		// the drive
		extent.flush();
	}

}

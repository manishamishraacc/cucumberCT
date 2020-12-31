package stepDefinition;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import TestRunner.TestRunner;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.MobileUtility;
import utility.Utility;

public class Hooks extends Utility {

	// Before starting any scenario beforeScenario method is triggered
	@Before(order = 0)
	public void beforeScenario() {
		System.out.println("------Scenario Started------");

	}

	@Before(value = "@Desktop ", order = 1)
	public void launchBrowser() {
		setNLaunchChrome();
		sql_initialization_TEST();

	}

	@AfterStep(value = "@Desktop ", order = 0)
	public void afterStepResultPC(Scenario scenario) {
		if (scenario.isFailed()) {

			// To create reference of TakesScreenshot
			TakesScreenshot screenshott = (TakesScreenshot) Utility.driver;
			// Call method to capture screenshot
			File src = screenshott.getScreenshotAs(OutputType.FILE);
			// scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
		}
		
	}

	@Before(value = "@HybridApp", order = 1)
	public void setUpAndroid() throws MalformedURLException {
		launchHybridApp();
		sql_initialization_TEST();
		setNLaunchChrome();
	}

	@AfterStep(value = "@HybridApp", order = 1)
	public void afterStepResultApp(Scenario scenario) {
		if (scenario.isFailed()) {
			//Hooks.wantsToQuit = true == scenario.isFailed();
			// To create reference of TakesScreenshot
			TakesScreenshot screenshott = (TakesScreenshot)appiumDriver;
			// Call method to capture screenshot
			File src = screenshott.getScreenshotAs(OutputType.FILE);
			// scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
		}

	}

	@After
	public void afterScenario() {
		Utility.driver.quit();

		System.out.println("------------Scenario Ended------------");
	}
}

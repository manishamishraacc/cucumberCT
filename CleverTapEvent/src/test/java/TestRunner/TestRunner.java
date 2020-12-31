package TestRunner;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import io.cucumber.testng.CucumberOptions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import utility.MobileUtility;
import utility.Utility;

@CucumberOptions(plugin = { "html:target/cucumber-html-report", 
		"json:target/cucumber-json-report.json" },monochrome= true, dryRun = false,glue = "stepDefinition", features = {
				"classpath:Feature/DepositInitiate.feature" },tags = "not @Ignore")
public class TestRunner extends AbstractTestNGCucumberTests {
	private TestNGCucumberRunner testNGCucumberRunner;
	//public static WebDriver driver;
	public static String resultValue = "pass";
	public static ITestResult resultStatus;
	public static boolean wantsToQuit = false;
	public static AppiumDriver<MobileElement> appiumDriver;
	
	
	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		//Utility utility = new Utility();
	    //driver =  utility.setChromeOptions();
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

	}

	@Test(description = "Test Runner", dataProvider = "scenarios")
	public void runScenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
		
			testNGCucumberRunner.runScenario(pickle.getPickle());
		

	}

	@DataProvider
	public Object[][] scenarios() {

		return testNGCucumberRunner.provideScenarios();

	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		testNGCucumberRunner.finish();
		Utility.driver.quit();
	}

}

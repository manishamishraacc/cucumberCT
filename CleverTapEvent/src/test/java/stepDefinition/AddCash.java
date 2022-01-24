package stepDefinition;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import TestRunner.TestRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utility.KeyWords;
import utility.MobileUtility;
import utility.Utility;

public class AddCash extends Utility{
	// WebDriver driver = TestRunner.driver;
	static String parentWindow;
	static String childWindow;
	KeyWords ctObject;
	MobileUtility mu;
	Utility ut;
	SoftAssert softAssertion = new SoftAssert();
	String eventType;
	String adCampaign;
	String amount;
	String bonusAmount;
	String bonusCode;
	String bonusType;
	String browser;
	String browserVersion;
	String currentCashBalance;
	String clientType;
	String currentURL;
	String depositStatus;
	String deviceType;
	String device;
	String domainName;
	String email;
	String firstDepositDate;
	String gender;
	String source;
	String bonusApplied;
	String os;
	String paymentSubType;
	String paymentType;
	String phone;
	String identifyId;
	String playerID;
	String playerProfile;
	String regCity;
	String regDate;
	String regDevice;
	String ip;
	String regState;
	String state;
	String userName;
	String osVersion;
	String gateway;
	String lastLogin;
	String aliasName;
	String ctSource;
	String dob;

	@Given("^Login to khelPlay rummy with username and password$")
	public void goToKhelPlay(DataTable credentials) throws InterruptedException, SQLException {
		// Store username and password from feature file
		List<String> data = credentials.row(0);
		ut = new Utility();
		ut.login(data.get(0), data.get(1));
	}

	@And("^Click on add cash, Enter amount and Proceed$")
	public void addCash() throws InterruptedException {
		Utility.driver.findElement(By.xpath("//div[@class='tab_act_btn']//a[@href='/cashier-initiate']")).click();
		Thread.sleep(10000);

		Set<String> winHandles = driver.getWindowHandles();
		parentWindow = (String) winHandles.toArray()[0];
		childWindow = (String) winHandles.toArray()[1];
		Utility.driver.switchTo().window(childWindow);
		Utility.driver.manage().window().maximize();

		Utility.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Assert.assertEquals("https://test.khelplayrummy.com/select-amount-new#chooseAmount",
				Utility.driver.getCurrentUrl());
		Utility.driver.findElement(By.xpath("//button[@class='close']")).click();

		Utility.driver.findElement(By.xpath("//input[@class='allow_ony_nums custome_input']")).clear();
		Utility.driver.findElement(By.xpath("//input[@class='allow_ony_nums custome_input']")).sendKeys("100");
		Utility.driver.findElement(By.xpath("//div[@class='cashier_action']//a[@id='cntbtn' and text()='PROCEED']"))
				.click();
	}

	@And("^complete transaction successfully$")
	public void completeTransaction() throws InterruptedException {
		Thread.sleep(8000);
		Utility.driver.findElement(By.xpath("//div[@class='icon axis_bank']")).click();
		Thread.sleep(10000);
		Utility.driver.findElement(By.id("successBtn")).click();
		Utility.driver.close();
		Utility.driver.switchTo().window(parentWindow);

	}

	@And("^Clevertap login with username and password$")
	public void cleverTapLogin_addCash(DataTable creddata) throws InterruptedException {
		String username, password;
		List<String> data = creddata.row(0);
		username = data.get(0);
		password = data.get(1);
		ctObject = new KeyWords();
		ctObject.loginCleverTap(username, password);
	}

	@And("^Enter email as search crieria for add cash$")
	public void searchUserCleverTap(DataTable useremail) throws Exception {
		// click segement on Home page of Clever Tap
		ctObject.click("xpath", "//div[@class='segments']");
		Thread.sleep(2000);
		ctObject.setText("id", "searchIput", useremail.row(0).get(0));
		ctObject.click("id", "searchBtn");
		Thread.sleep(4000);
		// Verify is result for email is displayed or not
		Assert.assertEquals(true,Utility.driver.findElement(By.xpath("//div[@class ='user-profile__name ']")).isDisplayed());
		Thread.sleep(5000);
		ctObject.click("id", "dvViewer");
		Thread.sleep(3000);
		// ([0-9]{2}\/[0-9]{2}\/[0-9]{4})\s([0-9]{2}:[0-9]{2}:[0-9]{2}\s[a-z]{2}) regex
		// for date and time

	}

	@Then("^Webbrowser and user related data should be recorded in clevertap as mentioned below$")
	public void addCashPage(DataTable dataTable) throws Exception {

		// Store event realted data from feature file into list of map
		List<Map<String, String>> eventsData = dataTable.asMaps();
		
       eventType = ctObject.getText("xpath", "//table[@class='ev_23']/tbody/tr/td[2]//span[text()='Deposit']");
		adCampaign = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Ad Campaign' or @data-original-title ='Ad Campaign']");
		amount = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Amount' or @data-original-title ='Amount']");
		bonusAmount = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Bonus Amount' or @data-original-title ='Bonus Amount']");
		bonusCode = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Bonus Code' or  @data-original-title ='Bonus Code'] ");
		bonusType = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Bonus Type' or @data-original-title ='Bonus Type']");
		browser = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Browser' or @data-original-title ='Browser']");
		currentCashBalance = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Cash Current Balance' or @data-original-title ='Cash Current Balance']");
		clientType = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Client Type' or @data-original-title ='Client Type']");
		currentURL = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Current URL' or @data-original-title ='Current URL']");
		depositStatus = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Deposit Status' or @data-original-title ='Deposit Status']");
		deviceType = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Device Type' or @data-original-title ='Device Type']");
		device = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Device' or @data-original-title ='Device']");
		domainName = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Domain Name' or @data-original-title ='Domain Name']");
		email = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Email' or @data-original-title ='Email']");
		firstDepositDate = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='First Deposit Date' or @data-original-title ='First Deposit Date']");
		gender = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Gender' or @data-original-title ='Gender']");
		source = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Source' or @data-original-title ='Source']");
		bonusApplied = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Is Bonus Code Applied' or @data-original-title ='Is Bonus Code Applied']");
		os = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Operating System' or @data-original-title ='Operating System']");
		paymentSubType = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Payment Subtype' or @data-original-title ='Payment Subtype']");
		paymentType = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Payment Type' or @data-original-title ='Payment Type']");
		phone = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Phone' or @data-original-title ='Phone']");
		identifyId = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Identity' or @data-original-title ='Identity']");
		playerID = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Player ID' or @data-original-title ='Player ID']");
		playerProfile = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Player Profile' or @data-original-title ='Player Profile']");
		regCity = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg City' or @data-original-title ='Reg City']");
		regDate = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg Date' or @data-original-title ='Reg Date']");
		regDevice = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg device' or @data-original-title ='Reg device']");
		ip = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg IP' or @data-original-title ='Reg IP']");
		regState = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg State' or @data-original-title ='Reg State']");
		state = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='State' or @data-original-title ='State']");
		userName = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Username' or @data-original-title ='Username']");
		osVersion = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='OS Version' or @data-original-title ='OS Version']");
		gateway = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Gateway Name' or @data-original-title ='Gateway Name']");
		lastLogin = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Last Login Date' or @data-original-title ='Last Login Date']");
		aliasName = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Alias Name' or @data-original-title ='Alias Name']");
		ctSource = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='CT Source' or @data-original-title ='CT Source']");
		dob = ctObject.getText("xpath",
				"//table[@class='ev_23']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='DOB' or @data-original-title ='DOB']");

		softAssertion.assertEquals(eventsData.get(0).get("Event").toString(), eventType);
		softAssertion.assertEquals(eventsData.get(0).get("AdCampaign").toString(), adCampaign);
		softAssertion.assertEquals(eventsData.get(0).get("Amount").toString(), amount);
		softAssertion.assertEquals(eventsData.get(0).get("BonusAmount").toString(), bonusAmount);
		softAssertion.assertEquals(eventsData.get(0).get("BonusCode").toString(), bonusCode);
		softAssertion.assertEquals(eventsData.get(0).get("BonusType").toString(), bonusType);
		softAssertion.assertEquals(eventsData.get(0).get("Browser").toString(), browser);
		//softAssertion.assertEquals(eventsData.get(0).get("BrowserVersion").toString(), browserVersion);
		softAssertion.assertEquals(eventsData.get(0).get("cashCurrentBalance").toString(), currentCashBalance);
		softAssertion.assertEquals(eventsData.get(0).get("ClientType").toString(), clientType);
		softAssertion.assertEquals(eventsData.get(0).get("CurrentUrl").toString(), currentURL);
		softAssertion.assertEquals(eventsData.get(0).get("DepositStatus").toString(), depositStatus);
		softAssertion.assertEquals(eventsData.get(0).get("DeviceType").toString(), deviceType);
		softAssertion.assertEquals(eventsData.get(0).get("Device").toString(), device);
		softAssertion.assertEquals(eventsData.get(0).get("DomainName").toString(), domainName);
		softAssertion.assertEquals(eventsData.get(0).get("email").toString(), email);
		softAssertion.assertEquals(eventsData.get(0).get("FirstDepositeDate").toString(), firstDepositDate);
		softAssertion.assertEquals(eventsData.get(0).get("Gender").toString(), gender);
		softAssertion.assertEquals(eventsData.get(0).get("Source").toString(), source);
		softAssertion.assertEquals(eventsData.get(0).get("isBonusApplied").toString(), bonusApplied);
		softAssertion.assertEquals(eventsData.get(0).get("OS").toString(), os);
		softAssertion.assertEquals(eventsData.get(0).get("PaymentSubType").toString(), paymentSubType);
		
		softAssertion.assertEquals(eventsData.get(0).get("PaymentType").toString(), paymentType);
		softAssertion.assertEquals(eventsData.get(0).get("phone").toString(), phone);
		softAssertion.assertEquals(eventsData.get(0).get("identify").toString(), identifyId);
		softAssertion.assertEquals(eventsData.get(0).get("playerID").toString(), playerID);
		softAssertion.assertEquals(eventsData.get(0).get("plyaerProfile").toString(), playerProfile);
		softAssertion.assertEquals(eventsData.get(0).get("RegCity").toString(), regCity);
		softAssertion.assertEquals(eventsData.get(0).get("RegDate").toString(), regDate);
		softAssertion.assertEquals(eventsData.get(0).get("RegDevice").toString(), regDevice);
		softAssertion.assertEquals(eventsData.get(0).get("RegIP").toString(), ip);
		softAssertion.assertEquals(eventsData.get(0).get("RegState").toString(), regState);
		softAssertion.assertEquals(eventsData.get(0).get("State").toString(), state);
		softAssertion.assertEquals(eventsData.get(0).get("username").toString(), userName);
		softAssertion.assertEquals(eventsData.get(0).get("OSVersion").toString(), osVersion);
		softAssertion.assertEquals(eventsData.get(0).get("Gateway").toString(), gateway);
		//softAssertion.assertEquals(eventsData.get(0).get("lastLoginDate").toString(), lastLogin);
		softAssertion.assertEquals(eventsData.get(0).get("aliasName").toString(), aliasName);
		softAssertion.assertEquals(eventsData.get(0).get("CTSource").toString(), ctSource);
		softAssertion.assertEquals(eventsData.get(0).get("DOB").toString(), dob);
       softAssertion.assertAll();
	}

	@Given("^launch app and Login with username and password$")
	public void test(DataTable creds) throws SQLException, InterruptedException {
		System.out.println("secnario 2");
		String username, password;
		List<String> data = creds.row(0);
		username = data.get(0);
		password = data.get(1);
		mu = new MobileUtility();
		mu.loginUser(username, password);

	}
	@Given("^Set width and resolution of testing device$")
	public void setResolution(DataTable resolution) {
		// Store event realted data from feature file into list of map
		List<Map<String, String>> dimension = resolution.asMaps();
		mu.test_widthResolution = Integer.valueOf(dimension.get(0).get("xResolution"));
		mu.test_heightResolution = Integer.valueOf(dimension.get(0).get("yResolution"));
	}
	@And("^Perform add cash action$")
	public void addCashForApp() throws InterruptedException {
		Thread.sleep(10000);
		int xCoordinate, yCoordinate;
		
		//right navigation bar on home page
		xCoordinate = MobileUtility.heightCalculation(mu.test_widthResolution,mu.recorderd_widthResolution,987);
		yCoordinate = MobileUtility.widthCalculation(mu.test_heightResolution,mu.recorderd_heightResolution,77);			
		new TouchAction(mu.appiumDriver).tap(point(xCoordinate, yCoordinate)).waitAction(waitOptions(Duration.ofMillis(250))).perform();
		MobileElement addCash = mu.appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='ADD CASH']"));
		addCash.click();

		Thread.sleep(10000);
		mu.scrollDown();
		mu.scrollDown();
		MobileElement contBtn = mu.appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='PROCEED']"));
		contBtn.click();
    	Thread.sleep(2000);
	
		MobileElement debitCard = mu.appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='Axis Bank']"));
		debitCard.click();
		Thread.sleep(7000);
		MobileElement successbutton = mu.appiumDriver.findElement(By.xpath("//android.widget.Button[@resource-id='successBtn']"));
		successbutton.click();
    
		Thread.sleep(15000);
		MobileElement close = mu.appiumDriver.findElement(By.xpath("//android.view.View[@text='highlight_off']"));
		close.click();

	}
    
	@Given("^Clever tap event tracking$")
	public void ss() {
		System.out.println("xyz");
	}

}

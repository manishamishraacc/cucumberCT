package stepDefinition;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.appmanagement.ApplicationState;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utility.KeyWords;
import utility.MobileUtility;
import utility.Utility;

public class Registration extends Utility {

	String eventType;
	String name;
	String domainName;
	String aliasName;
	String adCampaign;
	String userName;
	String currentURL;
	String identify;
	String playerID;
	String email;
	String phone;
	String clientTye;
	String os;
	String regDate;
	String regDevice;
	String regIP;
	String device;
	String deviceType;
	String source;
	String browser;
	String browserVersion;
	String registrationVia;
	String osVersion;
	String gender;
	String regCity;
	String playerProfile;
	String cashBalance;
	String ctSource;
	String emailID;
	String password = "9893658203";
	String phoneNo;
	Pattern pattern;
	String screenWidth;
	String screenHeight;
	String deviceModel;
	String carrier;
	String wifi;
	String appVersion;
	String brand;
	String ctApp_Version;
	String regState;
	MobileUtility mu;
	Utility ut;
	KeyWords keywordsObject;
	SoftAssert softAssertion = new SoftAssert();

	@Given("^navigate to khelPlay rummy web site$")
	public void openKPR() {
		driver.navigate().to("https://test.khelplayrummy.com");
	}

	@And("^Perform registration and create new profile$")
	public void registerUser() throws Exception {
		Random rand = new Random();
		phoneNo = "87" + (1000 + rand.nextInt(9000)) + (1000 + rand.nextInt(9000));
		emailID = "CleverTapUser" + (1000 + rand.nextInt(9000)) + "@" + (100 + rand.nextInt(900)) + ".com";
		ut = new Utility();
		ut.registerUser(emailID, password, phoneNo);
	}

	@And("^Using below credential login to clevertap$")
	public void cleverTapLogin(DataTable creddata) throws SQLException, InterruptedException {
		String username, password;
		List<String> data = creddata.row(0);
		username = data.get(0);
		password = data.get(1);
		driver.get("https://in1.dashboard.clevertap.com/login.html#/");
		// Thread.sleep(8000);
		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class ='btn btn-primary btn-login login-button']")).click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//div[@class='segments']")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("searchIput")).sendKeys(emailID);

		driver.findElement(By.id("searchBtn")).click();
		
		Thread.sleep(4000);
		Assert.assertEquals(true, driver.findElement(By.xpath("//div[@class ='user-profile__name ']")).isDisplayed());
		driver.findElement(By.id("dvViewer")).click();
		
	}

	@Then("^Mentioned data should be reflected in CleverTap$")
	public void compareValue(DataTable data) throws Exception {
		List<Map<String, String>> eventsData = data.asMaps();
		keywordsObject = new KeyWords();
		System.out.println("abc" + driver.findElement(By.xpath("//table[@class='ev_21']/tbody/tr/td[2]//span[text()='Registration']")).getText());
		eventType = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[text()='Registration']");
		name = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Name' or @data-original-title ='Name']");
		domainName = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Domain Name' or @data-original-title ='Domain Name']");
		aliasName = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Alias Name' or @data-original-title ='Alias Name']");
		adCampaign = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Ad Campaign' or @data-original-title ='Ad Campaign']");
		userName = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Username' or @data-original-title ='Username']");
		currentURL = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Current URL' or @data-original-title ='Current URL']");
		identify = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Identity' or @data-original-title ='Identity']");
		playerID = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Player ID' or @data-original-title ='Player ID']");
		email = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Email' or @data-original-title ='Email']");
		phone = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Phone' or @data-original-title ='Phone']");
		clientTye = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Client Type' or @data-original-title ='Client Type']");
		os = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Operating System' or @data-original-title ='Operating System']");
		regDate = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg Date' or @data-original-title ='Reg Date']");
		regDevice = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg Device' or @data-original-title ='Reg Device']");
		regIP = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg IP' or @data-original-title ='Reg IP']");
		device = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Device' or @data-original-title ='Device']");
		deviceType = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Device Type' or @data-original-title ='Device Type']");
		source = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Source' or @data-original-title ='Source']");
		browser = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Browser' or @data-original-title ='Browser']");
		browserVersion = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Browser Version' or @data-original-title ='Browser Version']");
		registrationVia = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Registration Via' or @data-original-title ='Registration Via']");
		osVersion = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='OS Version' or @data-original-title ='OS Version']");
		gender = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Gender' or @data-original-title ='Gender']");
		regCity = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg City' or @data-original-title ='Reg City']");
		playerProfile = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Player Profile' or @data-original-title ='Player Profile']");
		cashBalance = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Cash Current Balance' or @data-original-title ='Cash Current Balance']");
		ctSource = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='CT Source' or @data-original-title ='CT Source']");

		softAssertion.assertEquals(eventsData.get(0).get("Event"), eventType);
		softAssertion.assertEquals(db_userName, name);
		softAssertion.assertEquals(eventsData.get(0).get("Domain Name"), domainName);
		softAssertion.assertEquals(eventsData.get(0).get("Alias Name"), aliasName);
		softAssertion.assertEquals(eventsData.get(0).get("Ad Campagin"), adCampaign);
		softAssertion.assertEquals(db_userName, userName);
		softAssertion.assertEquals(eventsData.get(0).get("Currenr Url"), currentURL);
		softAssertion.assertEquals(db_playerID, identify);
		softAssertion.assertEquals(db_playerID, playerID);
		softAssertion.assertEquals(emailID, email);
		softAssertion.assertEquals(phoneNo, phone);
		softAssertion.assertEquals(eventsData.get(0).get("Client Type"), clientTye);
		softAssertion.assertEquals(eventsData.get(0).get("OS"), os);
		pattern = Pattern.compile("([0-9]{2}\\/[0-9]{2}\\/[0-9]{4})\\s([0-9]{2}:[0-9]{2}:[0-9]{2})");
		Matcher matcher = pattern.matcher(regDate);
		if (matcher.find()) {
			softAssertion.assertEquals(db_regDate, regDate);
		}
		softAssertion.assertEquals(db_regIP, regIP);
		softAssertion.assertEquals(eventsData.get(0).get("Device"), device);
		softAssertion.assertEquals(eventsData.get(0).get("Device Type"), deviceType);
		softAssertion.assertEquals(eventsData.get(0).get("Source"), source);
		softAssertion.assertEquals(eventsData.get(0).get("Browser"), browser);
		softAssertion.assertEquals(eventsData.get(0).get("Browser Version"), browserVersion);
		softAssertion.assertEquals(eventsData.get(0).get("Registration Via"), registrationVia);
		softAssertion.assertEquals(eventsData.get(0).get("OS Version"), osVersion);
		softAssertion.assertEquals(eventsData.get(0).get("Gender"), gender);
		softAssertion.assertEquals(eventsData.get(0).get("Reg City"), regCity);
		softAssertion.assertEquals(eventsData.get(0).get("Player Profile"), playerProfile);
		softAssertion.assertEquals(eventsData.get(0).get("Cash Current Balance"), cashBalance);
		softAssertion.assertEquals(eventsData.get(0).get("CT Source"), ctSource);

	}

	@Given("^Hybrid App is launched$")
	public void launchApp() throws InterruptedException {
		Thread.sleep(30000);
		if (appiumDriver.findElement(By.id("com.khelplay.rummy:id/first_name")).isDisplayed()) {
			System.out.println("App launched");
		}
	}

	@And("^Register and create new user$")
	public void registerNewUser() throws InterruptedException, SQLException {
		Random rand = new Random();
		phoneNo = "87" + (1000 + rand.nextInt(9000)) + (1000 + rand.nextInt(9000));
		emailID = "CleverTapUser" + (1000 + rand.nextInt(9000)) + "@" + (100 + rand.nextInt(900)) + ".com";
		register_HybridApp(emailID, phoneNo, password);
	}

	@Then("^verify below data should be shown in cleverTap$")
	public void cleverTapLogin_addCash(DataTable dataTable) throws Exception {
		List<Map<String, String>> eventsData = dataTable.asMaps();
		keywordsObject = new KeyWords();
		eventType = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[text()='Registration']");
		domainName = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Domain Name' or @data-original-title ='Domain Name']");
		aliasName = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Alias Name' or @data-original-title ='Alias Name']");
		adCampaign = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Ad Campaign' or @data-original-title ='Ad Campaign']");
		userName = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Username' or @data-original-title ='Username']");
		playerID = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Player ID' or @data-original-title ='Player ID']");
		email = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Email' or @data-original-title ='Email']");
		phone = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Phone' or @data-original-title ='Phone']");
		clientTye = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Client Type' or @data-original-title ='Client Type']");
		os = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Operating System' or @data-original-title ='Operating System']");
		regDate = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg Date' or @data-original-title ='Reg Date']");
		regDevice = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg Device' or @data-original-title ='Reg Device']");
       regIP = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg IP' or @data-original-title ='Reg IP']");
		device = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Device' or @data-original-title ='Device']");
		deviceType = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Device Type' or @data-original-title ='Device Type']");
		source = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Source' or @data-original-title ='Source']");
		registrationVia = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Registration Via' or @data-original-title ='Registration Via']");
		osVersion = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='OS Version' or @data-original-title ='OS Version']");
		playerProfile = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Player Profile' or @data-original-title ='Player Profile']");
		cashBalance = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Cash Current Balance' or @data-original-title ='Cash Current Balance']");
		ctSource = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='CT Source' or @data-original-title ='CT Source']");
		screenWidth = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Screen Width' or @data-original-title ='Screen Width']");
		deviceModel = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Device Model' or @data-original-title ='Device Model']");
		carrier = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Carrier' or @data-original-title ='Carrier']");
		wifi = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Carrier' or @data-original-title ='Carrier']");
		regCity = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='City' or @data-original-title ='City']");
		appVersion = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='App Version' or @data-original-title ='App Version']");
		brand = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Brand' or @data-original-title ='Brand']");
		regState = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='State' or @data-original-title ='State']");
		screenHeight = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Screen Height' or @data-original-title ='Screen Height']");
		ctApp_Version = keywordsObject.getText("xpath",
				"//table[@class='ev_21']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='CT App Version' or @data-original-title ='CT App Version']");
		
		softAssertion.assertEquals(eventsData.get(0).get("Event"), eventType);
		softAssertion.assertEquals(eventsData.get(0).get("Domain Name"), domainName);
		softAssertion.assertEquals(eventsData.get(0).get("Alias Name"), aliasName);
		softAssertion.assertEquals(eventsData.get(0).get("Ad Campaign"), adCampaign);
		softAssertion.assertEquals(db_userName, userName);
		softAssertion.assertEquals(db_playerID, playerID);
		softAssertion.assertEquals(emailID, email);
		softAssertion.assertEquals(phoneNo, phone);
		softAssertion.assertEquals(eventsData.get(0).get("Client Type"), clientTye);
		softAssertion.assertEquals(eventsData.get(0).get("OS"), os);
		pattern = Pattern.compile("([0-9]{2}\\/[0-9]{2}\\/[0-9]{4})\\s([0-9]{2}:[0-9]{2}:[0-9]{2})");
		Matcher matcher = pattern.matcher(regDate);
		if (matcher.find()) {
			softAssertion.assertEquals(db_regDate, regDate);
		}
		softAssertion.assertEquals(eventsData.get(0).get("Reg Device"), regDevice);
		softAssertion.assertEquals(db_regIP, regIP);
		softAssertion.assertEquals(eventsData.get(0).get("Device"), device);
		softAssertion.assertEquals(eventsData.get(0).get("Device Type"), deviceType);
		softAssertion.assertEquals(eventsData.get(0).get("Source"), source);
		softAssertion.assertEquals(eventsData.get(0).get("Registration Via"), registrationVia);
		softAssertion.assertEquals(eventsData.get(0).get("OS Version"), osVersion);
		softAssertion.assertEquals(eventsData.get(0).get("Player Profile"), playerProfile);
		softAssertion.assertEquals(eventsData.get(0).get("Cash Current Balance"), cashBalance);
		softAssertion.assertEquals(eventsData.get(0).get("CT Source"), ctSource);
		softAssertion.assertEquals(eventsData.get(0).get("Screen Width"), screenWidth);
		softAssertion.assertEquals(eventsData.get(0).get("Device Model"), deviceModel);
		softAssertion.assertEquals(eventsData.get(0).get("Carrier"), carrier);
		softAssertion.assertEquals(eventsData.get(0).get("wifi"), wifi);
		softAssertion.assertEquals(db_regCity, regCity);
		softAssertion.assertEquals(eventsData.get(0).get("App Version"), appVersion);
		softAssertion.assertEquals(eventsData.get(0).get("Brand"), brand);
		softAssertion.assertEquals(eventsData.get(0).get("State"), regState);
		softAssertion.assertEquals(eventsData.get(0).get("Screen Height"), screenHeight);
		softAssertion.assertEquals(eventsData.get(0).get("CT App Version"), ctApp_Version);
	}

}

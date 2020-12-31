package stepDefinition;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.PortableServer.CurrentOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utility.KeyWords;
import utility.MobileUtility;
import utility.Utility;

public class Login extends Utility {

	MobileUtility mu;
	Utility ut;
	KeyWords keywordsObject;
	SoftAssert softAssertion = new SoftAssert();
	String eventType;
	String name;
	String adCampaign;
	String firstName;
	String lastName;
	String emailVerified;
	String mobileVerified;
	String browser;
	String browserVersion;
	String currentCashBalance;
	String clientType;
	String currentURL;
	String promoCurrentBalance;
	String deviceType;
	String device;
	String domainName;
	String email;
	String firstDepositDate;
	String gender;
	String source;
	String os;
	String loginVia;
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
	String lastLogin;
	String aliasName;
	String ctSource;
	String dob;
	String carrier;
	String brand;
	String city;
	String appVersion;
	String wifi;
	String screenHeight;
	
	String profile_firstName = "Chris";
	String profile_lastName = "Hemsworth";
	String profile_address = "Mumbai";
	String profile_city = "Mumbai";
	String profile_DOB;
    String profile_State = "Maharashtra";
	String screenWidth;
	String deviceModel;
	String ctAppVersion;
	Pattern pattern;
	Matcher matcher;
	String sql;
	

	@Given("^Perform registration and logout after updating profile info.$")
	public void goToKhelPlay() throws InterruptedException, SQLException {
		// Store username and password from feature file
		Random rand = new Random();
		phoneNo = "87" + (1000 + rand.nextInt(9000)) + (1000 + rand.nextInt(9000));
		emailID = "CleverTapUser" + (1000 + rand.nextInt(9000)) + "@" + (100 + rand.nextInt(900)) + ".com";
		registerUser(emailID, password, phoneNo);
		driver.findElement(By.xpath("//div[@class='myaccount_page_menu']//a[@href='/my-profile']")).click();
		driver.findElement(By.id("fname")).clear();
		driver.findElement(By.id("fname")).sendKeys(profile_firstName);
		driver.findElement(By.id("lname")).clear();
		driver.findElement(By.id("lname")).sendKeys(profile_lastName);
		driver.findElement(By.id("address")).sendKeys(profile_address);
		driver.findElement(By.id("city")).clear();
		driver.findElement(By.id("city")).sendKeys(profile_city);
		driver.findElement(By.xpath("//input[@type='tel' and @tabindex='6']")).sendKeys("400063");
		Select dropDown = new Select(driver.findElement(By.id("state")));
		dropDown.selectByVisibleText(profile_State);;
		profile_DOB = driver.findElement(By.xpath("//input[@class='custome_input' and @tabindex ='7']")).getAttribute("value").toString();
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//button[@type='submit' and @tabindex ='13']")));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//button[@type='submit' and @tabindex ='13']")));
		Thread.sleep(8000);
		logout();
		
	}
	@And("^Login with newly registred user.$")
	public void loginAfterUpfate() throws InterruptedException, SQLException {
		login(emailID, password);
	}

	@And("^Login to cleverTap with below credentials$")
	public void cleverTapLogin_LoginEvent(DataTable creddata) throws InterruptedException {
		String username, password;
		List<String> data = creddata.row(0);
		username = data.get(0);
		password = data.get(1);
		loginCleverTap(username, password);
		Thread.sleep(10000);
	}

	@And("^Search login events for created user.$")
	public void searchUserCleverTap_LoginEvent() throws Exception {
		keywordsObject = new KeyWords();
		// click segement on Home page of Clever Tap
		keywordsObject.click("xpath", "//div[@class='segments']");
		Thread.sleep(2000);
		keywordsObject.setText("id", "searchIput", emailID);
		keywordsObject.click("id", "searchBtn");
		Thread.sleep(4000);
		// Verify is result for email is displayed or not
		Assert.assertEquals(true,
				Utility.driver.findElement(By.xpath("//div[@class ='user-profile__name ']")).isDisplayed());
		// keywordsObject.scrollNClick("xpath", "//div[@id='fbGrid']//span//a");
		Thread.sleep(5000);
		keywordsObject.click("id", "dvViewer");
		Thread.sleep(3000);
		// ([0-9]{2}\/[0-9]{2}\/[0-9]{4})\s([0-9]{2}:[0-9]{2}:[0-9]{2}\s[a-z]{2}) regex
		// for date and time

	}

	@Then("^Below mentioned data should be reflected in cleverTap$")
	public void loginEvent_DataVerification(DataTable dataTable) throws Exception {

		List<Map<String, String>> eventsData = dataTable.asMaps();
		String dynamicdate = eventsData.get(0).get("Action Date");
		String xpath = "//h4[text()='" + dynamicdate + "']/ancestor::div[@class='new_day']";
		System.out.println(xpath);
		eventType = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']//tbody//tr//td[2]//span[text()='Login']");
		name = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Name' or @data-original-title ='Name']");
		domainName = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Domain Name' or @data-original-title ='Domain Name']");
		adCampaign = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Ad Campaign' or @data-original-title ='Ad Campaign']");
		userName = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Username' or @data-original-title ='Username']");
		currentURL = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Current URL' or @data-original-title ='Current URL']");
		identifyId = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Identity' or @data-original-title ='Identity']");
		playerID = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Player ID' or @data-original-title ='Player ID']");
		email = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Email' or @data-original-title ='Email']");
		phone = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Phone' or @data-original-title ='Phone']");
		firstName = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='First Name' or @data-original-title ='First Name']");
		lastName = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Last Name' or @data-original-title ='Last Name']");
		gender = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Gender' or @data-original-title ='Gender']");
		dob = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='DOB' or @data-original-title ='DOB']");
		regState = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg State' or @data-original-title ='Reg State']");
		regCity = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg City' or @data-original-title ='Reg City']");
		emailVerified = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Email Verified' or @data-original-title ='Email Verified']");
		mobileVerified = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Mobile Verified' or @data-original-title ='Mobile Verified']");
		playerProfile = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Player Profile' or @data-original-title ='Player Profile']");
		promoCurrentBalance = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Promo Current Balance' or @data-original-title ='Promo Current Balance']");
		clientType = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Client Type' or @data-original-title ='Client Type']");
		os = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Operating System' or @data-original-title ='Operating System']");
		regDate = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg Date' or @data-original-title ='Reg Date']");
		regDevice = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg Device' or @data-original-title ='Reg Device']");
		ip = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Reg IP' or @data-original-title ='Reg IP']");
		device = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Device' or @data-original-title ='Device']");
		deviceType = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Device Type' or @data-original-title ='Device Type']");
		source = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Source' or @data-original-title ='Source']");
		osVersion = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='OS Version' or @data-original-title ='OS Version']");
		loginVia = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Login Via' or @data-original-title ='Login Via']");
		browser = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Browser' or @data-original-title ='Browser']");
		browserVersion = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Browser Version' or @data-original-title ='Browser Version']");
		currentCashBalance = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='Cash Current Balance' or @data-original-title ='Cash Current Balance']");
		ctSource = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/following-sibling::div[1]//table[@class='ev_20']/tbody/tr/td[2]//span[@class='label-gray ']//span[@title='CT Source' or @data-original-title ='CT Source']");

		softAssertion.assertEquals(eventsData.get(0).get("Event").toString(), eventType);
		softAssertion.assertEquals(db_userName, name);
		softAssertion.assertEquals(eventsData.get(0).get("Domain Name").toString(), domainName);
		softAssertion.assertEquals(eventsData.get(0).get("adCampagin").toString(), adCampaign);
		softAssertion.assertEquals(eventsData.get(0).get("CurrenrURL").toString(), currentURL);
		softAssertion.assertEquals(db_playerID, identifyId);
		softAssertion.assertEquals(db_playerID, playerID);
		softAssertion.assertEquals(emailID, email);
		softAssertion.assertEquals("+91"+phoneNo, phone);
		softAssertion.assertEquals(profile_firstName, firstName);
		softAssertion.assertEquals(profile_lastName, lastName);
		softAssertion.assertEquals(eventsData.get(0).get("Gender").toString(), gender);
		//pattern = Pattern.compile("([0-9]{2}\\/[0-9]{2}\\/[0-9]{4})\\s([0-9]{2}:[0-9]{2}:[0-9]{2})");
		//matcher = pattern.matcher(profile_DOB);
		//if (matcher.find()) {
			softAssertion.assertEquals(profile_DOB, dob);
		//}
		softAssertion.assertEquals(eventsData.get(0).get("RegState").toString(), regState);
		softAssertion.assertEquals(profile_city, regCity);
		softAssertion.assertEquals(eventsData.get(0).get("Email verified").toString(), emailVerified);
		softAssertion.assertEquals(eventsData.get(0).get("phone verified").toString(), mobileVerified);
		softAssertion.assertEquals(eventsData.get(0).get("player Profile").toString(), playerProfile);
		softAssertion.assertEquals(eventsData.get(0).get("Promo current Balance").toString(), promoCurrentBalance);
		softAssertion.assertEquals(eventsData.get(0).get("ClientType").toString(), clientType);
		softAssertion.assertEquals(eventsData.get(0).get("OS").toString(), os);
		//pattern = Pattern.compile("([0-9]{2}\\/[0-9]{2}\\/[0-9]{4})\\s([0-9]{2}:[0-9]{2}:[0-9]{2})");
		//matcher = pattern.matcher(regDate);
		//if (matcher.find()) {
		//	softAssertion.assertEquals(db_regDate, regDate);
		//}
		softAssertion.assertEquals(eventsData.get(0).get("Reg Device").toString(), regDevice);
		softAssertion.assertEquals(db_regIP, ip);
		softAssertion.assertEquals(eventsData.get(0).get("device").toString(), device);
		softAssertion.assertEquals(eventsData.get(0).get("Device type").toString(), device);
		softAssertion.assertEquals(eventsData.get(0).get("Soure").toString(), source);
		softAssertion.assertEquals(eventsData.get(0).get("OS version").toString(), osVersion);
		softAssertion.assertEquals(eventsData.get(0).get("Login Via").toString(), loginVia);
		softAssertion.assertEquals(eventsData.get(0).get("Browser").toString(), browser);
		softAssertion.assertEquals(eventsData.get(0).get("Browser Version").toString(), browserVersion);
		softAssertion.assertEquals(eventsData.get(0).get("cash Balance").toString(), currentCashBalance);
		softAssertion.assertEquals(eventsData.get(0).get("Ct Source").toString(), ctSource);
		softAssertion.assertAll();
	}

	@Given("^Hybrid app login event with mentioned username and password$")
	public void loginHybridApp(DataTable creds) throws SQLException, InterruptedException {
		String username, password;
		List<String> data = creds.row(0);
		username = data.get(0);
		password = data.get(1);
		sql = "select player_id, mobile_no, user_name, reg_device from  weaver.st_pm_player_master where email_id = '"+ username+"'";
		
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			db_playerID = rs.getString(1);
			phoneNo = rs.getString(2);
			db_userName = rs.getString(3);
			db_registerDevice = rs.getString(4);
		}
		String sql1 = "select registration_ip from weaver.st_pm_player_info where player_id = '"+ db_playerID+"'";
		rs = stmt.executeQuery(sql1);
		while(rs.next()) {
			db_regIP = rs.getString(1);
		}
		loginUser_HybridApp(username, password);
		String sql2 =  "select cash_bal from weaver.st_txn_plr_wallet_master where player_id = '" + db_playerID + "'";
		rs = stmt.executeQuery(sql2);
		while(rs.next()) {
			db_cashBalance = rs.getString(1);
			db_cashBalance = db_cashBalance.substring(0, db_cashBalance.indexOf("."));
		}
	}
	@And("^Perform search for below mentioned user$")
	public void searchUser(DataTable email) throws Exception {
		List<String> data = email.row(0);
		keywordsObject = new KeyWords();
		// click segement on Home page of Clever Tap
		keywordsObject.click("xpath", "//div[@class='segments']");
		Thread.sleep(2000);
		keywordsObject.setText("id", "searchIput", data.get(0));
		keywordsObject.click("id", "searchBtn");
		Thread.sleep(4000);
		// Verify is result for email is displayed or not
		Assert.assertEquals(true,
				Utility.driver.findElement(By.xpath("//div[@class ='user-profile__name ']")).isDisplayed());
		// keywordsObject.scrollNClick("xpath", "//div[@id='fbGrid']//span//a");
		Thread.sleep(5000);
		keywordsObject.click("id", "dvViewer");
		Thread.sleep(3000);
	}

	@Then("^For Hybrid app, mentioned data should be reflected in clever Tap$")
	public void verifyLoginData(DataTable dataTable) throws Exception {
		List<Map<String, String>> eventsData = dataTable.asMaps();
		String dynamicdate = eventsData.get(0).get("Action Date");
		String xpath = "//h4[text()='" + dynamicdate + "']/ancestor::div[@class='new_day']";
		System.out.println(xpath);
		eventType = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[text()='Login']");
		domainName = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Domain Name' or @data-original-title ='Domain Name']");
		adCampaign = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Ad Campaign' or @data-original-title ='Ad Campaign']");
		userName = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Username' or @data-original-title ='Username']");
		playerID = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Player ID' or @data-original-title ='Player ID']");
		email = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Email' or @data-original-title ='Email']");
		phone = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Phone' or @data-original-title ='Phone']");
		gender = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Gender' or @data-original-title ='Gender']");
		regCity = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Reg City' or @data-original-title ='Reg City']");
		playerProfile = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Player Profile' or @data-original-title ='Player Profile']");
		promoCurrentBalance = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Promo Current Balance' or @data-original-title ='Promo Current Balance']");
		clientType = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Client Type' or @data-original-title ='Client Type']");
		firstDepositDate = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='First Deposit Date' or @data-original-title ='First Deposit Date']");
		os = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Operating System' or @data-original-title ='Operating System']");
		regDate = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Reg Date' or @data-original-title ='Reg Date']");
		regDevice = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Reg Device' or @data-original-title ='Reg Device']");
		ip = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Reg IP' or @data-original-title ='Reg IP']");
		deviceType = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Device Type' or @data-original-title ='Device Type']");
		source = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Source' or @data-original-title ='Source']");
		osVersion = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='OS Version' or @data-original-title ='OS Version']");
		loginVia = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Login Via' or @data-original-title ='Login Via']");
		currentCashBalance = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Cash Current Balance' or @data-original-title ='Cash Current Balance']");
		ctSource = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='CT Source' or @data-original-title ='CT Source']");
		carrier = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Carrier' or @data-original-title ='Carrier']");
		brand = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Brand' or @data-original-title ='Brand']");
		city = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='City' or @data-original-title ='City']");
		appVersion = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='App Version' or @data-original-title ='App Version']");
		wifi = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Wifi' or @data-original-title ='Wifi']");
		screenHeight = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Screen Height' or @data-original-title ='Screen Height']");
		state = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='State' or @data-original-title ='State']");
		screenWidth = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Screen Width' or @data-original-title ='Screen Width']");
		deviceModel = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='Device Model' or @data-original-title ='Device Model']");
		ctAppVersion = keywordsObject.getText("xpath", "//h4[text()='" + dynamicdate
				+ "']/ancestor::div[@class='new_day']//div[@class='dv_js_selector dv_Mobile'][1]//table//tbody//tr//td[2]//span[@title='CT App Version' or @data-original-title ='CT App Version']");

		softAssertion.assertEquals(eventsData.get(0).get("Event").toString(), eventType);
		softAssertion.assertEquals(eventsData.get(0).get("Domain Name").toString(), domainName);
		softAssertion.assertEquals(eventsData.get(0).get("Ad campaign").toString(), adCampaign);
		softAssertion.assertEquals(db_userName, userName);
		softAssertion.assertEquals(db_playerID, playerID);
		softAssertion.assertEquals(eventsData.get(0).get("email").toString(), email);
		softAssertion.assertEquals("+91"+phoneNo, phone);
		softAssertion.assertEquals(eventsData.get(0).get("Gender").toString(), gender);
		softAssertion.assertEquals(eventsData.get(0).get("Reg City").toString(), regCity);
		softAssertion.assertEquals(eventsData.get(0).get("Player Profile").toString(), playerProfile);
		// softAssertion.assertEquals(eventsData.get(0).get("CurrenrURL").toString(),
		// currentURL);
		softAssertion.assertEquals(eventsData.get(0).get("Promo Current Balance").toString(), promoCurrentBalance);
		softAssertion.assertEquals(eventsData.get(0).get("Client Type").toString(), clientType);
		// softAssertion.assertEquals(eventsData.get(0).get("First Deposit
		// Date").toString(), firstDepositDate);
		// softAssertion.assertEquals(eventsData.get(0).get("OS").toString(), os);
		softAssertion.assertEquals(eventsData.get(0).get("Reg Date").toString(), regDate);
		softAssertion.assertEquals(db_registerDevice, regDevice);
		softAssertion.assertEquals(db_regIP, ip);
		// softAssertion.assertEquals(eventsData.get(0).get("Identify").toString(),
		// identifyId);
		softAssertion.assertEquals(eventsData.get(0).get("Device Type").toString(), deviceType);
		softAssertion.assertEquals(eventsData.get(0).get("Source").toString(), source);
		softAssertion.assertEquals(eventsData.get(0).get("OS Version").toString(), osVersion);
		softAssertion.assertEquals(eventsData.get(0).get("Login Via").toString(), loginVia);
		softAssertion.assertEquals(db_cashBalance, currentCashBalance);
		softAssertion.assertEquals(eventsData.get(0).get("CT Source").toString(), ctSource);
		softAssertion.assertEquals(eventsData.get(0).get("Carrier").toString(), carrier);
		softAssertion.assertEquals(eventsData.get(0).get("Brand").toString(), brand);
		softAssertion.assertEquals(eventsData.get(0).get("City").toString(), city);
		softAssertion.assertEquals(eventsData.get(0).get("App Version").toString(), appVersion);
		softAssertion.assertEquals(eventsData.get(0).get("wifi").toString(), wifi);
		softAssertion.assertEquals(eventsData.get(0).get("Screen Height").toString(), screenHeight);
		softAssertion.assertEquals(eventsData.get(0).get("State").toString(), state);
		softAssertion.assertEquals(eventsData.get(0).get("Screen Width").toString(), screenWidth);
		softAssertion.assertEquals(eventsData.get(0).get("Device Model").toString(), deviceModel);
		softAssertion.assertEquals(eventsData.get(0).get("CT App Version").toString(), ctAppVersion);
		softAssertion.assertAll();

	}

}

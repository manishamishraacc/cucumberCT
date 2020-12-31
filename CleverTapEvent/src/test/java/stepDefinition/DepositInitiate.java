package stepDefinition;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utility.KeyWords;
import utility.Utility;

public class DepositInitiate extends Utility {

	String parentWindow;
	String childWindow;
	String event;
	String domainName;
	String userName;
	String amount;
	String currentURL;
	String playerID;
	String identify ;
	String email;
	String phone;
	String browser;
	String State ;
	String city ;
	String country;
	String os;
	String clientType;
	String deviceType;
	String aliasName;
	String regDate;
	String osVersion;
	String emailVerified;
	String phoneVerified;
	String ctSource;

	@Given("^KhelPlay rummy portal$")
	public void navigateKPR() {
		driver.navigate().to("https://test.khelplayrummy.com");
	}

	@And("^create new user and inititate deposit$")
	public void initiateDeposit() throws SQLException, InterruptedException {
		Random rand = new Random();
		phoneNo = "87" + (1000 + rand.nextInt(9000)) + (1000 + rand.nextInt(9000));
		emailID = "CleverTapUser" + (1000 + rand.nextInt(9000)) + "@" + (100 + rand.nextInt(900)) + ".com";
		registerUser(emailID, password, phoneNo);
		driver.findElement(By.xpath("//div[@class='tab_act_btn']//a[@href='/cashier-initiate']")).click();
		Thread.sleep(10000);
		Set<String> winHandles = driver.getWindowHandles();
		parentWindow = (String) winHandles.toArray()[0];
		childWindow = (String) winHandles.toArray()[1];
		driver.switchTo().window(childWindow);
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Assert.assertEquals("https://test.khelplayrummy.com/select-amount-new#chooseAmount", driver.getCurrentUrl());
		//driver.findElement(By.xpath("//button[@class='close']")).click();
		driver.findElement(By.xpath("//input[@class='allow_ony_nums custome_input']")).clear();
		driver.findElement(By.xpath("//input[@class='allow_ony_nums custome_input']")).sendKeys("100");
		driver.findElement(By.xpath("//div[@class='cashier_action']//a[@id='cntbtn' and text()='PROCEED']")).click();
		driver.findElement(By.xpath("//div[@class='icon axis_bank']")).click();
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(parentWindow);
	}

	@And("^Using below data login to clever Tap$")
	public void cleverTapLogin(DataTable creddata) throws InterruptedException {
		String username, password;
		List<String> data = creddata.row(0);
		username = data.get(0);
		password = data.get(1);
		loginCleverTap(username, password);
		Thread.sleep(8000);
	}

	@Then("^Deposit initiate should be recorded in clevertap")
	public void verifyEvent(DataTable table) throws InterruptedException {
		// Store event realted data from feature file into list of map
		List<Map<String, String>> eventsData = table.asMaps();
		// search user in clever tap
		driver.findElement(By.xpath("//div[@class='segments']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("searchIput")).sendKeys(emailID);
		driver.findElement(By.id("searchBtn")).click();
		Thread.sleep(4000);
		
		// Verify is result for email is displayed or not
		Assert.assertEquals(true,driver.findElement(By.xpath("//div[@class ='user-profile__name ']")).isDisplayed());
		Thread.sleep(5000);
		driver.findElement(By.id("dvViewer")).click();
		Thread.sleep(3000);

		// extract data from searched profile.
		KeyWords keywordsObject = new KeyWords();
		event = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[text()='Deposit Initiate']")).getText();
		domainName = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Domain Name' or @data-original-title ='Domain Name']")).getText();
		userName = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Username' or @data-original-title ='Username']")).getText();
		amount = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Amount' or @data-original-title ='Amount']")).getText();
	    currentURL = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Current URL' or @data-original-title ='Current URL']")).getText();
	    identify = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Identity' or @data-original-title ='Identity']")).getText();
	    playerID = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Player ID' or @data-original-title ='Player ID']")).getText();
	    email = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Email' or @data-original-title ='Email']")).getText();
	    phone = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Phone' or @data-original-title ='Phone']")).getText();
	    browser = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Browser' or @data-original-title ='Browser']")).getText();
	    State = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='State' or @data-original-title ='State']")).getText();
	    city = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='City' or @data-original-title ='City']")).getText();
	    country = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Country' or @data-original-title ='Country']")).getText();
	    os = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Operating System' or @data-original-title ='Operating System']")).getText();
	    clientType = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Client Type' or @data-original-title ='Client Type']")).getText();
	    deviceType = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Device Type' or @data-original-title ='Device Type']")).getText();
	    aliasName = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Alias Name' or @data-original-title ='Alias Name']")).getText();
	    regDate = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Reg Date' or @data-original-title ='Reg Date']")).getText();
	    osVersion = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='OS Version' or @data-original-title ='OS Version']")).getText();
	    emailVerified = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Email Verified' or @data-original-title ='Email Verified']")).getText();
	    phoneVerified = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='Mobile Verified' or @data-original-title ='Mobile Verified']")).getText();
	    ctSource = driver.findElement(By.xpath("//table[@class='ev_22']//tbody//tr//td[2]//span[@class='label-gray ']//span[@title='CT Source' or @data-original-title ='CT Source']")).getText();
	   
	    
	    Assert.assertEquals(eventsData.get(0).get("Event").toString(), event);
	    Assert.assertEquals(eventsData.get(0).get("Domain Name").toString(), domainName);
	    Assert.assertEquals(db_userName, userName);
	    Assert.assertEquals(eventsData.get(0).get("Amount").toString(), amount);
	    Assert.assertEquals(eventsData.get(0).get("Current URL").toString(), currentURL);
	    Assert.assertEquals(db_playerID, identify);
	    Assert.assertEquals(db_playerID, playerID);
	    Assert.assertEquals(emailID, email);
	    Assert.assertEquals("+91"+phoneNo, phone);
	   // Assert.assertEquals(db_regDate, regDate.replace("/", "-"));
	    Assert.assertEquals(eventsData.get(0).get("Browser").toString(), browser);
	    Assert.assertEquals(eventsData.get(0).get("State").toString(), State);
	    Assert.assertEquals(eventsData.get(0).get("Country").toString(), country);
	    Assert.assertEquals(eventsData.get(0).get("OS").toString(), os);
	    Assert.assertEquals(eventsData.get(0).get("Client Type").toString(), clientType);
	    Assert.assertEquals(eventsData.get(0).get("Device Type").toString(), deviceType);
	    Assert.assertEquals(eventsData.get(0).get("Alias Name").toString(), aliasName);
	    Assert.assertEquals(eventsData.get(0).get("Email Verified").toString(), emailVerified);
	    Assert.assertEquals(eventsData.get(0).get("OS Version").toString(), osVersion);
	    Assert.assertEquals(eventsData.get(0).get("Phone Verified").toString(), phoneVerified);
	    Assert.assertEquals(eventsData.get(0).get("CT Source").toString(), ctSource);
	   
	}
}

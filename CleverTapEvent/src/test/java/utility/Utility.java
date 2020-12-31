package utility;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Utility {
	public static WebDriver driver;
	protected ChromeOptions options;
	protected JavascriptExecutor js ;
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static Connection conn = null;
	public static Statement stmt = null;
	public static String hostname;
	public static String DB_URL = "jdbc:mysql://weaver-only-2020-data.cluster-ct6k0a8gki8b.ap-south-1.rds.amazonaws.com";
	// public static String DB_URL_1;
	// public static String DB_URL_2;
	// public static String USER;
	// public static String PASS;
	protected ResultSet rs = null;
	protected ResultSet rs_1 = null;
	protected ResultSet rs_2 = null;
	public static String USER_TEST = "qateam";
	public static String PASS_TEST = "qaweaver2020";
	public static String db_playerID;
	public static String db_regDate;
	public static String lastLoginDate;
	public static String firstName, lastName;
	public static String db_userName;
	public static String db_regIP;
	public static String db_regCity;
	protected String db_registerDevice;
	protected String db_cashBalance;
	protected String phoneNo;
	protected String emailID;
	protected String password = "Test123";
	
	public static int test_widthResolution;
	public static int test_heightResolution;

	public final int recorderd_widthResolution = 1080;
	public final int recorderd_heightResolution = 2208;

	protected int xCoordinate = 0;
	protected int yCoordinate = 0;

	public static AppiumDriver<MobileElement> appiumDriver;
	public static DesiredCapabilities cap;

	public static void sql_initialization_TEST() {
		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER_TEST, PASS_TEST);
			System.out.println("Connected successfully to database...");

			// Execute a query
			System.out.println("Reading server information from Weaver");
			stmt = conn.createStatement();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public void setNLaunchChrome() {
		WebDriverManager.chromedriver().driverVersion("85.0.4183.87").setup();
		// System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("enable-automation");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-gpu");
		// options.addArguments("--headless");
		options.addArguments("--disable-notifications");
		options.addArguments("--window-size=1920,1080");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}

	public void login(String username, String pwd) throws InterruptedException, SQLException {
		driver.navigate().to("https://test.khelplayrummy.com");
		driver.findElement(By.xpath("//input[@placeholder='Username/Email/Mobile']")).sendKeys(username);
		driver.findElement(
				By.xpath("//input[@placeholder='Username/Email/Mobile']/following-sibling::input[@id='password']"))
				.sendKeys(pwd);
		driver.findElement(By.xpath("//input[@placeholder='Login']")).click();
		Thread.sleep(10000);
		driver.findElement(
				By.xpath("//div[@class='modal-content']//div[@class='sp-module-content']//button[@class='close']"))
				.click();
		String sql1 = "select player_id , user_name from weaver.st_pm_player_master where email_id = '" + username + "'";
		System.out.println(sql1);
		rs = stmt.executeQuery(sql1);
		while (rs.next()) {
			db_playerID = rs.getString(1);
			db_userName = rs.getString(2);
		}
	}

	public void registerUser(String email, String password, String phone) throws SQLException, InterruptedException {
		System.out.println("******* Start User Registration *******");
		driver.navigate().to("https://test.khelplayrummy.com");
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("reg_password")).sendKeys(password);
		driver.findElement(By.id("mobile")).sendKeys(phone);
		driver.findElement(By.xpath("//div[@class='button_holder']//button[@type ='submit']")).click();
		Thread.sleep(10000);
		String sql1 = "select player_id, last_login_date , user_name , last_login_ip from weaver.st_pm_player_master where email_id = '"+ email + "'";
		System.out.println(sql1);
		rs = stmt.executeQuery(sql1);
		while (rs.next()) {
			db_playerID = rs.getString(1);
			db_regDate = rs.getString(2);
			db_userName = rs.getString(3);
			db_regIP = rs.getString(4);
		}
		System.out.println("******* End User Registration *******");

	}

	public void logout() {
     driver.findElement(By.xpath("//i[@class='fa fa-power-off']")).click();
     driver.findElement(By.xpath("//button[text()='Yes']")).click();
	}

	public void launchHybridApp() throws MalformedURLException {
		System.out.println("Starting kpr app");

		cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "OPPO F11 PRO");
		// cap.setCapability("udid", "OJMVNFJJLVP7LBCA");
		cap.setCapability("udid", "65SOHY8TZ94DE6PN");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "10");
		// cap.setCapability("unicodeKeyboard", true);
		// cap.setCapability("resetKeyboard", true);
		cap.setCapability("appPackage", "com.khelplay.rummy");

		cap.setCapability("appActivity", "org.cocos2dx.javascript.splash.AppActivity");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");

		appiumDriver = new AndroidDriver<MobileElement>(url, cap);
		appiumDriver.resetApp();
		appiumDriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		System.out.println("Application Started......");
	}

	public void loginUser_HybridApp(String myUserName, String myPassword) throws SQLException, InterruptedException {
		MobileElement userName = appiumDriver.findElement(By.id("com.khelplay.rummy:id/first_name"));
		userName.sendKeys(myUserName);
		MobileElement passwordEle = appiumDriver.findElement(By.id("com.khelplay.rummy:id/et_password"));
		passwordEle.sendKeys(myPassword);
		MobileElement loginBtn = appiumDriver.findElement(By.id("com.khelplay.rummy:id/login_text_view"));
		loginBtn.click();

		Thread.sleep(5000);
		String sql1 = "select player_id from weaver.st_pm_player_master where email_id = '" + myUserName + "'";
		System.out.println(sql1);
		rs = stmt.executeQuery(sql1);
		while (rs.next()) {
			db_playerID = rs.getString(1);
		}
		System.out.println("Login Successful");

	}

	public void register_HybridApp(String email, String phone, String password)
			throws InterruptedException, SQLException {
		System.out.println("**** Registration Stated ****");
		MobileElement registerLink = appiumDriver.findElement(By.id("com.khelplay.rummy:id/register"));
		registerLink.click();
		MobileElement emailTxtBox = appiumDriver.findElement(By.id("com.khelplay.rummy:id/email_id"));
		emailTxtBox.sendKeys(email);
		MobileElement mobilextBox = appiumDriver.findElement(By.id("com.khelplay.rummy:id/mobile_number"));
		mobilextBox.sendKeys(phone);
		MobileElement passwordTxtBox = appiumDriver.findElement(By.id("com.khelplay.rummy:id/password"));
		passwordTxtBox.sendKeys(password);
		MobileElement registerButton = appiumDriver.findElement(By.id("com.khelplay.rummy:id/register_text_view"));
		registerButton.click();
		Thread.sleep(10000);
		try {
			Thread.sleep(10000);
			// right navigation bar on home page
			xCoordinate = heightCalculation(test_widthResolution, recorderd_widthResolution, 987);
			yCoordinate = widthCalculation(test_heightResolution, recorderd_heightResolution, 77);
			new TouchAction(appiumDriver).tap(point(xCoordinate, yCoordinate))
					.waitAction(waitOptions(Duration.ofMillis(250))).perform();
			Thread.sleep(2000);

			MobileElement myAccount1 = appiumDriver
					.findElement(By.xpath("//android.widget.TextView[@text='My Account']"));
			if (myAccount1.isDisplayed()) {
				System.out.println("**** Registration Done ****");
			}
		} catch (Exception e) {
			System.out.println("Test in app not found");
		}
		String sql1 = "select player_id, last_login_date , user_name , last_login_ip from weaver.st_pm_player_master where email_id = '"
				+ email + "'";
		System.out.println(sql1);
		rs = stmt.executeQuery(sql1);
		while (rs.next()) {
			db_playerID = rs.getString(1);
			db_regDate = rs.getString(2);
			db_userName = rs.getString(3);
			db_regIP = rs.getString(4);
		}
		String sql2 = "select city from weaver.st_pm_player_info where player_id = '" + db_playerID + "'";
		while (rs.next()) {

		}
	}

	public static int heightCalculation(float testingHeight, float heightResolution, float button_xCoordinate) {
		float xCoordinate;
		xCoordinate = ((1 / heightResolution) * testingHeight) * button_xCoordinate;
		// System.out.println(xCoordinate);
		// System.out.println("\n");
		return (int) xCoordinate;
	}

	public static int widthCalculation(float testingWidth, float recorderdWidthResolution, float button_yCoordinate) {
		float yCoordinate;
		yCoordinate = ((1 / recorderdWidthResolution) * testingWidth) * button_yCoordinate;
		// System.out.println(yCoordinate);
		return (int) yCoordinate;
	}

	public static void scrollDown() {
		Dimension dim = appiumDriver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int startx = (int) (width * 0.9);
		int endx = (int) (width * 0.9);
		int starty = (int) (height * 0.5);
		int endy = (int) (height * 0.1);
		TouchAction act = new TouchAction(appiumDriver);
		act.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(endx, endy)).release().perform();
	}

	public void loginCleverTap(String username, String password) throws InterruptedException {
		driver.get("https://in1.dashboard.clevertap.com/login.html#/");
		Thread.sleep(8000);
		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class ='btn btn-primary btn-login login-button']")).click();
		Thread.sleep(10000);
		try {
			if (driver.findElement(By.xpath("//div[@class='announcement-popup']")).isDisplayed()) {
				// driver.findElement(By.xpath("//div[@class='announcement-popup']")).click();
			}
		} catch (Exception e) {
			System.out.println("Announcement popup is not displayed");
		}
	}
}

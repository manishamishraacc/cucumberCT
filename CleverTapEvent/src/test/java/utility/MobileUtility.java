package utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class MobileUtility extends Utility{
	// Change as per device used
	public static int test_widthResolution ;
	public static int test_heightResolution ;

	public final int recorderd_widthResolution = 1080;
	public final int recorderd_heightResolution = 2208;

	protected int xCoordinate = 0;
	protected int yCoordinate = 0;

	//public static AppiumDriver<MobileElement> appiumDriver;
	public static DesiredCapabilities cap;
	
	public void loginUser(String myUserName, String myPassword) throws SQLException, InterruptedException {
		MobileElement userName = appiumDriver.findElement(By.id("com.khelplay.rummy:id/first_name"));
		userName.sendKeys(myUserName);
		MobileElement passwordEle = appiumDriver.findElement(By.id("com.khelplay.rummy:id/et_password"));
		passwordEle.sendKeys(myPassword);
		MobileElement loginBtn = appiumDriver.findElement(By.id("com.khelplay.rummy:id/login_text_view"));
		loginBtn.click();

		Thread.sleep(5000);
		System.out.println("Login Successful");
		
	}
	public static int heightCalculation(float testingHeight, float heightResolution,  float button_xCoordinate) {
		float xCoordinate;
		xCoordinate = ((1/heightResolution) * testingHeight) * button_xCoordinate;
	//	System.out.println(xCoordinate);
		//System.out.println("\n");
		return (int)xCoordinate;
	}
	public static int widthCalculation(float testingWidth, float recorderdWidthResolution,  float button_yCoordinate) {
		float yCoordinate;
		yCoordinate = ((1/recorderdWidthResolution) * testingWidth) * button_yCoordinate;
		//System.out.println(yCoordinate);
		return (int)yCoordinate;
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
		act.press(PointOption.point(startx, starty))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(endx, endy))
		.release().perform();
	}

}

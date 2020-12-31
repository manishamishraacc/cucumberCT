package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class KeyWords extends Utility{

	
	public String getText(String locatorType, String locatorValue) throws Exception {
		return driver.findElement(this.getObject(locatorType, locatorValue)).getText();
	}
	public void setText(String locatorType, String locatorValue, String value) throws Exception {
		driver.findElement(this.getObject(locatorType, locatorValue)).sendKeys(value);
	}
	public void click(String locatorType, String locatorValue) throws Exception {
		driver.findElement(this.getObject(locatorType, locatorValue)).click();
	}
	public void sendKeys(String locatorType, String locatorValue) throws Exception {
		driver.findElement(this.getObject(locatorType, locatorValue)).sendKeys(Keys.ENTER);
	}
	public void scrollNClick(String locatorType, String locatorValue) throws Exception {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);",
		driver.findElement(this.getObject(locatorType, locatorValue)));
		je.executeScript("arguments[0].click();", driver.findElement(this.getObject(locatorType, locatorValue)));
	}

	public void isElementPresent(String locatorType, String locatorValue) throws Exception {
		if (driver.findElement(this.getObject(locatorType, locatorValue)).isDisplayed()) {
			System.out.println("Element is resent");
		}
		
	}
	
	private By getObject(String objectType,String objectName ) throws Exception{
        //Find by xpath
        if(objectType.equalsIgnoreCase("XPATH")){
            
            return By.xpath((objectName));
        }
        //find by class
        else if(objectType.equalsIgnoreCase("CLASSNAME")){
            
            return By.className((objectName));
            
        }
        //find by name
        else if(objectType.equalsIgnoreCase("NAME")){
            
            return By.name((objectName));
            
        }
        //Find by css
        else if(objectType.equalsIgnoreCase("CSS")){
            
            return By.cssSelector((objectName));
            
        }
        //find by link
        else if(objectType.equalsIgnoreCase("LINK")){
            
            return By.linkText((objectName));
            
        }
      else if(objectType.equalsIgnoreCase("ID")){
            
            return By.id((objectName));
            
        }
        //find by partial link
        else if(objectType.equalsIgnoreCase("PARTIALLINK")){
            
            return By.partialLinkText((objectName));
            
        }else
        {   
 
        	throw new Exception("Wrong object type");
            
        }

}}

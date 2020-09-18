package in.valtech.uiFunctions;


import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;


public class GUIFunctions {

public static Logger log = Logger.getLogger(GUIFunctions.class.getName());
	
	/**
	 * 
	 * Method Name : navigateBack
	 *
	 * WebDriver
	 */
	public static WebDriver navigateBack(WebDriver driver)
	{
		try{
		driver.navigate().back();
		System.out.println("User is navigated to back");
		}
		catch(Exception e){
			log.error("Navigation to previous page failed--> \n" + e);
			Reporter.log("<p>Navigation to previous page failed");
		}
		return(driver);
	}
	/**
	 * 
	 * Method Name : sleep
	 *
	 * void
	 */
	public static void sleep() throws InterruptedException
	{	
		Thread.sleep(2000);
	}
	/**
	 * 
	 * Method Name : implicitWait
	 *
	 * void
	 */
	
	public static void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		log.info("Browser waited for '5' secs");
	}
	
	/**
	 * 
	 * Method Name : webdriverWait
	 *
	 * void
	 */
	public static void webdriverWait(WebDriver driver, By by){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		log.info("Waited 10 secs for"+ ele);
		
	}
	/**
	 * 
	 * Method Name : clickButton
	 *
	 * void
	 */
	public static void clickButton(WebDriver driver, By by){
		try{
			driver.findElement(by).click();
			log.info("Successfully clicked the button '");
		}
		 catch (NoSuchElementException e) {
			 log.error("Button to click is not present because of" + e);
	}
	}
	/**
	 * 
	 * Method Name : selectRadioButton
	 *
	 * void
	 */
	public static void selectRadioButton(WebDriver driver, By by){
		WebElement ele = driver.findElement(by);
		if(!ele.isSelected()){
			ele.click();
			log.info("Successfully selected the raido button '" + ele);
		}
		else{
			log.error("Exception element not present : " + ele);
		}
	}
	/**
	 * 
	 * Method Name : enterTextinToTextBox
	 *
	 * void
	 */
	public static void enterTextinToTextBox(WebDriver driver, By by, String str){
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(str);
		
		log.info("text is filled with the given value");		
	}
	/**
	 * 
	 * Method Name : selectDropdownValue
	 * 
	 * Variable 'i' is the case number
	 *
	 * Variable "str" is the Value/Visible Text to be selected from the drop down.
	 * 
	 * Variable "j" is the index value.
	 * 
	 * void
	 */
	public static void selectDropdownValue(WebDriver driver, By by, int i, String str, int j){
		WebElement ele = driver.findElement(by);
		Select s = new Select(ele);
		System.out.println(s.getFirstSelectedOption());
		switch(i){
			case 1:
				s.selectByValue(str);
				break;
			case 2:
				s.selectByIndex(j);
				break;
			case 3:
				s.selectByVisibleText(str);
				log.info("dropdown value is"+ str);
				break;
			 default:
				 log.error("Unable to select the value");
					Reporter.log("<p>Unable to select the value");			
		}	
	}
	/**
	 * 
	 * Method Name : compareStrings
	 *
	 * void
	 */
	public static void compareStrings(WebDriver driver, String str1, String str2){
	
		boolean bval = str1.equalsIgnoreCase(str2);
				if(bval==true){
			log.info("Strings are same");
				}
			else{
				log.error("Strings are not same");
				Reporter.log("<p>Strings are not same");
			}			
	}
	
	/**
	 * 
	 * Method Name :clearField
	 *
	 * void
	 */
	public static void clearField(WebDriver driver, By by){
		driver.findElement(by).clear();
		log.info("Currently field is empty");
	}
	/**
	 * 
	 * Method Name : deleteAllCookies
	 *
	 * void
	 */
	public static void deleteAllCookies(WebDriver driver){
		driver.manage().deleteAllCookies();
		log.info("cookies are deleted");
	}
	/**
	 * 
	 * Method Name : mouseHover
	 *
	 * void
	 */
	public static void mouseHoveronElement(WebDriver driver, WebElement ele){
		try{
			Actions act = new Actions(driver);
				act.moveToElement(ele).perform();
				log.info("Mouse hover on element");
		}catch(NoSuchElementException e){
			log.error("No such element found" + e);
			Reporter.log("<p>No such element found");
					}
	}
	/**
	 * 
	 * Method Name : mouseHoveronElementandClick
	 *
	 * void
	 */
	public static void mouseHoveronElementandClick(WebDriver driver, WebElement ele){
		try{
			Actions act = new Actions(driver);
			act.moveToElement(ele).click().build().perform();
			log.info("Mouse hovered and clicked on element");
		}catch(NoSuchElementException e){
			log.error("No such element found" + e);
			Reporter.log("<p>No such element found");
		}
	}
	/**
	 * 
	 * Method Name : contextClick
	 *
	 * void
	 */
	public static void contextClick(WebDriver driver, WebElement ele)
	{
		try{
			Actions act = new Actions(driver);
				act.contextClick(ele).build().perform();
				log.info("Right clicked on the element");
		}catch(NoSuchElementException e){
			log.error("No such element found" + e);
			Reporter.log("<p>No such element found");
		}
		
	}
	/**
	 * 
	 * Method name : doubleClick
	 *
	 * void
	 */
	public static void doubleClick(WebDriver driver, WebElement ele){
		try{
			Actions act = new Actions(driver);
				act.doubleClick(ele).build().perform();
			log.info("Double clicked on the element");
		}catch(NoSuchElementException e){
			log.error("No such element found" + e);
			Reporter.log("<p>No such element found");
		}
	}
	/**
	 * 
	 * Method Name : assertVerify
	 *
	 * void
	 */
	public static void assertVerify(WebDriver driver, By by, String expectedValue) throws Exception

	{
		try 
		{
			WebElement element = driver.findElement(by);
			String ActualValue = element.getText();

			log.info("actual = "+ ActualValue);
			log.info("expected = "+ expectedValue);
			Assert.assertEquals(ActualValue, expectedValue, "Text is not matching");
			log.info("Text is matching");
		}
		catch (NoSuchElementException e) 
		{
			log.error("Text is not matching");
			Reporter.log("<p>Text is not matching");
		}
}
}





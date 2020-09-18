package in.valtech.custom;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


import in.valtech.util.ExcelReader;
import in.valtech.util.Executable_DS;


public class CustomFunction 
{
	public static Logger log = Logger.getLogger(CustomFunction.class.getName());

	
	public static List<Executable_DS> executableDetails;
	

	/** 
	 * Method Name: isElementPresent Description: tHIS Method to used to verify whether the Element is present or not
	 * @param by :by is the Element locator
	 * @param driver:WebDriver           
	 * @return true: if element is present, return false: if element is not present
	 */

	public static boolean isElementPresent(By by, WebDriver driver) 
	{
		try 
		{
			System.out.println("element is present ");
			driver.findElement(by);
			return true;
		} 
		catch (NoSuchElementException e) 
		{			
			return false;
		}
	}


	/** 
	 * Method Name: getRootDir Description: Method to get Root directory
	 * @return :rootDir
	 */
	
	public static String getRootDir() 
	{
		File path = new File("");
		String absPath = path.getAbsolutePath();
		File dir = new File(absPath);
		String rootDir = dir.getParent();
		System.out.println("Project Location is(CustomFunction)-rootDir = "+rootDir);
		System.out.println("Project Location is(CustomFunction)-absPath = "+absPath);
		return rootDir;
	}

	
	/**
	 * Method Name: refreshPage Description: Method to used refresh a page after
	 * doing some action (if required page refresh) 
	 * @param driver :WebDriver        
	 * @return driver
	 */
	public static WebDriver refreshPage(WebDriver driver)
	{
		driver.navigate().refresh();
		return (driver);
	}
	
	

	/**
	 * Method Name: switchToNewWindow Description: This function switches the
	 * browser control to new window and verifies title
	 * @param driver  : WebDriver         
	 * @param pageTitle : title of the page         
	 * @return newWindow(driver)
	 */
	public static WebDriver switchToNewWindow(WebDriver driver, String pageTitle)
	{

		WebDriver newWindow = null;
		Set<String> windowIterator = driver.getWindowHandles();
		System.err.println("No of windows :  " + windowIterator.size());
		for (String s : windowIterator)
		{
			String windowHandle = s;
			newWindow = driver.switchTo().window(windowHandle);
			System.out.println("Window Title : " + newWindow.getTitle());
			System.out.println("Window Url : " + newWindow.getCurrentUrl());
			if (newWindow.getTitle().equals(pageTitle))
			{
				System.out.println("Selected Window Title : "+ newWindow.getTitle());
				return newWindow;
			}
		}
		System.out.println("Window Title :" + newWindow.getTitle());
		System.out.println();
		return newWindow;
	}

	
	/**
	 * MethodName:generateTimeStamp Description: This method generates timestamp
	 * @return newDate
	 */
	public static String generateTimeStamp()
	{

		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat stringDate = new SimpleDateFormat("ddMMyyhhmmss");
		String newDate = stringDate.format(date);
		return newDate;
	}
	

	/**
	 * MethodName:verifyDropdownValues 
	 * Description: This method verifies the dropdown options
	 * @param Webelement ele         
	 * @param dropDownValuesArray
	 * @throws Exception
	 */
	public static boolean verifyDropdownValues(WebElement ele,String[] dropDownValuesArray) throws Exception 
	{
		boolean match = false;
		try
		{
			Select dropDown = new Select(ele);
			List<WebElement> options = dropDown.getOptions();
			if (options.size() == dropDownValuesArray.length)
			{
				for (WebElement we : options) 
				{
					for (int i = 0; i < dropDownValuesArray.length; i++)
					{
						String act = we.getText().replace("\n", "").trim();
						if (act.equalsIgnoreCase(dropDownValuesArray[i])) 
						{

							match = true;
							Reporter.log("Dropdown options: " + we.getText());
							log.info("Dropdown options: " + we.getText());
							System.out.println("Dropdown options: "
									+ we.getText());
						}
					}
				}
			} 
			else 
			{
				log.error("Dropdown values size is not matching");
				Reporter.log("<p>Dropdown values size is not matching");
			}
		} 
		catch (NoSuchElementException e)
		{
			log.error("Element to type data is not present " + e);
			Reporter.log("<p>Element to type data is not present");
		}
		return match;
	}

	
	/**
	 * MethodName:verifySelectedDropdownValue
	 * 
	 * Description: This method verifies the Selected dropdown value
	 * 
	 * @param Webelement
	 *            ele
	 * @param shippingmethodarray
	 * @throws Exception
	 */
	public static boolean verifySelectedDropdownValue(WebElement dropDown,String dropDownValue) throws Exception
	{

		boolean match = false;

		// Dropdown element initialization
		Select dropDownEle = new Select(dropDown);
		// Get Selected dropdown value
		String defaultoption = dropDownEle.getFirstSelectedOption().getText();
		defaultoption = defaultoption.replace("\n", "").trim();
		log.info("Default DropDown option: " + defaultoption);
		log.info("DropDown Value: " + dropDownValue);
        // Verify selected value
		if (defaultoption.equalsIgnoreCase(dropDownValue)) {
			match = true;
		}

		return match;
	}
	
	
	/**
	 * MethodName:getTestDataSetDetails
	 * Description: This method retrieves Data from executable file
	 * @throws Exception
	 */
	

	/**
	 * This element verifies element is displayed or not
	 * @param ele
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public static boolean isElementVisible(By ele, WebDriver driver)
			throws Exception 

			{
		try 
		{
			driver.findElement(ele).isDisplayed();
			System.out.println("Element is Displayed");
			return true;
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Element is not Displayed");
			return false;

		}
			}

	
	/**
	 * This element verifies element is selected or not
	 * @param ele
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public static boolean isElementSelected(By ele, WebDriver driver)
			throws Exception 

			{
		try 
		{
			driver.findElement(ele).isSelected();
			System.out.println("Element is Selected");
			return true;
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Element is not Selected");
			return false;

		}
			}





	/**
	 * This element Gets  the text
	 * @param ele
	 * @param driver
	 * @throws Exception
	 * 
	 */	
	public void getElementText(WebDriver driver,By ele) throws Exception
	{ 
		try 
		{
			WebElement element = driver.findElement(ele);
			String textValue = element.getText();
			System.out.println("textValue="+textValue);
			log.info("got text value ");
			Reporter.log("<p>got text value");
		} catch (NoSuchElementException e) {
			log.error("text value is missing");
			Reporter.log("<p>text value is missing"); 
		}
	}

	
	/**Method Name: initializeDriver
	 * Descriptions: to launch a browser
	 * @param driverName
	 * @param driver
	 * @param rootDir
	 * @throws Exception
	 * @throws IOException
	 */
	public static WebDriver initializeDriver(String driverName,WebDriver driver, String rootDir)
			throws Exception, IOException, InterruptedException,MalformedURLException 
			{
		System.out.println("**************"+driverName);
	
		System.out.println(driver);
		System.out.println(rootDir);
		switch (driverName) 
		{
		case "FF":
			
			System.setProperty("webdriver.Firefox.driver", rootDir+ "C:\\D2C\\Dolby\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			break;

		case "CHROME":
			//E:\Lee Valley_Workspace\Lee Valley\LeeValley\resources\drivers
			//rootDir+ "/resources/drivers
			System.setProperty("webdriver.chrome.driver", "D:\\Dolby_helix\\Dolby\\drivers\\chromedriver.exe");
			System.out.println("Entered in to chrome1");
			 driver = new ChromeDriver();
			System.out.println("Entered in to chrome2");
			 driver.manage().window().maximize();
				System.out.println("Entered in to chrome3");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			break;

		case "IE":
			
			System.setProperty("webdriver.ie.driver", rootDir+ "C:\\D2C\\Dolby\\resources\\drivers\\geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().deleteAllCookies();
			break;

		case "SAFARI":

			// Delete browser cookies and cache

			String[] command = new String[] 
					{
					rootDir+ "/daddario/resources/front/SafariClearCookies.sh" 
					};
			
			Runtime.getRuntime().exec(command);
			driver = new SafariDriver();
			driver.manage().deleteAllCookies();
			log.info("SafariDriver Started");
			break;

		case "ANDROID":
			DesiredCapabilities capabilitiesAndroid = new DesiredCapabilities();
			
			capabilitiesAndroid.setCapability("browserName", "Chrome");
            capabilitiesAndroid.setCapability("deviceName", "Android");
            capabilitiesAndroid.setCapability("platformName", "Android");

			driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"),	capabilitiesAndroid);
			driver.manage().deleteAllCookies();
			log.info("AndroidDriver Started");
			break;

		default:
			
			// By default initiating the Firefox driver.
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
		System.out.println("return statement");
		return driver;
			}

	
	
	/**Method Name: waitForElement
	 * Descriptions:this will wait for the certain conditions before it throws element not visible
	 * 
	 */
	public  void waitForElement(WebDriver driver,By by)
	{
		WebDriverWait wait = new WebDriverWait(driver,50);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		System.out.println("waited for the element before throwing an error");
	}



}



package in.valtech.config;

import in.valtech.custom.CustomFunction;
import in.valtech.util.PropertyFileReader;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class BaseTest {
	
	public static WebDriver driver;
	public static Logger log;
	public static ITestResult result;
	public String rootDir=CustomFunction.getRootDir();
	public static String url;
	public static String BrowserName;
	public static String UserName;
	public static String Password;
	public static String osVersionConfig;
	public static String testCaseName;
	
	
	@BeforeSuite
	public void suiteSetUp() throws Exception 
	{
		// For logging
		log = Logger.getLogger(this.getClass().getName());
	}

	@Parameters({ "TC_NAME","OS_VERSION", "URL",
			 "EMAIL", "PASSWORD", "DRIVER"})
	


	
@BeforeTest
	public void setUp(String tcName, 
			String osVersion, String applicationURL, 
			String userName, String password, String browser)
			throws Exception
	{
		
		result = null;
		
		testCaseName = tcName;
		log.info("Base test started");
		log.info("Test case Name= "+ tcName);
		
		url = applicationURL;
		log.info("Application URL = "+ applicationURL);
		
		BrowserName = browser;
		log.info("Driver Name = "+ browser);
		
		UserName = userName;
		log.info("UserName = "+ userName);
		
		Password = password;
		log.info("Password = "+ password);
		
		osVersionConfig = osVersion;
		log.info("osVersion_Config = "+ osVersion);
		
		
		//Get Test Data set Details
		//CustomFunction.getTestDataSetDetailsFromExecutableSheet(osVersion);
		
		//***********************Load Property File**************//
		

		PropertyFileReader.getValue(rootDir);
		System.out.println("BaseTest initializeDriver");
		driver = CustomFunction.initializeDriver(browser,driver,rootDir);
		if (BrowserName.equalsIgnoreCase("FF")||BrowserName.equalsIgnoreCase("CHROME")||BrowserName.equalsIgnoreCase("IE"))
		{

			System.out.println("Browser Name===="+BrowserName);
			driver.manage().window().setSize(new Dimension(1259, 906));
			driver.manage().window().maximize();
			
			log.info("Window Size is: " +driver.manage().window().getSize());
		}
			
	}

	
	@BeforeMethod
	public void methodLevelSetup() throws Exception {

		/*
		 * Checks for exceptions like IllegalStateException or SkipException or
		 * SessionNotFoundException or UnreachableBrowserException, If found
		 * skips the test method
		 */
		if (result != null) 
		{
			if ((result.getThrowable().toString()
					.contains("IllegalStateException")
					|| result.getThrowable().toString()
					.contains("SkipException")
					|| result.getThrowable().toString()
					.contains("SessionNotFoundException") || result
					.getThrowable().toString()
					.contains("UnreachableBrowserException"))) {

				throw new SkipException("Skip Methods");
			}
		}

	}
	
	@AfterMethod
	public void methodLevelTearDown() throws Exception {

		/*
		 * Checks for exceptions like IllegalStateException or SkipException or
		 * SessionNotFoundException or UnreachableBrowserException, If found
		 * skips the test method
		 */
		if (result != null) {
			if ((result.getThrowable().toString()
					.contains("IllegalStateException")
					|| result.getThrowable().toString()
					.contains("SkipException")
					|| result.getThrowable().toString()
					.contains("SessionNotFoundException") || result
					.getThrowable().toString()
					.contains("UnreachableBrowserException"))) {

				throw new SkipException("Skip Methods");
			}
		}

	}
	
	@AfterClass
			public void classLevelTearDown() throws Exception {
				/*
				 * Checks for exceptions like IllegalStateException or SkipException or
				 * UnreachableBrowserException, If found skips the test cases
				 */
				if (result != null) 
				{
					if ((result.getThrowable().toString()
							.contains("IllegalStateException")
							|| result.getThrowable().toString()
							.contains("SkipException") || result.getThrowable()
							.toString().contains("UnreachableBrowserException")))
					{

						throw new SkipException("Skip Testcases");
					}
				}
			}	
	@AfterSuite
		public void tearDown() throws Exception
	{
		// Closing the driver once the suite execution is finished.
		//driver.close();
		// Quitting the driver once the suite execution is finished.
		driver.quit();
	}

}

package in.valtech.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import in.valtech.custom.CustomFunction;
import in.valtech.uiFunctions.GUIFunctions;
import in.valtech.util.PropertyFileReader;

public class SearchPage {

	// For logging
	public Logger log = Logger.getLogger(this.getClass().getName());

	private final WebDriver driver;


	public SearchPage(WebDriver driver) throws InterruptedException

	{
		this.driver= driver;
	}

	
	
	//By loginBtn = By.name(PropertyFileReader.getValue("LP_lbutton"));
			

	




	/**
	 * verifyLoginPage:
	 * @return
	 * @throws InterruptedException
	 */


	public SearchPage verifyLoginPage() throws InterruptedException
	{

		log.info("Dolby Page title" + driver.getTitle());
		if ((CustomFunction
				.isElementPresent(
						By.xpath(PropertyFileReader.getValue("Hey")),
						driver))) 

		{

			System.out.println("You are in login page");
			log.info("You are in login page");
		}
		else {
			try
			{

				throw new InterruptedException("This is not login page");
			}catch (InterruptedException e)
			{
			}
		}
		return new SearchPage(driver);
	}

	
	public static void  navigateToApplication_URL(WebDriver driver, String url, String driverName) throws InterruptedException
	{
		System.out.println("before loading url");
		GUIFunctions.deleteAllCookies(driver);
		driver.get(url);
		Reporter.log("Page Title:" + driver.getTitle());
		System.out.println("Page Title:" + driver.getTitle());
		Thread.sleep(2000);
		System.out.println("URL got Loaded successfully");

	}

	


	By LP_Username_Id = By.xpath(PropertyFileReader.getValue("UserName"));
			


	/**
	 * 
	 * enterUserName
	 * @param userName
	 * @return :LoginPage
	 * @throws InterruptedException 
	 *
	 */
	public SearchPage EnterUserName(String UserName) throws InterruptedException {
		GUIFunctions.enterTextinToTextBox(driver, LP_Username_Id ,UserName);
		log.info("User Entered user name in the textbox");
		return new SearchPage(driver);
	}

	By pwd_login = By.xpath(PropertyFileReader.getValue("Password"));
			
	/**
	 * 
	 * enterPassword
	 * @param password
	 * @return :LoginPage
	 * @throws InterruptedException 
	 *
	 */
	public SearchPage EnterPassword(String Password) throws InterruptedException{
		GUIFunctions.enterTextinToTextBox(driver, pwd_login , Password);
		log.info("User Entered password in the password field");
		//Thread.sleep(2000);
		return new SearchPage(driver);
		
	}
	
	
	By SearchPage = By.xpath(PropertyFileReader.getValue("LoginCTA"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public SearchPage clickHomepage() throws InterruptedException{
		GUIFunctions.clickButton(driver, SearchPage);
		//Thread.sleep(2000);
		log.info("User logged in to the application & lands on the Experience finder landing page");
		return new SearchPage(driver);


	}	
	
	By Cookiesnotfi = By.xpath(PropertyFileReader.getValue("AcceptCookies"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public SearchPage clickCookiesnotfi() throws InterruptedException{
		GUIFunctions.clickButton(driver, Cookiesnotfi);
		//Thread.sleep(2000);
		log.info("User logged in to the application & click on cookies accepting button");
		return new SearchPage(driver);

	
	}
	By Humburger = By.xpath(PropertyFileReader.getValue("HumburgerMenu"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public SearchPage clickBreadcrumbtn() throws InterruptedException{
		GUIFunctions.clickButton(driver, Humburger);
		//Thread.sleep(2000);
		log.info("User logged in to the application & click On Humburgermenu");
		return new SearchPage(driver);

	
}
	By Searchbtn = By.xpath(PropertyFileReader.getValue("SerachIcon"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public SearchPage clickSearchbtn() throws InterruptedException{
		GUIFunctions.clickButton(driver, Searchbtn);
		//Thread.sleep(2000);
		log.info("User logged in to the application & click On breadcrumbutton");
		return new SearchPage(driver);

}}

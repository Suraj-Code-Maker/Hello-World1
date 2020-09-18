package in.valtech.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import in.valtech.custom.CustomFunction;
import in.valtech.uiFunctions.GUIFunctions;
import in.valtech.util.PropertyFileReader;

public class TabbedPage {

	// For logging
	public Logger log = Logger.getLogger(this.getClass().getName());

	private final WebDriver driver;


	public TabbedPage(WebDriver driver) throws InterruptedException

	{
		this.driver= driver;
	}

	
	
	//By loginBtn = By.name(PropertyFileReader.getValue("LP_lbutton"));
			

	




	/**
	 * verifyLoginPage:
	 * @return
	 * @throws InterruptedException
	 */


	public TabbedPage verifyLoginPage() throws InterruptedException
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
		return new TabbedPage(driver);
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
	public TabbedPage EnterUserName(String UserName) throws InterruptedException {
		GUIFunctions.enterTextinToTextBox(driver, LP_Username_Id ,UserName);
		log.info("User Entered user name in the textbox");
		return new TabbedPage(driver);
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
	public TabbedPage EnterPassword(String Password) throws InterruptedException{
		GUIFunctions.enterTextinToTextBox(driver, pwd_login , Password);
		log.info("User Entered password in the password field");
		//Thread.sleep(2000);
		return new TabbedPage(driver);
		
	}
	
	
	By TabbedPage = By.xpath(PropertyFileReader.getValue("LoginCTA"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public TabbedPage clickHomepage() throws InterruptedException{
		GUIFunctions.clickButton(driver, TabbedPage);
		//Thread.sleep(2000);
		log.info("User logged in to the application & lands on the Experience finder landing page");
		return new TabbedPage(driver);


	}	
	
	By Cookiesnotfi = By.xpath(PropertyFileReader.getValue("AcceptCookies"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public TabbedPage clickCookiesnotfi() throws InterruptedException{
		GUIFunctions.clickButton(driver, Cookiesnotfi);
		//Thread.sleep(2000);
		log.info("User logged in to the application & click on cookies accepting button");
		return new TabbedPage(driver);

	
	}
	By ChangeTab = By.xpath(PropertyFileReader.getValue("clickOnTab"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public TabbedPage clickChangeTab() throws InterruptedException{
		GUIFunctions.clickButton(driver, ChangeTab);
		//Thread.sleep(2000);
		log.info("User logged in to the application & click On ChangeTabhey");
		return new TabbedPage(driver);

}
	By ClickonLcta = By.xpath(PropertyFileReader.getValue("ClickhereLinkCTA"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public TabbedPage clickCLickonLcta() throws InterruptedException{
		GUIFunctions.clickButton(driver, ClickonLcta);
		//Thread.sleep(2000);
		log.info("User logged in to the application & click On CTA link");
		return new TabbedPage(driver);

}}



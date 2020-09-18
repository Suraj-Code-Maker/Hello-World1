package in.valtech.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import in.valtech.custom.CustomFunction;
import in.valtech.uiFunctions.GUIFunctions;
import in.valtech.util.PropertyFileReader;

public class XFLPage {

	// For logging
	public Logger log = Logger.getLogger(this.getClass().getName());

	private final WebDriver driver;


	public XFLPage(WebDriver driver) throws InterruptedException

	{
		this.driver= driver;
	}

	
	
	//By loginBtn = By.name(PropertyFileReader.getValue("LP_lbutton"));
			

	




	/**
	 * verifyLoginPage:
	 * @return
	 * @throws InterruptedException
	 */


	public XFLPage verifyLoginPage() throws InterruptedException
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
		return new XFLPage(driver);
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
	public XFLPage EnterUserName(String UserName) throws InterruptedException {
		GUIFunctions.enterTextinToTextBox(driver, LP_Username_Id ,UserName);
		log.info("User Entered user name in the textbox");
		return new XFLPage(driver);
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
	public XFLPage EnterPassword(String Password) throws InterruptedException{
		GUIFunctions.enterTextinToTextBox(driver, pwd_login , Password);
		log.info("User Entered password in the password field");
		//Thread.sleep(2000);
		return new XFLPage(driver);
		
	}
	
	
	By TabbedPage = By.xpath(PropertyFileReader.getValue("LoginCTA"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public XFLPage clickHomepage() throws InterruptedException{
		GUIFunctions.clickButton(driver, TabbedPage);
		//Thread.sleep(2000);
		log.info("User logged in to the application & lands on the Experience finder landing page");
		return new XFLPage(driver);


	}	
	
	By Cookiesnotfi = By.xpath(PropertyFileReader.getValue("AcceptCookies"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public XFLPage clickCookiesnotfi() throws InterruptedException{
		GUIFunctions.clickButton(driver, Cookiesnotfi);
		//Thread.sleep(2000);
		log.info("User logged in to the application & click on cookies accepting button");
		return new XFLPage(driver);

	
	}
	By ClickOnRINGBtn = By.xpath(PropertyFileReader.getValue("RingCircleButton"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public XFLPage ClickOnRINGBtn() throws InterruptedException{
		GUIFunctions.clickButton(driver, ClickOnRINGBtn);
		//Thread.sleep(2000);
		log.info("User logged in to the application & click On ChangeTabhey");
		return new XFLPage(driver);

}
	By ClickonXbtn = By.xpath(PropertyFileReader.getValue("CloseXButton"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public XFLPage ClickonXbtn() throws InterruptedException{
		GUIFunctions.clickButton(driver, ClickonXbtn);
		//Thread.sleep(2000);
		log.info("User logged in to the application & click On CTA link");
		return new XFLPage(driver);

}}






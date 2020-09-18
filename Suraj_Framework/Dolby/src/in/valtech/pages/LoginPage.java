package in.valtech.pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import in.valtech.util.PropertyFileReader;

import in.valtech.custom.CustomFunction;
import in.valtech.uiFunctions.GUIFunctions;

public class LoginPage 
{




	// For logging
	public Logger log = Logger.getLogger(this.getClass().getName());

	private final WebDriver driver;


	public LoginPage(WebDriver driver) throws InterruptedException

	{
		this.driver= driver;
	}

	
	
	//By loginBtn = By.name(PropertyFileReader.getValue("LP_lbutton"));
			

	




	/**
	 * verifyLoginPage:
	 * @return
	 * @throws InterruptedException
	 */


	public LoginPage verifyLoginPage() throws InterruptedException
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
		return new LoginPage(driver);
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
	public LoginPage EnterUserName(String UserName) throws InterruptedException {
		GUIFunctions.enterTextinToTextBox(driver, LP_Username_Id ,UserName);
		log.info("User Entered user name in the textbox");
		return new LoginPage(driver);
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
	public LoginPage EnterPassword(String Password) throws InterruptedException{
		GUIFunctions.enterTextinToTextBox(driver, pwd_login , Password);
		log.info("User Entered password in the password field");
		//Thread.sleep(2000);
		return new LoginPage(driver);
		
	}
	
	
	By loginBtn = By.xpath(PropertyFileReader.getValue("LoginCTA"));
	/**
	 * 
	 * clickLoginBtn
	 * @return :ExperienceFinderLandingPage
	 * @throws InterruptedException 
	 *
	 */
	
	public LoginPage clickLoginBtn() throws InterruptedException{
		GUIFunctions.clickButton(driver, loginBtn);
		//Thread.sleep(2000);
		log.info("User logged in to the application & lands on the Experience finder landing page");
		return new LoginPage(driver);


	}	
	
	}



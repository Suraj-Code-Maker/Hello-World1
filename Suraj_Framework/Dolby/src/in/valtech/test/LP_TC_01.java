package in.valtech.test;


import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import in.valtech.config.BaseTest;

import in.valtech.pages.LoginPage;

public class LP_TC_01 extends BaseTest
{


	static  LoginPage Loginpage ;


	@SuppressWarnings("static-access")
	
	@Test(priority =1, description = "Step 1:  Open browser,Navigate to the Dolby URL")
	public void Step01_navigateToApplication_URL() throws Exception 
	{
		System.out.println("step 1 begin");
		LoginPage.navigateToApplication_URL(driver, url, BrowserName);   
		Thread.sleep(2000);
//		log.info("Successfully navigated to application URL \n");
//		Reporter.log("<p>Successfully navigated to application URL");
//		System.out.println("step 1 end");
	}


	@Test(priority =2, description = "Step 2:  Enter userName")

	public void Step02_enterUserName() throws Exception 
	{
		System.out.println("step 2 begin");	
		LoginPage login1 = new LoginPage (driver);
		//Thread.sleep(2000);
		login1.EnterUserName(UserName);
		System.out.println(UserName + "=username");
		System.out.println("step 2 End");       
	}

	@Test(priority =3, description = "Step 3:  Enter password")

	public void Step03_enterPassword() throws Exception 
	{
		System.out.println("step 3 begin");
		LoginPage login = new LoginPage (driver);
		//Thread.sleep(3000);
		login.EnterPassword(Password);
		System.out.println(Password + "//=password");
		//Thread.sleep(2000);
		System.out.println("step 3 End");
	}
	@Test(priority =4, description = "Step 4: Click on Login Button")

	public void Step04_ClickOnLoginInBtn() throws Exception 
	{
		System.out.println("step 4 begin");
		LoginPage login = new LoginPage (driver);
		//Thread.sleep(2000);
		login.clickLoginBtn();
		System.out.println("step 4 End");
	}

	
/*
	@Test(priority =5, description = "Step 4: wait for the page to load")

	public void Step00_waitForPagetoLoad() throws InterruptedException 
	{
		System.out.println("step 4 begin");
		LoginPage login = new LoginPage (driver);
		//Thread.sleep(2000);
		login.exWait();
		System.out.println("step 4 End");
	}

	
	*/
	
}


package in.valtech.test;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import in.valtech.config.BaseTest;
import in.valtech.pages.Homepage;
import in.valtech.pages.LoginPage;
import in.valtech.pages.TabbedPage;


public class TB_TC_04 extends BaseTest
{


	static  TabbedPage SearchPage ;


	@SuppressWarnings("static-access")
	
	@Test(priority =1, description = "Step 1:  Open browser,Navigate to the Dolby URL")
	public void Step01_navigateToApplication_URL() throws Exception 
	{
		System.out.println("step 1 begin");
		TabbedPage.navigateToApplication_URL(driver, url, BrowserName);   
		Thread.sleep(2000);
//		log.info("Successfully navigated to application URL \n");
//		Reporter.log("<p>Successfully navigated to application URL");
//		System.out.println("step 1 end");
	}


	@Test(priority =2, description = "Step 2:  Enter userName")

	public void Step02_enterUserName() throws Exception 
	{
		System.out.println("step 2 begin");	
		TabbedPage login1 = new TabbedPage (driver);
		//Thread.sleep(2000);
		login1.EnterUserName(UserName);
		System.out.println(UserName + "=username");
		System.out.println("step 2 End");       
	}

	@Test(priority =3, description = "Step 3:  Enter password")

	public void Step03_enterPassword() throws Exception 
	{
		System.out.println("step 3 begin");
		TabbedPage login = new TabbedPage (driver);
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
		TabbedPage login = new TabbedPage (driver);
		//Thread.sleep(2000);
		login.clickHomepage();
		System.out.println("step 4 End");
		Thread.sleep(4000);
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
	



@Test(priority =5, description = "Step 5: Accept Cookies")

public void Step05_ClickAcceptCOokie() throws Exception 
{
	System.out.println("step 5 begin");
	TabbedPage login = new TabbedPage (driver);
	//Thread.sleep(2000);
	Thread.sleep(4000);
	login.clickCookiesnotfi();
	Thread.sleep(4000);
	System.out.println("step 5 End");
}

@Test(priority =6, description = "Step 6: Click On on tab")

public void Step06_clickChangeTab() throws Exception 
{
	System.out.println("step 6 begin");
	TabbedPage login = new TabbedPage (driver);
	//Thread.sleep(2000);
	Thread.sleep(4000);
	login.clickChangeTab();
	Thread.sleep(4000);
	System.out.println("step 6 End");
}
@Test(priority =7, description = "Step 7: Click On searchbutton")

public void Step07_ClickonLcta() throws Exception 
{
	System.out.println("step 7 begin");
	TabbedPage login = new TabbedPage (driver);
	//Thread.sleep(2000);
	Thread.sleep(4000);
	login.clickCLickonLcta();
	Thread.sleep(4000);
	System.out.println("step 7 End");
	Thread.sleep(4000);
}
}




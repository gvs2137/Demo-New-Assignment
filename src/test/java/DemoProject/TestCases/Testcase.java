package DemoProject.TestCases;


import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import DemoProject.DemoProject.GenericFunctions;

public class Testcase extends GenericFunctions

{
	SoftAssert sassert=new SoftAssert();
	public static Logger logger=LogManager.getLogger(Testcase.class.getSimpleName());
	//
	@Test(priority=1)
	public void Siteaccess()
	{
		navigate("urlKey");
		String actualsite=driver.getTitle();
		sassert.assertEquals(actualsite, envprop.getProperty("ExpectedTitles"));
	}
	//Step 1--Login to the Portal (https://customer.servetel.in)
   @Test(priority=2)
	public void Login() throws InterruptedException
    {
	   	
    	getElement("username_xpath").sendKeys(envprop.getProperty("username"));
    	getElement("password_xpath").sendKeys(envprop.getProperty("password"));
    	getElement("Login_xpath").click();
    	Thread.sleep(2000);	
    	String ActualTitleSA2=driver.getTitle();
    	sassert.assertEquals(ActualTitleSA2, envprop.getProperty("ExpectedTitlesl"));
    	logger.info("---------------------Login succesfully pass----------------------------");	
    	}
    
   //Step 2-Upload Recording (Service --->System Recordings--->Create Recordings)
   @Test(priority=3)
   public void uploadrecording() throws InterruptedException
	{
		getElement("Services_xpath").click();
		logger.info("Cliked on Services");
		implicitwait(5);
		getElement("Systemrecording_xpath").click();
		logger.info("System Recordings");
		Thread.sleep(3);
		String ActualTitleSR=driver.getTitle();
		sassert.assertEquals(ActualTitleSR, envprop.getProperty("ExpectedTitlesSR"),"Assertion for System Recording page failed");
		logger.info("Assertion pass");
		getElement("UploadRecording_xpath").click();
		logger.info("Clicked on Upload Recording");
		driver.switchTo().activeElement();
		logger.info("Switch to modal");
		getElement("Filename_xpath").sendKeys(envprop.getProperty("filename"));
		logger.info("Entering file name");
		
		try{
			WebElement choose=getElement("Choosefile_xpath");
			choose.sendKeys(System.getProperty("user.dir")+"\\BrowserDrivers\\file_example_MP3_700KB.mp3");
			logger.info("****Uploaded document Selected*********");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.info("File not found", e);
				logger.info("****Uploaded document  not found*********");
			}
		
		getElement("Saverecording_xpath").click();
		/*String ActualTitleSR1=driver.getTitle();
		sassert.assertEquals(ActualTitleSR1, envprop.getProperty("ExpectedTitlesSR"));*/
	}
   //Step3 -Create/Add Agent using the API POST Method, API Ref Link - https://servetel.readme.io/reference#agent generate Auth Token from the customer Portal (Services ---> Account API ---> Generate Token) and the xpath to the view button should be Dynamic.
@Test(priority=4)
public void GenerateToken() throws InterruptedException
{
	Thread.sleep(5000);
	scroller();
	getElement("AccounAPI_xpath").click();
	String ActualTitleCA=driver.getTitle();
	sassert.assertEquals(ActualTitleCA, envprop.getProperty("ExpectedTitleGT"));
	getElement("GenerateToken_xpath").click();
	getElement("TokenName_xpath").sendKeys(envprop.getProperty("Tokenname"));
	getElement("Savetoken_xpath").click();
	Thread.sleep(5000);
	Webtable(envprop.getProperty("Tokenname"));
	Thread.sleep(2000);
	getElement("Copytoken_xpath").click();
	logger.info("Token copied");
	getElement("CloseToken_xpath").click();
	logger.info("Token close");
	
}
//Adding agent using API (Post)
@Test(priority=5)
public void addagent() throws InterruptedException
{
	((JavascriptExecutor) driver).executeScript("window.open()");
	ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	driver.switchTo().window(tabs.get(1));
	navigate("urlKey1");
	String ActualTitleaa=driver.getTitle();
	sassert.assertEquals(ActualTitleaa, envprop.getProperty("ExpectedTitleAPI"));
	getElement("apiname_xpath").sendKeys(envprop.getProperty("apiname"));
	getElement("followmeno_xpath").sendKeys(envprop.getProperty("followno"));
	logger.info("data entered");
	Actions builder = new Actions(driver);
	getElement("auth_xpath").click();
	builder.keyDown( Keys.CONTROL ).sendKeys( "v" ).keyUp( Keys.CONTROL ).build().perform();
	logger.info("Data pasted");
	
	getElement("tryitout_xpath").click();
	logger.info("Clicked on try it out");
	
	driver.switchTo().window(tabs.get(0));
}

//Adding autoattendant
@Test(priority=6)
public void addautoattedant() throws InterruptedException 
{
	getElement("Autoattendant_xpath").click();
	getElement("Addautoattendamt_xpath").click();
	String Actualtitleaate=driver.getTitle();
	sassert.assertEquals(Actualtitleaate,envprop.getProperty("ExpectedTitleAA") );
	getElement("AutoAttendantname_xpath").sendKeys(envprop.getProperty("name"));
	getElement("AutoAttendantdescription_xpath").sendKeys(envprop.getProperty("description"));
	getElement("Recording_xpath").click();
	getElement("Recordingautosug_xpath").sendKeys(envprop.getProperty("filename"));
	Thread.sleep(3000);
	 Actions b = new Actions(driver);
	 b.sendKeys(Keys.ENTER).build().perform();
		getElement("Destination_xpath").click();
		getElement("Destinationautosug_xpath").sendKeys(envprop.getProperty("apiname"));
		Thread.sleep(3000);
		 b.sendKeys(Keys.ENTER).build().perform();
		    getElement("saveattendant_xpath").click();
		
}
	
}




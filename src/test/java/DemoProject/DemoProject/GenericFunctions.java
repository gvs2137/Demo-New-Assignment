package DemoProject.DemoProject;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import DemoProject.Basepackage.Baseclass;

public class GenericFunctions extends Baseclass

{
	public static Logger logger=LogManager.getLogger(GenericFunctions.class.getSimpleName());
	SoftAssert sassert=new SoftAssert();
	public String Authtoken;
	
	//Generic method To locate Elements
			public WebElement getElement(String locatorKey){
				WebElement e=null;
				try{
					
				if(locatorKey.endsWith("_id"))
					e=driver.findElement(By.id(prop.getProperty(locatorKey)));
				else if(locatorKey.endsWith("_name"))
					e=driver.findElement(By.name(prop.getProperty(locatorKey)));
				else if(locatorKey.endsWith("_xpath"))
					e=driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				else if(locatorKey.endsWith("_css"))
					e=driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
				else{
					reportFailure("Locator not correct - " + locatorKey);
					Assert.fail("Locator not correct - " + locatorKey);
				}
				
				}catch(Exception ex){
					// fail the test and report the error
					reportFailure(ex.getMessage());
					ex.printStackTrace();
					Assert.fail("Failed the test - "+ex.getMessage());
				}
				return e;
			}	
//Method for logging pass and Failure			
			public void reportPass(String msg)
			{
				logger.info(msg);
			}
			
			public void reportFailure(String msg)
			{
				logger.info(msg);
			}
			
//Method to navigate URL
		
			public void navigate(String urlKey)
			{
				logger.info("Navigating the Application URL");
				driver.get(envprop.getProperty(urlKey));
				String ActualURLsa=driver.getTitle();
				sassert.assertEquals(ActualURLsa, envprop.getProperty("ExpectedTitlesa"),"assertion for site access gets fail");
			logger.info("Navigated to Application URL");	
				
			}
			
			//Implicit wait
			public void implicitwait(int time)
			{
				driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
			}
			
			public  void Webtable(String Tokenname) throws InterruptedException
			{
				
						
						String Beforexpath="//table[@id='exttable']/tbody/tr[";
						String Afterxpath="]/td[2]";
						String Afterxpath1="]/td[3]/span/a";
						WebElement table = driver.findElement(By.xpath("//table[@id='exttable']"));
						List<WebElement> rows = table.findElements(By.tagName("tr"));
						
						for(int i=1;i<=rows.size();i++)
						{
							String name=driver.findElement(By.xpath(Beforexpath+i+Afterxpath)).getText();
							//System.out.println(name);
							if (name.equalsIgnoreCase(Tokenname))
							{
								System.out.println(Tokenname +"Found----------");
								driver.findElement(By.xpath(Beforexpath+i+Afterxpath1)).getText();
								Authtoken=driver.findElement(By.xpath(Beforexpath+i+Afterxpath1)).getAttribute("data-token");
								driver.findElement(By.xpath(Beforexpath+i+Afterxpath1)).click();
								break;				
							}
							
						}
						
					}
			//For logout operation
			@AfterTest
			public void logout()
			{
				getElement("logout_xpath").click();
				getElement("logout1_xpath").click();
				getElement("logout2_xpath").click();
			}
	            
			//For Taking screenshot for failed Testcases
			@AfterMethod
			@Parameters("browser")
			public void teardown(ITestResult result, String browser)
			{
			logger.info("Taking screenshots for fail Test case");
			// Here will compare if test is failing then only it will enter into if condition
			if(ITestResult.FAILURE==result.getStatus())
			{
			try 
			{
				// fileName of the screenshot
					Date d=new Date();
					String screenshotFile1=d.toString().replace(":", "_").replace(" ", "_")+"Failed for "+browser+" Browser"+".png";
			// Create reference of TakesScreenshot
			TakesScreenshot ts=(TakesScreenshot)driver;

			// Call method to capture screenshot
			File source=ts.getScreenshotAs(OutputType.FILE);

			// Copy method  specific location here it will save all screenshot in our project home directory and
			// result.getName() will return name of test case so that screenshot name will be same
			try
			{
			FileHandler.copy(source, new File(System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+ screenshotFile1));
			System.out.println("Screenshot taken");
			//capturess= System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+ screenshotFile1;
			//System.out.println("Path for screenshot---------------"+capturess);
			}
			catch (Exception e)
			{
			System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
			} 
			catch (Exception e)
			{
			System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
			}
			logger.info("Screenshot captured for fail Test case");
			}
			
			//Javascript exceutor for scrolling element
			public void scroller()
			{
				JavascriptExecutor js =(JavascriptExecutor)driver;
				js.executeScript(prop.getProperty("scroll_xpath"));
			}
			
	          }
	          
			
						


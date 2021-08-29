package DemoProject.Basepackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Baseclass 
{

	public static Properties prop;	
	public Properties envprop;
	public WebDriver driver;
	public static Logger logger=LogManager.getLogger(Baseclass.class.getSimpleName());
	@BeforeTest
	public void initpropfile()
	//to Initiate property file
	{
		
		logger.info("Initiating the properties file");
		prop=new Properties();
		envprop=new Properties();
		try {
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//projectconfig.properties");
			try {
				prop.load(file);
				String env=prop.getProperty("env");
				file = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources///"+env+".properties");
				envprop.load(file);		
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}		
		}
		catch(FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@BeforeTest
	@Parameters("browser")
	public void openBrowser(String browser) throws Exception{
		try{
		
	logger.info("Starting the Browser");
			
			if(browser.equals("Mozilla")) {
				System.setProperty("webdriver.gecko.driver", prop.getProperty("geckodriver_exe"));
				driver = new FirefoxDriver();
				
			}else if(browser.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver_exe"));
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("incognito");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				
				driver=new ChromeDriver(capabilities);
				
			//	   WebDriverManager.chromedriver().setup();
				
			//	driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			}
			else if (browser.equals("IE")){
				System.setProperty("webdriver.ie.driver", prop.getProperty("iedriver_exe"));
				 // Create the DesiredCapability object of InternetExplorer
				 DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				 
				 // Settings to Accept the SSL Certificate in the Capability object
				 capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				
				driver= new InternetExplorerDriver();
			}	
			
			else if (browser.equals("Edge")){
				System.setProperty("webdriver.edge.driver", prop.getProperty("msedgedriver.exe"));
				driver= new EdgeDriver();
			}
			
			logger.info(browser +"  :Browser started");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}	
	
	
}

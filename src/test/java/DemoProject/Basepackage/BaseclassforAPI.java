package DemoProject.Basepackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;



public class BaseclassforAPI
{

	public static Properties prop;	
	public Properties envprop;
	public WebDriver driver;
	public static Logger logger=LogManager.getLogger(BaseclassforAPI.class.getSimpleName());
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

	}	
	
	


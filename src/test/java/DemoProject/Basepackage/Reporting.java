package DemoProject.Basepackage;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class Reporting
{
	public static ExtentReports extent;
	public static ExtentSparkReporter Report;
	public static Logger logger=LogManager.getLogger(Reporting.class.getSimpleName());
	public static String path;
	
	//Creating Extent Reports	
	
	public static ExtentReports ExtReports()
	{
		//ExtentReports. ExtentSparkReporter
			Date d=new Date();
			String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
		 path=System.getProperty("user.dir")+"\\Reports\\"+fileName;
		
		//Responsible to create HTML report and configurations
		Report =new ExtentSparkReporter(path);
		Report.config().setReportName("Assignment for Interview");
		Report.config().setDocumentTitle("Assignment 1");
		//Responsible for creation and consolidated all test reports
		extent=new ExtentReports();
		extent.attachReporter(Report);
		extent.setSystemInfo("Tester Name", "Gaurav Vikram Singh");
		extent.setSystemInfo("Execution Environment", "Demo Server");
		extent.setSystemInfo("Testcase", "Assignments");
		extent.setSystemInfo("Build no", "1.0");
		return extent;
		
	}
}

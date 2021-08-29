package DemoProject.Basepackage;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Listeners extends Baseclass implements ITestListener
{
	ExtentReports ext=Reporting.ExtReports();	
	ExtentTest test;
	public void onTestStart(ITestResult result) 
	{
		test=ext.createTest(result.getMethod().getMethodName());
		
		}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS,"Test Passed");
		
	}

	public void onTestFailure(ITestResult result) 
	{
		test.fail(result.getThrowable());
	}

	public void onTestSkipped(ITestResult result)
	{
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		
		ext.flush();
		
	}

}

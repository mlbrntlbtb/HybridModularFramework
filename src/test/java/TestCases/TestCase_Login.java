package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import ApplicationModules.*;
import PageObjects.*;
import Utilities.*;

public class TestCase_Login 
{
	WebDriver AutoDriver;
	
  @Test (dataProvider= "testData", dataProviderClass= Utilities.DataProviderHandler.class)
  public void Test(String testCaseName, String browserName, String userName, String passWord, String firstName, String lastName, int resultRow) throws Exception 
  {
	  try 
	  {
		  LogHandler.startTest(testCaseName);
		  new BaseDriver(WebDriverHandler.InitBrowser(browserName));
		  
		  LogHandler.info("Starting register module... ");
		  Register_Action.execute(firstName, lastName, userName, passWord);
		  
		  LogHandler.info("Starting login module... ");
		  Login_Action.execute(userName, passWord);

		  WebDriverHandler.CloseBrowser();
		  LogHandler.endTest(testCaseName);
		  
		  ExcelHandler.setExcelCellDatafromColumn("Result", resultRow, "Passed");
	  }
	  catch (Exception e)
	  {
		  ExcelHandler.setExcelCellDatafromColumn("Result", resultRow, "Failed");
		  new ExceptionHandler(e.getClass().getSimpleName(), e);
	  }
  }
}

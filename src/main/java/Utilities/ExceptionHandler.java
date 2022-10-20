package Utilities;

public class ExceptionHandler 
{
	public ExceptionHandler(String exceptionName, Exception exception) throws Exception
	{
		switch(exceptionName) 
		{
		 case "NoSuchElementException":
			 LogHandler.error("[" + exceptionName + "]: Element not found.");
		 	break;
		 	
		 case "StaleElementReferenceException":
			 LogHandler.error("[" + exceptionName + "]: Target element has been stale and no longer available.");
			 break;
			 
		 case "ElementNotInteractableException":
			 LogHandler.error("[" + exceptionName + "]: Element is not interactable.");
			 break;
			 
		 case "ElementClickInterceptedException":
			 LogHandler.error("[" + exceptionName + "]: Element is cannot be clicked due to blocking of another element.");
			 break;
			 
		 case "NoSuchFrameException":
			 LogHandler.error("[" + exceptionName + "]: No frame found.");
			 break;
			 
		 case "NoAlertPresentException":
			 LogHandler.error("[" + exceptionName + "]: No present alert has been found on the page.");
			 break;
	     
		 case "NoSuchWindowException":
			 LogHandler.error("[" + exceptionName + "]: No window has been found.");
			 break;
			 
		 case "TimeoutException":
			 LogHandler.error("[" + exceptionName + "]: Locating the element has reached the maximum time limit. Element not found.");
			 break;
			 
		 case "NoSuchSessionException":
			 LogHandler.error("[" + exceptionName + "]: Web driver is not available or has been terminated.");
			 break;
		
		 case "WebDriverException":
			 LogHandler.error("[" + exceptionName + "]: Web driver is not available or has been terminated.");
			 break;
			 
		 default:
			 LogHandler.error("[" + exceptionName + "]: Unexpected error has been encountered.");
			 break;
		}
		
		LogHandler.error("See error details: [" + exception.getMessage() + "]");
		
		try 
		{
			ScreenshotHandler.takeScreenShot(WebDriverHandler.Driver());
		}
		catch(Exception ex)
		{
			LogHandler.error("No screenshot captured due to error encountered. [" + ex.getClass().getSimpleName() + "]");
			LogHandler.error("See error details: [" + ex.getMessage() + "]");
		}
		finally 
		{
			WebDriverHandler.CloseBrowser();
		}
		
		throw exception;
	}
}

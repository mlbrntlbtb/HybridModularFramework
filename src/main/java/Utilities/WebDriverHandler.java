package Utilities;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverHandler 
{
	private static WebDriver autoDriver;
	private static String BrowserName;
	
	public static WebDriver Driver() throws Exception 
	{
		if(autoDriver == null) 
		{
			throw new Exception("The WebDriver browser instance was not initialized. You should first call the method InitBrowser.");
		}
		return autoDriver;
	}
	
	public static WebDriver InitBrowser(String browserName) throws Exception
	{
		LogHandler.info("Initializing browser: [" + browserName + "]");
		
		switch(browserName.toLowerCase()) 
		{
			case "chrome":
				autoDriver = WebDriverManager.chromedriver().create();
				break;
		
			case "firefox":
				autoDriver = WebDriverManager.firefoxdriver().create();
				break;
		
			default:
				throw new Exception("Browser: [" + browserName + "] does not exist.");
		}
		BrowserName = browserName;
		autoDriver.manage().window().maximize();
		
		LogHandler.info("Navigating to URL: [" + browserName + "]");
		autoDriver.navigate().to(Constants.URL);
		return autoDriver;
	}
	
	public static void CloseBrowser() throws Exception 
	{
		LogHandler.info("Terminating browser: [" + BrowserName + "]");
		//autoDriver.close();
		autoDriver.quit();
	}
}

package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseDriver 
{
	public static WebDriver AutoDriver;
	public static WebDriverWait wait;
	
	public BaseDriver (WebDriver autoDriver)
	{
		BaseDriver.AutoDriver = autoDriver;
		BaseDriver.wait = new WebDriverWait(autoDriver, Duration.ofSeconds(10));
	}
}

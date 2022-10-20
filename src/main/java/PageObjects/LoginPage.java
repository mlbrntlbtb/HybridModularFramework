package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseDriver
{
	
	//Constructor
	public LoginPage(WebDriver autoDriver)
	{
		super(autoDriver);
	}
	
	//Page elements
	public static WebElement LargeBanner() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
	}
	
	public static WebElement SmallBanner() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h5")));
	}
	
	public static WebElement Username() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userName']")));
	}
	
	public static WebElement Password() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']")));
	}
	
	public static WebElement Login() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='login']")));
	}
	
	public static WebElement NewUser() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='newUser']")));
	}
}

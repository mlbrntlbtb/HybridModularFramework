package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BaseDriver
{
	//Constructor
	public RegisterPage(WebDriver autoDriver) 
	{
		super(autoDriver);
	}
	
	//Page elements
	public static WebElement NewUser() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='newUser']")));
	}
	
	public static WebElement FirstName() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstname']")));
	}
	
	public static WebElement LastName() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='lastname']")));
	}
	
	public static WebElement Username() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userName']")));
	}
	
	public static WebElement Password() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']")));
	}
	
	public static WebElement Captcha() 
	{
		//Not working for FF browser. Captcha requires image selection after checkbox click.
		BaseDriver.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@title,'CAPTCHA')][contains(@src,'https://www.google.com/recaptcha')]")));
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='recaptcha-checkbox-border']")));
	}
	
	public static WebElement Register() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='register']")));
	}
	
	public static WebElement BackToLogin() 
	{
		return BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='gotologin']")));
	}
	
	public static Boolean UserExistsWarning()
	{
		try 
		{
			BaseDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='name']")));
			return true;
		}
		catch(TimeoutException e)
		{
			return false;
		}
	}
}

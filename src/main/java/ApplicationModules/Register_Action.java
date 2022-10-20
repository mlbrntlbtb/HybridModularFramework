package ApplicationModules;

import org.openqa.selenium.Alert;

import PageObjects.BaseDriver;
import PageObjects.RegisterPage;
import Utilities.LogHandler;

public class Register_Action
{
	public static void execute(String firstName, String lastName, String userName, String passWord) throws Exception 
	{
		LogHandler.info("Clicking new user... ");
		RegisterPage.NewUser().click();
		
		LogHandler.info("Clearing register fields... ");
		RegisterPage.FirstName().clear();
		RegisterPage.LastName().clear();
		RegisterPage.Username().clear();
		RegisterPage.Password().clear();
		
		LogHandler.info("Setting first name: [" + firstName + "]...");
		RegisterPage.FirstName().sendKeys(firstName);
		
		LogHandler.info("Setting last name: [" + lastName + "]...");
		RegisterPage.LastName().sendKeys(lastName);
		
		LogHandler.info("Setting username: [" + userName + "]...");
		RegisterPage.Username().sendKeys(userName);
		
		LogHandler.info("Setting password: [" + passWord + "]...");
		RegisterPage.Password().sendKeys(passWord);
		
		LogHandler.info("Clicking captcha... ");
		RegisterPage.Captcha().click();
		BaseDriver.AutoDriver.switchTo().defaultContent();
		
		LogHandler.info("Clicking register... ");
		RegisterPage.Register().click();
		
		LogHandler.info("Verifying registration... ");
		if(!RegisterPage.UserExistsWarning()) 
		{
			Alert successAlert = BaseDriver.AutoDriver.switchTo().alert();
			LogHandler.info("Alert message: [" + successAlert.getText() + "]");
			successAlert.accept();
		}
		else 
		{
			LogHandler.info("User already exists. Registration failed.");
		}
		
		LogHandler.info("Navigating to login page again... ");
		RegisterPage.BackToLogin().click();
	}
}

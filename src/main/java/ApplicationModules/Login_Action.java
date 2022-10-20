package ApplicationModules;
import PageObjects.*;
import Utilities.LogHandler;

public class Login_Action
{
	public static void execute(String username, String password) throws Exception 
	{
		LogHandler.info("Clearing login fields... ");
		LoginPage.Username().clear();
		LoginPage.Password().clear();
		
		LogHandler.info("Setting username: [" + username + "]...");
		LoginPage.Username().sendKeys(username);
		
		LogHandler.info("Setting password: [" + password + "]...");
		LoginPage.Password().sendKeys(password);
		
		LogHandler.info("Clicking login... ");
		LoginPage.Login().click();
	}
}

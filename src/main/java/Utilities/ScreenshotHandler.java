package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHandler 
{
	public static void takeScreenShot(WebDriver AutoDriver) throws Exception 
	{
		try 
		{
			File screenshot = ((TakesScreenshot)AutoDriver).getScreenshotAs(OutputType.FILE);
			File path = new File(new File(Constants.screenShotPath, "EXCPTNIMG_" + (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).toString() + ".png").getPath());
			FileUtils.copyFile(screenshot, path);
			LogHandler.info("See screenshot details here: [" + path + "]");
		}
		catch(Exception ex) 
		{
			LogHandler.error("No screenshot captured due to error encountered. [" + ex.getClass().getSimpleName() + "]");
			LogHandler.error("See error details: [" + ex.getMessage() + "]");
		}	
	}
}

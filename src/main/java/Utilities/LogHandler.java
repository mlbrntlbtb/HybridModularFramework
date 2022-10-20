package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.testng.Reporter;

public class LogHandler 
{
	private static Logger log = Logger.getLogger(LogHandler.class);
	private static String dateTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()).toString();
	private static boolean initFlag = false;
	
	private static void initialize() throws Exception 
	{
		if(!initFlag) 
		{
			try 
			{
				log.setLevel(Level.TRACE);
				
				RollingFileAppender appender = new RollingFileAppender();
				appender.setAppend(true);
				appender.setMaxFileSize("10MB");
				appender.setMaxBackupIndex(1);
				appender.setFile(new File(Constants.logPath, "LOG_" + (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()).toString() + ".log").getPath());
				appender.activateOptions();
				
				ConsoleAppender console = new ConsoleAppender();
				console.setTarget("System.out");
				console.activateOptions();
				
				PatternLayout layout = new PatternLayout();
				layout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} [%p] %c{1}:%L - %m%n");
				
				appender.setLayout(layout);
				console.setLayout(layout);

				log.addAppender(appender);
				log.addAppender(console);
				
				initFlag = true;
			}
			catch(Exception e) 
			{
				new ExceptionHandler(e.getClass().getSimpleName(), e);
			}
		}
	}
	
	public static void startTest (String message) throws Exception 
	{
		initialize();
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		log.info("Test case: [" + message + "] has started... ");
		Reporter.log(dateTime + " [INFO]: Test case: [" + message + "] has started... ");
	}
	
	public static void endTest (String message) throws Exception 
	{
		initialize();
		log.info("Test case: [" + message + "] has ended.");
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		Reporter.log(dateTime + " [INFO]: Test case: [" + message + "] has ended.");
	}
	
	public static void info (String message) throws Exception 
	{
		initialize();
		log.info(message);
		Reporter.log(dateTime + " [INFO]: " + message);
	}
	
	public static void error (String message) throws Exception 
	{
		initialize();
		log.error(message);
		Reporter.log(dateTime + " [ERROR]: " + message);
	}
	
	public static void warn (String message) throws Exception 
	{
		initialize();
		log.warn(message);
		Reporter.log(dateTime + " [WARN]: " + message);
	}
	
	public static void fatal (String message) throws Exception 
	{
		initialize();
		log.fatal(message);
		Reporter.log(dateTime + " [FATAL]: " + message);
	}
	
	public static void debug (String message) throws Exception 
	{
		initialize();
		log.debug(message);
		Reporter.log(dateTime + " [DEBUG]: " + message);
	}
}

package Utilities;

import java.io.File;

import org.testng.annotations.*;

public class DataProviderHandler 
{
	@DataProvider (name="testData")
	public Object[][] DataProvider() throws Exception
	{
		ExcelHandler.setExcelFile(new File(Constants.testDataPath, Constants.fileName).getPath() , Constants.sheetName);
		return ExcelHandler.getExcelData(); 
	}
}

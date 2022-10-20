package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

public class ExcelHandler 
{
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static String currentFilePath;
	//private static String currentSheetName;

	//Method for user-defined file path and sheet name test data
	public static void setExcelFile(String filePath, String sheetName) throws Exception 
	{
		try 
		{
			File file = new File(filePath);
			FileInputStream inputStream = new FileInputStream(file);
			
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet(sheetName);
			
			currentFilePath = filePath;
			//currentSheetName = sheetName;
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public static List<String> getColumnData(int columnNumber) throws Exception 
	{
		try 
		{
			List<String> columnDataList = new ArrayList<String>();
			for(Row row: sheet) 
			{
				if(row.getRowNum() != 0) 
				{
					columnDataList.add(row.getCell(columnNumber).getStringCellValue());
				}
			}
			return columnDataList;
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
			return null;
		}
	}
	
	public static int getColNumberFromHeader(String headerName) throws Exception 
	{
		try 
		{
			Iterator<Cell> cellIterator = sheet.getRow(0).iterator();
			while(cellIterator.hasNext()) 
			{
				Cell cell = cellIterator.next();
				if(cell.toString().equals(headerName)) 
				{
					return cell.getColumnIndex();
				}
			}
			return -1;
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
			return -1;
		}
	}
	
	//Subtract '1' to row size to exclude count for column header
	//Start with '1' in for loop to start with first row and not column header row
	
	public static int getRowNumberFromCellValue(String cellValue) throws Exception 
	{
		try 
		{
			int targetRowNumber = -1;
			for(int r=1; r < getRowSize(); r++) 
			{
				for(int c=0; c < getColumnSize(); c++) 
				{
					if(sheet.getRow(r).getCell(c).getStringCellValue().equals(cellValue))
						targetRowNumber = r;
				}
			}
			
			if(targetRowNumber < 0) 
				throw new Exception("No cell found with value [" + cellValue + "]");
			
			return targetRowNumber;
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
			return -1;
		}
	}
	
	public static Object[][] getExcelData() throws Exception
	{
		try 
		{
			//Subtract '1' to row size to exclude count for column header
			//Start with '1' in for loop to start with first row and not column header row
			//Subtract '1' in object list row to avoid null rows
			
			Object[][] list = new Object[getRowSize()-1][getColumnSize()];
			for(int r=1; r < getRowSize(); r++) 
			{
				for(int c=0; c < getColumnSize(); c++) 
				{
					if(c < getColumnSize()-1) //Set all excel data as string format except last column 'Result'
						list[r-1][c] = sheet.getRow(r).getCell(c).getStringCellValue();
					else
						list[r-1][c] = r; //Set row number of Result row
				}
			}
			return list;
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
			return null;
		}
	}
	
	public static Object[][] getExcelCells() throws Exception
	{
		try 
		{
			//Subtract '1' to row size to exclude count for column header
			//Start with '1' in for loop to start with first row and not column header row
			//Subtract '1' in object list row to avoid null rows
			
			Object[][] list = new Object[getRowSize()-1][getColumnSize()];
			for(int r=1; r < getRowSize(); r++) 
			{
				for(int c=0; c < getColumnSize(); c++) 
				{
					list[r-1][c] = sheet.getRow(r).getCell(c);
				}
			}
			return list;
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
			return null;
		}
	}
	
	public static void setExcelCellDatafromColumn(String headerName, int rowNumber, String value) throws Exception 
	{
		try 
		{
			if(rowNumber == 0 || rowNumber >= getRowSize())
				throw new Exception("Invalid row number from excel data to set value.");
				
			int columnNumber = getColNumberFromHeader(headerName);
			XSSFCell cell = sheet.getRow(rowNumber).getCell(columnNumber);
			
			if(cell == null) //If cell is blank/null, create new cell from current row
				cell = sheet.getRow(rowNumber).createCell(columnNumber);
			
			cell.setCellValue(value);
			
			FileOutputStream outputStream = new FileOutputStream(currentFilePath);
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		}
		catch(Exception e) 
		{
			new ExceptionHandler(e.getClass().getSimpleName(), e);
		}
	}
	
	public static int getRowSize() 
	{
		return sheet.getPhysicalNumberOfRows(); //Actual number of rows
	}
	
	public static int getColumnSize() 
	{
		return sheet.getRow(0).getPhysicalNumberOfCells(); //Getting number of cells from first row since this is declared with column headers
	}
	
	
}

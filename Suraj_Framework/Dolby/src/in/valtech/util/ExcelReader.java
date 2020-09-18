package in.valtech.util;


import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReader 

{

	static List<Executable_DS> executable_DSDetailsList;
		
	public static Logger log = Logger.getLogger(ExcelReader.class);
	
	
	/**
	 * Method:getExecutable_DSDetails Description:This method fetches the
	 * test case details from Excel whose Execute Status is "YES"
	 * 
	 * @param fileName
	 *            :Excel File name from where data to be fetched
	 * @return :executable_DSDetailsList
	 */
	
	public static List<Executable_DS> getExecutable_DSDetails(String fileName,String sheetName) 
	{
		executable_DSDetailsList = new ArrayList<Executable_DS>();
		

		/**
		 * Create a new instance for cellDataList
		 */
		List<List<HSSFCell>> cellDataList = new ArrayList<List<HSSFCell>>();
		try {
			/**
			 * Create a new instance for FileInputStream
			 */
			FileInputStream fileInputStream = new FileInputStream(fileName);

			/**
			 * Create a new instance for POIFSFileSystem class
			 */
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

			/*
			 * Create a new instance for HSSFWorkBook
			 */
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
			HSSFSheet hssfSheet = workBook.getSheet(sheetName);
			
			/**
			 * Iterate the rows and cells of the spreadsheet * to get all the
			 * data
			 */
			Iterator<Row> rowIterator = hssfSheet.rowIterator();

			while (rowIterator.hasNext()) 
			{
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();

				Iterator<Cell> iterator = hssfRow.cellIterator();

				List<HSSFCell> cellTempList = new ArrayList<HSSFCell>();
				while (iterator.hasNext()) 
				{

					HSSFCell hssfCell = (HSSFCell) iterator.next();

					cellTempList.add(hssfCell);
					//cellTempList is a list obj that contains all columns value for particular row
				}
				cellDataList.add(cellTempList);
				//cellDataList is a list obj that contains column values for all available rows in excel 
			}



			for (int i = 0; i < cellDataList.size(); i++)
			{

				List cellTempList = (List) cellDataList.get(i);
				
				if (cellTempList.get(0).toString().equalsIgnoreCase("YES"))
				{

					Executable_DS Executable_DSDetailsObj = new Executable_DS();

					Executable_DSDetailsObj.setExecute(cellTempList.get(
						getColumnIndex(fileName,sheetName,"EXECUTE"))
						.toString());


				//Set test case module
					Executable_DSDetailsObj.setModule(cellTempList.get(
						getColumnIndex(fileName, sheetName, "MODULE"))
						.toString());


				// Set test case description value
					Executable_DSDetailsObj.setTc_descrptions(cellTempList.get(
						getColumnIndex(fileName, sheetName, "TC_DESCRIPTIONS"))
						.toString());


				// Set test case priority value
					Executable_DSDetailsObj.setPriority(cellTempList.get(
						getColumnIndex(fileName, sheetName, "PRIORITY"))
						.toString());

				//Set test case name
					Executable_DSDetailsObj.setTc_name(cellTempList.get(
						getColumnIndex(fileName, sheetName, "TC_NAME"))
						.toString());

				//Set reusable test case ID
					Executable_DSDetailsObj.setReusable_tc_id(cellTempList.get(
						getColumnIndex(fileName, sheetName, "REUSABLE_TC_ID"))
						.toString());


				// Set test case DS_CONFIG value					
					Executable_DSDetailsObj.setOs_version(cellTempList.get(
						getColumnIndex(fileName, sheetName, "OS_VERSION"))
						.toString());

				// Set test case DRIVER value
					Executable_DSDetailsObj.setDriver(cellTempList.get(
						getColumnIndex(fileName, sheetName, "DRIVER"))
						.toString());


				// Set testcase URL value
					Executable_DSDetailsObj.setUrl(cellTempList.get(
						getColumnIndex(fileName, sheetName, "URL"))
						.toString());



				// Set test case HMC_USERNAME value
					Executable_DSDetailsObj.setEmail(cellTempList.get(
						getColumnIndex(fileName, sheetName, "EMAIL"))
						.toString());

				// Set test case HMC_PASSWORD value
					Executable_DSDetailsObj.setPassword(cellTempList.get(
						getColumnIndex(fileName, sheetName, "PASSWORD"))
						.toString());


				// Add GlobalExecutorDetails object to
				// globalExecutorDetailsList
				executable_DSDetailsList.add(Executable_DSDetailsObj);

			}

		}

	} catch (Exception e) 
		{
		
		e.printStackTrace();
	}
	return executable_DSDetailsList;
}


	/**
	 * Method:getexecutable_DS sheet details
	 * Description:This method fetches the executable_DS Sheets
	 * 
	 * @param fileName
	 *            :Excel File name from where data to be fetched
	 * @return :
	 */
	
	public static List<String> getGlobalFeatureSheets(String fileName)
	{
		//System.out.println("fileName=========="+fileName);

		List<String> sheetList = new ArrayList<String>();

		try {
			/**
			 * Create a new instance for FileInputStream
			 */
			FileInputStream fileInputStream = new FileInputStream(fileName);

			/**
			 * Create a new instance for POIFSFileSystem class
			 */
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

			/*
			 * Create a new instance for HSSFWorkBook
			 */
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);

			//Get Sheet Count
			int sheetCount = workBook.getNumberOfSheets();

			//Add Feature GLOBALEXECUTER sheets to list
			for (int i = 0; i < sheetCount; i++) 
			{

				if (workBook.getSheetName(i).contains("EXECUTABLE_DS"))
				{
					//	  sheetList.add(workBook.getSheetName(i).replace("GLOBALEXECUTER_",""));
					sheetList.add(workBook.getSheetName(i));


				}
			}


			for (int i = 0; i < sheetList.size(); i++) 
			{
				//System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
				//System.out.println("Sheet name :" + sheetList.get(i));

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return sheetList;
	}


	/**
	 * Method:getColumnValue Description:This method fetches the column value
	 * for the specified version from the excel sheet.
	 * 
	 * @param fileName
	 *            :Excel File name from where data to be fetched
	 * @param sheet
	 *            : Sheet name of the excel
	 * @param dataVersion
	 *            : DataVesion for which column value to be retrieved
	 * @param colName
	 *            : Name of the column for which column value to be retrieved
	 * 
	 * @return :colValue
	 */
	
	public static String getColumnValue(String fileName, String sheet,String dataVersion, String colName) 
	{

		int columnIndex = 0;
		String expString = null;
		/**
		 * Create a new instance for cellDataList
		 */
		List<List<HSSFCell>> cellDataList = new ArrayList<List<HSSFCell>>();
		try {
			/**
			 * Create a new instance for FileInputStream
			 */
			FileInputStream fileInputStream = new FileInputStream(fileName);

			/**
			 * Create a new instance for POIFSFileSystem class
			 */
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

			/*
			 * Create a new instance for HSSFWorkBook
			 */
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
			HSSFSheet hssfSheet = workBook.getSheet(sheet);
			/**
			 * Iterate the rows and cells of the spreadsheet * to get all the
			 * datas
			 */
			Iterator<Row> rowIterator = hssfSheet.rowIterator();

			while (rowIterator.hasNext()) 
			{
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();

				Iterator<Cell> iterator = hssfRow.cellIterator();

				List<HSSFCell> cellTempList = new ArrayList<HSSFCell>();
				while (iterator.hasNext()) 
				{

					HSSFCell hssfCell = (HSSFCell) iterator.next();

					if (hssfRow.getRowNum() == 0) 
					{

						if (hssfCell.getStringCellValue().equalsIgnoreCase(colName)) 
						{
							columnIndex = hssfCell.getColumnIndex();
						}
					}

					cellTempList.add(hssfCell);
				}
				cellDataList.add(cellTempList);
			}
			for (int i = 0; i < cellDataList.size(); i++)
			{

				List cellTempList = (List) cellDataList.get(i);


				for (int j = 0; j < cellTempList.size(); j++) 
				{

					if (cellTempList.get(j).toString().equalsIgnoreCase(dataVersion)) 
					{
						expString = cellTempList.get(columnIndex).toString();

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expString;
	}


	/**
	 * Method:getCheckout_DS Description:This method fetches user
	 * test data CHECKOUT_DS details for specified version from the excel sheet.
	 * 
	 * @param fileName
	 *            :Excel File name from where data to be fetched
	 * @param sheet
	 *            : Sheet name of the excel
	 * @param dataVersion
	 *            : DataVesion for which column value to be retrieved
	 * 
	 * @return :ShippingDetails object
	 * 
	 */

	

	
	/**
	 * Method: getColumnIndex
	 * Description:This method return the specific column cell value from Excel sheet.
	 * 
	 * 
	 * @param fileName : Excel file absolute path
	 * @param sheet  : Name of the target sheet
	 * @param colName : Name of the target column
	 * @return  : return the cell value of the specific column.
	 */
	
	public static Integer getColumnIndex(String fileName, String sheet,String colName) 
	{
		int columnIndex = 0;
		try {
			
			FileInputStream fileInputStream = new FileInputStream(fileName);
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
			HSSFSheet hssfSheet = workBook.getSheet(sheet);
			Iterator<Row> rowIterator = hssfSheet.rowIterator();
			while (rowIterator.hasNext()) 
			{
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();
            	Iterator<Cell> iterator = hssfRow.cellIterator();
				while (iterator.hasNext()) 
				{
					HSSFCell hssfCell = (HSSFCell) iterator.next();
					if (hssfRow.getRowNum() == 0)
					{
						if (hssfCell.getStringCellValue().equalsIgnoreCase(colName)) 
						{
							columnIndex = hssfCell.getColumnIndex();
						}
					}
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return columnIndex;
	}

	/**
	 * Method:getColumnValue Description:This method fetches the column value
	 * for the specified version from the excel sheet.
	 * 
	 * @param fileName
	 *            :Excel File name from where data to be fetched
	 * @param sheet
	 *            : Sheet name of the excel
	 * @param dataVersion
	 *            : DataVesion for which column value to be retrieved
	 * @param colName1
	 *            : Name of the column for which column value to be
	 *            'dataVersion'
	 * @param colName2
	 *            : Name of the column for which column value to be retrieved
	 * 
	 * @return :colValue
	 */
	
	public static String getColumnValueDep(String fileName, String sheet,
			String dataVersion, String colName1, String colName2)
	{
		int columnIndex = 0;
		String expString = null;
		/**
		 * Create a new instance for cellDataList
		 */
		List<List<HSSFCell>> cellDataList = new ArrayList<List<HSSFCell>>();
		try {
			/**
			 * Create a new instance for FileInputStream
			 */
			FileInputStream fileInputStream = new FileInputStream(fileName);

			/**
			 * Create a new instance for POIFSFileSystem class
			 */
			POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

			/*
			 * Create a new instance for HSSFWorkBook
			 */
			HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
			HSSFSheet hssfSheet = workBook.getSheet(sheet);
			/**
			 * Iterate the rows and cells of the spreadsheet * to get all the
			 * data
			 */
			Iterator<Row> rowIterator = hssfSheet.rowIterator();

			while (rowIterator.hasNext()) 
			{
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();

				Iterator<Cell> iterator = hssfRow.cellIterator();

				List<HSSFCell> cellTempList = new ArrayList<HSSFCell>();
				while (iterator.hasNext())
				{

					HSSFCell hssfCell = (HSSFCell) iterator.next();

					if (hssfRow.getRowNum() == 0) 
					{
						if (hssfCell.getStringCellValue().equalsIgnoreCase(
								colName1)) {
							columnIndex = hssfCell.getColumnIndex();
						}
					}

					cellTempList.add(hssfCell);
				}
				cellDataList.add(cellTempList);
			}

			for (int i = 0; i < cellDataList.size(); i++)
			{

				List cellTempList = (List) cellDataList.get(i);
				for (int j = 0; j < cellTempList.size(); j++) 
				{
					if (cellTempList.get(columnIndex).toString()
							.equalsIgnoreCase(dataVersion)) 
					{
						expString = cellTempList.get(
								getColumnIndex(fileName, sheet, colName2))
								.toString();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return expString;
	}

	
	/**
	 * Method:getProd_DS Description:This method fetches user
	 * test data Prod_DS details for specified version from the excel sheet.
	 * 
	 * @param fileName
	 *            :Excel File name from where data to be fetched
	 * @param sheet
	 *            : Sheet name of the excel
	 * @param dataVersion
	 *            : DataVesion for which column value to be retrieved
	 * 
	 * @return :ShippingDetails object
	 * 
	 */
	
	
			

	// Added for testing purpose
		public static void main(String args[]) throws Exception, SQLException,
		Exception {

			getGlobalFeatureSheets("D:\\Dolby_helix\\Dolby\\resources\\Testdata\\Automation_DS.xls.xls");
			
		} 


	}



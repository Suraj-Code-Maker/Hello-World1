package in.valtech.util;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import in.valtech.custom.CustomFunction;


public class GenerateTestNGXML {

	public static String rootDir = CustomFunction.getRootDir();
	
	static XmlTest test = null;  
	static XmlSuite suite = null; 

	/**
	 * Method:generateTestNGXMLfile Description: This method generates testNg.xml by
	 * reading the data mentioned in "Automation_DS"Excel
	 * 
	 * @throws Exception
	 */
	
	public static void generateXMLfile() throws Exception 
	{
		System.out.println("rootDir======================="+rootDir);
		List<String> featureNameList = ExcelReader.getGlobalFeatureSheets("D:\\Dolby_helix\\Dolby\\resources\\Testdata\\AUTOMATION_DS.xls");

		for (int i = 0; i < featureNameList.size(); i++) 
		{

			generateModuleXMLFile(featureNameList.get(i).toLowerCase());

		}

	}
	
	/**
	 * Method:generateModuleTestNGXMLFile Description: This method generates
	 * testNg.xml by reading the data mentioned in "Automation_DS"Excel module
	 * wise
	 * 
	 * @throws Exception
	 */
	
	
	public static void generateModuleXMLFile(String module) throws Exception {
		try {
			
			FileWriter writer=null;
			// Fetching TC details from Excel
			
			List<Executable_DS> executable_DSDetailsList = ExcelReader
					.getExecutable_DSDetails(
							"D:\\Dolby_helix\\Dolby\\resources\\Testdata\\AUTOMATION_DS.xls",
							module.toUpperCase());
			System.out.println("List Size : "+ executable_DSDetailsList.size());
			System.out.println("Feature name: "+ module);
			
			if (executable_DSDetailsList.size() != 0) 
			{
				// Create an instance of XmlSuite and assign a name for it.
				suite = new XmlSuite();

				suite.setName("Dolby Automation  :"+ module.toUpperCase());
			
				suite.setGroupByInstances(true);

				// For adding listners to suite
			List<String> listnerClasses = new ArrayList<String>();
			
		
				//suite.setListeners(listnerClasses);
 
				for (int i = 0; i < executable_DSDetailsList.size(); i++) 
				{

//				
						// Create a instance of XmlTest and assign a name for
						// it.
						test = new XmlTest(suite);

						test.setName(executable_DSDetailsList.get(i)
								.getModule()
								+ "_"
								+ executable_DSDetailsList.get(i)
								.getTc_descrptions()
								+ "_"
								+ executable_DSDetailsList.get(i)
								.getTc_name());

						// This Map can hold your testng Parameters.
						Map<String, String> testngParams = new HashMap<String, String>();

						// Assigning the TC_DESCRIPTIONS.
						testngParams.put("TC_DESCRIPTIONS",
								executable_DSDetailsList.get(i)
										.getTc_descrptions());

						// Assigning the TC_NAME.
						testngParams.put("TC_NAME", executable_DSDetailsList
								.get(i).getTc_name());
						
						// Assigning the REUSABLE_TC ID.
						testngParams.put("REUSABLE_TC_ID", executable_DSDetailsList
								.get(i).getReusable_tc_id());
						
						// Assigning the OS_VERSION.
						testngParams.put("OS_VERSION", executable_DSDetailsList
								.get(i).getOs_version());

						// Assigning the DRIVER.
						testngParams.put("DRIVER", executable_DSDetailsList
								.get(i).getDriver());

						// Assigning the Home Page URL.
						testngParams.put("URL", executable_DSDetailsList
								.get(i).getUrl());

						
						// Assigning the USERNAME
						testngParams.put("EMAIL",
								executable_DSDetailsList.get(i)
										.getEmail());
						
						// Assigning the PASSWORD
						testngParams.put("PASSWORD",
								executable_DSDetailsList.get(i)
										.getPassword());
					

						// Add any parameters that you want to set to the Test.
						test.setParameters(testngParams);

						
						XmlClass testClass = null;
						// Create an array list of XmlClass
						ArrayList<XmlClass> classes = new ArrayList<XmlClass>();

						testClass = new XmlClass();

					// ==================== Dependent Classes ============
					String depClasses = ExcelReader.getColumnValueDep(
							"D:\\Dolby_helix\\Dolby\\resources\\Testdata\\AUTOMATION_DS.xls",
							"EXECUTABLE_DS",
							executable_DSDetailsList.get(i)
							.getTc_descrptions(), "TC_DESCRIPTIONS",
							"REUSABLE_TC_ID");
					System.out.println("Dependency = "+depClasses);
					if (!depClasses.equalsIgnoreCase("NA"))
					{
						String[] array = depClasses.split(",");
						for (int j = 0; j < array.length; j++) {
							testClass = new XmlClass();
							System.out.println("Array[j] = "+array[j]);
							testClass
							.setName("in.valtech.test."
									+ ExcelReader
									.getColumnValueDep(
											"D:\\Dolby_helix\\Dolby\\resources\\Testdata\\AUTOMATION_DS.xls",
											module.toUpperCase(),
											array[j],
											"TC_DESCRIPTIONS",
											"TC_NAME").toUpperCase());
							
							
							classes.add(testClass);
							testClass = new XmlClass();
							System.out.println("******** " + "in.valtech.test."
									+ ExcelReader
									.getColumnValueDep(
											"D:\\Dolby_helix\\Dolby\\resources\\Testdata\\AUTOMATION_DS.xls",
											module.toUpperCase(),
											array[j],
											"TC_DESCRIPTIONS",
											"TC_NAME").toUpperCase());
						}
					}


						// =====================================

						testClass.setName("in.valtech.test"
								+ "."
								+ executable_DSDetailsList.get(i)
										.getTc_name());

						classes.add(testClass);
						test.setXmlClasses(classes);

						// Creating TestNG.xml file

						File file = new File(System.getProperty("user.dir")
								+ "/testSuites/TestSuite_" + module
								+ ".xml");

						 writer = new FileWriter(file);
						writer.write(suite.toXml());
						                  
					//}
				}
				writer.close();
				executable_DSDetailsList.clear();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// For testing purpose
	public static void main(String args[]) throws Exception {
	
		generateXMLfile();
	}
}
	

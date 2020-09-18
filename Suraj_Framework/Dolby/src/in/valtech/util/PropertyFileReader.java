package in.valtech.util;


import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileReader {

		public static Properties obj;		
		
		/**
		 * Load Property File.
		 * 
		 **/
		public static String getValue(String key)
		{
			String path="D:\\Dolby_helix\\Dolby\\resources\\Testdata\\ObjectRepository.properties";
			try
			{
				obj=new Properties();
				obj.load(new FileInputStream(path));
				path=obj.getProperty(key);
			}
			catch(Exception e)
			{
				
			}
			return path;
		}
						
	
	}
		





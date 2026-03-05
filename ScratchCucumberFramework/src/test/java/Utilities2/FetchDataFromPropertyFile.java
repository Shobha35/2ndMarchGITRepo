package Utilities2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import ConstantData.ConstantsData;

public class FetchDataFromPropertyFile {
	
	public static Properties getDataFromProperty() throws IOException
	{
		FileReader reader = new FileReader(ConstantsData.PROP_FILE_PATH);
		Properties prop = new Properties();
		prop.load(reader);
		return prop;
	}

}

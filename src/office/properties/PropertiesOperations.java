package office.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class for working with app configuration file
 * 
 * @author b.potapskiy
 * 
 */
public class PropertiesOperations {

	public final static String CONFIG_FILE_NAME = "config.properties";

	public final static String LOCAL_DB_URL = "LOCAL_DB_URL";
	public final static String LOCAL_DB_USER = "LOCAL_DB_USER";
	public final static String LOCAL_DB_PASSWORD = "LOCAL_DB_PASSWORD";
	public final static String LOCAL_DB_DRIVER_NAME = "LOCAL_DB_DRIVER_NAME";
	public final static String SALT = "SALT";
	
	public final static String HIVE_DB_URL = "HIVE_DB_URL";
	/**
	 * set properties and save them to file
	 * 
	 * @return
	 */
	public static boolean setProperties() {
		Properties prop = new Properties();

		File f = new File(CONFIG_FILE_NAME);
		if (!f.exists()) {
			try {
				// set the properties value
				prop.setProperty(LOCAL_DB_DRIVER_NAME, "org.mariadb.jdbc.Driver");
				prop.setProperty(LOCAL_DB_URL, "jdbc:mysql://localhost:3306/post_office?useUnicode=true&characterEncoding=utf8");
				prop.setProperty(LOCAL_DB_USER, "root");
				prop.setProperty(LOCAL_DB_PASSWORD, "root");
				prop.setProperty(SALT,"mdrt2xLJn2942");
				
				
				prop.store(new FileOutputStream(CONFIG_FILE_NAME), null);

			} catch (IOException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public static String getProperty(String propertyName) {
		Properties prop = new Properties();

		try {
			// load a properties file
			prop.load(new FileInputStream(CONFIG_FILE_NAME));
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		// get the property value and print it out

		return prop.getProperty(propertyName);

	}

}

package office.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import office.properties.PropertiesOperations;

/**
 * Class provides a database connection
 * 
 * @author s.bulavin
 * 
 */
public class DBConnector {
	private static Connection connection = null;

	static {
		new DBConnector();
	}

	/**
	 * Constructor that create connection
	 */
	private DBConnector() {

		try {

			String url = PropertiesOperations
					.getProperty(PropertiesOperations.LOCAL_DB_URL);
			String user = PropertiesOperations
					.getProperty(PropertiesOperations.LOCAL_DB_USER);
			String password = PropertiesOperations
					.getProperty(PropertiesOperations.LOCAL_DB_PASSWORD);
			String driver_name = PropertiesOperations
					.getProperty(PropertiesOperations.LOCAL_DB_DRIVER_NAME);

			Class.forName(driver_name);

			// System.out.println(url + "  " + user + " "+password);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
//			log.error("ClassNotFoundException in LocalDBConnector");
			e.printStackTrace();
		} catch (SQLException e) {
//			log.error("SQLException in LocalDBConnector");
			e.printStackTrace();
		} catch (Exception e) {
//			log.error(e.toString() + " in LocalDBConnector");
			e.printStackTrace();
		}

	}
	
	
	public static Connection getConnection() {
		return connection;

	}

}
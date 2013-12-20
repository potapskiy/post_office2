package office.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.apache.log4j.Logger;


public class ConfigDAO {

	static Connection conn = null;

	/**
	 * Constructor that create table
	 */
	public ConfigDAO() {
		conn = DBConnector.getConnection();
	}

	/**
	 * Creating table CONFIG
	 */
	public void createTable() {
		Logger log = Logger.getLogger("userlogger");		
		Statement st;
		try {
			st = DBConnector.getConnection().createStatement();
			// st.execute("DROP TABLE " + DBParams.TABLE3);
			st.execute("CREATE TABLE IF NOT EXISTS " + DBParams.TABLE6
					+ " (ID int NOT NULL AUTO_INCREMENT, PARAM varchar(15), VALUE varchar(100))");
			log.info("Table CONFIG is created!");

		} catch (SQLException e) {
			log.error("Table CONFIG is not created!!!");
			e.printStackTrace();
		}

	}

	/**
	 * Convert DT tag value to date format yyyy-MM-dd hh:mm:ss
	 * 
	 * @param ts
	 * @return strDate
	 */

	public String convertTStoDateFormat(String ts) {
		String strDate = ts.subSequence(0, 4) + "-" + ts.substring(4, 6) + "-"
				+ ts.substring(6, 8) + " " + ts.substring(8, 10) + ":"
				+ ts.substring(10, 12) + ":" + ts.substring(12) + ".0";

		return strDate;

	}

	/**
	 * Insert name of parameter and its value in table
	 * 
	 * @param param
	 *            - name of parameter
	 * @param value
	 *            - value of parameter
	 * 
	 * @param date
	 *            - date of changing of value
	 */
	public void insertDataInTable(String param, String value) {
		// String conv_date = convertTStoDateFormat(date+"00");

		try {
			String query = "INSERT INTO " + DBParams.TABLE6
					+ " (PARAM, VALUE) VALUES (?,?)";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, param);
			pst.setString(2, value);
			pst.executeUpdate();

			//log.info("Insert data in table " + DBParams.TABLE3 + ":  OK");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Update value of parameter with the specified param's name
	 * 
	 * @param param
	 *            - name of parameter
	 * @param value
	 *            - value of parameter
	 * @param date
	 *            - date of changing of value
	 */
	public void updateDataInTable(String param, String value) {

		// String conv_date = convertTStoDateFormat(date+"00");
		try {
			String query = "UPDATE " + DBParams.TABLE6
					+ " SET value = ?, date = ? WHERE param = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, value);
			java.util.Date today = new java.util.Date();
			pst.setTimestamp(2, new java.sql.Timestamp(today.getTime()));
			pst.setString(3, param);
			pst.executeUpdate();

			//log.info("Update data in table " + DBParams.TABLE3 + ":  OK");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Print all data from table CONFIG
	 */
	public void printAllDataFromTable() {

		Statement stmt = null;
		ResultSet resultSet;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery("SELECT * FROM " + DBParams.TABLE6);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("PARAM") + "   "
						+ resultSet.getString("VALUE") + "   "
						+ resultSet.getString("DATE"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	

	/**
	 * Check is parameter with specified name exists or not
	 * 
	 * @param parname
	 *            parameter name
	 * @return true or false
	 */
	public boolean isParamExists(String parname) {

		ResultSet resultSet;
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT value FROM "
					+ DBParams.TABLE6
					+ " WHERE param = ? AND value IS NOT NULL");
			pst.setString(1, parname);
			resultSet = pst.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;

	}

	/**
	 * Removing parameter by name
	 * 
	 * @param parname
	 *            parameter name
	 */
	public void removeParamByName(String parname) {
		String sql = "DELETE FROM " + DBParams.TABLE6 + " WHERE param = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, parname);
			ps.execute();
			//log.info("Deleting parameter by name finished successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getting parameter by name
	 * 
	 * @param parname
	 *            parameter name
	 */
	public String getParamByName(String parname) {
		ResultSet resultSet = null;
		String value = "";
		String sql = "SELECT value FROM " + DBParams.TABLE6 + " WHERE param = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, parname);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				value = resultSet.getString("value");
			}
			//log.info("Deleting parameter by name finished successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
}

package office.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentsDAO {
	
	static Connection conn = null;

	/**
	 * Constructor that create connection
	 */
	public DepartmentsDAO() {
		conn = DBConnector.getConnection();
	}

	protected static void createTable() {
		Statement st;
		try {
			st = DBConnector.getConnection().createStatement();
			st.execute("CREATE TABLE IF NOT EXISTS "
					+ DBParams.TABLE3
					+ " (departmentId varchar(50) PRIMARY KEY, address varchar(100))");
	
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void printAllDataFromTable() {
		Statement stmt = null;
		ResultSet resultSet;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery("SELECT * FROM " + DBParams.TABLE3);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("departmentId") + "  "
						+ resultSet.getString("address"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public void updateAddress(String id, String address) {
		PreparedStatement stat;
		try {
			stat = conn.prepareStatement("UPDATE "  
					+ DBParams.TABLE3
					+ " SET address = ? WHERE departmentId = ?");
			stat.setString(1, address);
			stat.setString(2, id);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
								
	}
	
	
	public boolean isIdInTable(String id) {

		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("SELECT * FROM " + DBParams.TABLE3
					+ " WHERE departmentId = ?");
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;

	}
	
	public void insertDepartment(String id, String address) {
		
		if (isIdInTable(id) ) {
			updateAddress(id,address);
			return;
		}
		
		ResultSet rs = null;
		try {
			String query = "INSERT INTO " + DBParams.TABLE3
					+ " (departmentId, address) VALUES (?,?)";
			PreparedStatement pst = conn.prepareStatement(query);
			
			pst.setString(1, id);
			pst.setString(2, address);
			pst.executeUpdate();

			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

	}

	public String getAddress(String id) {
		ResultSet rs;
		String name = "";

		try {
			String query = "SELECT NAME FROM " + DBParams.TABLE3
					+ " WHERE departmentId = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				name = rs.getString("NAME");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;

	}
	
	
	public int getCode(String address) {
		ResultSet rs;
		int code = 0;

		try {
			String query = "SELECT departmentId FROM " + DBParams.TABLE3
					+ " WHERE address = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, address);
			rs = pst.executeQuery();
			while (rs.next()) {
				code = rs.getInt("departmentId");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;

	}
	
	
	public void deleteDepartment(String id) {
		boolean rs;
		try {
			String query = "DELETE FROM " + DBParams.TABLE3
					+ " WHERE departmentId = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, id);
			rs = pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	
	

}

package office.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class TownsDAO {
	static Connection conn = null;

	/**
	 * Constructor that create connection
	 */
	public TownsDAO() {
		conn = DBConnector.getConnection();
	}

	
	public void printAllDataFromTable() {
		Statement stmt = null;
		ResultSet resultSet;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery("SELECT * FROM " + DBParams.TABLE4);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("townId") + "  "
						+ resultSet.getString("NAME"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public void updateName(int id, String name) {
		PreparedStatement stat;
		try {
			stat = conn.prepareStatement("UPDATE "  
					+ DBParams.TABLE4 
					+ " SET NAME = ? WHERE townId = ?");
			stat.setString(1, name);
			stat.setInt(2, id);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
								
	}
	
	
	public boolean isIdInTable(int id) {

		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("SELECT * FROM " + DBParams.TABLE4
					+ " WHERE townId = ?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;

	}
	
	public void insertTown(int id, String name) {
		
		if (isIdInTable(id) ) {
			updateName(id,name);
			return;
		}
		
		ResultSet rs = null;
		try {
			String query = "INSERT INTO " + DBParams.TABLE4
					+ " (townId, NAME) VALUES (?,?)";
			PreparedStatement pst = conn.prepareStatement(query);
			
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.executeUpdate();

			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

	}

	public String getName(int id) {
		ResultSet rs;
		String name = "";

		try {
			String query = "SELECT NAME FROM " + DBParams.TABLE4
					+ " WHERE townId = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				name = rs.getString("NAME");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;

	}
	
	
	public int getCode(String name) {
		ResultSet rs;
		int code = 0;

		try {
			String query = "SELECT townId FROM " + DBParams.TABLE4
					+ " WHERE name = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				code = rs.getInt("townId");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;

	}
	
	
	public void deleteTown(int id) {
		boolean rs;
		try {
			String query = "DELETE FROM " + DBParams.TABLE4
					+ " WHERE townId = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	

}

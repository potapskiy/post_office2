package office.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
					+ " (departmentId int PRIMARY KEY AUTO_INCREMENT, address varchar(200), id_town int, number int)");
	
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
				System.out.println(resultSet.getInt("departmentId") + "  "
						+ resultSet.getString("address"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public void updateAddress(int id, String address) {
		PreparedStatement stat;
		try {
			stat = conn.prepareStatement("UPDATE "  
					+ DBParams.TABLE3
					+ " SET address = ? WHERE departmentId = ?");
			stat.setString(1, address);
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
			pst = conn.prepareStatement("SELECT * FROM " + DBParams.TABLE3
					+ " WHERE departmentId = ?");
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
	
	public void insertDepartment(String address, int town_id) {
		
		
		
		ResultSet rs = null;
		
		int curDepNumb = getMaxDepartmentNumber(town_id);
		try {
			String query = "INSERT INTO " + DBParams.TABLE3
					+ " (address, id_town, number) VALUES (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(query);
			
			pst.setString(1, address);
			pst.setInt(2, town_id);
			pst.setInt(3, curDepNumb + 1);
			pst.executeUpdate();

			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

	}

	public String getAddress(int id) {
		ResultSet rs;
		String name = "";

		try {
			String query = "SELECT NAME FROM " + DBParams.TABLE3
					+ " WHERE departmentId = ?";
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
	
	
	public int getIdByTownAndNum(int townID, int num) {
		ResultSet rs;
		int id = 0;

		try {
			String query = "SELECT departmentId FROM " + DBParams.TABLE3
					+ " WHERE id_town = ? AND number = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, townID);
			pst.setInt(2, num);
			rs = pst.executeQuery();
			while (rs.next()) {
				id = rs.getInt("departmentId");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;

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
	
	
	public void deleteDepartment(int id) {
		boolean rs;
		try {
			String query = "DELETE FROM " + DBParams.TABLE3
					+ " WHERE departmentId = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public int getMaxDepartmentNumber(int town_id) {
		ResultSet rs;
		int code = 0;

		try {
			String query = "SELECT MAX(number) as tn FROM " + DBParams.TABLE3
					+ " WHERE id_town = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, town_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				code = rs.getInt("tn");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;

	}
	
	
	
	public List<String> getTownList(String startName){
		List<String> tl = new ArrayList<String>();
		ResultSet rs;
		String name;
		startName = startName.toLowerCase();
		
		
		try {
			String query = "SELECT t.name FROM towns t INNER JOIN dapartments d ON (t.townId = d.id_town) " + 
					"WHERE LOWER(SUBSTRING(t.name, 1, ?)) = ? group by d.id_town";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, startName.length());
			pst.setString(2, startName);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
				System.out.println(name);
				tl.add(name);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return tl;
		
		
	}
	
	
	public List<String> getDepartmInfo(String townName){
		List<String> tl = new ArrayList<String>();
		ResultSet rs;
		String record;
		
		townName = townName.toLowerCase();
		
		int townId = new TownsDAO().getCode(townName);
		
		
		try {
			String query = "SELECT * FROM " + DBParams.TABLE3 +
					" WHERE id_town = ? group by number";
			
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, townId);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				record = rs.getString("number")+"|"+rs.getString("address");
				tl.add(record);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return tl;
		
		
	}
	
	
	

}

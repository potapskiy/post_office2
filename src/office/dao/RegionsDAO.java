package office.dao;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RegionsDAO {

	static Connection conn = null;

	/**
	 * Constructor that create connection
	 */
	public RegionsDAO() {
		conn = DBConnector.getConnection();
	}

	

	public void printAllDataFromTable() {
		Statement stmt = null;
		ResultSet resultSet;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery("SELECT * FROM " + DBParams.TABLE5);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("ID") + "  "
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
					+ DBParams.TABLE5 
					+ " SET NAME = ? WHERE ID = ?");
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
			pst = conn.prepareStatement("SELECT * FROM " + DBParams.TABLE5
					+ " WHERE ID = ?");
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
	
	public void insertRegion(int id, String name) {
		
		if (isIdInTable(id) ) {
			updateName(id,name);
			return;
		}
		
		ResultSet rs = null;
		try {
			String query = "INSERT INTO " + DBParams.TABLE5
					+ " (ID, NAME) VALUES (?,?)";
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
			String query = "SELECT NAME FROM " + DBParams.TABLE5
					+ " WHERE id = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				name = rs.getString("NAME");
			}
			
			try {
				name = new String(name.getBytes("cp1251"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			String query = "SELECT ID FROM " + DBParams.TABLE5
					+ " WHERE name = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				code = rs.getInt("ID");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;

	}
	
	
	public void deleteRegion(int id) {
		boolean rs;
		try {
			String query = "DELETE FROM " + DBParams.TABLE5
					+ " WHERE id = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	public void clearTable() {
		String sql = "TRUNCATE TABLE " + DBParams.TABLE5;
		try {
			Statement st = conn.createStatement();
			st.execute(sql);
			//log.info("Cleaning of table PRODUCTLIST finished successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

	
	public static void main(String[] args) {
		RegionsDAO r  = new RegionsDAO();
		
		String name = "�.�ȯ�";
		
		try {
			name = new String(name.getBytes("utf-8"), "cp1251");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(r.getCode(name));
/*		try {
			String s = FileOperations.readEntireFile("regions.txt", true);
			String[] lines = s.split("\n");
			System.out.println(lines.length);
			
			for(int i = 0; i < lines.length; i++){
				String[] line = lines[i].split(" ");
				//System.out.println(line[0]+"___"+line[1]);
				//System.out.println(line.length);
				if (line.length == 3){
					r.insertRegion(Integer.parseInt(line[0]), line[1]+" "+line[2]);
				} else{
					r.insertRegion(Integer.parseInt(line[0]), line[1]);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/	
	//	r.printAllDataFromTable();
	}
	
}

package office.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import office.entities.Parcel;
import office.entities.User;

public class UsersDAO {

	Connection conn = null;

	public UsersDAO() {
		conn = DBConnector.getConnection();
	}

	public boolean isUserRegistered(String telephoneNumber) {

		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("SELECT * FROM " + DBParams.TABLE1
					+ " WHERE telephone = ?");
			pst.setString(1, telephoneNumber);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateUser(User user)  {

		try {
			PreparedStatement stat = conn
					.prepareStatement("UPDATE "
							+ DBParams.TABLE1
							+ " SET telephone = ?, password = ?, firstName = ?, secondName = ?, address = ?, kind =? WHERE userId = ?");
			stat.setString(1, user.getTelephone());
			stat.setString(2, user.getPassword());
			stat.setString(3, user.getFirstName());
			stat.setString(4, user.getSecondName());
			stat.setString(5, user.getAddress());
			stat.setInt(6, user.getKind());
			stat.setInt(7, user.getId());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public void insertUser(User user) {

		if (isUserRegistered(user.getTelephone())) {
			updateUser(user);
			return;
		}

		try {
			PreparedStatement stat = conn
					.prepareStatement("INSERT INTO "
							+ DBParams.TABLE1
							+ " (telephone, password, firstName, secondName, address, kind) VALUES (?,?,?,?,?,?)");
			stat.setString(1, user.getTelephone());
			stat.setString(2, user.getPassword());
			stat.setString(3, user.getFirstName());
			stat.setString(4, user.getSecondName());
			stat.setString(5, user.getAddress());
			stat.setInt(6, user.getKind());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
	
	
	
	
	
	public void updateUserWP(User user)  {

		try {
			PreparedStatement stat = conn
					.prepareStatement("UPDATE "
							+ DBParams.TABLE1
							+ " SET telephone = ?, firstName = ?, secondName = ?, address = ?, kind =? WHERE userId = ?");
			stat.setString(1, user.getTelephone());
			stat.setString(2, user.getFirstName());
			stat.setString(3, user.getSecondName());
			stat.setString(4, user.getAddress());
			stat.setInt(5, user.getKind());
			stat.setInt(6, user.getId());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public void insertUserWP(User user) {

		if (isUserRegistered(user.getTelephone())) {
			updateUserWP(user);
			return;
		}

		try {
			PreparedStatement stat = conn
					.prepareStatement("INSERT INTO "
							+ DBParams.TABLE1
							+ " (telephone, firstName, secondName, address, kind) VALUES (?,?,?,?,?,?)");
			stat.setString(1, user.getTelephone());
			stat.setString(2, user.getFirstName());
			stat.setString(3, user.getSecondName());
			stat.setString(4, user.getAddress());
			stat.setInt(5, user.getKind());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public void removeUserById(int id) {
		String sql = "DELETE FROM " + DBParams.TABLE1 + " WHERE userId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void removeUserByRelephone(String telephone) {
		String sql = "DELETE FROM " + DBParams.TABLE1 + " WHERE telephone = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, telephone);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public User getUserById(int id) {
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		User user = new User();

		String query = "SELECT * FROM " + DBParams.TABLE1 + " WHERE userId = ? ";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				user.setId(resultSet.getInt("userId"));
				user.setTelephone(resultSet.getString("telephone"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setSecondName(resultSet.getString("secondName"));
				user.setAddress(resultSet.getString("address"));
				user.setKind(resultSet.getInt("kind"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User getUserByTelephone(String tel) {
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		User user = new User();

		String query = "SELECT * FROM " + DBParams.TABLE1
				+ " WHERE telephone = ? ";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, tel);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				user.setId(resultSet.getInt("userId"));
				user.setTelephone(resultSet.getString("telephone"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setSecondName(resultSet.getString("secondName"));
				user.setAddress(resultSet.getString("address"));
				user.setKind(resultSet.getInt("kind"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean isUserTelAndPassCorrect(String tel, String pas) {
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		boolean res = false;

		String query = "SELECT * FROM " + DBParams.TABLE1
				+ " WHERE telephone = ? AND password = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, tel);
			ps.setString(2, pas);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				res = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	

}

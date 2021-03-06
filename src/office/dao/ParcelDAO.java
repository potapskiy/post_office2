package office.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import office.entities.Parcel;
import office.entities.User;

public class ParcelDAO {

	Connection conn = null;

	public ParcelDAO() {
		conn = DBConnector.getConnection();
	}

	public boolean isParselInTable(String id) {

		PreparedStatement pst;
		try {
			pst = conn.prepareStatement("SELECT * FROM " + DBParams.TABLE2
					+ " WHERE parcelId = ?");
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
	
	
	
	public void setParcelStatus(int status, String id){
		try {
			PreparedStatement stat = conn
					.prepareStatement("UPDATE "
							+ DBParams.TABLE2
							+ " SET "
							+ "status = ? WHERE parcelId = ?");

			stat.setInt(1, status);
			stat.setString(2, id);

			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		
		
		
		
	}
	
	
	public void updateParcel(Parcel parcel) throws SQLException {

		try {
			PreparedStatement stat = conn
					.prepareStatement("UPDATE "
							+ DBParams.TABLE2
							+ " SET "
							+ "userId = ?,  departmentIdFrom = ?,  departmentIdTo = ?,  clientToTel = ?,  "
							+ "clientToName = ?,  clientToSurname = ?,  dateFrom = ?,  dateTo = ?,  type = ?,  homeAddress = ?,  status = ?,  "
							+ "price = ?,  weight = ?" + "WHERE parcelId = ?");

			stat.setString(1, parcel.getUserId());
			stat.setInt(2, parcel.getDepartmentIdFrom());
			stat.setInt(3, parcel.getDepartmentIdTo());
			stat.setString(4, parcel.getClientToTel());
			stat.setString(5, parcel.getClientToName());
			stat.setString(6, parcel.getClientToSurname());
			stat.setString(7, parcel.getDateFrom());
			stat.setString(8, parcel.getDateTo());
			stat.setInt(9, parcel.getType());
			stat.setString(10, parcel.getHomeAddress());
			stat.setInt(11, parcel.getStatus());
			stat.setFloat(12, parcel.getPrice());
			stat.setFloat(13, parcel.getWeight());
			stat.setString(14, parcel.getId());

			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public void insertParsel(Parcel parcel) throws SQLException {

		if (isParselInTable(parcel.getId())) {
			updateParcel(parcel);
			return;
		}

		try {
			PreparedStatement stat = conn
					.prepareStatement("INSERT INTO "
							+ DBParams.TABLE2
							+ " (parcelId,  userId,  departmentIdFrom,  departmentIdTo,  clientToTel,  "
							+ "clientToName,  clientToSurname,  dateFrom,  dateTo,  type,  homeAddress,  status,  "
							+ "price,  weight) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stat.setString(1, parcel.getId());
			stat.setString(2, parcel.getUserId());
			stat.setInt(3, parcel.getDepartmentIdFrom());
			stat.setInt(4, parcel.getDepartmentIdTo());
			stat.setString(5, parcel.getClientToTel());
			stat.setString(6, parcel.getClientToName());
			stat.setString(7, parcel.getClientToSurname());
			stat.setString(8, parcel.getDateFrom());
			stat.setString(9, parcel.getDateTo());
			stat.setInt(10, parcel.getType());
			stat.setString(11, parcel.getHomeAddress());
			stat.setInt(12, parcel.getStatus());
			stat.setFloat(13, parcel.getPrice());
			stat.setFloat(14, parcel.getWeight());

			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public void removeParcel(String id) {
		String sql = "DELETE FROM " + DBParams.TABLE2 + " WHERE parcelId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void removeParcelByUserId(String id) {
		String sql = "DELETE FROM " + DBParams.TABLE2 + " WHERE userlId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Parcel getParcel(String id) {
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		Parcel parcel = new Parcel();

		String query = "SELECT * FROM " + DBParams.TABLE2
				+ " WHERE parcelId = ? ";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				parcel.setId(resultSet.getString("parcelId"));
				parcel.setUserId(resultSet.getString("userId"));
				parcel.setDepartmentIdFrom(resultSet.getInt("departmentIdFrom"));
				parcel.setDepartmentIdTo(resultSet.getInt("departmentIdTo"));
				parcel.setClientToTel(resultSet.getString("clientToTel"));
				parcel.setClientToName(resultSet.getString("clientToName"));
				parcel.setClientToSurname(resultSet
						.getString("clientToSurname"));
				parcel.setDateFrom(resultSet.getString("dateFrom"));
				parcel.setDateTo(resultSet.getString("dateTo"));
				parcel.setType(resultSet.getInt("type"));
				parcel.setHomeAddress(resultSet.getString("homeAddress"));
				parcel.setStatus(resultSet.getInt("status"));
				parcel.setPrice(resultSet.getFloat("price"));
				parcel.setWeight(resultSet.getFloat("weight"));
				
				return parcel;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		parcel.setStatus(-1);
		return parcel;
	}

	public void updateParcelStatus(String id, int status) throws SQLException {

		try {
			PreparedStatement stat = conn.prepareStatement("UPDATE "
					+ DBParams.TABLE2 + " SET "
					+ "status = ? WHERE parcelId = ?");

			stat.setInt(1, status);
			stat.setString(2, id);

			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public ArrayList<Parcel> getUserParcelsSend(String userId) {
		PreparedStatement ps = null;
		ResultSet resultSet = null;

		ArrayList<Parcel> parcelList = new ArrayList<Parcel>();

		String query = "SELECT * FROM " + DBParams.TABLE2
				+ " WHERE userId = ? ";

		UsersDAO uDao = new UsersDAO();

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, userId);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Parcel parcel = new Parcel();
				parcel.setId(resultSet.getString("parcelId"));
				parcel.setUserId(resultSet.getString("userId"));
				parcel.setDepartmentIdFrom(resultSet.getInt("departmentIdFrom"));
				parcel.setDepartmentIdTo(resultSet.getInt("departmentIdTo"));

				String tel = resultSet.getString("clientToTel");
				User us = uDao.getUserByTelephone(tel);

				parcel.setClientToTel("0" + tel);

				parcel.setClientToName(us.getFirstName());
				parcel.setClientToSurname(us.getSecondName());

				String df = (resultSet.getString("dateFrom") != null) ? resultSet
						.getString("dateFrom") : "";
				String dt = (resultSet.getString("dateTo") != null) ? resultSet
								.getString("dateTo") : "";
				parcel.setDateFrom(df);
				parcel.setDateTo(dt);
				parcel.setType(resultSet.getInt("type"));
				parcel.setHomeAddress(resultSet.getString("homeAddress"));
				int st = resultSet.getInt("status");
				parcel.setStatus(resultSet.getInt("status"));
				parcel.setStatusStr(getStatus(st));
				parcel.setStatus(resultSet.getInt("status"));
				parcel.setPrice(resultSet.getFloat("price"));
				parcel.setWeight(resultSet.getFloat("weight"));

				parcelList.add(parcel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parcelList;
	}
	
	
	
	public ArrayList<Parcel> getParcelsToHome() {
		PreparedStatement ps = null;
		ResultSet resultSet = null;

		ArrayList<Parcel> parcelList = new ArrayList<Parcel>();

		String query = "SELECT * FROM " + DBParams.TABLE2
				+ " WHERE type = ? AND status = ?";

		
		try {
			ps = conn.prepareStatement(query);
			UsersDAO uDao = new UsersDAO();
			
			ps.setInt(1, Parcel.TYPE_HOME);
			ps.setInt(2, Parcel.STATUS_IN_END);
			
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Parcel parcel = new Parcel();
				parcel.setId(resultSet.getString("parcelId"));
				parcel.setUserId(resultSet.getString("userId"));
				parcel.setDepartmentIdFrom(resultSet.getInt("departmentIdFrom"));
				parcel.setDepartmentIdTo(resultSet.getInt("departmentIdTo"));

				String tel = resultSet.getString("clientToTel");
				User us = uDao.getUserByTelephone(tel);

				parcel.setClientToTel("0" + tel);

				parcel.setClientToName(us.getFirstName());
				parcel.setClientToSurname(us.getSecondName());

				String df = (resultSet.getString("dateFrom") != null) ? resultSet
						.getString("dateFrom") : "";
				String dt = (resultSet.getString("dateTo") != null) ? resultSet
								.getString("dateTo") : "";
				parcel.setDateFrom(df);
				parcel.setDateTo(dt);
				parcel.setType(resultSet.getInt("type"));
				parcel.setHomeAddress(resultSet.getString("homeAddress"));
				int st = resultSet.getInt("status");
				parcel.setStatus(resultSet.getInt("status"));
				parcel.setStatusStr(getStatus(st));
				parcel.setStatus(resultSet.getInt("status"));
				parcel.setPrice(resultSet.getFloat("price"));
				parcel.setWeight(resultSet.getFloat("weight"));

				parcelList.add(parcel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parcelList;
	}

	public ArrayList<Parcel> getUserParcelsGet(String tel) {
		PreparedStatement ps = null;
		ResultSet resultSet = null;

		ArrayList<Parcel> parcelList = new ArrayList<Parcel>();
		UsersDAO uDao = new UsersDAO();

		String query = "SELECT * FROM " + DBParams.TABLE2
				+ " WHERE clientToTel = ? ";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, tel);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Parcel parcel = new Parcel();
				parcel.setId(resultSet.getString("parcelId"));
				// /////////

				String telp = resultSet.getString("userId");
				User us = uDao.getUserByTelephone(telp);

				parcel.setClientToTel("0" + telp);

				parcel.setClientToName(us.getFirstName());
				parcel.setClientToSurname(us.getSecondName());
				// ////////////

				parcel.setUserId(resultSet.getString("userId"));
				parcel.setDepartmentIdFrom(resultSet.getInt("departmentIdFrom"));
				parcel.setDepartmentIdTo(resultSet.getInt("departmentIdTo"));
				// parcel.setClientToTel(resultSet.getString("clientToTel"));
				// parcel.setClientToName(resultSet.getString("clientToName"));
				// parcel.setClientToSurname(resultSet
				// .getString("clientToSurname"));
				
				String df = (resultSet.getString("dateFrom") != null) ? resultSet
						.getString("dateFrom") : "";
				String dt = (resultSet.getString("dateTo") != null) ? resultSet
								.getString("dateTo") : "";
				parcel.setDateFrom(df);
				parcel.setDateTo(dt);
				
				parcel.setType(resultSet.getInt("type"));
				parcel.setHomeAddress(resultSet.getString("homeAddress"));
				int st = resultSet.getInt("status");
				parcel.setStatus(resultSet.getInt("status"));
				parcel.setStatusStr(getStatus(st));
				parcel.setPrice(resultSet.getFloat("price"));
				parcel.setWeight(resultSet.getFloat("weight"));

				parcelList.add(parcel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parcelList;
	}
	
	
	private String getStatus(int code){

		
		String c = "";
		
		switch (code) {
		case Parcel.STATUS_AT_START:
			c = "В пункті відправлення";
			break;
		case Parcel.STATUS_IN_WAY:
			c = "В дорозыі";
			break;
		case Parcel.STATUS_IN_END:
			c = "В пункті призначення";
			break;
		case Parcel.STATUS_DONE:
			c = "Посилку отримано";
			break;

		default:
			c = "Інформація відсутня";
			break;
		}
		
		return c;
		
		
	}
}

package office.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Parcel {

	public static final int PARCEL_LOADED = 0;
	public static final int PARCEL_SENT = 1;
	public static final int PARCEL_NOT_LOADED = 2;
	public static final int PARCEL_NOT_SENT = 3;
	
	public static final int TYPE_DEPARTMENT = 0;
	public static final int TYPE_HOME = 1;

	public static final int STATUS_AT_START = 1;
	public static final int STATUS_IN_WAY = 2;
	public static final int STATUS_IN_END = 3;
	public static final int STATUS_DONE = 4;

	private String id;
	private String userId;
	private int departmentIdFrom;
	private int departmentIdTo;
	private String clientToTel;
	private String clientToName;
	private String clientToSurname;
	private String dateFrom;
	private String dateTo;
	private int type;
	private String homeAddress;
	private int status;
	private float price;
	private float weight;
	private String statusStr;

	public Parcel(String userId, int departmentIdFrom,
			int departmentIdTo, String clientToTel, String clientToName,
			String clientToSurname, String dateFrom, String dateTo, int type,
			String homeAddress, int status, float price, float weight) {
		
		this.userId = userId;
		this.departmentIdFrom = departmentIdFrom;
		this.departmentIdTo = departmentIdTo;
		this.clientToTel = clientToTel;
		this.clientToName = clientToName;
		this.clientToSurname = clientToSurname;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.type = type;
		this.homeAddress = homeAddress;
		this.status = status;
		this.price = price;
		this.weight = weight;
		
		
		
		
		
	}
	
	
	public Parcel() {
	}
	
	public String getStatusStr() {
		return statusStr;
	}
	
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	
	
	public Parcel(String tel_from, int id_fr, int id_to, String tel_to,
			int type2, String addr, double price2, float weight2) {
		
		this.id = generateID(id_fr);
		this.userId = tel_from;
		this.departmentIdFrom = id_fr;
		this.departmentIdTo = id_to;
		this.clientToTel = tel_to;
		this.type = type2;
		this.homeAddress = addr;
		this.status = STATUS_AT_START;
		this.price = (float) price2;
		this.weight = weight2;
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		this.dateFrom = sdf.format(cal.getTime());
	}


	private String generateID(int id_fr){
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(cal.getTime())+String.valueOf(id_fr);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getDepartmentIdFrom() {
		return departmentIdFrom;
	}

	public void setDepartmentIdFrom(int departmentIdFrom) {
		this.departmentIdFrom = departmentIdFrom;
	}

	public int getDepartmentIdTo() {
		return departmentIdTo;
	}

	public void setDepartmentIdTo(int departmentIdTo) {
		this.departmentIdTo = departmentIdTo;
	}

	public String getClientToTel() {
		return clientToTel;
	}

	public void setClientToTel(String clientToTel) {
		this.clientToTel = clientToTel;
	}

	public String getClientToName() {
		return clientToName;
	}

	public void setClientToName(String clientToName) {
		this.clientToName = clientToName;
	}

	public String getClientToSurname() {
		return clientToSurname;
	}

	public void setClientToSurname(String clientToSurname) {
		this.clientToSurname = clientToSurname;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}

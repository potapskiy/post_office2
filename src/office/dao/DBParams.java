package office.dao;


public interface DBParams {
	
	String TABLE1 = "USERS"; 	
	String TABLE2 = "PARCELS";	
	String TABLE3 = "DAPARTMENTS";
	String TABLE4 = "TOWNS";
	String TABLE5 = "REGIONS";
	String TABLE6 = "CONFIG";
	
	String LOCAL_DB_DRIVER_NAME= "org.mariadb.jdbc.Driver";
	String LOCAL_DB_URL= "jdbc:mysql://localhost:3306/post_office?useUnicode=true&characterEncoding=utf8";
	String LOCAL_DB_USER= "root";
	String LOCAL_DB_PASSWORD= "root";
	String SALT="mdrt2xLJn2942";
	
}

package office.entities;

public class User {

	public static final int ADMIN = 0;
	public static final int USER = 1;
	public static final int MANAGER = 2;
	public static final int LOADER = 3;
	public static final int COURIER = 4;
	public static final int DIRECTOR = 5;

	private int id;
	private String telephone;
	private String password;
	private String firstName;
	private String secondName;
	private String address;
	private int kind;

	public User(String telephone, String password, String firstName,
			String secondName, String address, int kind) {
		this.telephone = telephone;
		this.password = password;
		this.firstName = firstName;
		this.secondName = secondName;
		this.address = address;
		this.kind = kind;
	}

	public User(String telephone, String password, String firstName,
			String secondName, String address) {
		this.telephone = telephone;
		this.password = password;
		this.firstName = firstName;
		this.secondName = secondName;
		this.address = address;
		this.kind = USER;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

}

package office;


import java.util.List;
import java.util.Properties;


import office.crypto.SHAHashing;
import office.properties.PropertiesOperations;

public class Main {

	public static void main(String[] args) {

		//PropertiesOperations.setProperties();

		String login = "1177700367";
		System.out.println(login.matches("(\\d){9}"));
		
		//System.out.println(SHAHashing.getHash("123"));
	}

	
}

package office;


import java.util.List;
import java.util.Properties;


import office.crypto.SHAHashing;
import office.properties.PropertiesOperations;

public class Main {

	public static void main(String[] args) {

		//PropertiesOperations.setProperties();

		
		System.out.println(SHAHashing.getHash("123"));
	}

	
}

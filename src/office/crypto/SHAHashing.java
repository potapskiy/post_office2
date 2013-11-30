package office.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import office.dao.DBParams;
import office.properties.PropertiesOperations;

public class SHAHashing {

	private static String SHA1Hashing(String stringToHash) {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");

			md.update(stringToHash.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getHash(String stringToHash) {

		String salt = DBParams.SALT;

		return SHA1Hashing(SHA1Hashing(stringToHash).concat(salt));

	}

}

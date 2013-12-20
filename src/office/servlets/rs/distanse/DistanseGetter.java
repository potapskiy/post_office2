package office.servlets.rs.distanse;

import java.net.URLEncoder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class DistanseGetter {

	public float getDistanse(String cityFrom, String cityTo) {

		String responseBody = "";
		try {

			String url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="
					+ cityFrom + "&destinations=" + cityTo
							+ "&mode=drivinr&language=ua-UA&sensor=false";
			
			
			//System.out.println(url);
			
			Client client = Client.create();
			WebResource webResource = client.resource(url);

			ClientResponse response = webResource.type(
					"application/xml;charset=utf-8").get(ClientResponse.class);

			responseBody = response.getEntity(String.class);

			//System.out.println(responseBody);

			float d = new DistanseParser().parseDistanse(responseBody);
			
			return d;
		} catch (ClientHandlerException e) {
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public static void main(String[] args) {

		DistanseGetter t = new DistanseGetter();
		System.out.println(t.getDistanse("Луганськ", "Черкаси"));

	}

}
package office.servlets.rs.distanse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DistanseParser {
	
	public float parseDistanse(String inputJSON){
		
		float distansef = 0;
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(inputJSON);
			JSONObject root = (JSONObject) obj;
			
			JSONArray rows = (JSONArray) root.get("rows");
			String elements = rows.toJSONString();
			
			Object obj2 = rows.get(0);
			JSONObject dist = (JSONObject) obj2;
			
			JSONArray el = (JSONArray) dist.get("elements");
			JSONObject obj3 = (JSONObject)el.get(0);
			
			JSONObject distance = (JSONObject) obj3.get("distance");
			
			String meters = distance.get("value").toString();
			
			distansef = Float.parseFloat(meters) / 1000;
			
			
		
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return distansef;
	}

}

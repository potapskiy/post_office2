package office.servlets.web;

import office.dao.ConfigDAO;
import office.servlets.rs.distanse.DistanseGetter;


public class PriseCalcServ {

	public double getPrice(String from, String to, String type, double weight){
		
		float distance = new DistanseGetter().getDistanse(from, to);
		
		if ((distance < 0) || (weight <= 0)){
			return -1;
		}

		ConfigDAO c = new ConfigDAO();
		float price_per_kg = Float.parseFloat(c
				.getParamByName("PRICE_BY_KG"));
		float price_per_km = Float.parseFloat(c
				.getParamByName("PRICE_BY_KM"));
		float price_toHome = Float.parseFloat(c.getParamByName("TO_HOME"));

		boolean toHome = type.equals("Склад - Двері");
		price_toHome = (toHome) ? price_toHome : 0;

		double price = (weight * price_per_kg) + (distance * price_per_km)
				+ price_toHome;
		
		price =  Math.round(price*100.0)/100.0;
		
		
		return price;
	}
	
}

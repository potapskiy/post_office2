package office.servlets.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import office.dao.ConfigDAO;
import office.dao.DepartmentsDAO;
import office.dao.ParcelDAO;
import office.dao.TownsDAO;
import office.dao.UsersDAO;
import office.entities.Parcel;
import office.entities.User;
import office.servlets.rs.distanse.DistanseGetter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/save_parcel/*")
public class SaveParcel extends HttpServlet {
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		try {
			
			System.out.println("fdfsdfsdfsdfs");
			
			String town_from = request.getParameter("from_t");
			String town_to = request.getParameter("to_t");
			String dep_from = request.getParameter("dep_fr");
			String dep_to = request.getParameter("dep_to");
			String weightS = request.getParameter("weight");
			System.out.println(weightS);
			float weight = Float.parseFloat(weightS);
			String type = request.getParameter("type");
			String tel_from = request.getParameter("tel_fr");
			String tel_to = request.getParameter("tel_to");
			
			String name_from = request.getParameter("name_fr");
			String name_to = request.getParameter("name_to");
			String sname_from = request.getParameter("sname_fr");
			String sname_to = request.getParameter("sname_to");
			String addr = request.getParameter("address");
			String priceS = request.getParameter("price");
			
			
			
			System.out.println(town_from);
			System.out.println(town_to );
			System.out.println(dep_from);
			System.out.println(dep_to);
			System.out.println(weightS);
			System.out.println(weight);
			System.out.println(type );
			System.out.println(tel_from );
			System.out.println( tel_to );
			System.out.println( name_from );
			System.out.println( name_to );
			System.out.println( sname_from);
			System.out.println( sname_to );
			System.out.println( addr);
			System.out.println( priceS );
			
			JSONObject json = new JSONObject();
			
			if ((!tel_from.matches("(\\d){9}")) || (!tel_to.matches("(\\d){9}"))){
				json.put( "status", "ERROR");
			    String requestBody = json.toJSONString();
			    System.out.println(requestBody);
				response.getWriter().write(new Gson().toJson(requestBody));
				return;
	        }
			
			
			double price = new PriseCalcServ().getPrice(town_from, town_to, type, weight);
			if((price > 0) && (weight > 0)){
				saveUser(tel_from, name_from, sname_from);
				saveUser(tel_to, name_to, sname_to);
				
				DepartmentsDAO ddao = new DepartmentsDAO();
				TownsDAO tDao = new TownsDAO();
				ParcelDAO pdao = new ParcelDAO();
				
				int depNumbFrom = Integer.parseInt(dep_from.substring(0,dep_from.indexOf("|")));
				int depNumbTo = Integer.parseInt(dep_to.substring(0,dep_to.indexOf("|")));
				int town_frID = tDao.getCode(town_from);
				int town_toID = tDao.getCode(town_to);
				
				int id_fr = ddao.getIdByTownAndNum(town_frID, depNumbFrom);
				int id_to = ddao.getIdByTownAndNum(town_toID, depNumbTo);
				
				int itype = type.equals("Склад - Двері") ? Parcel.TYPE_HOME : Parcel.TYPE_DEPARTMENT;
				Parcel p = new Parcel(tel_from, id_fr, id_to, tel_to, itype, addr, price, weight);
				
				pdao.insertParsel(p);
				
				json.put( "status", "OK");
			} else{
				
				json.put( "status", "ERROR");
			}
			
		
			
			
			
	        String requestBody = json.toJSONString();
	        System.out.println(requestBody);
			response.getWriter().write(new Gson().toJson(requestBody));
			
			return;
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject json = new JSONObject();
			json.put( "status", "ERROR");
		    String requestBody = json.toJSONString();
		    System.out.println(requestBody);
			response.getWriter().write(new Gson().toJson(requestBody));
		}
	}
	
	
	private void saveUser(String tel, String name, String sname ){
		
		UsersDAO uDao = new UsersDAO();
		
		if (!uDao.isUserRegistered(tel)){
			uDao.insertUser(new User(tel, "", name, sname, ""));
		}
		
	}
}

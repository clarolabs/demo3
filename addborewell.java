package com.fetching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mysql.jdbc.PreparedStatement;


@WebServlet("/addborewell")
public class addborewell extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
try{
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		
			String line="";
		
		    if(reader!=null)
		    {
		    line=reader.readLine();
		    }
		    System.out.println(line);
	    JSONParser parser = new JSONParser();
	    JSONObject obj2 = (JSONObject) parser.parse(line);
	    Class.forName("com.mysql.jdbc.Driver");
		Connection  con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/frpdatabase","root","");
		  long b_id=(long)obj2.get("Borewell_id");
	      long f_id =(long)obj2.get("Farmer_id");
	      double lat=(double)obj2.get("Latitude");
	      double long1=(double)obj2.get("Longitude");
	      String url=(String)obj2.get("Image_url");
	    
	    ZoneId zoneid=ZoneId.of("Asia/Kolkata");
		LocalDateTime ldt=LocalDateTime.now(zoneid);
	    //ZonedDateTime zdt=ZonedDateTime.of(ldt, zoneid);  
		 System.out.println("Time Stamp is "+ldt);
		 String format_date = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		 System.out.println("Formatted date is "+format_date);
		 
		 String qry="Insert into borewell_reg(b_id,f_id,latitude,longitude,img_url,time_stamp) Values(?,?,?,?,?,?)";
		    PreparedStatement ps=(PreparedStatement) con1.prepareStatement(qry);
		    
		    ps.setLong(1, b_id);
		    ps.setLong(2,f_id );
		    ps.setDouble(3,lat);
		    ps.setDouble(4, long1);
		    ps.setString(5, url);
		    ps.setString(6, format_date);
		    
		    ps.executeUpdate();
		    
		ps.close();
		 
		 
		 System.out.println("hhh");
		 
		 
		 
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}

		
		
		
	



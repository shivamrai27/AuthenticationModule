package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entitiy.*; 
public class UserDAO {
	private Connection con;

	public UserDAO(Connection con) {
		super();
		this.con = con;
	}
	public boolean userRegister(User us) {
		
		boolean f=false;
		
		try {
			String query ="insert into login(name,email,password) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, us.getName());
			ps.setString(2, us.getEmail());
			ps.setString(3, us.getPassword());
			
			ps.execute();
			f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public User getLogin(String email, String pass) {
		
		User us=null;
		
		try {
			String query2 = "select * from login where email=? and password=?";
			PreparedStatement ps = con.prepareStatement(query2);
			
			ps.setString(1, email);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				us= new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return us;
	}
}
;

package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDAO;
import com.Db.DBConnect;
import com.entitiy.User;
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		User us = new User();
		us.setName(name);
		us.setEmail(email);
		us.setPassword(password);
		
		
		UserDAO dao = new UserDAO(DBConnect.getConnection());
		
		boolean f= dao.userRegister(us);
		if(f) {
		HttpSession session = req.getSession();
		session.setAttribute("suc", "Registration Successful ");
		resp.sendRedirect("register.jsp");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("fail", "Something went Wrong on Server");
			resp.sendRedirect("register.jsp");
			
		}
		
	}

}
